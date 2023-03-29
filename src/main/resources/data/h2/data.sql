INSERT INTO PRODUCTOS (CODIGO, NOMBRE, DESCRIPCION, FECHA_ALTA, PRECIO, FAMILIA, DESCATALOGADO) VALUES
(100, 'Producto 1', 'Descripción Producto 1', '2015-10-23', 2.5, 'CERVEZA', 1),
(101, 'Producto 2', 'Descripción Producto 2', '2015-10-23', 2.0, 'CERVEZA', 0),
(102, 'Producto 3', 'Descripción Producto 3', '2015-10-24', 4.0, 'COMIDA', 0),
(103, 'Producto 4', 'Descripción Producto 4', '2015-10-24', 5.5, 'CERVEZA', 0),
(104, 'Producto 5', 'Descripción Producto 5', '2015-10-25', 7.8, 'COMIDA', 0),
(105, 'Producto 6', 'Descripción Producto 6', '2015-10-25', 1.0, 'CERVEZA', 1),
(106, 'Producto 7', 'Descripción Producto 7', '2015-10-26', 3.4, 'REFRESCO', 0);

INSERT INTO EMPLEADOS (DNI, NOMBRE, APELLIDO1, APELLIDO2, ACTIVO) VALUES
('37667523F', 'Carlota', 'Cifuentes', 'Merino', TRUE),
('45899212H', 'Anna', 'Roca', 'Arderiu', TRUE),
('20098127Y', 'Fernando', 'Gimeno', 'Losada', FALSE),
('19822376G', 'Japón', 'Sevilla', 'Uriarte', TRUE);

INSERT INTO PEDIDOS (CODIGO, FECHA_HORA, DNI_EMPLEADO, ESTADO) VALUES
(10, '2019-03-24', '37667523F', 'ENTREGADO'),
(11, '2019-03-25', '45899212H', 'ENTREGADO'),
(12, '2019-03-27', '37667523F', 'EN_PROCESO');

INSERT INTO LINEAS_PEDIDO (CODIGO_PEDIDO, CODIGO_PRODUCTO, CANTIDAD) VALUES
(10, 100, 4),
(10, 102, 2),
(10, 103, 1),
(10, 105, 2),
(10, 106, 2),
(11, 101, 1),
(11, 102, 1),
(11, 104, 5),
(11, 105, 2),
(11, 106, 1),
(12, 102, 1);