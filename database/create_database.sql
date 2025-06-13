-- Création de la base de données
CREATE DATABASE IF NOT EXISTS airport_db;

-- Utilisation de la base
USE airport_db;

-- Création de la table flights
CREATE TABLE IF NOT EXISTS vols (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vol_number VARCHAR(10) NOT NULL,
    departure_airport VARCHAR(5) NOT NULL,
    arrival_airport VARCHAR(5) NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled',
    aircraft VARCHAR(50)
);
