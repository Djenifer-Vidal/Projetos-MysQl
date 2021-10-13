/** 
Assistencia tecnica
@author Djeniffer Vidal
*/

create database dbinfox;
use dbinfox;
create table usuarios(
id int primary key auto_increment,
usuario varchar(50) not null,
login varchar(50) not null unique,
senha varchar(250) not null,
perfil varchar(50) not null
);
describe usuarios;

-- a linha abaixo insere uma senha com criptografia
insert into usuarios(usuario,login,senha,perfil) values ('Gabriel Almeida', 'admin', md5('123456'), 'administrador');
insert into usuarios(usuario,login,senha,perfil) values ('Kely Cristina', 'Kely', md5('1234567'),'operador');
insert into usuarios(usuario,login,senha,perfil) values ('Joao Marcos', 'Joao', md5('1234567'),'operador');

select *from usuarios;

-- selecionando o usuario e sua senha (tela e login)
select * from usuarios where login='admin' and senha=md5('123456');

update usuarios set usuario='Kely Cristina dos santos', login='kely.santos', senha=md5('12345'), perfil='operador' where id=2;

-- CHAR tipo de dados que aceita strig de caractere não variaveis
create table cliente(
idcli int primary key auto_increment,
nome varchar(50) not null,
cep char(8),
endereco varchar(50) not null,
numero varchar(10) not null,
complemento varchar(30),
bairro varchar(50) not null,
cidade varchar(50) not null,
uf char(2) not null,
fone varchar(11) not null
);

insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Joaquina Santos', 0101-0101, 'Rua Santa','15', 'casa02','Vila Bela', 'São Paulo', 'SP',1123232-3232);
insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Samantha Macedo', 0202-0202, 'Rua Doze','197', 'NG', 'Satelite','São Paulo','SP',1195252-5252);
insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Camila Silva', 0303-0303, 'Avenida Estrela','12357', 'apt solarius bolo 2', 'Correa','Rio de Janeiro','RJ',1595458-9000);
insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Pedro João', 0404-0404, 'Alameda Joao Ferraz','12258', 'CS 2', 'Santa Barbara','São Paulo','SP',92222-3333);
insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Kevim Ferreira', 0505-0505, 'Rua Marcelos','579', 'casa 03', 'Vila Carrão','Pernambuco','PE',3591111-1111);
insert into cliente(nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values ('Juliana c', 0606-0606, 'Rua Marcelos','582', 'casa 01', 'Vila Carrão','Pernambuco','PE',3591111-1111);

select *from cliente;

-- foreign key cria um relacionamento de 1 para muitos 
create table tbos (
 os int primary key auto_increment,
 dataos timestamp default current_timestamp,
 tipo varchar(20) not null,
 statusos varchar(30) not null,
 equipamento varchar(200) not null,
 defeito varchar(200) not null,
 tecnico varchar(50),
 valor decimal(10.22),
 idcli int not null,
 foreign key(idcli) references cliente(idcli)
);

describe tbos;
insert into tbos(tipo,statusos,equipamento,defeito,idcli) values ('Orçamento','Bancada','Notebook Lenovo G90','Não liga',1);
insert into tbos(tipo, statusos, equipamento, defeito, tecnico, valor, idcli) values ('Orçamento', 'Aguardando aprovação', 'Impressora hp2020', 'Papel Preso', 'Robson', 300, 1);
insert into tbos(tipo, statusos, equipamento, defeito, tecnico, valor, idcli) values ('Orçamento', 'Retirado ', 'PC Dell Vostro', 'Não liga', 'Camila', 200, 3);
insert into tbos(tipo, statusos, equipamento, defeito, tecnico, valor, idcli) values ('Serviço', 'Retirado', 'Tablet Multilaser Mts 20', 'Tela quebrada', 'Joao Marcos', 250, 4);
insert into tbos(tipo, statusos, equipamento, defeito, tecnico, valor, idcli) values ('Serviço', ' Aprovado', 'Impressora hp2020', 'Papel Preso', 'Robson', 300, 2);
insert into tbos(tipo, statusos, equipamento, defeito, tecnico, valor, idcli) values ('Serviço', 'Aprovado', 'PC Dell Vostro', 'Papel Preso', 'Robson', 300, 5);

select *from tbos;

-- inner join --> União de tabelas relacionadas para consultas e updates
-- relatorio 1
select * from tbos inner join cliente on tbos.idcli= cliente.idcli;

-- relatorio 2 
select tbos.equipamento,tbos.defeito,tbos.statusos as status_os,tbos.valor,cliente.nome, cliente.fone 
from tbos inner join cliente on tbos.idcli= cliente.idcli where statusos = 'Aguardando Aprovação';

select tbos.equipamento,tbos.defeito,tbos.statusos as status_os,tbos.valor,cliente.nome, cliente.fone 
from tbos inner join cliente on tbos.idcli= cliente.idcli where statusos = 'Aprovado';

-- relatorio 3
select tbos.os, date_format(dataos, '%d/%m/%Y - %H:%i') as status_os, tbos.equipamento, tbos.defeito, tbos.valor, cliente.nome 
from tbos inner join cliente on tbos.idcli= cliente.idcli where statusos = 'Retirado';

-- Relatorio 4
select sum(valor) as faturamento from tbos where statusos ='Retirado'; 



