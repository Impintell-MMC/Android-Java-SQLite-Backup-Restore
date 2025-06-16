# 💾 IntelliBoxCounter Backup Utility

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

```java
Backup backup = new Backup();
boolean result = backup.createBackup("path/to/dbfile.db", "path/to/backup/folder");
