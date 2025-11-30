CREATE DATABASE IF NOT EXISTS concesionaria_db;
USE concesionaria_db;

CREATE TABLE IF NOT EXISTS vehiculos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL, -- Campo de Texto
    precio DECIMAL(10, 2) NOT NULL -- Campo Numérico
);

-- Insertamos algunos datos de prueba para que la tabla no esté vacía al iniciar
INSERT INTO vehiculos (modelo, precio) VALUES ('Toyota Corolla 2024', 450000.00);
INSERT INTO vehiculos (modelo, precio) VALUES ('Honda Civic 2023', 520000.50);
INSERT INTO vehiculos (modelo, precio) VALUES ('Mazda 3 2025', 480000.00);
INSERT INTO vehiculos (modelo, precio) VALUES ('Nissan Sentra 2022', 390000.00);
INSERT INTO vehiculos (modelo, precio) VALUES ('Volkswagen Jetta 2024', 460000.00);
INSERT INTO vehiculos (modelo, precio) VALUES ('Mitsubishi Lancer 2011', 120000.00);