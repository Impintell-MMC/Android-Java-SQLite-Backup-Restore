

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This class provides functionality to back up a database file,
 * keep a limited number of backups, and check backup availability.
 */
public class Backup {

    // Maximum number of backup files to retain
    private static final int MAX_BACKUPS = 10;

    /**
     * Creates a timestamped backup of the given database file.
     * 
     * @param dbPath      Path to the database file
     * @param backupPath  Directory to store the backup files
     * @return            true if backup was successful, false otherwise
     */
    public boolean createBackup(String dbPath, String backupPath) {
        File dbFile = new File(dbPath);
        File backupDir = new File(backupPath);

        // Check if the database file exists
        if (!dbFile.exists()) {
            return false;
        }

        // Create backup directory if it doesn't exist
        if (!backupDir.exists() && !backupDir.mkdirs()) {
            return false;
        }

        // Generate timestamp for backup file name
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));

        String backupFileName = "backup_" + timestamp + ".db";
        File backupFile = new File(backupDir, backupFileName);

        // Copy the content from the original DB file to the backup file
        try (FileInputStream fis = new FileInputStream(dbFile);
             FileOutputStream fos = new FileOutputStream(backupFile)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            // Remove old backups beyond the maximum allowed
            deleteOldBackups(backupDir);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes oldest backup files if the total exceeds MAX_BACKUPS.
     * 
     * @param backupDir Directory containing backup files
     */
    private void deleteOldBackups(File backupDir) {
        File[] backupFiles = backupDir.listFiles();

        if (backupFiles != null && backupFiles.length > MAX_BACKUPS) {
            // Sort files by last modified date (oldest first)
            Arrays.sort(backupFiles, Comparator.comparingLong(File::lastModified));

            // Delete oldest files until only MAX_BACKUPS remain
            for (int i = 0; i < backupFiles.length - MAX_BACKUPS; i++) {
                backupFiles[i].delete();
            }
        }
    }

    /**
     * Checks whether there is at least one backup file available.
     * 
     * @param backupPath Directory to check for backup files
     * @return           true if a backup is found, false otherwise
     */
    public boolean isBackupAvailable(String backupPath) {
        File backupDir = new File(backupPath);

        if (!backupDir.exists() || !backupDir.isDirectory()) {
            return false;
        }

        File[] backupFiles = backupDir.listFiles((dir, name) -> 
                name.startsWith("backup_") && name.endsWith(".db"));

        return backupFiles != null && backupFiles.length > 0;
    }
}
