-- Utilisation de la base
USE airport_db;

-- Insertion de quelques vols
INSERT INTO vols (vol_number, departure_airport, arrival_airport, departure_time, arrival_time, status, aircraft)
VALUES 
('AF123', 'CDG', 'JFK', '2025-06-14 10:00:00', '2025-06-14 14:00:00', 'Scheduled', 'Airbus A350'),
('BA456', 'LHR', 'DXB', '2025-06-15 08:00:00', '2025-06-15 16:00:00', 'On Time', 'Boeing 777'),
('LH789', 'FRA', 'SIN', '2025-06-16 22:00:00', '2025-06-17 16:00:00', 'Delayed', 'Airbus A380'),
('EK202', 'JFK', 'DXB', '2025-06-18 23:00:00', '2025-06-19 19:00:00', 'Cancelled', 'Boeing 777');
