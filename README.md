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

### Create a Backup


Backup backup = new Backup();
boolean result = backup.createBackup("path/to/dbfile.db", "path/to/backup/folder");

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
