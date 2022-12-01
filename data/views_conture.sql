-- View reporte 

INSERT INTO situacao_atual VALUES
              	       (0,'Todas situações atuais'),
              	       (0,'Aposentado'),
                       (0,'Trabalho Formal'),
                       (0,'Trabalho Informal'),
                       (0,'Incapacitado'),
                       (0,'Estudante'),
                       (0,'Pensionista'),
                       (0,'Desempregado Sem Renda'),
                       (0,'Desempregado Com Renda'),
                       (0,'Do Lar');

INSERT INTO usuario(email, senha, nome, sobrenome, cpf, genero, data_nascimento, estado_civil, telefone, cep, data_cadastro, grau_escolaridade, removido, fk_situacao_atual) VALUES 
('igor.sergio@gmail.com','senha123','Igor','Sérgio','42176556810','M','2002-07-02','S','11973484561','07011040','2021-10-10 00:00:00','A',false,1);

INSERT INTO usuario(email, senha, nome, sobrenome, cpf, genero, data_nascimento, estado_civil, telefone, cep, data_cadastro, grau_escolaridade, removido, fk_situacao_atual) VALUES 
('mathfranciscone@gmail.com','senha12335@','Matheus','Franciscone','42176556810','M','2002-07-02','S','11973484561','07011040','2021-10-10 00:00:00','A',false,2);

INSERT INTO tipo_reporte (nome) VALUES
                       ('Assédio'),
                       ('Discurso de ódio'),
                       ('Informações do perfil'),
                       ('Nudez ou atividade sexual'),
                       ('Produto falso'),
                       ('Spam');

INSERT INTO reporte (data, fk_reportador, fk_reportado, fk_tipo_reporte)
VALUES ('2002-07-02', 1, 2, 2); 

CREATE OR REPLACE VIEW vw_reporte_tipo_reporte AS  
	SELECT COUNT(1) AS 'Total de Reportes', tr.nome AS 'Reporte' 
		FROM reporte r
	INNER JOIN tipo_reporte tr
		ON r.fk_tipo_reporte = tr.id_tipo_reporte 
	GROUP BY tr.nome; 

SELECT * FROM vw_reporte_tipo_reporte;

-- View Desligamento

INSERT INTO motivo_desligamento_conta (motivo) VALUES
('Não estou mais usando'),
('Plataforma não intuitiva'),
('Plataforma pouco segura');

INSERT INTO desligamento_conta(data_desligamento, fk_motivo_desligamento_conta, fk_usuario)VALUES 
('2022-10-11', 1, 2),
('2022-10-11', 1, 1),
('2022-10-11', 2, 2),
('2022-10-11', 3, 2);

CREATE OR REPLACE VIEW vw_qtd_delisgamento_motivo_delisgamento AS
	SELECT COUNT(1) AS 'Quantidade de Desligamentos', mdc.motivo AS 'Motivo'
		FROM desligamento_conta dc
	INNER JOIN motivo_desligamento_conta mdc
		ON dc.fk_motivo_desligamento_conta = mdc.id_motivo_desligamento_conta 
	GROUP BY mdc.motivo;

SELECT * FROM vw_qtd_delisgamento_motivo_delisgamento;

-- View Produtos Vendidos X Doados

INSERT INTO categoria_produto(nome) VALUES
						('NOTEBOOK'),
						('TABLET'),
						('SMARTPHONE'),
						('MESA DIGITALIZADORA'),
						('HEADSET');

INSERT INTO produto_doacao (nome, marca, modelo, descricao, imagem_principal, defeito, entrega, quantidade_visualizacao, data_criacao, data_conclusao, status, removido, fk_categoria_produto, fk_doador) VALUES
('Celular Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', 0, false, false, 2, 1);

INSERT INTO produto_doacao (nome, marca, modelo, descricao, imagem_principal, defeito, entrega, quantidade_visualizacao, data_criacao, data_conclusao, status, removido, fk_categoria_produto, fk_doador) VALUES
('Celular Samsung Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', '2022-10-10', false, false, 2, 1),
('Celular Apple Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', 0, false, false, 3, 1),
('Tablet Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', null, false, false, 2, 1),
('Notebook Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', '2022-10-10', false, false, 2, 1),
('Smartphone Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', 0, false, false, 1, 1),
('Headset Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', 0, false, false, 1, 1),
('Celular Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 200, '2022-10-07', '2022-10-10', false, false, 2, 1);

INSERT INTO produto_doacao (nome, marca, modelo, descricao, imagem_principal, defeito, entrega, quantidade_visualizacao, data_criacao, data_conclusao, status, removido, fk_categoria_produto, fk_doador) VALUES
('Celular Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 500, '2022-10-07', '2018-10-03', false, false, 5, 1);

INSERT INTO produto_doacao (nome, marca, modelo, descricao, imagem_principal, defeito, entrega, quantidade_visualizacao, data_criacao, data_conclusao, status, removido, fk_categoria_produto, fk_doador) VALUES
('Celular Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 540, '2022-10-07', '2019-10-03', false, false, 3, 1),
('Celular Dell Inspiron 356712', 'Dell', 'Inspiron 3567', 'Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7', 'aldaksdsakdsaldkaldad', false, true, 345, '2022-10-07', '2019-10-03', false, false, 3, 1);

CREATE OR REPLACE VIEW vw_produtos_vendidos_doados AS
	SELECT cp.nome AS 'produto', pd.data_conclusao AS 'data_de_doacao',pd.quantidade_visualizacao AS 'qtd_visualizacao', COUNT(*) as 'qtd_produtos'
		FROM produto_doacao pd 
	INNER JOIN categoria_produto cp 
		ON pd.fk_categoria_produto = cp.id_categoria_produto
	WHERE pd.data_conclusao != 'NULL'
		GROUP BY cp.nome, YEAR(pd.data_conclusao)
	ORDER BY cp.nome DESC;

SELECT * FROM vw_produtos_vendidos_doados;
	
-- View qtd presente X qtd doações passado

CREATE OR REPLACE VIEW 	 AS
	SELECT DATE_FORMAT(pd.data_conclusao, '%m%Y') AS 'Mês', COUNT(*) as 'Qtd. produtos'
		FROM produto_doacao pd
	WHERE pd.data_conclusao != 'NULL'
		GROUP BY YEAR(pd.data_conclusao)
	ORDER BY pd.data_conclusao DESC;

SELECT * FROM vw_doacao_historica;
	
	
	
	