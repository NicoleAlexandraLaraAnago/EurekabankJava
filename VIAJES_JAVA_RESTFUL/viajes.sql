CREATE DATABASE aerolineas_condor;
USE aerolineas_condor;
CREATE TABLE vuelos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ciudad_origen VARCHAR(3),
  ciudad_destino VARCHAR(3),
  valor DECIMAL(7,2),
  hora_salida DATETIME,
  fecha DATE
);

CREATE TABLE compras (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  vuelo_id BIGINT,
  comprador VARCHAR(100),
  fecha_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
USE aerolineas_condor;
INSERT INTO vuelos (ciudad_origen, ciudad_destino, valor, hora_salida, fecha) VALUES
('UIO', 'GYE', 120.50, '2025-06-01 08:30:00', '2025-06-01'),
('UIO', 'GYE', 180.75, '2025-06-01 10:45:00', '2025-06-01'),
('UIO', 'CUE', 99.99,  '2025-06-02 14:00:00', '2025-06-02'),
('GYE', 'UIO', 150.00, '2025-06-01 09:15:00', '2025-06-01'),
('CUE', 'GYE', 110.00, '2025-06-03 16:30:00', '2025-06-03'),
('GYE', 'CUE', 130.25, '2025-06-02 17:45:00', '2025-06-02');

INSERT INTO compras (vuelo_id, comprador, fecha_compra) VALUES
(1, 'Juan Pérez', '2025-05-21 10:00:00'),
(2, 'María Gómez', '2025-05-21 11:15:00'),
(3, 'Carlos López', '2025-05-22 08:30:00'),
(4, 'Ana Sánchez', '2025-05-22 09:45:00'),
(5, 'Pedro Ramos', '2025-05-23 14:00:00'),
(6, 'Luisa Mendoza', '2025-05-23 15:20:00');

SELECT c.id, v.ciudad_origen, v.ciudad_destino, v.valor, c.comprador, c.fecha_compra
FROM compras c
JOIN vuelos v ON c.vuelo_id = v.id;

SELECT * FROM vuelos WHERE ciudad_origen = 'UIO' AND ciudad_destino = 'GYE';

SELECT * FROM compras;
