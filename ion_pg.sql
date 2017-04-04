alter table ion.cliente drop constraint FK334B85FA870525B0;
alter table ion.lista drop constraint FK6236383870525B0;
alter table listacliente drop constraint FKCC5CB917EF334BC3;
alter table listacliente drop constraint FKCC5CB917733EAE95;
drop table ion.cliente;
drop table ion.lista;
drop table ion.mensaje;
drop table ion.usuario;
drop table listacliente;
drop sequence hibernate_sequence;
create table cliente (
    id int8 not null,
    tlf varchar(12) not null,
    nombre varchar(64),
    email varchar(64),
    activo int2 not null,
    owner varchar(255) not null,
    primary key (id)
);
create table lista (
    id int8 not null,
    nombre varchar(32) not null,
    owner varchar(255) not null,
    primary key (id)
);
create table mensaje (
    id int8 not null,
    fechahora timestamp not null,
    msg varchar(160),
    mobile varchar(13) not null,
    servicio varchar(64),
    cliente varchar(64),
    cnx varchar(64),
    tipo int4 not null,
    primary key (id)
);
create table usuario (
    username varchar(255) not null,
    nombre varchar(64),
    password varchar(32) not null,
    primary key (username)
);
create table listacliente (
    cliente int8 not null,
    lista int8 not null,
    primary key (lista, cliente)
);
alter table cliente 
    add constraint FK334B85FA870525B0 
    foreign key (owner) 
    references usuario;
alter table lista 
    add constraint FK6236383870525B0 
    foreign key (owner) 
    references usuario;
alter table listacliente 
    add constraint FKCC5CB917EF334BC3 
    foreign key (cliente) 
    references cliente;
alter table listacliente 
    add constraint FKCC5CB917733EAE95 
    foreign key (lista) 
    references lista;
create sequence msg_sequence;
