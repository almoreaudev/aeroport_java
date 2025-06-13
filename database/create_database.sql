-- Utilisation de la base
Add comment
More actions

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

‎database/insert_sample_data.sql
Copy file name to clipboard+148-7Lines changed: 148 additions & 7 deletions
Original file line numberDiff line numberDiff line change
@@ -1,10 +1,151 @@

-- Utilisation de la base

USE airport_db;




-- Insertion de quelques vols


INSERT INTO vols (vol_number, departure_airport, arrival_airport, departure_time, arrival_time, status, aircraft)


VALUES 


('AF123', 'CDG', 'JFK', '2025-06-14 10:00:00', '2025-06-14 14:00:00', 'Scheduled', 'Airbus A350'),


('BA456', 'LHR', 'DXB', '2025-06-15 08:00:00', '2025-06-15 16:00:00', 'On Time', 'Boeing 777'),


('LH789', 'FRA', 'SIN', '2025-06-16 22:00:00', '2025-06-17 16:00:00', 'Delayed', 'Airbus A380'),


('EK202', 'JFK', 'DXB', '2025-06-18 23:00:00', '2025-06-19 19:00:00', 'Cancelled', 'Boeing 777');


-- vider les tables existantes


DELETE FROM a_comme_equipage;


DELETE FROM a_pour_escale;


DELETE FROM Repas;


DELETE FROM Vol;


DELETE FROM Avion;


DELETE FROM Personnel;


DELETE FROM TypePersonnel;


DELETE FROM TypeRepas;


DELETE FROM CategorieVol;


DELETE FROM Aeroport;


DELETE FROM TypeAvion;








-- Insertion de données dans la table Type_Avion


INSERT INTO TypeAvion (codeType, capacitePassagers) VALUES


('A320', 180),


('B737', 160),


('B777', 550),


('A380', 850);





-- Insertion de données dans la table Avion


INSERT INTO Avion (dateEntretien, dateControleSecurite, codeType) VALUES


('2023-01-15 10:00:00', '2023-01-20 10:00:00', 'A320'),


('2023-02-10 11:00:00', '2023-02-15 11:00:00', 'B737'),


('2023-03-05 12:00:00', '2023-03-10 12:00:00', 'B777'),


('2023-04-01 13:00:00', '2023-04-05 13:00:00', 'A380');





-- TypePersonnel


INSERT INTO TypePersonnel (codeType, libelle) VALUES


('PILOT', 'Pilote'),


('COPIL', 'Copilote'),


('CHEFCAB', 'Chef de cabine'),


('HOT', 'Hôtesse de l’air'),


('STEW', 'Steward');





-- Personnel


INSERT INTO Personnel (nom, prenom, dateNaissance, codeType) VALUES


('Dupont', 'Jean', '1985-06-15', 'PILOT'),


('Martin', 'Claire', '1990-08-20', 'COPIL'),


('Durand', 'Sophie', '1988-12-01', 'CHEFCAB'),


('Lefevre', 'Paul', '1992-03-10', 'HOT'),


('Moreau', 'Lucie', '1995-07-25', 'STEW'),


('Bernard', 'Marc', '1987-11-30', 'PILOT'),


('Petit', 'Alice', '1991-05-05', 'COPIL'),


('Roux', 'Nathalie', '1989-09-15', 'CHEFCAB'),


('Garnier', 'Thomas', '1993-02-20', 'HOT'),


('Lemoine', 'Julien', '1994-04-10', 'STEW'),


('Boucher', 'Elise', '1986-10-30', 'PILOT'),


('Gauthier', 'Antoine', '1990-01-15', 'COPIL'),


('Blanc', 'Isabelle', '1988-07-05', 'CHEFCAB'),


('Noir', 'Vincent', '1992-12-20', 'HOT'),


('Fournier', 'Camille', '1995-03-30', 'STEW');








-- TypeRepas (snack, petit déjeuner, déjeuner, dîner)


INSERT INTO TypeRepas (codeType, libelle) VALUES


('SNACK', 'Snack'),


('PETIT_DEJ', 'Petit Déjeuner'),


('DEJEUNER', 'Déjeuner'),


('DINER', 'Dîner');





-- CategorieVol (courts-courriers, moyens-courriers, longs-courriers)


INSERT INTO CategorieVol (codeCategorie, libelle) VALUES


('COURT', 'Courts-courriers'),


('MOYEN', 'Moyens-courriers'),


('LONG', 'Longs-courriers');





-- Aeroport


INSERT INTO Aeroport (codeAeroport, nom, ville, pays) VALUES


('CDG', 'Aéroport Charles de Gaulle', 'Paris', 'France'),


('ORY', 'Aéroport d’Orly', 'Paris', 'France'),


('LHR', 'Heathrow Airport', 'Londres', 'Royaume-Uni'),


('JFK', 'John F. Kennedy International Airport', 'New York', 'États-Unis'),


('DXB', 'Dubai International Airport', 'Dubaï', 'Émirats Arabes Unis');





-- Vol


INSERT INTO Vol (dateDepart, dateArrivee, distance, statut, carburantNecessaire, duree, idAvion, codeAeroportDepart, codeAeroportArrivee, codeCategorieVol) VALUES


