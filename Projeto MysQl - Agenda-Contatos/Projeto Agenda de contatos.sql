
/**
Agenda de Contatos
@autor Djeniffer Vidal
*/
-- exibir banco de dados do servidor
show databases;
-- Criar um novo bancos de dados
create database dbageda;
-- excluir um banco de dados
drop database dbteste;
-- Selecionar o banco de dados
use dbagenda;

-- Verificar tabelas esistentes 
show tables;
-- Criando uma tabela
-- Toda tabela precisa ter uma chave primaria (PK)
-- int (tipo de dados --> numeros inteiros)
-- Primary Key --> tranformar este campo em chave PK
-- auto_increment --> numeração automatica
-- id nome que escolhi para armazenar a chave PK
-- varchar (tipo de dados cadeia de caracteres, String)
-- not null -> preenchimento obrigatorio
-- unique --> não permite valores duplicados na tabela
create table contatos(
id int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar(50) unique
);	
-- descrição da tabela
describe contatos;

-- alterar o nome de um campo na tabela
alter table contatos change nome contato varchar(50) not null;

-- adicionar um novo campo na tabela
alter table contatos add column obs varchar(250);

-- adicionar um novo campo (coluna ) em um local especifico na tabela
alter table contatos add column fone2 varchar(15) after fone;

-- modificar o tipo de dados e/ou validação na coluna
alter table contatos modify column fone2 int;
alter table contatos modify column email varchar(100) not null;

-- excluir uma coluna da tabela
alter table contatos drop column obs;

-- **CRUD (create Read UPdate Delete)
-- operações basicas do banco de dados

-- CRUD CREATE - criar dados
insert into contatos (nome, fone, email) values ('Djeniffer_vidal','958703829','djeniffervidal@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao', '156164618', 'joaomarcola@hotmail.com');
insert into contatos (nome, fone, email) values ('Gabriel','1561646654',' ');
insert into contatos (nome, fone, email) values ('Victor_Marcelo','792254689','pedorvisvoterl@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao Alfredo','8654643484','mileumautilidades@hotmail.com');
insert into contatos (nome, fone, email) values ('Joao pedro','99999999','joaopedro@hotmail.com');
insert into contatos (nome, fone, email) values ('Daniela','111111111','daniela12@hotmail.com');
insert into contatos (nome, fone, email) values ('vitoria','22222222','vitoria582@hotmail.com');

-- **CRUD READ - pesquisar dados
-- Seleionar todos os regitros (dados) da tabela
select *from contatos;

-- Selecionar colunas especificas da tabela
select nome, fone from contatos;
select nome from contatos;


-- Selecionar colunas em ordem crescente e decrescente
select *from contatos order by nome;
-- Ordem decrescente
select id, nome from contatos order by id desc;
-- Ordem crescente
select id, nome from contatos order by id asc;


-- Uso de filtros
Select *from contatos where id = 1;
select *from contatos where nome = 'Djeniffer_vidal';
-- Realizar pesquisa por letras em comum
select *from contatos where nome like 'ga%';


-- Selecionar todos os registros (dados) da tabela
select *from contatos;

-- **CRUD UPDATE - editar contato 
-- ATENCÃO !!!!!!!!!!!! NÃO ESQUECE DE USAR O ID PARA FAZER ALTERAÇÕES, USAR WHERE SSSSSSEEEEEEMMMMPPPPPPRRRRREEEEEEEEEEE
update contatos set fone= '5464468798' where id = 10;
update contatos set nome= 'Djeniffer Vidal', fone= '88888-8888', email= 'djeniffervidall@hotmail.com' where id=1;
update contatos set nome= 'Isaque', fone= '22222-2222', email= 'isaquesanttos@hotmail.com' where id=10;

-- **CRUD Delete - exluir dados
-- Atenção!! Não esquecer de usar o WHERE --- IMPORTANTE
delete from contatos where id=10;




