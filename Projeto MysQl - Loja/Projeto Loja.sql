/**
E-Comerce 
@author Djeniffer Vidal
*/

drop database dbloja;
show databases;
create database dbloja;
use dbloja;
show tables;

create table clientes (
	idcli int primary key auto_increment,
    nome varchar(50) not null,
    email varchar(50) unique not null,
    senha varchar(250)not null
);

insert into clientes (nome,email,senha)
values ('Djeniffer vidal','Djenifferv@gmail.com',md5('123456'));
insert into clientes (nome,email,senha)
values ('Daniela','Dani1285@gmail.com',md5('5434354'));
insert into clientes (nome,email,senha)
values ('Joao Marcos','joao58marcos@gmail.com',md5('3563743gfg'));
insert into clientes (nome,email,senha)
values ('Josue Santos','josue58246@gmail.com',md5('545873686'));

select * from clientes;
describe clientes;

-- Criando outra tabale
-- timestamp default current_timestamp (data e hora automático)
-- date (tipo de dados relacionado a data formato padrão YYYYMMDD)
create table produtos (
	codigo int primary key	auto_increment,
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

describe produtos;

insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('nescau','nestle',20220220,100,10,'UN',8.95,'setor A p15');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('suco de uva','magary',20211231,200,15,'UN',10.85,'setor S p03');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('pipoca','yoki',20220330,150,20,'PC',8.22,'setor D p05');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('leite integral','piracanjuba',20220830,50,10,'CX',5.10,'setor E p6');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('farinha de trigo','renata',20210830,200,15,'PC',5.99,'setor F p3');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('arroz integral','camil',20210730,500,20,'PC',4.39,'setor H p4');
insert into produtos(produto,fabricante,dataval,estoque,estoquemin,medida,valor,loc)
value ('feijao carioca','camil',20220131,10,30,'PC',7.39,'setor H p8');

select * from produtos;

-- Inventário do estoque (total)
select sum(valor * estoque) as total from produtos;
select sum(estoque) as total from produtos;

-- Relatório de reposição do estoque 1
select * from produtos where estoque < estoquemin;
select * from produtos where estoque > estoquemin;

-- Relatório de reposição do estoque 2
-- date_format (irá formatar a exibição da data)
-- %d (dia) %m(mês) %y (dois dígitos) %Y (quatro dígitos)
select codigo as código,produto,date_format(dataval,'%d/%m/%Y') as data_validade,
estoque,estoquemin as estoque_mínimo from produtos where estoque < estoquemin;

-- Relatório de produtos vencidos 1 (validade dos produtos)
select codigo as código,produto,date_format(dataval,'%d/%m/%Y') as data_validade
from produtos;

-- Relatório de validade produtos 2
-- datediff() retorna a diferença em dias de duas datas
-- curdate() data autal
select codigo as código,produto,date_format(dataval, '%d/%m/%Y') as data_validade,
datediff(dataval,curdate()) as dias_restantes from produtos;

select * from produtos;

-- Fazer UPDATE
update produtos set estoquemin =15 where codigo = 3;
update produtos set produto = 'nescau2.0', fabricante ='nestle*',dataval=20220330,estoque=250, estoquemin=20, valor=5.99 where codigo = 1;
 
 select * from produtos;
 
 -- DELETE UM INTEM
delete from produtos where codigo = 6;

select * from produtos;
-- decimal (Tipo de dados numérico não inteiro)

create table pedidos(
	pedido int primary key auto_increment,
    dataped timestamp default current_timestamp,
    totalped decimal(10,2),
    idcli int not null,
    foreign key(idcli) references clientes(idcli)
);

insert into pedidos(idcli) values(1);
select * from pedidos;

-- abertura do pedido
select 
pedidos.pedido,
date_format(pedidos.dataped,'%d/%m/%Y - H%:%i') as data_pedido,
clientes.nome as cliente, clientes.email as e_mail
from pedidos inner join clientes
on pedidos.idcli = clientes.idcli;

create table carrinho(
	pedido int not null,
    codigo int not null,
    quantidade int not null,
	foreign key (pedido) references pedidos(pedido),
    foreign key (codigo) references produtos(codigo)
);

use dbloja;
    
select * from carrinho;

-- inserindo produtos no carrinho
insert into carrinho(pedido,codigo,quantidade)
	values (1,1,5);
insert into carrinho(pedido,codigo,quantidade)
	values (1,2,4);
    
select * from carrinho;

-- exibir o carrinho
select
pedidos.pedido,
carrinho.codigo as Código,
produtos.produto,
carrinho.quantidade as Quantidade,
produtos.valor as Valor,
produtos.valor * carrinho.quantidade as Sub_Total
from (carrinho inner join pedidos on carrinho.pedido = pedidos.pedido)
inner join produtos on carrinho.codigo = produtos.codigo;

-- total do carrinho
select sum(produtos.valor * carrinho.quantidade) as Total
from carrinho inner join produtos on carrinho.codigo = produtos.codigo;

-- atualizar o banco de dados apos o fechanento do pedido
update carrinho
inner join produtos
on carrinho.codigo = produtos.codigo
set produtos.estoque = produtos.estoque - carrinho.quantidade
where carrinho.quantidade >0;

select * from produtos;

-- repondo estoque
insert into produtos(codigo,produto,estoque)
	values (1,1,10);
insert into produtos(codigo,produto,estoque)
	values (1,2,10);
    
select * from produtos;

use dbloja;