('2023-05-01 08:00:00', '2023-05-01 10:00:00', 500, 'Programmé', 2000, '2h', 1, 'CDG', 'LHR', 'COURT'),


('2023-05-02 09:00:00', '2023-05-02 12:00:00', 1000, 'Programmé', 4000, '3h', 2, 'CDG', 'JFK', 'MOYEN'),


('2023-05-03 14:00:00', '2023-05-03 20:00:00', 8000, 'Programmé', 30000, '8h', 3, 'CDG', 'DXB', 'LONG'),


('2023-05-04 11:00:00', '2023-05-04 13:30:00', 600, 'Programmé', 2500, '2h30', 4, 'ORY', 'LHR', 'COURT'),


('2023-05-05 15:00:00', '2023-05-05 18:00:00', 1200, 'Programmé', 5000, '3h', 1, 'ORY', 'JFK', 'MOYEN'),


('2023-05-06 07:00:00', '2023-05-06 15:00:00', 9000, 'Programmé', 35000, '9h', 2, 'ORY', 'DXB', 'LONG'),


('2023-05-07 10:00:00', '2023-05-07 12:30:00', 700, 'Programmé', 3000, '2h30', 3, 'LHR', 'CDG', 'COURT'),


('2023-05-08 13:00:00', '2023-05-08 16:00:00', 1100, 'Programmé', 4500, '3h', 4, 'LHR', 'JFK', 'MOYEN'),


('2023-05-09 16:00:00', '2023-05-09 22:00:00', 9500, 'Programmé', 40000, '8h30', 1, 'LHR', 'DXB', 'LONG'),


('2023-05-10 12:00:00', '2023-05-10 14:30:00', 800, 'Programmé', 3500, '2h30', 2, 'JFK', 'CDG', 'COURT'),


('2023-05-11 09:00:00', '2023-05-11 12:00:00', 1300, 'Programmé', 5000, '3h', 3, 'JFK', 'LHR', 'MOYEN'),


('2023-05-12 14:00:00', '2023-05-12 20:00:00', 10000, 'Programmé', 45000, '9h', 4, 'JFK', 'DXB', 'LONG');





-- a_comme_equipage


INSERT INTO a_comme_equipage (idVol, idPersonnel) VALUES


(1, 1), -- Vol 1 avec Pilote 1


(1, 2), -- Vol 1 avec Copilote 2


(1, 3), -- Vol 1 avec Chef de cabine 3


(1, 4), -- Vol 1 avec Hôtesse de l’air 4


(1, 5), -- Vol 1 avec Steward 5


(2, 6), -- Vol 2 avec Pilote 6


(2, 7), -- Vol 2 avec Copilote 7


(2, 8), -- Vol 2 avec Chef de cabine 8


(2, 9), -- Vol 2 avec Hôtesse de l’air 9


(2, 10), -- Vol 2 avec Steward 10


(3, 1), -- Vol 3 avec Pilote 1


(3, 2), -- Vol 3 avec Copilote 2


(3, 3), -- Vol 3 avec Chef de cabine 3


(3, 4), -- Vol 3 avec Hôtesse de l’air 4


(3, 5), -- Vol 3 avec Steward 5


(4, 6), -- Vol 4 avec Pilote 6


(4, 7), -- Vol 4 avec Copilote 7


(4, 8), -- Vol 4 avec Chef de cabine 8


(4, 9), -- Vol 4 avec Hôtesse de l’air 9


(4, 10), -- Vol 4 avec Steward 10


(5, 1), -- Vol 5 avec Pilote 1


(5, 2), -- Vol 5 avec Copilote 2


(5, 3), -- Vol 5 avec Chef de cabine 3


(5, 4), -- Vol 5 avec Hôtesse de l’air 4


(5, 5), -- Vol 5 avec Steward 5


(6, 6), -- Vol 6 avec Pilote 6


(6, 7), -- Vol 6 avec Copilote 7


(6, 8), -- Vol 6 avec Chef de cabine 8


(6, 9), -- Vol 6 avec Hôtesse de l’air 9


(6, 10); -- Vol 6 avec Steward 10





-- Repas


INSERT INTO Repas (codeType, idVol) VALUES


('SNACK', 1),


('PETIT_DEJ', 1),


('DEJEUNER', 2),


('DINER', 2),


('SNACK', 3),


('PETIT_DEJ', 3),


('DEJEUNER', 4),


('DINER', 4),


('SNACK', 5),


('PETIT_DEJ', 5),


('DEJEUNER', 6),


('DINER', 6);





-- a_pour_escale


INSERT INTO a_pour_escale (idVol, codeAeroport, dateArriveeEscale, dateDepartEscale) VALUES


(2, 'ORY', '2023-05-01 09:00:00', '2023-05-01 09:30:00'),


(2, 'CDG', '2023-05-02 10:00:00', '2023-05-02 10:30:00'),


(3, 'LHR', '2023-05-03 15:00:00', '2023-05-03 15:30:00'),


(4, 'JFK', '2023-05-04 12:00:00', '2023-05-04 12:30:00'),


(5, 'DXB', '2023-05-05 16:00:00', '2023-05-05 16:30:00'),


(5, 'CDG', '2023-05-06 08:00:00', '2023-05-06 08:30:00'),


(5, 'ORY', '2023-05-07 11:00:00', '2023-05-07 11:30:00');