CREATE DATABASE "Proyecto"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Spanish_Mexico.1252'
       LC_CTYPE = 'Spanish_Mexico.1252'
       CONNECTION LIMIT = -1;

CREATE SCHEMA Transaccion;
CREATE SCHEMA Almacen;

CREATE TABLE Almacen.Vendedor
(
	IdVendedor BIGSERIAL NOT NULL,
	Nombre VARCHAR(100) NOT NULL,
	Domicilio VARCHAR(150) NOT NULL,
	Email VARCHAR(150) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	FechaNac DATE NOT NULL,
	Edad INT,
	CONSTRAINT PK_VENDEDOR PRIMARY KEY(IdVendedor)
);

ALTER TABLE Almacen.Vendedor ADD CONSTRAINT UQ_EMAIL UNIQUE (Email);

CREATE TABLE Transaccion.Cliente
(
	IdCliente BIGSERIAL NOT NULL,
	Nombre VARCHAR(100) NOT NULL,
	Domicilio VARCHAR(150) NOT NULL,
	Email VARCHAR(150) NOT NULL,
	Telefono VARCHAR(20) NOT NULL,
	FechaNac DATE NOT NULL,
	Edad INT,
	CONSTRAINT PK_CLIENTE PRIMARY KEY(IdCliente)
);

ALTER TABLE Transaccion.Cliente ADD CONSTRAINT UQ_EMAIL UNIQUE (Email);

CREATE TABLE Almacen.TipoProducto
(
	IdTipoProducto BIGSERIAL NOT NULL,
	Nombre VARCHAR(100) NOT NULL,
	Descripcion VARCHAR(500) NOT NULL,
	CONSTRAINT PK_TIPOPRODUCTO PRIMARY KEY(IdTipoProducto)
);


CREATE TABLE Almacen.Producto
(
	IdProducto BIGSERIAL NOT NULL,
	IdTipoProducto BIGINT NOT NULL,
	Stock INT NOT NULL,
	Tamaño VARCHAR(50) NOT NULL,
	Precio FLOAT NOT NULL,
	CONSTRAINT PK_PRODUCTO1 PRIMARY KEY(IdProducto),
	CONSTRAINT FK_TIPOPRODUCTO FOREIGN KEY(IdTipoProducto) REFERENCES Almacen.TipoProducto(IdTipoProducto)
);


ALTER TABLE Almacen.Producto ADD CONSTRAINT CH_PRECIO CHECK (
	Precio >= 100 AND Precio <= 500
);

 ALTER TABLE Almacen.Producto ADD CONSTRAINT CH_TAMAÑO CHECK (
	Tamaño = 'Individual' OR
	Tamaño = 'Matrimonial' OR
	Tamaño = 'Queen Size'
);

CREATE TABLE Transaccion.Venta 
(
	IdVenta BIGSERIAL NOT NULL,
	IdCliente BIGINT NOT NULL,
	IdVendedor BIGINT NOT NULL,
	Fecha DATE NOT NULL,
	TOTAL FLOAT NULL,
	CONSTRAINT PK_VENTA1 PRIMARY KEY(IdVenta),
	CONSTRAINT FK_CLIENTE FOREIGN KEY(IdCliente) REFERENCES Transaccion.Cliente(IdCliente),
	CONSTRAINT FK_VENDEDOR FOREIGN KEY (IdVendedor) REFERENCES Almacen.Vendedor(IdVendedor) 
);

CREATE TABLE Transaccion.DetalleVenta
(
	IdVenta BIGINT NOT NULL,
	IdProducto BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Subtotal FLOAT,
	CONSTRAINT FK_VENTA2 FOREIGN KEY(IdVenta) REFERENCES Transaccion.Venta(IdVenta),
	CONSTRAINT FK_PRODUCTO2 FOREIGN KEY(IdProducto) REFERENCES Almacen.Producto(IdProducto)
);


CREATE TABLE Almacen.Devolucion
 (
	IdDevolucion BIGSERIAL NOT NULL,
	IdVenta BIGINT NOT NULL,
	Motivo VARCHAR(400) NOT NULL,
	Fecha DATE NOT NULL,
	Total INT,
	CONSTRAINT PK_DEVOLUCION PRIMARY KEY(IdDevolucion),
	CONSTRAINT FK_VENTA3 FOREIGN KEY(IdVenta) REFERENCES Transaccion.Venta(IdVenta)
 );


 CREATE TABLE Almacen.DetalleDevolucion
 (
	IdDevolucion BIGINT NOT NULL,
	IdProducto BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Subtotal INT,
	CONSTRAINT FK_DEVOLUCCION FOREIGN KEY(IdDevolucion) REFERENCES Almacen.Devolucion(IdDevolucion),
	CONSTRAINT FK_VENTA4 FOREIGN KEY(IdProducto) REFERENCES Almacen.Producto(IdProducto)
 );

