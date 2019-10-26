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


INSERT INTO Almacen.Vendedor (Nombre,Domicilio,Email,Telefono,FechaNac) VALUES ('JUAN','DLJBKBKB','JH@BKHBKB','7866125678','05/10/1996')
SELECT * FROM Almacen.Vendedor

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
INSERT INTO Transaccion.Cliente (Nombre,Domicilio,Email,Telefono,FechaNac) VALUES ('DIEGO','DLJBKBKB','JH@BKHBKB','7866125678','05/10/1996')
SELECT * FROM Transaccion.Cliente

ALTER TABLE Transaccion.Cliente ADD CONSTRAINT UQ_EMAIL UNIQUE (Email);

CREATE TABLE Almacen.TipoProducto
(
	IdTipoProducto BIGSERIAL NOT NULL,
	Nombre VARCHAR(100) NOT NULL,
	Descripcion VARCHAR(500) NOT NULL,
	CONSTRAINT PK_TIPOPRODUCTO PRIMARY KEY(IdTipoProducto)
);

INSERT INTO Almacen.TipoProducto(Nombre,Descripcion) VALUES ('PRUEBA','Alamo 22');
SELECT * FROM Almacen.TipoProducto

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

INSERT INTO Almacen.Producto (IdTipoProducto,Stock,Tamaño,Precio) VALUES (1,150,'Individual',500);
SELECT * FROM Almacen.Producto;

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

INSERT INTO Transaccion.Venta  (IdCliente,IdVendedor,Fecha) VALUES (1,1,'13/10/2019');
SELECT * FROM Transaccion.Venta;

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

INSERT INTO Almacen.Devolucion (IdVenta,Motivo,Fecha) VALUES (1,'PRUEBA','15/8/2019');
SELECT * FROM Almacen.Devolucion;
SELECT * FROM Almacen.Producto;

 CREATE TABLE Almacen.DetalleDevolucion
 (
	IdDevolucion BIGINT NOT NULL,
	IdProducto BIGINT NOT NULL,
	Cantidad INT NOT NULL,
	Subtotal INT,
	CONSTRAINT FK_DEVOLUCCION FOREIGN KEY(IdDevolucion) REFERENCES Almacen.Devolucion(IdDevolucion),
	CONSTRAINT FK_VENTA4 FOREIGN KEY(IdProducto) REFERENCES Almacen.Producto(IdProducto)
 );
 INSERT INTO Almacen.DetalleDevolucion (IdDevolucion,IdProducto,Cantidad) VALUES (1,1,150);
 SELECT * FROM Almacen.Producto;
 UPDATE Almacen.DetalleDevolucion SET cantidad = 200 where iddevolucion=1;
 DELETE FROM Almacen.DetalleDevolucion  WHERE iddevolucion=1 AND  idproducto=3;
 --triggers


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


CREATE OR REPLACE FUNCTION tr_stock_articulo_almacen2()
  RETURNS TRIGGER AS
$BODY$
	DECLARE i_existe INTEGER;
		i_tipo INTEGER;
BEGIN
 
	-- Opera trigger
	IF TG_OP = 'INSERT' THEN
		UPDATE Almacen.Producto SET Stock = Stock - (NEW.cantidad ) WHERE idproducto = NEW.idproducto;
 
	ELSEIF TG_OP = 'UPDATE' THEN
		UPDATE Almacen.Producto SET Stock = Stock + (OLD.cantidad ) WHERE idproducto = OLD.idproducto;
		UPDATE Almacen.Producto SET Stock = Stock - (NEW.cantidad) WHERE idproducto = NEW.idproducto;
 
	ELSEIF TG_OP = 'DELETE' THEN
		UPDATE Almacen.Producto SET Stock = Stock + (OLD.cantidad ) WHERE idproducto = OLD.idproducto;
	END IF;
 
	RETURN NULL;
END;
$BODY$
  LANGUAGE plpgsql;

CREATE TRIGGER tr_stock_articulo_DETTALEDEVOLUCION AFTER INSERT OR UPDATE OR DELETE 
ON almacen.detalledevolucion FOR EACH ROW
EXECUTE PROCEDURE tr_stock_articulo_almacen();

CREATE TRIGGER tr_stock_articulo_DETTALEVENTA AFTER INSERT OR UPDATE OR DELETE 
ON Transaccion.DetalleVenta FOR EACH ROW
EXECUTE PROCEDURE tr_stock_articulo_almacen2();

---------------------------------------usuarios--------------------------------------------------
CREATE USER Administrador WITH LOGIN ENCRYPTED PASSWORD '123';
GRANT CONNECT ON DATABASE "Proyecto" TO Administrador;
GRANT USAGE ON SCHEMA almacen,transaccion TO Administrador;
SELECT * FROM almacen.Producto;
GRANT SELECT,UPDATE,INSERT,DELETE ON ALL TABLES IN SCHEMA almacen,transaccion TO Administrador;
------------------------------------------------------------------------------------------------------
CREATE USER Gerente WITH LOGIN ENCRYPTED PASSWORD '123';
GRANT CONNECT ON DATABASE "Proyecto" TO Gerente; 
GRANT USAGE ON SCHEMA almacen,transaccion TO Gerente;
GRANT INSERT,UPDATE,DELETE ON ALL TABLES IN SCHEMA transaccion TO Gerente;
GRANT SELECT ON ALL TABLES IN SCHEMA transaccion,almacen TO Gerente;
GRANT INSERT,UPDATE,DELETE ON almacen.detalledevolucion,almacen.devolucion,almacen.vendedor TO Gerente;
---------------------------------------------------------------------
CREATE USER Empleado WITH LOGIN ENCRYPTED PASSWORD '123';
GRANT CONNECT ON DATABASE "Proyecto" TO Empleado; 
GRANT USAGE ON SCHEMA almacen,transaccion TO Empleado;
GRANT SELECT ON ALL TABLES IN SCHEMA transaccion,almacen TO Empleado;
GRANT INSERT,UPDATE,DELETE ON ALL TABLES IN SCHEMA transaccion TO Empleado;
REVOKE INSERT,UPDATE,DELETE ON TABLE transaccion.cliente FROM Empleado;
GRANT INSERT,UPDATE,DELETE ON almacen.detalledevolucion,almacen.devolucion TO Empleado;

select * from almacen.devolucion;
