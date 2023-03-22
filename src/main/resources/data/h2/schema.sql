CREATE SEQUENCE "PRODUCTO_SEQ"
	MINVALUE 1
	MAXVALUE 99999999999999
	INCREMENT BY 1
	START WITH 1000
	NOCACHE
	NOCYCLE;
	
CREATE TABLE PRODUCTOS(

	CODIGO				BIGINT				NOT NULL,
	NOMBRE				VARCHAR(150)		,
	DESCRIPCION			VARCHAR(250)		,
	FECHA_ALTA			DATE				,
	PRECIO				DOUBLE				,
	FAMILIA				VARCHAR(50)			,
	DESCATALOGADO		TINYINT				,
	
	PRIMARY KEY (CODIGO)

);


CREATE TABLE EMPLEADOS(

	DNI			VARCHAR(9)			NOT NULL,
	NOMBRE		VARCHAR(100)		,
	APELLIDO1	VARCHAR(100)		,
	APELLIDO2	VARCHAR(100)		,
	ACTIVO		BOOLEAN				,
	
	PRIMARY KEY (DNI)
 
);