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


select * from client;
