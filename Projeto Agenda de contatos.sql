
/**
Agenda de Contatos
@autor Djeniffer Vidal
*/
-- exibir banco de dados do servidor
show databases;

create database dbageda;

drop database dbteste;

use dbagenda;


show tables;

create table contatos(
id int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50) unique
);	

describe contatos;


alter table contatos change nome contato varchar(50) not null;

alter table contatos add column obs varchar(250);

alter table contatos add column fone2 varchar(15) after fone;

alter table contatos modify column fone2 int;
alter table contatos modify column email varchar(100) not null;

alter table contatos drop column obs;

insert into contatos (nome, fone, email) values ('Matheus','99999-9999','Matheus985@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao', '88888-8888', 'joao267@hotmail.com');
insert into contatos (nome, fone, email) values ('Gabriel','22222-2222',' ');
insert into contatos (nome, fone, email) values ('Victor_Marcelo','44444-4444','vitorm56@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao Alfredo','8654643484','joaoalfredo@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao pedro','99999999','joaopedro@hotmail.com');
insert into contatos (nome, fone, email) values ('Daniela','111111111','daniela12@hotmail.com');
insert into contatos (nome, fone, email) values ('vitoria','22222222','vitoria582@hotmail.com');

select *from contatos;

select nome, fone from contatos;
select nome from contatos;


select *from contatos order by nome;
select id, nome from contatos order by id desc; 
select id, nome from contatos order by id asc;


Select *from contatos where id = 1;
select *from contatos where nome = 'Matheus';
select *from contatos where nome like 'ga%';


select *from contatos;

update contatos set fone= '5464468798' where id = 10;
update contatos set nome= 'Matheus', fone= '88888-8888', email= 'matheus5679hotmail.com' where id=1;
update contatos set nome= 'Isaque', fone= '22222-2222', email= 'isaquesanttos@hotmail.com' where id=10;


delete from contatos where id=10;




