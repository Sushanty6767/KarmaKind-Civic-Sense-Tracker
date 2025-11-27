CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT NOT NULL UNIQUE,
  karma INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS tasks (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  title TEXT,
  description TEXT,
  base_points INTEGER,
  due_date TEXT,
  type TEXT,
  impact TEXT,
  completed INTEGER DEFAULT 0,
  user_id INTEGER,
  FOREIGN KEY(user_id) REFERENCES users(id)
);