INSERT INTO Almacen.Vendedor (Nombre,Domicilio,Email,Telefono,FechaNac) VALUES ('Juanhgfds','DLJBKBKB','JH@jhgBOSI','7866125678','05/10/1994')
INSERT INTO Transaccion.Cliente (Nombre,Domicilio,Email,Telefono,FechaNac) VALUES ('DIEGO','DLJBKBKB','JH@BKHBKB','7866125678','05/10/1996')
INSERT INTO Almacen.TipoProducto(Nombre,Descripcion) VALUES ('PRUEBA','Alamo 22');
INSERT INTO Transaccion.Venta  (IdCliente,IdVendedor,Fecha) VALUES (1,1,'13/10/2019')
INSERT INTO Almacen.Devolucion (IdVenta,Motivo,Fecha) VALUES (1,'PRUEBA','15/8/2019')
INSERT INTO Almacen.DetalleDevolucion (IdDevolucion,IdProducto,Cantidad) VALUES (1,3,150)

SELECT * FROM Almacen.Vendedor
SELECT * FROM Transaccion.Cliente
SELECT * FROM Almacen.TipoProducto INSERT INTO Almacen.Producto (IdTipoProducto,Stock,Tamaño,Precio) VALUES (1,150,'Individual',500)
SELECT * FROM Almacen.Producto
SELECT * FROM Transaccion.Venta
SELECT * FROM Almacen.Devolucion
SELECT * FROM Almacen.Producto
SELECT * FROM Almacen.Producto
SELECT * FROM Almacen.DetalleDevolucion 

DELETE FROM Almacen.DetalleDevolucion  WHERE iddevolucion=1 AND  idproducto=3

 /***triggers***/
 --Trigger de Stock
CREATE OR REPLACE FUNCTION tr_stock_articulo_almacen()
  RETURNS TRIGGER AS
$BODY$
	DECLARE i_existe INTEGER;
		i_tipo INTEGER;
BEGIN
 
	-- Opera trigger
	IF TG_OP = 'INSERT' THEN
		UPDATE Almacen.Producto SET Stock = Stock + (NEW.cantidad ) WHERE idproducto = NEW.idproducto;
 
	ELSEIF TG_OP = 'UPDATE' THEN
		UPDATE Almacen.Producto SET Stock = Stock - (OLD.cantidad ) WHERE idproducto = OLD.idproducto;
		UPDATE Almacen.Producto SET Stock = Stock + (NEW.cantidad) WHERE idproducto = NEW.idproducto;
 
	ELSEIF TG_OP = 'DELETE' THEN
		UPDATE Almacen.Producto SET Stock = Stock - (OLD.cantidad ) WHERE idproducto = OLD.idproducto;
	END IF;
 
	RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER tr_stock_articulo_DETTALEVENTA AFTER INSERT OR UPDATE OR DELETE 
ON almacen.detalledevolucion FOR EACH ROW
EXECUTE PROCEDURE tr_stock_articulo_almacen();

CREATE TRIGGER tr_stock_articulo_DETTALEVENTA AFTER INSERT OR UPDATE OR DELETE 
ON Transaccion.DetalleVenta FOR EACH ROW
EXECUTE PROCEDURE tr_stock_articulo_almacen();

/*********************/
-----Trigger Edad
CREATE FUNCTION EdadAPersona()
  RETURNS TRIGGER AS
$$
    DECLARE 
BEGIN
	IF TG_OP = 'INSERT' THEN
		UPDATE Almacen.Vendedor SET Edad = (SELECT EXTRACT(YEAR FROM current_date) - EXTRACT(YEAR FROM NEW.FechaNac)) WHERE IdVendedor = NEW.IdVendedor;
	END IF;
	RETURN NULL;
END;
$$
  LANGUAGE plpgsql;

CREATE TRIGGER triGen_edad AFTER INSERT OR UPDATE 
ON Almacen.Vendedor FOR EACH ROW
EXECUTE PROCEDURE EdadAPersona();

CREATE TRIGGER triGen_edad AFTER INSERT OR UPDATE 
ON Transaccion.Cliente FOR EACH ROW
EXECUTE PROCEDURE EdadAPersona();

INSERT INTO Almacen.Vendedor (Nombre,Domicilio,Email,Telefono,FechaNac) VALUES ('Alejnadro','Salvadro','ya@dfsfse','78665678','05/10/1996');
SELECT * FROM Almacen.Vendedor;

/*********************/
------Trigger Subtotal------
CREATE FUNCTION CalculaSubtotal()
  RETURNS TRIGGER AS
$$
    DECLARE 
BEGIN
	IF TG_OP = 'INSERT' THEN
		UPDATE Transaccion.DetalleVenta SET Subtotal = Subtotal + (NEW.cantidad ) WHERE IdDevolucion = NEW.IdDevolucion;
 
	ELSEIF TG_OP = 'UPDATE' THEN
		UPDATE Transaccion.DetalleVenta SET Subtotal = Subtotal - (OLD.cantidad ) WHERE IdDevolucion = OLD.IdDevolucion;
 
	ELSEIF TG_OP = 'DELETE' THEN
		UPDATE Transaccion.DetalleVenta SET Subtotal = Subtotal - (OLD.cantidad ) WHERE IdDevolucion = OLD.IdDevolucion;
	END IF;
 
	RETURN NULL;
$$
LANGUAGE plpgsql;
												     
---------------------------------------usuarios--------------------------------------------------
CREATE ROLE Administrador WITH LOGIN ENCRYPTED PASSWORD '123';
CREATE ROLE Gerente WITH LOGIN ENCRYPTED PASSWORD '123';
CREATE ROLE Empleado WITH LOGIN ENCRYPTED PASSWORD '123';
