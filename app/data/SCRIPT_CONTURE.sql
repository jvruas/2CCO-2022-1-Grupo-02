-- DROP SCHEMA conture;

CREATE SCHEMA IF NOT EXISTS conture;
USE conture;

CREATE TABLE IF NOT EXISTS situacao_atual(
id_situacao_atual INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
PRIMARY KEY(id_situacao_atual)
);

CREATE TABLE IF NOT EXISTS usuario(
id_usuario INT NOT NULL AUTO_INCREMENT,
email VARCHAR(80) NOT NULL,
senha VARCHAR(18) NOT NULL,
nome VARCHAR(45) NOT NULL,
sobrenome VARCHAR(60) NOT NULL,
cpf VARCHAR(45) NOT NULL,
genero CHAR(1) NOT NULL,
data_nascimento DATE NOT NULL,
estado_civil VARCHAR(5) NOT NULL,
telefone CHAR(11) NOT NULL,
cep CHAR(8) NOT NULL,
data_cadastro DATE NOT NULL,
escolaridade VARCHAR(5) NOT NULL,
fk_situacao_atual INT,
PRIMARY KEY(id_usuario),
FOREIGN KEY(fk_situacao_atual) REFERENCES situacao_atual(id_situacao_atual)
);

CREATE TABLE IF NOT EXISTS categoria_produto(
id_categoria_produto INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45),
PRIMARY KEY(id_categoria_produto)
);

CREATE TABLE IF NOT EXISTS produto_doacao(
id_produto_doacao INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(60) NOT NULL,
marca VARCHAR(60) NOT NULL,
modelo VARCHAR(60) NOT NULL,
descricao VARCHAR(400) NOT NULL,
defeito TINYINT NOT NULL,
status_produto TINYINT NOT NULL,
entrega TINYINT NOT NULL,
quantidade_visualizacao TINYINT NOT NULL,
data_criacao DATETIME NOT NULL,
data_conclusao DATETIME,
fk_doador INT,
fk_categoria_produto INT,
FOREIGN KEY(fk_doador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_categoria_produto) REFERENCES categoria_produto(id_categoria_produto),
PRIMARY KEY(id_produto_doacao, fk_doador)
);

CREATE TABLE IF NOT EXISTS `match`(
match_porcentagem DECIMAL(5,2),
data_interesse DATETIME NOT NULL,
status_match CHAR(1) NOT NULL,
fk_doador INT,
fk_produto_doacao INT,
fk_donatario INT,
FOREIGN KEY(fk_doador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_produto_doacao) REFERENCES produto_doacao(id_produto_doacao),
FOREIGN KEY(fk_donatario) REFERENCES usuario(id_usuario),
PRIMARY KEY(fk_doador, fk_produto_doacao, fk_donatario)
);

CREATE TABLE IF NOT EXISTS avaliacao(
valor INT NOT NULL,
comentario VARCHAR(300),
data_avaliacao DATETIME NOT NULL,
fk_doador INT,
fk_produto_doacao INT,
fk_donatario INT,
FOREIGN KEY(fk_doador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_produto_doacao) REFERENCES produto_doacao(id_produto_doacao),
FOREIGN KEY(fk_donatario) REFERENCES usuario(id_usuario),
PRIMARY KEY(fk_doador, fk_produto_doacao, fk_donatario)
);

CREATE TABLE IF NOT EXISTS notificacao(
id_notificacao INT NOT NULL AUTO_INCREMENT,
data_notificacao DATETIME NOT NULL,
status_notificacao TINYINT NOT NULL,
tipo_notificacao VARCHAR(45) NOT NULL,
corpo_notificacao VARCHAR(60) NOT NULL,
fk_destinatario INT,
fk_remetente INT,
FOREIGN KEY(fk_destinatario) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_remetente) REFERENCES usuario(id_usuario),
PRIMARY KEY(id_notificacao, fk_destinatario, fk_remetente)
);

CREATE TABLE IF NOT EXISTS tipo_reporte(
id_tipo_reporte INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
PRIMARY KEY(id_tipo_reporte)
);

CREATE TABLE IF NOT EXISTS reporte(
id_reporte INT,
data_reporte DATETIME NOT NULL,
fk_reportador INT,
fk_reportado INT,
fk_tipo_reporte INT,
FOREIGN KEY(fk_reportador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_reportado) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_tipo_reporte) REFERENCES tipo_reporte(id_tipo_reporte),
PRIMARY KEY(id_reporte, fk_reportado, fk_reportador)
);

CREATE TABLE IF NOT EXISTS chat_direto(
id_chat_direto INT NOT NULL AUTO_INCREMENT,
fk_usuario_remetente INT,
fk_usuario_destinatario INT,
FOREIGN KEY(fk_usuario_remetente) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_usuario_destinatario) REFERENCES usuario(id_usuario),
PRIMARY KEY(id_chat_direto)
);

CREATE TABLE IF NOT EXISTS mensagem(
id_mensagem INT NOT NULL AUTO_INCREMENT,
data_mensagem DATETIME NOT NULL,
mensagem VARCHAR(200) NOT NULL,
fk_chat_direto INT,
FOREIGN KEY(fk_chat_direto) REFERENCES chat_direto(id_chat_direto),
PRIMARY KEY(id_mensagem)
);

CREATE TABLE IF NOT EXISTS preferencia_donatario(
genero CHAR(1) NOT NULL,
faixa_etaria CHAR(10) NOT NULL,
estado_civil VARCHAR(5) NOT NULL,
grau_escolaridade VARCHAR(5) NOT NULL,
fk_situacao_atual INT,
fk_produto_doacao INT,
fk_doador INT,
FOREIGN KEY(fk_situacao_atual) REFERENCES situacao_atual(id_situacao_atual),
FOREIGN KEY(fk_produto_doacao, fk_doador) REFERENCES produto_doacao(id_produto_doacao, fk_doador),
PRIMARY KEY(fk_doador, fk_produto_doacao)
);

CREATE TABLE IF NOT EXISTS pergunta(
id_pergunta INT NOT NULL AUTO_INCREMENT,
data_pergunta DATETIME NOT NULL,
mensagem VARCHAR(200) NOT NULL,
fk_donatario INT,
fk_doador INT,
fk_produto_doacao INT,
FOREIGN KEY(fk_donatario) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_doador, fk_produto_doacao) REFERENCES produto_doacao(fk_doador, id_produto_doacao),
PRIMARY KEY(id_pergunta, fK_doador, fk_produto_doacao)
);

CREATE TABLE IF NOT EXISTS resposta(
id_resposta INT NOT NULL AUTO_INCREMENT, 
data_resposta DATETIME NOT NULL,
mensagem VARCHAR(200) NOT NULL,
fk_pergunta INT,
fk_doador INT,
fk_produto_doacao INT,
FOREIGN KEY (fk_pergunta, fk_doador, fk_produto_doacao) REFERENCES pergunta(id_pergunta, fk_doador, fk_produto_doacao),
PRIMARY KEY(id_resposta)
);

CREATE TABLE IF NOT EXISTS desligamento_conta(
id_desligamento_reposta INT NOT NULL AUTO_INCREMENT,
motivo_desligamento_reposta VARCHAR(45) NOT NULL,
PRIMARY KEY(id_desligamento_reposta)
);