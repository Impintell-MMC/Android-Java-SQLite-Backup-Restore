<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IntelliBoxCounter Backup Utility</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 40px auto;
            max-width: 900px;
            background-color: #f9f9f9;
            color: #333;
            line-height: 1.6;
        }
        h1, h2, h3 {
            color: #0a558c;
        }
        code {
            background: #eee;
            padding: 2px 5px;
            border-radius: 4px;
            font-family: Consolas, monospace;
        }
        pre {
            background: #f3f3f3;
            padding: 10px;
            border-left: 5px solid #0a558c;
            overflow-x: auto;
        }
        .section {
            margin-bottom: 40px;
        }
        ul {
            margin-left: 20px;
        }
        .note {
            background: #fff9db;
            padding: 10px;
            border-left: 5px solid #ffcb00;
            margin: 10px 0;
        }
    </style>
</head>
<body>

    <h1>💾 IntelliBoxCounter Database Backup Utility</h1>

    <div class="section">
        <p>
            This Java class provides a simple yet robust backup solution for SQLite or similar local databases. 
            It creates timestamped backup files, retains a fixed number of backups, and automatically deletes older ones.
        </p>
    </div>

    <div class="section">
        <h2>🔧 Features</h2>
        <ul>
            <li>Automatic timestamped backup creation</li>
            <li>Keeps up to 10 recent backups</li>
            <li>Checks for availability of backup files</li>
            <li>Easy integration in Java applications</li>
        </ul>
    </div>

    <div class="section">
        <h2>🚀 Usage</h2>
        <h3>Create a Backup</h3>
        <pre><code>
Backup backup = new Backup();
boolean result = backup.createBackup("path/to/dbfile.db", "path/to/backup/folder");
        </code></pre>
        <p>Returns <code>true</code> if backup is successful, <code>false</code> otherwise.</p>

        <h3>Check for Backup Availability</h3>
        <pre><code>
boolean available = backup.isBackupAvailable("path/to/backup/folder");
        </code></pre>
    </div>

    <div class="section">
        <h2>🗃️ Backup File Naming</h2>
        <p>Backup files are named with a timestamp using the following format:</p>
        <pre><code>backup_dd_MM_yyyy_HH_mm_ss.db</code></pre>
        <p>Example: <code>backup_16_06_2025_14_32_51.db</code></p>
    </div>

    <div class="section">
        <h2>🧹 Automatic Cleanup</h2>
        <p>
            When the number of backups exceeds 10, the oldest ones are deleted automatically to save space.
        </p>
    </div>

    <div class="section">
        <h2>📁 Suggested Folder Structure</h2>
        <pre><code>
/project-root
  └── /backups
        ├── backup_15_06_2025_18_30_22.db
        ├── backup_15_06_2025_19_45_10.db
        └── ...
        </code></pre>
    </div>

    <div class="section">
        <h2>⚠️ Notes</h2>
        <div class="note">
            <ul>
                <li>Ensure the database is not locked during backup</li>
                <li>Tested with file-based databases like SQLite</li>
                <li>Consider transactional backup if supported by the DB</li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>📜 License</h2>
        <p>MIT License — © 2025 Impintell</p>
    </div>

</body>
</html>
