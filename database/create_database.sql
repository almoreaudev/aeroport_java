-- Création de la base de données
CREATE DATABASE IF NOT EXISTS airport_db;

-- Utilisation de la base
USE airport_db;

DROP TABLE IF EXISTS a_comme_equipage;
DROP TABLE IF EXISTS a_pour_escale;
DROP TABLE IF EXISTS Repas;
DROP TABLE IF EXISTS Vol;
DROP TABLE IF EXISTS Avion;
DROP TABLE IF EXISTS Personnel;
DROP TABLE IF EXISTS TypePersonnel;
DROP TABLE IF EXISTS TypeRepas;
DROP TABLE IF EXISTS CategorieVol;
DROP TABLE IF EXISTS Aeroport;
DROP TABLE IF EXISTS TypeAvion;

CREATE TABLE IF NOT EXISTS TypeAvion (
    codeType VARCHAR(10) PRIMARY KEY,
    capacitePassagers INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Avion (
    idAvion INT AUTO_INCREMENT PRIMARY KEY,
    dateEntretien DATETIME NOT NULL,
    dateControleSecurite DATETIME NOT NULL,
    codeType VARCHAR(10) NOT NULL,
    FOREIGN KEY (codeType) REFERENCES Type_Avion(codeType)
);


-- TypePersonnel
CREATE TABLE IF NOT EXISTS TypePersonnel (
    codeType VARCHAR(10) PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- Personnel
CREATE TABLE IF NOT EXISTS Personnel (
    idPersonnel INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    dateNaissance DATE NOT NULL,
    codeType VARCHAR(10) NOT NULL,
    FOREIGN KEY (codeType) REFERENCES TypePersonnel(codeType)
);


-- TypeRepas
CREATE TABLE IF NOT EXISTS TypeRepas (
    codeType VARCHAR(10) PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- CategorieVol
CREATE TABLE IF NOT EXISTS CategorieVol (
    codeCategorie VARCHAR(10) PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL
);

-- Aeroport
CREATE TABLE IF NOT EXISTS Aeroport (
    codeAeroport VARCHAR(10) PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    ville VARCHAR(50) NOT NULL,
    pays VARCHAR(50) NOT NULL
);

-- Vol
CREATE TABLE IF NOT EXISTS Vol (
    idVol INT AUTO_INCREMENT PRIMARY KEY,
    dateDepart DATETIME NOT NULL,
    dateArrivee DATETIME NOT NULL,
    distance INT NOT NULL,
    statut VARCHAR(50) NOT NULL,
    carburantNecessaire INT NOT NULL,
    duree VARCHAR(50) NOT NULL,
    idAvion INT NOT NULL,
    codeAeroportDepart VARCHAR(10) NOT NULL,
    codeAeroportArrivee VARCHAR(10) NOT NULL,
    codeCategorieVol VARCHAR(10) NOT NULL,
    FOREIGN KEY (codeAeroportDepart) REFERENCES Aeroport(codeAeroport),
    FOREIGN KEY (codeAeroportArrivee) REFERENCES Aeroport(codeAeroport),
    FOREIGN KEY (codeCategorieVol) REFERENCES CategorieVol(codeCategorie)
);

-- a_comme_equipage
CREATE TABLE IF NOT EXISTS a_comme_equipage (
    idVol INT NOT NULL,
    idPersonnel INT NOT NULL,
    PRIMARY KEY (idVol, idPersonnel),
    FOREIGN KEY (idVol) REFERENCES Vol(idVol),
    FOREIGN KEY (idPersonnel) REFERENCES Personnel(idPersonnel)
);

-- Repas
CREATE TABLE IF NOT EXISTS Repas (
    idRepas INT AUTO_INCREMENT PRIMARY KEY,
    codeType VARCHAR(10) NOT NULL,
    idVol INT NOT NULL,
    FOREIGN KEY (codeType) REFERENCES TypeRepas(codeType),
    FOREIGN KEY (idVol) REFERENCES Vol(idVol)
);

-- a_pour_escale
CREATE TABLE IF NOT EXISTS a_pour_escale (
    idVol INT NOT NULL,
    codeAeroport VARCHAR(10) NOT NULL,
    dateArriveeEscale DATETIME NOT NULL,
    dateDepartEscale DATETIME NOT NULL,
    PRIMARY KEY (idVol, codeAeroport),
    FOREIGN KEY (idVol) REFERENCES Vol(idVol),
    FOREIGN KEY (codeAeroport) REFERENCES Aeroport(codeAeroport)
);