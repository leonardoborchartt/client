Prerequisitos de softwares para rodar a aplicação:

MySQL Ver 8.0.19
Apache Maven 3.6.3
Java version "9.0.1"

Crie as databases e tabelas antes de rodar a aplicação.
Os comandos para criação do banco e tabelas são descritos também abaixo:

-> Para criação do bd para o microservico Clientes:

create database clients;
use clients;
create table client (
      id bigint not null auto_increment,
      name varchar(255),
      gender varchar(255),
      birthday varchar(255),
      city varchar(255),
      primary key (id)
);


/---------------------------------------------/


-> Para criação do bd para o microservico Cidades:

create database city;
use city;
create table cities (
	id int not null auto_increment,
	name varchar(255),
	state varchar(255),
	primary key (id)
  );
