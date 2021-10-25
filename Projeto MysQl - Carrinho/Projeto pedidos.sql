/** E-Commerce
Carrinho de Compras
autor Djeniffer vidal 10/2021
*/
-- decimal usar decimal(10,2)
show databases;

create database dbloja;
use dbloja;
drop database dbcarrinho;
show tables;

-- drop table carrinho;
create table clientes(
idcli int primary key auto_increment,
nome varchar(50) not null,
email varchar(50) unique not null,
senha varchar(250) not null
);

insert into clientes(nome,email,senha) values ('Jose de Assis', 'joseassis@hotmail.com',md5('123456'));

select * from clientes;
describe clientes;

-- Criar Nova tabela Estoque
-- timestamp default current_timestamp - (data e hora automatica)
-- date (tipo de dados data) YYYYMMDD
create table produtos(
codigo int primary key auto_increment,
barcode varchar(50) unique,
produto varchar(100) not null,
fabricante varchar(100) not null,
datacad timestamp default current_timestamp,
dataval date not null,
estoque int not null,
estoquemin int not null,
medida varchar(50) not null,
valor decimal(10,2),
loc varchar(100)
);

select * from produtos;
describe produtos;

insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Macarão', 'Adria', 20241005, 500, 100, 'CX', 30.75, '01.01.01');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Feijão', 'Tio João', 20200109, 700, 200, 'CX', 50.90, '02.02.02');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Arroz', 'Camil', 20200509, 700, 300, 'CX', 50.90, '03.03.03');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Cebola', 'Fruit', 20191209, 200, 100, 'CX', 40.00, '04.04.04');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Bolacha', 'Bauducco', 20230507, 300, 150, 'CX', 20.00, '05.05.05');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Tomate', 'Fruit', 20211209, 50, 100, 'CX', 60.00, '06.06.06');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Pimentão', 'Fruit', 20211209, 150, 100, 'CX', 30.00, '07.07.07');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Cola_Cola LT2', 'Colacola', 20240906, 150, 300, 'CX', 100.00, '08.08.08');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Guaraná', 'Cola cola', 20200101, 10, 50, 'CX', 100.00, '09.09.09');
insert into produtos (produto, fabricante,dataval, estoque, estoquemin, medida, valor, loc) values ('Farinha', 'Adria', 20191005, 5, 20, 'CX', 100.00, '10.10.10');

delete from produtos where codigo=8;

-- inventario do estoque (total)
select sum(estoque * valor) as Total from produtos;
 
 -- Relatorio de reposição do estoque EX:1
 select *from produtos where estoque < estoquemin;
 
  -- Relatorio de reposição do estoque EX:2
  -- date_format --> formatar a exibição da data
  -- %d- dia/ %m - Mes/ %Y- Ano
select codigo as código, produto, date_format(dataval, '%d/%m/%Y') as data_validade, estoque, estoquemin as produtos_minimo from produtos 
where estoque < estoquemin;
 
 -- Relatorio de produtos vencidos 1 - validade dos produtos
 select codigo as código, produto, date_format(dataval, '%d/%m/%Y') as data_validade from produtos;
 
-- Relatorio de validade dos produtos 2
select codigo as código, produto, date_format(dataval, '%d/%m/%Y') as data_validade, datediff(dataval, curdate()) as dias_restantes from produtos;

update produtos set loc= '15.01.06' where codigo=9;
update produtos set produto= 'Copos',fabricante= 'platics', dataval= 20251009, estoque= 500, estoquemin= 200, medida='PT', valor= 1.00, loc='15.02.01' 
where codigo=2;


create table pedidos(
pedido int primary key auto_increment,
datape timestamp default current_timestamp,
total decimal(10,2),
idcli int not null,
foreign key(idcli) references clientes(idcli)
);
describe pedidos;
select * from pedidos;

insert into pedidos(idcli) values(1);

-- Abertura do pedidos
select pedidos.pedido, date_format(pedidos.datape, '%d/%m/&Y - &H:%i') as data_pedido, clientes.nome as cliente, clientes.email as e_mail from pedidos inner join clientes on pedidos.idcli = clientes.idcli;

create table carrinho(
pedido int not null,
codigo int not null,
quantidade int not null,
foreign key (pedido) references pedidos(pedido),
foreign key (codigo) references produtos(codigo)
);
select *from carrinho;

insert into carrinho(pedido,codigo,quantidade) values (1,1,10);
insert into carrinho(pedido,codigo,quantidade) values (1,2,5);
select
pedidos.pedido,carrinho.codigo as codigo,produtos.produto,carrinho.quantidade,produtos.valor,produtos.valor * carrinho.quantidade as sub_total
from (carrinho inner join pedidos on carrinho.pedido = pedidos.pedido) inner join produtos on carrinho.codigo = produtos.codigo;

-- Total do carrinho

select sum(produtos.valor * carrinho.quantidade) as Total from carrinho inner join produtos on carrinho.codigo = produtos.codigo;

-- atualizar o banco de dados apos fechamento do pedido

update carrinho inner join produtos on carrinho.codigo = produtos.codigo set produtos.estoque = produtos.estoque - carrinho.quantidade where carrinho.quantidade
>0;

select * from produtos;