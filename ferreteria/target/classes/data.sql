INSERT INTO clientes (nombre, contacto, email, telefono) values ('Ferraya S.L.', 'Andrés Ruiz', 'ferraya@ferraya.es', '912223344');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('Suministros fontaneros S.L.', 'Luis Alcaraz', 'suministros@suministros.es', '958332211');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('Soporte Técnico Micro', 'Jonathan Ruiz', 'jonathan@tech.com', '954889922');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('Alcaraz y asociados Gestores', 'Luis Efrán', 'luis@gestores.es', '671223311');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('Aduanas y soportes hospitales, S.L.', 'Juan Sarck', 'j@hsopitals.es', '607338844');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('Pinguin bath supports', 'Peter Gibbons', 'pingolin@pinguin.es', '665339240');
INSERT INTO clientes (nombre, contacto, email, telefono) values ('El piso inglés', 'Carl Sage', 'cs@englishflat.es', '911332211');

INSERT INTO pedidos (cliente_id, pedido_info) values ((SELECT cliente_id FROM clientes where nombre = 'El piso inglés'), '1500 Tubos 30 mm');
INSERT INTO pedidos (cliente_id, pedido_info) values ((SELECT cliente_id FROM clientes where nombre = 'El piso inglés'), '3000 Codos del 5');
INSERT INTO pedidos (cliente_id, pedido_info) values ((SELECT cliente_id FROM clientes where nombre = 'Aduanas y soportes hospitales, S.L.'), '200 Tapas W.C.');