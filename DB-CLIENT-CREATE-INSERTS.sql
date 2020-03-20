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
insert into client(name,gender,birthday,city) values ('João Silva','M', '1990-10-05','Floripa');
insert into client(name,gender,birthday,city) values ('Maria Antunes','F', '1984-10-05','Floripa');
insert into client(name,gender,birthday,city) values ('Felipe Damazio','M', '2007-10-05','São José');
insert into client(name,gender,birthday,city) values ('Joana Silva','M', '2001-10-05','Palhoça');
insert into client(name,gender,birthday,city) values ('Fernando Alcantara','M', '1990-08-10','Porto Alegre');
insert into client(name,gender,birthday,city) values ('Carlos Silveira','M', '1990-10-05','Alegrete');


select * from client;
