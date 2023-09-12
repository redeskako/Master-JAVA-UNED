CREATE TABLE clientes(
    cliente_id bigint auto_increment primary key,
    nombre varchar(64) not null,
    contacto varchar(128) not null,
    email varchar(128) not null,
    telefono varchar(24) not null
);

CREATE TABLE pedidos(
    pedido_id bigint auto_increment primary key,
    cliente_id bigint not null,
    pedido_info varchar (2048) not null,
    foreign key (cliente_id) references clientes(cliente_id)
);