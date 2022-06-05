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


insert into usuario (CEP,CPF,DATA_NASCIMENTO,EMAIL,ESTADO_CIVIL,GENERO,GRAU_ESCOLARIDADE,REMOVIDO,NOME,SENHA,SOBRENOME,TELEFONE,SITUACAO_ATUAL_ID_SITUACAO_ATUAL)values
('17450970','46002259880','2003-04-10','playk.games@outlook.com','S','M','A','Igor','igor1234','Sérgio','11973484561',4);
