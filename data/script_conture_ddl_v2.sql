-- -----------------------------------------------------
-- Schema conture
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conture` DEFAULT CHARACTER SET utf8;
USE `conture`;
SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;
-- -----------------------------------------------------
-- Table `conture`.`situacao_atual`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`situacao_atual` (
  `id_situacao_atual` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_situacao_atual`)
);
-- -----------------------------------------------------
-- Table `conture`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(80) NULL,
  `senha` NVARCHAR(18) NULL,
  `nome` NVARCHAR(45) NULL,
  `sobrenome` NVARCHAR(60) NULL,
  `cpf` CHAR(11) NULL,
  `genero` CHAR(1) NULL,
  `data_nascimento` TIMESTAMP NULL,
  `estado_civil` CHAR(1) NULL,
  `telefone` CHAR(11) NULL,
  `cep` CHAR(8) NULL,
  `data_cadastro` TIMESTAMP NULL,
  `grau_escolaridade` CHAR(1) NULL,
  `verificado` TINYINT NULL,
  `removido` TINYINT NOT NULL,
  `fk_situacao_atual` INT NULL,
  PRIMARY KEY (`id_usuario`),
  INDEX `fk_tb_usuario_tb_situacao_atual1_idx` (`fk_situacao_atual` ASC) VISIBLE,
  CONSTRAINT `fk_tb_usuario_tb_situacao_atual1` FOREIGN KEY (`fk_situacao_atual`) REFERENCES `conture`.`situacao_atual` (`id_situacao_atual`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`categoria_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`categoria_produto` (
  `id_categoria_produto` INT NOT NULL AUTO_INCREMENT,
  `nome` NVARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria_produto`)
);
-- -----------------------------------------------------
-- Table `conture`.`produto_doacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`produto_doacao` (
  `id_produto_doacao` INT NOT NULL AUTO_INCREMENT,
  `nome` NVARCHAR(60) NOT NULL,
  `marca` NVARCHAR(60) NOT NULL,
  `modelo` VARCHAR(60) NOT NULL,
  `descricao` NVARCHAR(255) NOT NULL,
  `bucket_name` VARCHAR(255) NULL,
  `object_name` VARCHAR(255) NULL,
  `defeito` TINYINT NOT NULL,
  `entrega` TINYINT NOT NULL,
  `quantidade_visualizacao` INT NOT NULL,
  `data_criacao` TIMESTAMP NOT NULL,
  `data_conclusao` TIMESTAMP NULL,
  `status` TINYINT NOT NULL,
  `removido` TINYINT NOT NULL,
  `fk_categoria_produto` INT NOT NULL,
  `fk_doador` INT NOT NULL,
  PRIMARY KEY (`id_produto_doacao`),
  INDEX `fk_Produto_Usuario1_idx` (`fk_doador` ASC) VISIBLE,
  INDEX `fk_tb_doacao_tbClassificacaoProduto1_idx` (`fk_categoria_produto` ASC) VISIBLE,
  CONSTRAINT `fk_Produto_Usuario1` FOREIGN KEY (`fk_doador`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_doacao_tbClassificacaoProduto1` FOREIGN KEY (`fk_categoria_produto`) REFERENCES `conture`.`categoria_produto` (`id_categoria_produto`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`match`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`match_doacao` (
  `id_match` INT NOT NULL AUTO_INCREMENT,
  `match_porcentagem` DECIMAL(5, 2) NULL,
  `data_interesse` TIMESTAMP NOT NULL,
  `status` TINYINT NOT NULL,
  `visualizado` TINYINT NOT NULL,
  `fk_produto_doacao` INT NOT NULL,
  `fk_donatario` INT NOT NULL,
  INDEX `fk_Doacao_copy1_Usuario1_idx` (`fk_donatario` ASC) VISIBLE,
  INDEX `fk_tbMatch_Produto1_idx` (`fk_produto_doacao` ASC) VISIBLE,
  PRIMARY KEY (`id_match`),
  CONSTRAINT `fk_Doacao_copy1_Usuario1` FOREIGN KEY (`fk_donatario`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbMatch_Produto1` FOREIGN KEY (`fk_produto_doacao`) REFERENCES `conture`.`produto_doacao` (`id_produto_doacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`avaliacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`avaliacao` (
  `id_avaliacao` INT NOT NULL AUTO_INCREMENT,
  `valor` INT NOT NULL,
  `comentario` NVARCHAR(300) NULL,
  `data` TIMESTAMP NOT NULL,
  `fk_match` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  INDEX `fk_avaliacao_match1_idx` (`fk_match` ASC) VISIBLE,
  CONSTRAINT `fk_avaliacao_match1` FOREIGN KEY (`fk_match`) REFERENCES `conture`.`match_doacao` (`id_match`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`tipo_reporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`tipo_reporte` (
  `id_tipo_reporte` INT NOT NULL AUTO_INCREMENT,
  `nome` NVARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tipo_reporte`)
);
-- -----------------------------------------------------
-- Table `conture`.`reporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`reporte` (
  `id_reporte` INT NOT NULL AUTO_INCREMENT,
  `data` TIMESTAMP NOT NULL,
  `fk_reportador` INT NOT NULL,
  `fk_reportado` INT NOT NULL,
  `fk_tipo_reporte` INT NOT NULL,
  PRIMARY KEY (`id_reporte`),
  INDEX `fk_tbReport_tbUsuario1_idx` (`fk_reportador` ASC) VISIBLE,
  INDEX `fk_tbReport_tbUsuario2_idx` (`fk_reportado` ASC) VISIBLE,
  INDEX `fk_tbReporte_tb_tipo_reporte1_idx` (`fk_tipo_reporte` ASC) VISIBLE,
  CONSTRAINT `fk_tbReport_tbUsuario1` FOREIGN KEY (`fk_reportador`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbReport_tbUsuario2` FOREIGN KEY (`fk_reportado`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbReporte_tb_tipo_reporte1` FOREIGN KEY (`fk_tipo_reporte`) REFERENCES `conture`.`tipo_reporte` (`id_tipo_reporte`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`chat_direto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`chat_direto` (
  `id_chat_direto` INT NOT NULL AUTO_INCREMENT,
  `fk_remetente` INT NOT NULL,
  `fk_destinatario` INT NOT NULL,
  PRIMARY KEY (`id_chat_direto`),
  INDEX `fk_chat_direto_usuario1_idx` (`fk_remetente` ASC) VISIBLE,
  INDEX `fk_chat_direto_usuario2_idx` (`fk_destinatario` ASC) VISIBLE,
  CONSTRAINT `fk_chat_direto_usuario1` FOREIGN KEY (`fk_remetente`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_direto_usuario2` FOREIGN KEY (`fk_destinatario`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`mensagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`mensagem` (
  `id_mensagem` INT NOT NULL AUTO_INCREMENT,
  `visualizado` TINYINT NOT NULL,
  `data` TIMESTAMP NOT NULL,
  `mensagem` NVARCHAR(200) NOT NULL,
  `fk_chat_direto` INT NOT NULL,
  PRIMARY KEY (`id_mensagem`),
  INDEX `fk_tb_mensagem_tb_chat_direto1_idx` (`fk_chat_direto` ASC) VISIBLE,
  CONSTRAINT `fk_tb_mensagem_tb_chat_direto1` FOREIGN KEY (`fk_chat_direto`) REFERENCES `conture`.`chat_direto` (`id_chat_direto`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`preferencia_donatario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`preferencia_donatario` (
  `id_preferencia_donatario` INT NOT NULL AUTO_INCREMENT,
  `genero` CHAR(1) NOT NULL,
  `faixa_etaria` CHAR(1) NOT NULL,
  `estado_civil` CHAR(1) NOT NULL,
  `grau_escolaridade` CHAR(1) NOT NULL,
  `fk_situacao_atual` INT NOT NULL,
  `fk_produto_doacao` INT NOT NULL,
  INDEX `fk_preferencia_donatario_situacao_atual1_idx` (`fk_situacao_atual` ASC) VISIBLE,
  INDEX `fk_preferencia_donatario_produto_doacao1_idx` (`fk_produto_doacao` ASC) VISIBLE,
  PRIMARY KEY (`id_preferencia_donatario`),
  CONSTRAINT `fk_preferencia_donatario_situacao_atual1` FOREIGN KEY (`fk_situacao_atual`) REFERENCES `conture`.`situacao_atual` (`id_situacao_atual`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_preferencia_donatario_produto_doacao1` FOREIGN KEY (`fk_produto_doacao`) REFERENCES `conture`.`produto_doacao` (`id_produto_doacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`mensagem_grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`mensagem_grupo` (
  `id_mensagem_grupo` INT NOT NULL AUTO_INCREMENT,
  `mensagem` NVARCHAR(200) NOT NULL,
  `data` TIMESTAMP NOT NULL,
  `fk_usuario` INT NOT NULL,
  `fk_produto_doacao` INT NOT NULL,
  `fk_mensagem_principal` INT NULL,
  PRIMARY KEY (`id_mensagem_grupo`),
  INDEX `fk_tb_chat_grupo_tb_usuario2_idx` (`fk_usuario` ASC) VISIBLE,
  INDEX `fk_pergunta_pergunta1_idx` (`fk_mensagem_principal` ASC) VISIBLE,
  INDEX `fk_pergunta_produto_doacao1_idx` (`fk_produto_doacao` ASC) VISIBLE,
  CONSTRAINT `fk_tb_chat_grupo_tb_usuario2` FOREIGN KEY (`fk_usuario`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_pergunta1` FOREIGN KEY (`fk_mensagem_principal`) REFERENCES `conture`.`mensagem_grupo` (`id_mensagem_grupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_produto_doacao1` FOREIGN KEY (`fk_produto_doacao`) REFERENCES `conture`.`produto_doacao` (`id_produto_doacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`desligamento_conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`desligamento_conta` (
  `id_desligamento_conta` INT NOT NULL AUTO_INCREMENT,
  `motivo_desligamento_conta` CHAR(1) NOT NULL,
  `data` TIMESTAMP NOT NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id_desligamento_conta`),
  INDEX `fk_desligamento_conta_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_desligamento_conta_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`imagem_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`imagem_usuario` (
  `id_imagem_usuario` INT NOT NULL AUTO_INCREMENT,
  `bucket_name` VARCHAR(255) NOT NULL,
  `object_name` VARCHAR(255) NOT NULL,
  `tipo_imagem` CHAR(1) NOT NULL,
  `fk_usuario` INT NOT NULL,
  PRIMARY KEY (`id_imagem_usuario`),
  INDEX `fk_imagem_usuario_usuario1_idx` (`fk_usuario` ASC) VISIBLE,
  CONSTRAINT `fk_imagem_usuario_usuario1` FOREIGN KEY (`fk_usuario`) REFERENCES `conture`.`usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
-- -----------------------------------------------------
-- Table `conture`.`imagem_produto_doacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conture`.`imagem_produto_doacao` (
  `id_imagem_produto_doacao` INT NOT NULL AUTO_INCREMENT,
  `bucket_name` VARCHAR(255) NOT NULL,
  `object_name` VARCHAR(255) NOT NULL,
  `fk_produto_doacao` INT NOT NULL,
  PRIMARY KEY (`id_imagem_produto_doacao`),
  INDEX `fk_imagem_produto_doacao_produto_doacao1_idx` (`fk_produto_doacao` ASC) VISIBLE,
  CONSTRAINT `fk_imagem_produto_doacao_produto_doacao1` FOREIGN KEY (`fk_produto_doacao`) REFERENCES `conture`.`produto_doacao` (`id_produto_doacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
