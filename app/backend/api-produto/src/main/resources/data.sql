insert into categoria_produto(nome)
values
	('CELULAR'),
	('DESKTOP'),
	('HEADSET'),
	('IMPRESSORA'),
	('MONITOR'),
	('MOUSE'),
	('NOTEBOOK'),
	('TABLET'),
    ('TECLADO');


insert into produto_doacao(DATA_CONCLUSAO ,DATA_CRIACAO ,DEFEITO ,DESCRICAO ,ENTREGA ,FK_DOADOR ,IMAGEM_PRINCIPAL ,MARCA ,MODELO ,NOME ,QUANTIDADE_VISUALIZACAO ,REMOVIDO ,STATUS ,FK_CATEGORIA_PRODUTO )values
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado perfeito de alta perfomance',false,1,' ','Lenovo','Ideapad gaming 3i','Lenovo gaming 3i',256,false,'t',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet em alta qualidade',false,1,' ','Samsung','galaxy tab 3','Samsung tablet 3',250,false,'t',8),
('2022-12-08 00:00:00','2022-10-08 00:00:00',true,'Iphone com a tela trincada',false,2,' ','Apple','Apple apple 6s','Apple apple 6s',100,false,'f',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Notebook perfeito',false,2,' ','Apple','Apple macbook','Apple macbook',100,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado perfeito de alta perfomance',false,3,' ','Lenovo','Ideapad gaming 3i','Lenovo gaming 3i',216,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet em alta qualidade',false,3,' ','Samsung','galaxy tab 3','Samsung tablet 3',230,false,'f',8),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Iphone perfeito',false,4,' ','Apple','Apple apple xr','Apple apple xr',20,false,'t',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',true,'Iphone com a tela trincada',false,4,' ','Apple','Apple apple 7s','Apple apple 7s',12,false,'f',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado perfeito',false,1,' ','HP','hp i3','hp i3',1234,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado perfeito e muito conservado',false,1,' ','HP','hp i5','hp i5',2122,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em estado bom',false,1,' ','Samsung','Samsung i5','Samsung i5',216,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador muito bom',false,1,' ','Lenovo','Thinkpad i5','Thinkpad i5',206,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador em Ótima qualidade',false,5,' ','HP','hp i7','hp i7',212,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador muito bom de jogos',false,5,' ','Alienware','Alienware i9','Alienware i9',210,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Computador muito bom para trabalhar',false,5,' ','Lenovo','Ideapad i5','Ideapad i5',106,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet muito bom',false,3,' ','Samsung','galaxy tab 2','Samsung tablet 2',200,false,'f',8),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet muito bom',false,2,' ','Apple','Ipad 9','Ipad 8',270,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'tablet muito bom de jogos',false,5,' ','Apple','Ipad 9','Ipad 8',260,false,'f',7),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Iphone perfeito',false,2,' ','Apple','Apple apple 11','Apple apple 11',250,false,'f',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Iphone perfeito',false,1,' ','Apple','Apple apple 11','Apple apple 11',240,false,'f',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Iphone viciado',false,4,' ','Apple','Apple apple 12','Apple apple 12',230,false,'f',1),
('2022-12-08 00:00:00','2022-10-08 00:00:00',false,'Iphone viciado',false,5,' ','Apple','Apple apple 13','Apple apple 13',210,false,'f',1);


insert into MATCH_DOACAO (DATA_INTERESSE ,FK_DONATARIO ,MATCH_PORCENTAGEM ,STATUS ,VISUALIZADO ,FK_PRODUTO_DOACAO ) values
('2022-10-10',2,80.0,true,true,1),
('2022-10-15',5,60.0,true,true,8),
('2022-10-12',3,75.0,true,true,2),
('2022-10-12',5,75.0,true,true,7);
insert into avaliacao(COMENTARIO ,DATA ,VALOR ,FK_MATCH ) values
('Produto de ótima qualidade, muito bom negociador','2022-10-10',4.9,1),
('Produto de ótima qualidade, veio o produto embalado, além de estar em ótimo estado.','2022-10-09',4,2),
('Produto de ótima qualidade, veio o produto em ótimo estado e foi muito bom negociar.','2022-10-10',4,3),
('Celular veio com todo os acessorios e em perfeito estado, foi muito bom negociar com você matheus!.','2022-10-10',4,4	);
