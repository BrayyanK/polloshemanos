INSERT INTO NOTICIAS (ID, TITULAR, SUB_TITULAR, CONTENIDO) VALUES
(1, 'El Barça gana al Madrid','Un Barça muy superior...','Se hizo justicia...'),
(2, 'El Español vuelve a perder','Más cerca del pozo de segunda','Un Español desdibujado...');

INSERT INTO COMENTARIOS (ID_NOTICIA, AUTOR, COMENTARIO, ESTRELLAS) VALUES
(1, 'Honorio', 'Ya están con la linea otra vez...',3),
(1, 'Honorio', 'Puies me alegro',3),
(2, 'Honorio', 'Ja ja ja ja',3),
(1, 'Carlota', 'Muy bien',2),
(2, 'Federico', 'Es donde les toca estar',4);

INSERT INTO ALMACENES (ID, METROS_CUADRADOS, DIR, MUNICIPIO, CP, PROVINCIA, COUNTRY) VALUES
(100, 2200, 'Avda Del Mar, 230 bis', 'Santa Coloma de Gramanet', '08950','Barcelona','España'),
(101, 12000, 'Plaza Castilla, 2', 'Sagunto', '28978', 'Valencia','España');

INSERT INTO CLIENTES (ID, NOMBRE, DIRECCION, POBLACION, CODIGO_POSTAL, PROVINCIA, PAIS, TELEFONO, EMAIL) VALUES
('C1','CEDEPSA','Avda de la Industria, 23 Polígono Industrial URBINSA','Santa Perpetua de Mogoda','08020','Barcelona','España','932209034','info@cedepsa.com'),
('C2','Comercial Hermanos Pardo, S.L.','c/ Armando Heredia, 334','Mostoles','80987','Madrid','España','915543444', null);

INSERT INTO ALUMNOS (ID, NOMBRE_COMPLETO) VALUES
('AL1', 'Pepín Gálvez Ridruejo'),
('AL2', 'Carlota Cifuentes Merino'),
('AL3', 'Anna Vilchez Losada'),
('AL4', 'Honorio Martín Salvador');

INSERT INTO ASIGNATURAS (ID, NOMBRE, CREDITOS) VALUES
('AS1', 'Java SE', 7),
('AS2', 'Oracle PL/SQL', 7),
('AS3', 'Java EE', 10),
('AS4', 'Patrones de Diseño', 5);

INSERT INTO ASOCIA_ALUMNOS_ASIGNATURAS (ID_ALUMNO, ID_ASIGNATURA) VALUES
('AL1','AS2'),
('AL2','AS1'),
('AL2','AS2'),
('AL2','AS3'),
('AL2','AS4'),
('AL4','AS3'),
('AL4','AS4');

INSERT INTO PRODUCTOS (CODIGO, NOMBRE, DESCRIPCION, FECHA_ALTA, PRECIO, FAMILIA, DESCATALOGADO) VALUES
(100, 'Producto 1', 'Descripción Producto 1', '2015-10-23', 2.5, 'CERVEZA', 1),
(101, 'Producto 2', 'Descripción Producto 2', '2015-10-23', 2.0, 'CERVEZA', 0),
(102, 'Producto 3', 'Descripción Producto 3', '2015-10-24', 4.0, 'COMIDA', 0),
(103, 'Producto 4', 'Descripción Producto 4', '2015-10-24', 5.5, 'CERVEZA', 0),
(104, 'Producto 5', 'Descripción Producto 5', '2015-10-25', 7.8, 'COMIDA', 0),
(105, 'Producto 6', 'Descripción Producto 6', '2015-10-25', 1.0, 'CERVEZA', 1),
(106, 'Producto 7', 'Descripción Producto 7', '2015-10-26', 3.4, 'REFRESCO', 0);

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
('3442FGF','2006-10-14', 'SEAT', 'Toledo TDI', 2400.0, 4, FALSE, 1, 160000),
('6765DDF','2006-10-12', 'AUDI', 'A6', 7100.0, 4, TRUE, 1, 450000),
('1298FGR','2006-10-14', 'SEAT', 'Toledo TDI', 2100.0, 4, FALSE, 0, 170000),
('6788FFH','2006-10-17', 'AUDI', 'A8', 6100.0, 3, FALSE, 1, 109222),
('3442FKL','2006-10-14', 'SEAT', 'Toledo TDI', 2300.0, 2, FALSE, 3, 234899),
('0090DGP','2006-11-19', 'SEAT', 'Ibiza', 700.0, 2, FALSE, 1, 89023);

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