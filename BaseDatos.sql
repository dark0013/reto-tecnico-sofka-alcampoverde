CREATE DATABASE IF NOT EXISTS finance_service_db;

CREATE USER IF NOT EXISTS 'admin'@'%' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON finance_service_db.* TO 'admin'@'%';

FLUSH PRIVILEGES;
