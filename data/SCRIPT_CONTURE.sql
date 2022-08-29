CREATE DATABASE IF NOT EXISTS conture;
USE conture;

-- OK
CREATE TABLE IF NOT EXISTS situacao_atual(
id_situacao_atual INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45),
PRIMARY KEY (id_situacao_atual)
);

-- OK
CREATE TABLE IF NOT EXISTS usuario(
id_usuario INT NOT NULL AUTO_INCREMENT,
email VARCHAR(80) NOT NULL,
senha NVARCHAR(18) NOT NULL,
nome NVARCHAR(45) NOT NULL,
sobrenome NVARCHAR(60) NOT NULL,
cpf CHAR(11) NOT NULL,
genero CHAR(1) NOT NULL,
data_nascimento TIMESTAMP NOT NULL,
estado_civil CHAR(1) NOT NULL,
telefone CHAR(11) NOT NULL,
cep CHAR(8) NOT NULL,
data_cadastro TIMESTAMP NOT NULL,
removido TINYINT NOT NULL,
fk_situacao_atual INT NOT NULL,
PRIMARY KEY (id_usuario),
FOREIGN KEY (fk_situacao_atual) REFERENCES situacao_atual (id_situacao_atual)
);

-- OK
CREATE TABLE IF NOT EXISTS categoria_produto(
id_categoria_produto INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
PRIMARY KEY(id_categoria_produto)
);

-- OK
CREATE TABLE IF NOT EXISTS produto_doacao(
id_produto_doacao INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(60) NOT NULL,
marca VARCHAR(60) NOT NULL,
modelo VARCHAR(60) NOT NULL,
descricao VARCHAR(400) NOT NULL,
imagem_principal MEDIUMBLOB NOT NULL,
defeito TINYINT NOT NULL,
entrega TINYINT NOT NULL,
quantidade_visualizacao TINYINT NOT NULL,
data_criacao DATETIME NOT NULL,
data_conclusao DATETIME,
`status` TINYINT NOT NULL,
removido TINYINT NOT NULL,
fk_doador INT,
fk_categoria_produto INT,
FOREIGN KEY(fk_doador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_categoria_produto) REFERENCES categoria_produto(id_categoria_produto),
PRIMARY KEY(id_produto_doacao, fk_doador)
);

-- OK
CREATE TABLE IF NOT EXISTS `match`(
id_match INT NOT NULL AUTO_INCREMENT,
match_porcentagem DECIMAL(5,2),
data_interesse TIMESTAMP NOT NULL,
`status` CHAR(1) NOT NULL,
visualizado TINYINT NOT NULL,
fk_produto_doacao INT,
fk_donatario INT,
FOREIGN KEY(fk_produto_doacao) REFERENCES produto_doacao(id_produto_doacao),
FOREIGN KEY(fk_donatario) REFERENCES usuario(id_usuario),
PRIMARY KEY(id_match)
);

-- OK
CREATE TABLE IF NOT EXISTS avaliacao(
id_avaliacao INT NOT NULL AUTO_INCREMENT,
valor INT NOT NULL,
comentario VARCHAR(300),
`data` TIMESTAMP NULL,
fk_match INT,
FOREIGN KEY(fk_match) REFERENCES `match`(id_match),
PRIMARY KEY(id_avaliacao)
);

-- OK
CREATE TABLE IF NOT EXISTS tipo_reporte(
id_tipo_reporte INT NOT NULL AUTO_INCREMENT,
nome VARCHAR(45) NOT NULL,
PRIMARY KEY(id_tipo_reporte)
);

-- OK
CREATE TABLE IF NOT EXISTS reporte(
id_reporte INT NOT NULL AUTO_INCREMENT,
`data` TIMESTAMP NOT NULL,
fk_reportador INT,
fk_reportado INT,
fk_tipo_reporte INT,
FOREIGN KEY(fk_reportador) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_reportado) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_tipo_reporte) REFERENCES tipo_reporte(id_tipo_reporte),
PRIMARY KEY(id_reporte)
);

-- OK
CREATE TABLE IF NOT EXISTS chat_direto(
id_chat_direto INT NOT NULL AUTO_INCREMENT,
fk_usuario_remetente INT,
fk_usuario_destinatario INT,
FOREIGN KEY(fk_usuario_remetente) REFERENCES usuario(id_usuario),
FOREIGN KEY(fk_usuario_destinatario) REFERENCES usuario(id_usuario),
PRIMARY KEY(id_chat_direto)
);

-- OK
CREATE TABLE IF NOT EXISTS mensagem(
id_mensagem INT NOT NULL AUTO_INCREMENT,
visualizado TINYINT NOT NULL,
`data` TIMESTAMP NOT NULL,
mensagem NVARCHAR(200) NOT NULL,
fk_chat_direto INT,
FOREIGN KEY(fk_chat_direto) REFERENCES chat_direto(id_chat_direto),
PRIMARY KEY(id_mensagem)
);

-- OK
CREATE TABLE IF NOT EXISTS mensagem_grupo(
id_mensagem_grupo INT NOT NULL AUTO_INCREMENT,
mensagem NVARCHAR(200) NOT NULL,
`data` TIMESTAMP NOT NULL,
fk_usuario INT NOT NULL,
fk_produto_doacao INT NOT NULL,
fk_mensagem_pricipal INT,
PRIMARY KEY(id_mensagem_grupo),
FOREIGN KEY(fk_usuario) REFERENCES usuario(id_usuario)
);

-- OK
CREATE TABLE IF NOT EXISTS preferencia_donatario(
id_preferencia_donatario INT NOT NULL AUTO_INCREMENT,
faixa_etaria CHAR(1) NOT NULL,
estado_civil VARCHAR(1) NOT NULL,
grau_escolaridade CHAR(1) NOT NULL,
fk_situacao_atual INT,
fk_produto_doacao INT,
FOREIGN KEY(fk_situacao_atual) REFERENCES situacao_atual(id_situacao_atual),
FOREIGN KEY(fk_produto_doacao) REFERENCES produto_doacao(id_produto_doacao),
PRIMARY KEY(id_preferencia_donatario)
);

-- OK
CREATE TABLE IF NOT EXISTS imagem_usuario(
id_imagem_usuario INT NOT NULL AUTO_INCREMENT,
imagem_usuario MEDIUMBLOB NOT NULL,
tipo_imagem CHAR(1) NOT NULL,
fk_usuario INT NOT NULL,
PRIMARY KEY (id_imagem_usuario),
FOREIGN KEY (fk_usuario) REFERENCES usuario (id_usuario)
);