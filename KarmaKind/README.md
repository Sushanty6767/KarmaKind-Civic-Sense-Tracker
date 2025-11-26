# KarmaKind — Civic Sense Tracker

A Java Swing GUI application to track civic tasks and habits and award karma points.

This project was generated from the user's uploaded PPT: KarmaKind – Civic Sense Tracker. See the original presentation for design and features: fileciteturn0file0

## What is included
- Simple Java Swing GUI (src/com/karmakind/ui)
- Core OOP classes (Task, HabitTask, BehaviourTask)
- TaskManager interface and implementation (in-memory with optional JDBC hooks)
- DAO skeleton for MySQL via JDBC (TaskDAO, DatabaseManager)
- SQL schema (schema.sql)
- Build & run instructions (README)
- .gitignore

> **Note:** The JDBC code is a working skeleton — you must provide your own MySQL connection details in `src/com/karmakind/db/DatabaseManager.java` before using the database features. The app will run in in-memory mode without a DB.

## How to build & run (local / quick)
Requirements:
- Java 11+ (JDK)
- Maven (optional) — this repo uses plain `javac` for simplicity.

From repo root:
```bash
# compile
javac -d out $(find src -name "*.java")

# run
java -cp out com.karmakind.Main
```

To use the MySQL database:
1. Create a database and run `schema.sql`.
2. Update JDBC URL/user/password in `DatabaseManager.java`.
3. Recompile and run. The DAO will attempt DB operations; if DB not reachable the app falls back to in-memory.

## Suggested next steps
- Add persistence tests.
- Expand GUI for tag filtering, charts, and streak visuals.
- Add a Maven `pom.xml` and package as an executable jar.
- Add automated CI (GitHub Actions) to run tests and build.

