-- View reporte 
CREATE OR REPLACE VIEW vw_reporte_tipo_reporte AS  
	SELECT COUNT(1) AS 'total_reportes', tr.nome AS 'reporte' 
		from reporte r
	inner join tipo_reporte tr
		on r.fk_tipo_reporte = tr.id_tipo_reporte 
	GROUP by tr.nome ; 
	
SELECT * FROM vw_reporte_tipo_reporte;	


-- View Desligamento
CREATE OR REPLACE VIEW vw_qtd_desligamento_motivo_desligamento AS
	SELECT COUNT(1) AS 'quantidade_desligamentos', mdc.motivo AS 'motivo'
		FROM desligamento_conta dc
	INNER JOIN motivo_desligamento_conta mdc
		ON dc.fk_motivo_desligamento_conta = mdc.id_motivo_desligamento_conta 
	GROUP BY mdc.motivo;

SELECT * FROM vw_qtd_desligamento_motivo_desligamento;


-- View Produtos Vendidos X Doados
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
CREATE OR REPLACE VIEW vw_doacao_historica AS
	SELECT pd.data_conclusao as 'mes', COUNT(*) as 'qtd_produtos'
		FROM produto_doacao pd
	WHERE pd.data_conclusao != 'NULL'
		GROUP BY YEAR(pd.data_conclusao)
	ORDER BY pd.data_conclusao DESC;

SELECT * FROM vw_doacao_historica;
	

-- View qtd doacao por estado
CREATE OR REPLACE VIEW vw_qtd_doacao_estado AS
	SELECT COUNT(*) as 'qt_produtos', uf.uf
		FROM produto_doacao pd 
			INNER JOIN usuario u 
				ON pd.fk_doador = u.id_usuario 
			INNER JOIN uf_usuario uf 
				ON u.id_usuario = uf.fk_usuario
		WHERE pd.data_conclusao != 'NULL'
	GROUP BY uf.uf;

SELECT * FROM vw_qtd_doacao_estado;


-- View Qtd. Reportes
CREATE OR REPLACE VIEW vw_qtd_reportes_estado AS
	SELECT COUNT(*) AS 'qt_reportes', uf.uf
		FROM reporte r
			INNER JOIN usuario u 
				ON r.fk_reportador = u.id_usuario 
			INNER JOIN uf_usuario uf 
				ON u.id_usuario = uf.fk_usuario
	GROUP BY uf.uf;

SELECT * FROM vw_qtd_reportes_estado;



	
	