CREATE DATABASE conture;
USE conture;

CREATE TABLE desligamento_conta(
	id_desligamento_conta INT AUTO_INCREMENT,
	motivo_desligamento_conta CHAR(1),
	data_desligamento TIMESTAMP,
	fk_usuario INT,
	CONSTRAINT `pk_desligamento_conta`
		PRIMARY KEY (id_desligamento_conta),
	CONSTRAINT `fk_desligamento_usuario`
		FOREIGN KEY (fk_usuario) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE categoria_produto(
	id_categoria_produto INT AUTO_INCREMENT NOT NULL,
	nome NVARCHAR(45) NOT NULL,
	CONSTRAINT `pk_categoria_produto`
		PRIMARY KEY (id_categoria_produto)
);

CREATE TABLE produto_doacao(
	id_produto_doacao INT AUTO_INCREMENT,
	nome NVARCHAR(60) NOT NULL,
	marca NVARCHAR(60) NOT NULL,
	modelo NVARCHAR(60) NOT NULL,
	descricao NVARCHAR(400) NOT NULL,
	imagem_principal VARCHAR(1400),
	defeito TINYINT NOT NULL,
	entrega TINYINT NOT NULL,
	quantidade_visualizacao INT NOT NULL,
	data_criacao TIMESTAMP NOT NULL,
	data_conclusao TIMESTAMP,
	status TINYINT NOT NULL,
	removido TINYINT NOT NULL,
	fk_categoria_produto INT NOT NULL,
	fk_doador INT NOT NULL,
	CONSTRAINT `pk_produto`
		PRIMARY KEY (id_produto_doacao),
	CONSTRAINT `fk_produto_categoria`
		FOREIGN KEY (fk_categoria_produto) REFERENCES categoria_produto (id_categoria_produto)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_produto_doador`
		FOREIGN KEY (fk_doador) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE preferencia_donatario(
	id_preferencia_donatario INT AUTO_INCREMENT NOT NULL,
	faixa_etaria CHAR(1) NOT NULL,
	estado_civil CHAR(1) NOT NULL,
	grau_escolaridade CHAR(1) NOT NULL,
	fk_situacao_atual INT NOT NULL, 
	fk_produto_doacao INT NOT NULL,
	CONSTRAINT `pk_preferencia_donatario`
		PRIMARY KEY (id_preferencia_donatario),
	CONSTRAINT `fk_situacao_atual_preferencia_donatario`
		FOREIGN KEY (fk_situacao_atual) REFERENCES situacao_atual (id_situacao_atual)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_preferencia_produto`
		FOREIGN KEY (fk_produto_doacao) REFERENCES produto_doacao (id_produto_doacao)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE imagem_produto_doacao(
	id_imagem_produto_doacao INT AUTO_INCREMENT NOT NULL,
	imagem VARCHAR(1400),
	fk_produto_doacao INT,
	CONSTRAINT `pk_imagem_produto_doacao`
		PRIMARY KEY (id_imagem_produto_doacao),
	CONSTRAINT `fk_produto_doacao`
		FOREIGN KEY (fk_produto_doacao) REFERENCES produto_doacao (id_produto_doacao)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE `match` (
	id_match INT AUTO_INCREMENT NOT NULL,
	match_porcentagem DECIMAL(5,2),
	data_interesse TIMESTAMP NOT NULL,
	status CHAR(1) NOT NULL,
	visualizado TINYINT NOT NULL,
	fk_produto_doacao INT NOT NULL,
	fk_donatario INT NOT NULL,
	CONSTRAINT `pk_match`
		PRIMARY KEY (id_match),
	CONSTRAINT `fk_match_produto_doacao`
		FOREIGN KEY (fk_produto_doacao) REFERENCES produto_doacao (id_produto_doacao)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_match_donatario`
		FOREIGN KEY (fk_donatario) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE avaliacao(
	id_avaliacao INT NOT NULL AUTO_INCREMENT,
	valor INT NOT NULL,
	comentario NVARCHAR(300),
	`data` TIMESTAMP,
	fk_match INT NOT NULL,
	CONSTRAINT `pk_avaliacao`
		PRIMARY KEY (id_avaliacao),
	CONSTRAINT `fk_avaliacao_match`
		FOREIGN KEY (fk_match) REFERENCES `match` (id_match)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE mensagem_grupo(
	id_mensagem_grupo INT NOT NULL AUTO_INCREMENT,
	mensagem NVARCHAR(200) NOT NULL,
	`data` TIMESTAMP,
	fk_usuario INT NOT NULL,
	fk_produto_doacao INT NOT NULL,
	fk_mensagem_principal INT,
	CONSTRAINT `pk_mensagem_grupo`
		PRIMARY KEY (id_mensagem_grupo),
	CONSTRAINT `fk_usuario_mensagem_grupo`
		FOREIGN KEY (fk_usuario) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_mensagem_grupo_produto`
		FOREIGN KEY (fk_produto_doacao) REFERENCES produto_doacao (id_produto_doacao)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_mensagem_principal`
		FOREIGN KEY (fk_mensagem_principal) REFERENCES mensagem_grupo (id_mensagem_grupo)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TSIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE chat_direto(
	id_chat_direto INT AUTO_INCREMENT NOT NULL,
	fk_remetente INT NOT NULL,
	fk_destinatario INT NOT NULL,
	CONSTRAINT `pk_chat_direto`
		PRIMARY KEY (id_chat_direto),
	CONSTRAINT `fk_rementente_chat`
		FOREIGN KEY (fk_remetente) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_destinatario_chat`
		FOREIGN KEY (fk_destinatario) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE mensagem(
	id_mensagem INT AUTO_INCREMENT NOT NULL,
	visualizado TINYINT NOT NULL,
	`data` TIMESTAMP NOT NULL,
	mensagem NVARCHAR(200) NOT NULL,
	fk_chat_direto INT NOT NULL,
	CONSTRAINT `pk_mensagem`
		PRIMARY KEY (id_mensagem),
	CONSTRAINT `fk_mensagem_chat`
		FOREIGN KEY (fk_chat_direto) REFERENCES chat_direto (id_chat_direto)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	start_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW START,
	end_timestamp TIMESTAMP(6) GENERATED ALWAYS AS ROW END,
	PERIOD FOR SYSTEM_TIME(start_timestamp, end_timestamp)
) WITH SYSTEM VERSIONING;

CREATE TABLE tipo_reporte(
	id_tipo_reporte INT AUTO_INCREMENT NOT NULL,
	nome NVARCHAR(45) NOT NULL,
	CONSTRAINT `pk_tipo_reporte`
		PRIMARY KEY (id_tipo_reporte)
);

CREATE TABLE reporte(
	id_reporte INT AUTO_INCREMENT NOT NULL,
	`data` TIMESTAMP NOT NULL,
	fk_reportador INT NOT NULL,
	fk_reportado INT NOT NULL,
	fk_tipo_reporte INT NOT NULL,
	CONSTRAINT `pk_reporte`
		PRIMARY KEY (id_reporte),
	CONSTRAINT `fk_reportador`
		FOREIGN KEY (fk_reportador) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_reportado`
		FOREIGN KEY (fk_reportado) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	CONSTRAINT `fk_tipo_reporte`
		FOREIGN KEY (fk_tipo_reporte) REFERENCES usuario (id_usuario)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);





