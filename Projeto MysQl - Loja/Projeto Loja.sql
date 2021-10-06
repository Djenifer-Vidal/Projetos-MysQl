/** Atividade: Carrinho de Compras
autor Djeniffer vidal 10/2021
*/

show databases;
create database dbloja;
use dbloja;
drop database dbcarrinho;
show tables;

create table carrinho(
codigo int primary key auto_increment,
produto varchar(250) not null,
quantidade int not null,
valor decimal(10,2) not null
);


describe carrinho;

alter table carrinho change carrinho produto varchar(30) not null;

alter table carrinho add column OBS varchar(250);

alter table carrinho add column OBS2 varchar(150) after OBS;

alter table carrinho modify column OBS2 varchar(150);

alter table carrinho drop column OBS2;
alter table carrinho drop column OBS;


insert into carrinho (produto, quantidade, valor) values ('macarrao',100, 15.00);
insert into carrinho (produto, quantidade, valor) values ('Feijão',50, 20.50);
insert into carrinho (produto, quantidade, valor) values ('Arroz',46, 15.30);
insert into carrinho (produto, quantidade, valor) values ('Cebola',98, 1.50);
insert into carrinho (produto, quantidade, valor) values ('Bolacha',13, 2.00);
insert into carrinho (produto, quantidade, valor) values ('Tomate',130, 2.00);
insert into carrinho (produto, quantidade, valor) values ('Pimentão',150, 5.00);
insert into carrinho (produto, quantidade, valor) values ('Cola_Cola LT2',200, 12.00);

select *from carrinho;
 
 select sum(quantidade * valor) as Total from carrinho;
 
select produto, valor from carrinho;
select produto from carrinho;
 
select codigo, produto from carrinho order by codigo desc;
select codigo, produto from carrinho order by codigo asc;

select *from carrinho where codigo=3;
select *from carrinho where produto='macarrão';

select *from carrinho where produto like 'ma%';

select *from carrinho;

update carrinho set quantidade= 500 where codigo= 1;
update carrinho set produto= 'Macarrão', quantidade= 200, valor= 25.30 where codigo= 1;
update carrinho set produto='Feijão', valor=15.50 where codigo= 2;

delete from carrinho where codigo= 3;

create table estoque(
codigo int primary key auto_increment,
barcode varchar(50) unique,
produto varchar(100) not null,
fabricante varchar(100) not null,
datacad timestamp default current_timestamp,
dataval date not null,
quantidade int not null,
estoquemin int not null,
medida varchar(50) not null,
valor decimal(10,2),
loc varchar(100)
);
select *from estoque;
describe estoque;

insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Macarão', 'Adria', 20241005, 500, 100, 'CX', 30.75, 'AA');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Feijão', 'Tio João', 20200109, 700, 200, 'CX', 50.90, 'AA');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Arroz', 'Camil', 20200509, 700, 300, 'CX', 50.90, 'AA');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Cebola', 'Fruit', 20191209, 200, 100, 'CX', 40.00, 'AC');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Bolacha', 'Bauducco', 20230507, 300, 150, 'CX', 20.00, 'AD');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Tomate', 'Fruit', 20211209, 50, 100, 'CX', 60.00, 'AC');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Pimentão', 'Fruit', 20211209, 150, 100, 'CX', 30.00, 'AC');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Cola_Cola LT2', 'Colacola', 20240906, 150, 300, 'CX', 100.00, 'AB');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Guaraná', 'Cola cola', 20200101, 10, 50, 'CX', 100.00, '09.09.01');
insert into estoque (produto, fabricante,dataval, quantidade, estoquemin, medida, valor, loc) values ('Farinha', 'Adria', 20191005, 5, 20, 'CX', 100.00, '10.10.01');

update estoque set loc= '01.01.01' where codigo=1;
update estoque set loc= '02.02.01' where codigo=2;
update estoque set loc= '03.03.01' where codigo=3;
update estoque set loc= '04.04.01' where codigo=4;
update estoque set loc= '05.05.01' where codigo=5;
update estoque set loc= '06.06.01' where codigo=6;
update estoque set loc= '07.07.01' where codigo=7;
update estoque set loc= '08.08.01' where codigo=8;
update estoque set quantidade= 100 where codigo=5;
update estoque set quantidade= 200 where codigo=3;
update estoque set quantidade= 100 where codigo=1;


select sum(quantidade * valor) as Total from estoque;
 
 select *from estoque where quantidade < estoquemin;
 
select codigo as código, produto, date_format(dataval, '%d/%m/%Y') as data_validade, quantidade, estoquemin as estoque_minimo from estoque 
where quantidade < estoquemin;
 
select codigo as código,produto,date_format(dataval,'%d/%m/%Y') as data_validade
from estoque;

select codigo as código,produto,date_format(dataval, '%d/%m/%Y') as data_validade,
datediff(dataval,curdate()) as dias_restantes from estoque;

update estoque set estoquemin =15 where codigo = 3;

update estoque set produto = 'Farinha', fabricante ='nestle*',dataval=20220330,quantidade=250, estoquemin=20, valor=5.99 where codigo = 10;
 
 select * from estoque;
 
delete from estoque where codigo = 6;

select * from estoque;







