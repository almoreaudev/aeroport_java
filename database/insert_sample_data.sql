-- Utilisation de la base
USE airport_db;

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
DELETE FROM CategoriePassager;
DELETE FROM Utilisateur;
DELETE FROM Facture;
DELETE FROM Billet;


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

-- CategoriePassager
INSERT INTO CategoriePassager (codeCategorie, libelle, tarif) VALUES
('SENIOR', 'Sénior (65 ans et plus)', 100.00),
('ADULTE', 'Adulte (25-64 ans inclus)', 150.00),
('ETUDIANT', 'Etudiant (18-29 ans inclus)', 120.00),
('JEUNE_ADULTE', 'Jeune adulte (18-24 ans inclus)', 110.00),
('JEUNE', 'Jeune (12-17 ans inclus)', 90.00),
('ENFANT', 'Enfant (2-11 ans inclus)', 50.00);

-- Utilisateur 
INSERT INTO Utilisateur (nom, prenom, email, motDePasse, adresse, numPasseport, numCarteIdentite) VALUES
('Dupont', 'Alice', 'alice@gmail.com', 'password123', '123 Rue de Paris, Paris, France', 'P12345678', 'CI12345678'),
('Martin', 'Bob', 'bob@free.fr', 'password456', '456 Avenue des Champs, Paris, France', 'P87654321', 'CI87654321'),
('Durand', 'Charlie', 'charlie@gmail.com', 'password789', '789 Boulevard Saint-Germain, Paris, France', 'P11223344', 'CI11223344');

-- Facture
INSERT INTO Facture (idUtilisateur, dateFacture, montantTotal) VALUES
(1, '2023-05-01 10:00:00', 200.00),
(1, '2023-05-02 11:00:00', 250.00),
(1, '2023-05-03 13:00:00', 300.00),
(2, '2023-05-01 09:30:00', 150.00),
(2, '2023-05-02 12:00:00', 300.00),
(3, '2023-05-03 14:00:00', 150.00);




-- Billet
INSERT INTO Billet (idUtilisateur, idVol, codeCategorie, prenom, nom, prix) VALUES
(1, 1, 'ADULTE', 'Alice', 'Dupont', 150.00),
(1, 2, 'ETUDIANT', 'Alice', 'Dupont', 120.00),
(2, 3, 'ADULTE', 'Bob', 'Martin', 150.00),
(3, 4, 'ENFANT', 'Charlie', 'Durand', 50.00),
(1, 5, 'JEUNE_ADULTE', 'Alice', 'Dupont', 110.00),
(2, 6, 'JEUNE', 'Bob', 'Martin', 90.00);

