CREATE TABLE ATUACAO(
    regiao varchar(255) primary key,
    estados array
);

CREATE TABLE VENDEDOR(
    id bigint auto_increment primary key,
    data_inclusao date,
    nome varchar(255),
    telefone varchar(255),
    idade int,
    cidade varchar(255),
    estado varchar(255),
    regiao varchar(255)
);

ALTER TABLE VENDEDOR
    ADD FOREIGN KEY (regiao)
    REFERENCES ATUACAO(regiao);