/** Atividade: Carrinho de Compras
autor Djeniffer vidal 10/2021
*/
-- decimal usar decimal(10,2)
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

-- drop table carrinho;

describe carrinho;

-- alterar o nome de um campo na tabela
alter table carrinho change carrinho produto varchar(30) not null;

-- adicionar um novo campo na tabela
alter table carrinho add column OBS varchar(250);

-- adicionar um novo campo (coluna ) em um local especifico na tabela
alter table carrinho add column OBS2 varchar(150) after OBS;

-- modificar o tipo de dados e/ou validação na coluna
alter table carrinho modify column OBS2 varchar(150);

-- excluir uma coluna da tabela
alter table carrinho drop column OBS2;
alter table carrinho drop column OBS;


-- CRUD CREATE - criar dados
insert into carrinho (produto, quantidade, valor) values ('macarrao',100, 15.00);
insert into carrinho (produto, quantidade, valor) values ('Feijão',50, 20.50);
insert into carrinho (produto, quantidade, valor) values ('Arroz',46, 15.30);
insert into carrinho (produto, quantidade, valor) values ('Cebola',98, 1.50);
insert into carrinho (produto, quantidade, valor) values ('Bolacha',13, 2.00);
insert into carrinho (produto, quantidade, valor) values ('Tomate',130, 2.00);
insert into carrinho (produto, quantidade, valor) values ('Pimentão',150, 5.00);
insert into carrinho (produto, quantidade, valor) values ('Cola_Cola LT2',200, 12.00);

-- Seleionar todos os regitros (dados) da tabela
select *from carrinho;
 
 -- Operações matematicas no banco de dados - Calcular o resultado
 select sum(quantidade * valor) as Total from carrinho;
 
 -- Selecionar colunas especificas da tabela
select produto, valor from carrinho;
select produto from carrinho;
 
 -- Selecionar colunas em ordem crescente e decrescente
 -- Ordem decrescente
select codigo, produto from carrinho order by codigo desc;
-- Ordem crescente
select codigo, produto from carrinho order by codigo asc;

-- Uso de filtros
select *from carrinho where codigo=3;
select *from carrinho where produto='macarrão';

-- Realizar pesquisa por letras em comum
select *from carrinho where produto like 'ma%';

-- **CRUD READ - pesquisar dados
-- Selecionar todos os registros (dados) da tabela
select *from carrinho;

-- **CRUD UPDATE - editar contato 
update carrinho set quantidade= 500 where codigo= 1;
update carrinho set produto= 'Macarrão', quantidade= 200, valor= 25.30 where codigo= 1;
update carrinho set produto='Feijão', valor=15.50 where codigo= 2;

-- **CRUD Delete - exluir dados
-- Atenção!! Não esquecer de usar o WHERE --- IMPORTANTE
delete from carrinho where codigo= 3;

-- Criar Nova tabela Estoque
-- timestamp default current_timestamp - (data e hora automatica)
-- date (tipo de dados data) YYYYMMDD
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


-- inventario do estoque (total)
select sum(quantidade * valor) as Total from estoque;
 
 -- Relatorio de reposição do estoque EX:1
 select *from estoque where quantidade < estoquemin;
 
  -- Relatorio de reposição do estoque EX:2
  -- date_format --> formatar a exibição da data
  -- %d- dia/ %m - Mes/ %Y- Ano
select codigo as código, produto, date_format(dataval, '%d/%m/%Y') as data_validade, quantidade, estoquemin as estoque_minimo from estoque 
where quantidade < estoquemin;
 
 






