CREATE DATABASE IF NOT EXISTS teste_oscar;

create sequence categorias_sequence start with 1 increment by 1;
create sequence cores_sequence start with 1 increment by 1;
create sequence marcas_sequence start with 1 increment by 1;
create sequence modelos_sequence start with 1 increment by 1;
create sequence produtos_sequence start with 1 increment by 1;
create table categoria (id bigint not null, nome varchar(255), primary key (id));
create table cor (id bigint not null, nome varchar(255), primary key (id));
create table marca (id bigint not null, nome varchar(255), primary key (id));
create table modelo (categoria_id bigint not null, id bigint not null, marca_id bigint not null, nome varchar(255), primary key (id));
create table produto (preco float(53), tamanho integer, cor_id bigint, data_cadastro timestamp(6), id bigint not null, modelo_id bigint not null, quantidade_estoque ;bigint, primary key (id))
alter table if exists modelo add constraint FK1qunbybfv0exqex3lmvsp1byk foreign key (categoria_id) references categoria;
alter table if exists modelo add constraint FKllxq2dldvhxvb5q9csar7vdfy foreign key (marca_id) references marca;
alter table if exists produto add constraint FK6meoj4aaywchbrreafbdbuoum foreign key (cor_id) references cor;
alter table if exists produto add constraint FKeer41nyic0tshe55wred3smw9 foreign key (modelo_id) references modelo;
