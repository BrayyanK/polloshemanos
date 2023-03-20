INSERT INTO EQUIPOS (ID, NOMBRE, NUMERO_TITULOS) VALUES
(10, 'R.C.D. Espanyol', 0),
(11, 'Valencia F.C.', 23);

INSERT INTO JUGADORES (ID, NOMBRE, ID_EQUIPO, ORDEN) VALUES
(1, 'Nombre Jugador 1', 10, 0),
(2, 'Nombre Jugador 2', 10, 2),
(3, 'Nombre Jugador 3', 11, 0),
(4, 'Nombre Jugador 4', 10, 1),
(5, 'Nombre Jugador 5', 10, 3),
(6, 'Nombre Jugador 6', 11, 1),
(7, 'Nombre Jugador 7', 11, 2),
(8, 'Nombre Jugador 8', 10, 4),
(9, 'Nombre Jugador 9', 10, 5),
(10, 'Nombre Jugador 10', 11, 4),
(11, 'Nombre Jugador 11', 11, 3),
(12, 'Nombre Jugador 12', 10, 7),
(13, 'Nombre Jugador 13', 10, 6),
(14, 'Nombre Jugador 14', 11, 5);

INSERT INTO COCHES (MATRICULA, FECHA_MATRICULACION, MARCA, MODELO, PRECIO, NUMERO_PUERTAS, SINIESTRADO, MOTOR, KILOMETROS) VALUES
('3442FGF','2006-10-14', 'SEAT', 'Toledo TDI', 2100.0, 4, FALSE, 1, 234899),
('0090DGP','2006-11-19', 'SEAT', 'Ibiza', 3400.0, 4, FALSE, 1, 89023);

INSERT INTO EMPLEADOS (DNI, NOMBRE, APELLIDO1, APELLIDO2, ACTIVO) VALUES
('37667523F', 'Carlota', 'Cifuentes', 'Merino', TRUE),
('45899212H', 'Anna', 'Roca', 'Arderiu', TRUE),
('20098127Y', 'Fernando', 'Gimeno', 'Losada', FALSE),
('19822376G', 'Japón', 'Sevilla', 'Uriarte', TRUE); 

INSERT INTO ESTACIONES_METEOROLOGICAS (CODIGO, NOMBRE, FECHA_ALTA, OPERATIVA, LONGITUD, LATITUD, ESTADO) VALUES
(100, 'Estación 1', '2015-05-24', TRUE, 23.103334, -4.203344, 'ACTIVA'),
(101, 'Estación 2', '2017-02-16', FALSE, 23.03000, -4.200345, 'BAJA');

INSERT INTO CANCIONES (ISBN, TITULO, GENERO, FECHA, DURACION) VALUES
('34945JH5', 'CANCION 1', 0, '1962-10-22', 135553),
('111FF5T6', 'CANCION 2', 2, '1968-11-14', 35566);