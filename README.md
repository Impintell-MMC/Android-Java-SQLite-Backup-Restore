# 💾 Android / Java / SQLite Backup Utility

This Java class provides a simple yet robust backup solution for file-based databases such as SQLite.  
It creates timestamped backup files, retains a fixed number of backups, and automatically deletes older ones.

---

## 🔧 Features

- ✅ Automatic timestamped backup creation  
- ✅ Keeps only the latest **10** backups  
- ✅ Checks for availability of backup files  
- ✅ Easy integration into any Java project  

---

## 🚀 Usage
```java
### Create a Backup
Backup backup = new Backup();
boolean result = backup.createBackup("path/to/dbfile.db", "path/to/backup/folder");
```
Returns true if the backup was successful, otherwise false.

Check for Backup Availability
```java
boolean available = backup.isBackupAvailable("path/to/backup/folder");
```
Returns true if at least one backup exists.

---

# 🔁 Android / Java / SQLite Restore Utility

This Java class provides functionality to restore a database file from the most recent backup available either on the device's internal storage (eMMC) or an external SD card.

---

## 🔧 Features

- ✅ Automatically selects the most recent backup file
- ✅ Supports restoring from two separate directories (eMMC and SD card)
- ✅ Simple byte stream copy for fast and effective restoration
- ✅ Minimal dependencies and easy integration

---

## 🚀 Usage

### Restore from Latest Backup

```java
Restore restore = new Restore();
boolean result = restore.restoreBackup(
    "data/database.db",
    "backups/emmc",
    "backups/sdcard"
);
```
Returns true if restore is successful, otherwise false.

🛠️ Method Description
restoreBackup(String dbPath, String sMMCBackupPath, String sdCardBackupPath)
Parameter	Description
dbPath	Path to the database file that will be restored (overwritten)
sMMCBackupPath	Path to the internal (eMMC) backup folder
sdCardBackupPath	Path to the SD card backup folder

The method automatically chooses the most recent backup between the two paths.

📁 Folder Structure Example
/project-root
  ├── /backups
  │     ├── /emmc
  │     │     └── backup_15_06_2025_20_00_00.db
  │     └── /sdcard
  │           └── backup_15_06_2025_21_30_00.db
  └── /data
        └── database.db


⚠️ Notes
Ensure that no other process is using the database while restoring.
Only the most recent .db file is used during restoration.
Supports file-based databases such as SQLite.

📜 License
MIT License — © 2025 Impintell
