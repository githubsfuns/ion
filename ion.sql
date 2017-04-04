alter table ion.cliente drop foreign key FK334B85FA870525B0;
alter table ion.lista drop foreign key FK6236383870525B0;
alter table listacliente drop foreign key FKCC5CB917EF334BC3;
alter table listacliente drop foreign key FKCC5CB917733EAE95;
drop table if exists ion.cliente;
drop table if exists ion.lista;
drop table if exists ion.mensaje;
drop table if exists ion.usuario;
drop table if exists listacliente;
create table ion.cliente (
    id bigint not null auto_increment,
    tlf varchar(12) not null,
    nombre varchar(64),
    email varchar(64),
    activo smallint not null,
    owner varchar(255) not null,
    primary key (id)
);
create table ion.lista (
    id bigint not null auto_increment,
    nombre varchar(32) not null,
    owner varchar(255) not null,
    primary key (id)
);
create table ion.mensaje (
    id bigint not null auto_increment,
    fechahora datetime not null,
    msg varchar(160),
    mobile varchar(13) not null,
    servicio varchar(64),
    cliente varchar(64),
    cnx varchar(64),
    tipo integer not null,
    primary key (id)
);
create table ion.usuario (
    username varchar(255) not null,
    nombre varchar(64),
    password varchar(32) not null,
    primary key (username)
);
create table listacliente (
    cliente bigint not null,
    lista bigint not null,
    primary key (lista, cliente)
);
alter table ion.cliente 
    add index FK334B85FA870525B0 (owner), 
    add constraint FK334B85FA870525B0 
    foreign key (owner) 
    references ion.usuario (username);
alter table ion.lista 
    add index FK6236383870525B0 (owner), 
    add constraint FK6236383870525B0 
    foreign key (owner) 
    references ion.usuario (username);
alter table listacliente 
    add index FKCC5CB917EF334BC3 (cliente), 
    add constraint FKCC5CB917EF334BC3 
    foreign key (cliente) 
    references ion.cliente (id);
alter table listacliente 
    add index FKCC5CB917733EAE95 (lista), 
    add constraint FKCC5CB917733EAE95 
    foreign key (lista) 
    references ion.lista (id);
