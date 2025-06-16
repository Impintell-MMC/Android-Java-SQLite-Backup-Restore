import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Provides functionality to restore the database file
 * from the most recent backup found in either the eMMC or SD card backup directory.
 */
public class Restore {

    /**
     * Restores the database file using the most recent backup
     * available in either the eMMC or SD card backup directories.
     *
     * @param dbPath            Path to the database file to be overwritten
     * @param sMMCBackupPath    Path to the eMMC backup folder
     * @param sdCardBackupPath  Path to the SD card backup folder
     * @return true if restore is successful, false otherwise
     */
    public boolean restoreBackup(String dbPath, String sMMCBackupPath, String sdCardBackupPath) {
        File sMMCBackupDir = new File(sMMCBackupPath);
        File sdCardBackupDir = new File(sdCardBackupPath);

        // Get the newest backup file from both directories
        File sMMCNewestBackup = getNewestBackup(sMMCBackupDir);
        File sdCardNewestBackup = getNewestBackup(sdCardBackupDir);

        // Compare the two and select the most recent one
        File newestBackup = getMostRecentBackup(sMMCNewestBackup, sdCardNewestBackup);

        if (newestBackup == null) {
            return false; // No valid backup found in either location
        }

        // Copy the backup file content into the database file path
        try (FileInputStream fis = new FileInputStream(newestBackup);
             FileOutputStream fos = new FileOutputStream(dbPath)) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            return true; // Restore successful

        } catch (IOException e) {
            e.printStackTrace();
            return false; // Restore failed
        }
    }

    /**
     * Returns the most recently modified file in the given directory.
     *
     * @param backupDir Directory containing backup files
     * @return Most recent file or null if none found
     */
    private File getNewestBackup(File backupDir) {
        if (backupDir == null || !backupDir.exists() || !backupDir.isDirectory()) {
            return null;
        }

        File[] backupFiles = backupDir.listFiles();

        if (backupFiles == null || backupFiles.length == 0) {
            return null;
        }

        return Arrays.stream(backupFiles)
                .filter(File::isFile)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);
    }

    /**
     * Compares two backup files and returns the newer one.
     *
     * @param backup1 First backup file
     * @param backup2 Second backup file
     * @return Most recently modified file between the two
     */
    private File getMostRecentBackup(File backup1, File backup2) {
        if (backup1 == null) return backup2;
        if (backup2 == null) return backup1;

        return backup1.lastModified() > backup2.lastModified() ? backup1 : backup2;
    }
}
