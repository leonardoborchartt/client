 create table client (
	  id bigint not null auto_increment,
      name varchar(255),
      gender varchar(255),
      birthday varchar(255),
      city varchar(255),
      primary key (id)
  );
insert into user(id,name,gender,birthday,city) values (1,'João Silva','M', '1990-10-05','Floripa');
insert into user(id,name,gender,birthday,city) values (2,'Maria Antunes','F', '1984-10-05','Floripa');
insert into user(id,name,gender,birthday,city) values (3,'Felipe Damazio','M', '2007-10-05','São José');
insert into user(id,name,gender,birthday,city) values (4,'Joana Silva','M', '2001-10-05','Palhoça');
insert into user(id,name,gender,birthday,city) values (5,'Fernando Alcantara','M', '1990-08-10','Porto Alegre');
insert into user(id,name,gender,birthday,city) values (6,'Carlos Silveira','M', '1990-10-05','Alegrete');

 
  select * from user;
