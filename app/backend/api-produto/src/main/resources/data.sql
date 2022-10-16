INSERT INTO categoria_produto(nome) values
('NOTEBOOK'),
('TABLET'),
('SMARTPHONE');
--('MESA DIGITALIZADORA'),
--('HEADSET');

--insert into produto_doacao(fk_doador,nome,marca,modelo,descricao,defeito,entrega,categoria_produto)values
--(1,'notebook lenovo','lenovo','ideapad gaming','Nootebook em perfeito estado',false,false,1);


	insert into produto_doacao(DATA_CONCLUSAO ,DATA_CRIACAO ,DEFEITO ,DESCRICAO ,ENTREGA ,FK_DOADOR ,IMAGEM_PRINCIPAL ,MARCA ,MODELO ,NOME ,QUANTIDADE_VISUALIZACAO ,REMOVIDO ,STATUS ,FK_CATEGORIA_PRODUTO )values
	('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado perfeito de alta perfomance',false,1,' ','Lenovo','Ideapad gaming 3i','Lenovo gaming 3i',256,false,'t',1),
	('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet em alta qualidade',false,1,' ','Samsung','galaxy tab 3','Samsung tablet 3',250,false,'f',2),
	('2022-12-08 00:00:00','2022-10-08 00:00:00',true,'Iphone com a tela trincada',false,1,' ','Apple','Apple apple 6s','Apple apple 6s',100,false,'f',3),
	('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Notebook perfeito',false,1,' ','Apple','Apple macbook','Apple macbook',100,false,'f',1);

insert into MATCH_DOACAO (DATA_INTERESSE ,FK_DONATARIO ,MATCH_PORCENTAGEM ,STATUS ,VISUALIZADO ,FK_PRODUTO_DOACAO ) values
('2022-10-10',2,80.0,true,true,1);

insert into avaliacao(COMENTARIO ,DATA ,VALOR ,FK_MATCH ) values('Produto de ótima qualidade, muito bom negociador','2022-10-10',4.9,1);


