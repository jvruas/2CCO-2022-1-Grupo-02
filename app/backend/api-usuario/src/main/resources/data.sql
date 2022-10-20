insert into situacao_atual (nome) values
              	       ('Todas situações atuais'),
              	       ('Aposentado'),
                       ('Trabalho Formal'),
                       ('Trabalho Informal'),
                       ('Incapacitado'),
                       ('Estudante'),
                       ('Pensionista'),
                       ('Desempregado Sem Renda'),
                       ('Desempregado Com Renda'),
                       ('Do Lar');

insert into tipo_reporte (nome) values
                       ('Assédio'),
                       ('Discurso de ódio'),
                       ('Informações do perfil'),
                       ('Nudez ou atividade sexual'),
                       ('Produto falso'),
                       ('Spam');


--insert into usuario (CEP,CPF,DATA_NASCIMENTO,EMAIL,ESTADO_CIVIL,GENERO,GRAU_ESCOLARIDADE,REMOVIDO,NOME,SENHA,SOBRENOME,TELEFONE,SITUACAO_ATUAL_ID_SITUACAO_ATUAL)values
--('17450970','46002259880','2003-04-10','playk.games@outlook.com','S','M','A','Igor','igor1234','Sérgio','11973484561',4);

insert into usuario(CEP,CODIGO,DATA_CADASTRO,DATA_NASCIMENTO,EMAIL,ESTADO_CIVIL,GENERO,GRAU_ESCOLARIDADE,NOME,REMOVIDO,SENHA,SOBRENOME,TELEFONE,VERIFICADO,FK_SITUACAO_ATUAL)values
('08071080','123768','2022-09-10',' 2003-04-10 00:00:00','igor.sergio@gmail.com','S','M','A','Igor',false,'senha123','Sérgio','11973484561',true,1),
('08071050','123769','2022-08-10',' 2003-05-15 00:00:00','joao.ruas@gmail.com','S','M','A','João',false,'senha123','Victor','11945666666',true,2),
('08071030','123770','2022-08-10',' 1997-07-22 00:00:00','flavia.olsi@gmail.com','S','S','A','Flávia',false,'senha123','Oliveira','11945666655',true,2),
('08071070','123771','2022-08-10',' 2003-07-10 00:00:00','matheus.fran@gmail.com','S','M','A','Matheus',false,'senha123','Franciscone','11945666610',true,2),
('13144550','123772','2022-08-10',' 2003-10-15 00:00:00','gabriel.saraiva@gmail.com','S','M','A','Gabriel',false,'senha123','Saraiva','11945666600',true,2);





