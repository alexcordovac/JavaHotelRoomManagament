CREATE DATABASE la_estancia;

USE la_estancia;

CREATE TABLE habitaciones(
  id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
  tipo_habitacion VARCHAR(15) NOT NULL,
  no_habitacion SMALLINT NOT NULL,
  estatus VARCHAR(15) DEFAULT 'Disponible',
  costo FLOAT
  )ENGINE=INNODB;

CREATE TABLE reservaciones(
  id_reservacion INT AUTO_INCREMENT PRIMARY KEY,
  fecha_entrada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecha_salida DATETIME NOT NULL,
  dias TINYINT NOT NULL,
  id_habitacion INT,
  FOREIGN KEY (id_habitacion) REFERENCES habitaciones(id_habitacion)
  ON UPDATE CASCADE ON DELETE RESTRICT
  )ENGINE=INNODB;

INSERT INTO habitaciones(tipo_habitacion, no_habitacion, costo) VALUES
  ('sencilla', 1001, 200),
  ('doble', 1002, 400),
  ('triple', 1003, 600),
  ('sencilla', 1004, 200),
  ('cuadruple', 1005, 800),
  ('doble', 1006, 400),
  ('cuadruple', 1007, 800),
  ('triple', 1008, 600),
  ('sencilla', 1009, 200),
  ('doble', 1010, 400);

INSERT INTO reservaciones(id_reservacion, fecha_entrada, fecha_salida, dias, id_habitacion) VALUES 
(null, '2021-01-20 12:45:00', '2021-01-22 15:00:00', 2,5),
(null, '2021-10-10 14:05:00', '2021-10-27 18:30:00', 17,5),
(null, '2021-10-15 01:05:00', '2021-10-30 12:35:00', 15,3),
(null, '2021-10-22 13:49:00', '2021-10-23 11:04:00', 1,1),
(null, '2021-09-11 06:56:00', '2021-11-01 12:45:00', 51,9);

