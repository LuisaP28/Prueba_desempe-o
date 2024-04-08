CREATE DATABASE prueba_desempeño;

USE prueba_desempeño;

CREATE TABLE tienda(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL, 
    ubicacion VARCHAR(255) NOT NULL
);

CREATE TABLE cliente(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL     
);

CREATE TABLE producto(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2),
    id_tienda INT,
    FOREIGN KEY (id_tienda) REFERENCES tienda(id) ON DELETE CASCADE
);

CREATE TABLE compra(
	id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_compra DATE, 
    cantidad INT,
    id_cliente INT,
    id_producto INT,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES producto(id) ON DELETE CASCADE
);

ALTER TABLE producto ADD COLUMN stock INT; 

SELECT * FROM compra;