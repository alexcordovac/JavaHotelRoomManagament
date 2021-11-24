SET NAMES 'utf8mb4';
DROP DATABASE IF EXISTS la_estancia;

CREATE DATABASE la_estancia
	CHARACTER SET utf8mb4
	COLLATE utf8mb4_0900_ai_ci;

USE la_estancia;

CREATE TABLE habitaciones (
  id_habitacion INT(11) NOT NULL AUTO_INCREMENT,
  tipo_habitacion VARCHAR(15) NOT NULL,
  no_habitacion SMALLINT(6) NOT NULL,
  estatus VARCHAR(15) DEFAULT 'disponible',
  costo FLOAT DEFAULT NULL,
  PRIMARY KEY (id_habitacion)
)
ENGINE = INNODB,
AUTO_INCREMENT = 12;

CREATE TABLE reservaciones (
  id_reservacion INT(11) NOT NULL AUTO_INCREMENT,
  fecha_entrada DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  fecha_salida DATETIME NOT NULL,
  dias TINYINT(4) NOT NULL,
  id_habitacion INT(11) DEFAULT NULL,
  PRIMARY KEY (id_reservacion)
)
ENGINE = INNODB,
AUTO_INCREMENT = 10;

ALTER TABLE reservaciones 
  ADD CONSTRAINT reservaciones_ibfk_1 FOREIGN KEY (id_habitacion)
    REFERENCES habitaciones(id_habitacion) ON UPDATE CASCADE;

CREATE TABLE empleado (
  idEmpleado INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  apellidos VARCHAR(100) NOT NULL,
  edad TINYINT(4) NOT NULL,
  puesto VARCHAR(50) NOT NULL,
  sueldo INT(11) NOT NULL,
  area VARCHAR(50) NOT NULL,
  PRIMARY KEY (idEmpleado)
)
ENGINE = INNODB,
AUTO_INCREMENT = 4;

INSERT INTO habitaciones VALUES
(1, 'sencilla', 1001, 'Disponible', 200),
(2, 'doble', 1002, 'Disponible', 400),
(3, 'triple', 1003, 'Disponible', 600),
(4, 'sencilla', 1004, 'Disponible', 200),
(5, 'cuadruple', 1005, 'Disponible', 800),
(6, 'doble', 1006, 'disponible', 400),
(7, 'cuadruple', 1007, 'disponible', 800),
(8, 'triple', 1008, 'Disponible', 600),
(9, 'sencilla', 1009, 'Disponible', 200),
(10, 'doble', 1010, 'Disponible', 400),
(11, 'doble', 1356, 'Ocupado', 900);


INSERT INTO reservaciones VALUES
(1, '2021-01-20 12:45:00', '2021-01-22 15:00:00', 2, 5),
(2, '2021-10-10 14:05:00', '2021-10-27 18:30:00', 17, 5),
(3, '2021-10-15 01:05:00', '2021-10-30 12:35:00', 15, 3),
(4, '2021-10-22 13:49:00', '2021-10-23 11:04:00', 1, 1),
(5, '2021-09-11 06:56:00', '2021-11-01 12:45:00', 51, 9),
(6, '2021-10-26 20:25:00', '2021-10-30 10:50:00', 4, 8),
(7, '2021-10-26 23:02:00', '2021-10-31 23:02:00', 5, 4),
(8, '2021-11-20 17:58:00', '2021-11-22 17:58:00', 2, 1),
(9, '2021-11-21 18:08:00', '2021-11-25 13:30:00', 4, 11);


INSERT INTO empleado VALUES
(1, 'Alex', 'Córdova Córdova', 22, 'Jefe de departamento', 60000, 'TI'),
(2, 'Carlos Alfredo', 'Pérez de los Santos', 45, 'Asesor de db', 40000, 'TI'),
(3, 'Emilio', 'Azcarraga Hernández', 22, 'Técnico', 30000, 'TI');
