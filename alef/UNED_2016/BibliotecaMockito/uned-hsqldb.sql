DROP TABLE Prestamos IF EXISTS;;
DROP TABLE Libros IF EXISTS;;
DROP TABLE Socios IF EXISTS;;

CREATE TABLE Libros(
  IdLibro INT NOT NULL IDENTITY,
  Nombre VARCHAR(100) NOT NULL default '',
  Autor VARCHAR(100) NOT NULL default '0',
  Tema VARCHAR(100) NOT NULL default '0',
  PRIMARY KEY(IdLibro),
  Unique(Nombre)
);;

CREATE TABLE Socios(
  IdSocio INT NOT NULL IDENTITY,
  DNI VARCHAR(15) NOT NULL,
  Apellidos VARCHAR(100) NOT NULL,
  Nombre VARCHAR(50) NOT NULL,
  Direccion VARCHAR(255) NOT NULL,
  FechaAlta DATE NOT NULL default '0000-00-00',
  PRIMARY KEY(IdSocio),
  Unique(DNI)
);;

CREATE TABLE Prestamos(
  IdPrestamo INT NOT NULL IDENTITY,
  IdSocio INT NOT NULL default '0',
  IdLibro INT NOT NULL default '0',
  FechaInicio DATE NOT NULL default '0000-00-00',
  FechaFin DATE NOT NULL default '0000-00-00',
  PRIMARY KEY(IdPrestamo),
  Unique(IdSocio,IdLibro,FechaInicio),
  FOREIGN KEY(IdSocio) REFERENCES Socios(IdSocio),
  FOREIGN KEY(IdLibro) REFERENCES Libros(IdLibro)
);;

INSERT INTO Libros VALUES (1,'Informatica sdaf','Alfredo Ruiz Roldan','Ingenieria Informatica');;
INSERT INTO Libros VALUES (2,'Oracle Forms','Samuel L. Jackson','Ingenieria Informatica');;
INSERT INTO Libros VALUES (3,'Diseño de Bases de Datos','Ruben aasdf','Ingenieria Informatica');;
INSERT INTO Libros VALUES (4,'Redes y Comunicaciones','Lucas Perilo','asd');;
INSERT INTO Libros VALUES (5,'Platero y yo','Juan Ramon Jimenez ','Historia');;
INSERT INTO Libros VALUES (6,'Cien años de soleda','Gabriel Garcia Marquez','Novela');;
INSERT INTO Libros VALUES (9,'Prueba','Prueba1','*>');;
INSERT INTO Libros VALUES (10,'Ciudad de las Bestias','Isabel Allende','Novela');;
INSERT INTO Libros VALUES (11,'Los Pasos Perdidos','Alejo Carpentier','Narrativa');;
INSERT INTO Libros VALUES (12,'Introduccion al Proceso Soft','Watts S. Humpphrey','Ingenieria Informatica');;
INSERT INTO Libros VALUES (13,'AProbar','AProbar','AProbar');;

INSERT INTO Prestamos VALUES (1,7,4,'2006-02-03','2006-04-04');;
INSERT INTO Prestamos VALUES (2,1,4,'2006-03-08','0000-00-00');;
INSERT INTO Prestamos VALUES (4,3,2,'2006-04-23','0000-00-00');;
INSERT INTO Prestamos VALUES (5,3,1,'2006-06-11','0000-00-00');;
INSERT INTO Prestamos VALUES (8,3,1,'2006-08-04','2006-08-04');;
INSERT INTO Prestamos VALUES (9,1,9,'2006-08-07','2005-05-05');;
INSERT INTO Prestamos VALUES (10,4,5,'2006-08-07','0000-00-00');;
INSERT INTO Prestamos VALUES (11,2,6,'2006-08-07','0000-00-00');;
INSERT INTO Prestamos VALUES (12,9,6,'2006-08-07','0000-00-00');;
INSERT INTO Prestamos VALUES (13,9,13,'2006-08-07','0000-00-00');;
 
INSERT INTO Socios VALUES (1,'11111111C','Luis adfd ','Fernandez Najera','Pico Urbion DFS','2004-05-05');;
INSERT INTO Socios VALUES (2,'11111112A','Sabadell Lopez','Alberto','Ruiz de Canavas','2005-04-27');;
INSERT INTO Socios VALUES (3,'11111113C','Martin Pretel','Roberto gas','XXXXX','2004-10-07');;
INSERT INTO Socios VALUES (4,'11111114D','Ruiz Ruiz af','XXXXX','Cura Merino','2006-08-01');;
INSERT INTO Socios VALUES (5,'11111115E','Rafael xxxxxx','Sanchez Lopez','Samaniego 30','2002-10-30');;
INSERT INTO Socios VALUES (7,'X3333333Z','Carlos','Ruiz Hernandez','*>','2006-08-01');;
INSERT INTO Socios VALUES (8,'00000000A','aa','bb','dd','2006-08-05');;
INSERT INTO Socios VALUES (9,'000005423','Banderas','Toño','Marbella Beach Corruption Village','2006-08-07');;
INSERT INTO Socios VALUES (10,'0000AAAAV','Rodriguez','Luis','Sabadell','2006-08-07');;
