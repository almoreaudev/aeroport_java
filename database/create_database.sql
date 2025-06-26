-- Utilisation de la base

USE airport_db;

-- Création de la table flights
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
DROP TABLE IF EXISTS CategoriePassager;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Facture;
DROP TABLE IF EXISTS Billet;


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

-- CategoriePassager
CREATE TABLE IF NOT EXISTS CategoriePassager (
    codeCategorie VARCHAR(10) PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    tarif DECIMAL(10, 2) NOT NULL
);

-- Utilisateur
CREATE TABLE IF NOT EXISTS Utilisateur (
    idUtilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    numPasseport VARCHAR(20) NOT NULL UNIQUE,
    numCarteIdentite VARCHAR(20) NOT NULL UNIQUE,
    superUtilisateur BOOLEAN NOT NULL DEFAULT FALSE,
);

-- Facture
CREATE TABLE IF NOT EXISTS Facture (
    idFacture INT AUTO_INCREMENT PRIMARY KEY,
    idUtilisateur INT NOT NULL,
    dateFacture DATETIME NOT NULL,
    montantTotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
);


-- Billet avion
CREATE TABLE IF NOT EXISTS Billet (
    idBillet INT AUTO_INCREMENT PRIMARY KEY,
    idUtilisateur INT NOT NULL,
    idVol INT NOT NULL,
    codeCategorie VARCHAR(10) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    nom VARCHAR(50) NOT NULL,
    prix DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur),
    FOREIGN KEY (idVol) REFERENCES Vol(idVol),
    FOREIGN KEY (codeCategorie) REFERENCES CategoriePassager(codeCategorie)
);

-- Passager
CREATE TABLE IF NOT EXISTS Passager (
    idPassager INT AUTO_INCREMENT PRIMARY KEY,
    idBillet INT NOT NULL,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    dateNaissance DATE NOT NULL,
    numPasseport VARCHAR(20) NOT NULL UNIQUE,
    codeCategorie VARCHAR(10) NOT NULL,
    FOREIGN KEY (idBillet) REFERENCES Billet(idBillet),
    FOREIGN KEY (codeCategorie) REFERENCES CategoriePassager(codeCategorie)
);
