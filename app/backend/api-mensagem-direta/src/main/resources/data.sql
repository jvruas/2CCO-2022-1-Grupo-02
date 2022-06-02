insert into chat_direto (FK_USUARIO_DESTINATARIO, FK_USUARIO_REMETENTE) values
                        (1,1),
                        (2,2),
                        (3,3);

insert into mensagem(DATA,MENSAGEM,VISUALIZADO,FK_CHAT_DIRETO_ID_CHAT_DIRETO) values
                    ('2022-10-01T14:50:27.886+00:00','Boa Tarde!!',true,1),
                    ('2022-10-01T14:55:27.886+00:00','Bom Tarde, gostou do notebook?',true,1),
                    ('2022-08-01T14:50:27.886+00:00','Qual é o tipo da entrada do carregador?',true,2),
                    ('2022-08-01T15:20:27.886+00:00','O tipo do carregador é C',true,2),
                    ('2022-09-01T18:50:27.886+00:00','Bom Noite, o computador tem quanto de memória RAM?',true,3),
                    ('2022-09-01T19:10:27.886+00:00','Boa Noite, O comptador possui 6Gbs',true,3);

