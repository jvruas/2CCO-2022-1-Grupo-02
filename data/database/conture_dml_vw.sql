USE conture;

-- View reporte 
CREATE OR REPLACE VIEW vw_reporte_tipo_reporte AS  
WITH CTE AS (
SELECT
	CASE
		WHEN tr.nome = "Assédio" THEN count(1)
	END "Assédio",
	CASE
		WHEN tr.nome = "Discurso de ódio" THEN count(1)
	END "Discurso de ódio",
	CASE
		WHEN tr.nome = "Informações do perfil" THEN count(1)
	END "Informações do perfil",
	CASE
		WHEN tr.nome = "Nudez ou atividade sexual" THEN count(1)
	END "Nudez ou atividade sexual",
	CASE
		WHEN tr.nome = "Produto falso" THEN count(1)
	END "Produto falso",
	CASE
		WHEN tr.nome = "Spam" THEN count(1)
	END "Spam",
	SUBSTR(r.data, 1, 4) AS 'data'
from
	reporte r
inner join tipo_reporte tr
		on
	r.fk_tipo_reporte = tr.id_tipo_reporte
GROUP by
	tr.nome,
	SUBSTR(r.data, 1, 4)
)SELECT
	CASE WHEN MAX(`Assédio`) IS NULL THEN 0
	ELSE MAX(`Assédio`)
	END AS 'Assedio',
	CASE WHEN MAX(`Discurso de ódio`) IS NULL THEN 0
	ELSE MAX(`Discurso de ódio`)
	END AS 'Discurso_de_odio',
	CASE WHEN MAX(`Informações do perfil`) IS NULL THEN 0
	ELSE MAX(`Informações do perfil`)
	END AS 'Informacoes_do_perfil',
	CASE WHEN MAX(`Nudez ou atividade sexual`) IS NULL THEN 0
	ELSE MAX(`Nudez ou atividade sexual`)
	END AS 'Nudez_ou_atividade_sexual',
	CASE WHEN MAX(`Produto falso`) IS NULL THEN 0
	ELSE MAX(`Produto falso`)
	END AS 'Produto_falso',
	CASE WHEN MAX(`Spam`) IS NULL THEN 0
	ELSE MAX(`Spam`)
	END AS 'Spam',
	`data`
FROM
	CTE
GROUP BY `data`
ORDER BY `data`;

SELECT * FROM vw_reporte_tipo_reporte;	


-- View Desligamento
CREATE OR REPLACE VIEW vw_qtd_desligamento_motivo_desligamento AS
WITH CTE AS (
SELECT
	CASE
		WHEN mdc.motivo = "Problemas em utilizar o site" THEN count(1)
	END "Problemas em utilizar o site",
	CASE
		WHEN mdc.motivo = "Não consigo achar os eletrônicos que preciso" THEN count(1)
	END "Não consigo achar os eletrônicos que preciso",
	CASE
		WHEN mdc.motivo = "Outros" THEN count(1)
	END "Outros",
	SUBSTR(dc.data_desligamento, 1, 4) as 'data'
FROM
	desligamento_conta dc
INNER JOIN motivo_desligamento_conta mdc
	ON
	dc.fk_motivo_desligamento_conta = mdc.id_motivo_desligamento_conta
GROUP BY
	mdc.motivo,SUBSTR(dc.data_desligamento, 1, 4)
)SELECT
	CASE WHEN MAX(`Problemas em utilizar o site`) IS NULL THEN 0
	ELSE MAX(`Problemas em utilizar o site`)
	END AS 'Problemas_em_utilizar_o_site',
	CASE WHEN MAX(`Não consigo achar os eletrônicos que preciso`) IS NULL THEN 0
	ELSE MAX(`Não consigo achar os eletrônicos que preciso`)
	END AS 'Nao_consigo_achar_os_eletronicos_que_preciso',
	CASE WHEN MAX(`Outros`) IS NULL THEN 0
	ELSE MAX(`Outros`)
	END AS 'Outros',
	`data`
FROM
	CTE
GROUP BY `data`
ORDER BY `data`;

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
WITH CTE AS (
SELECT
	SUBSTR(pd.data_conclusao,1,4)  as 'data',
	CASE
		WHEN cp.nome = "HEADSET" THEN count(1)
	END "HEADSET",
	CASE
		WHEN cp.nome = "MESA DIGITALIZADORA" THEN count(1)
	END "MESA DIGITALIZADORA",
	CASE
		WHEN cp.nome = "NOTEBOOK" THEN count(1)
	END "NOTEBOOK",
	CASE
		WHEN cp.nome = "SMARTPHONE" THEN count(1)
	END "SMARTPHONE",
	CASE
		WHEN cp.nome = "TABLET" THEN count(1)
	END "TABLET"
FROM
	produto_doacao pd
JOIN categoria_produto cp 
		ON
	cp.id_categoria_produto = pd.fk_categoria_produto
WHERE
	pd.data_conclusao != 'NULL'
GROUP BY
	cp.nome,SUBSTR(pd.data_conclusao,1,4)
)SELECT
	CASE WHEN MAX(`HEADSET`) IS NULL THEN 0
	ELSE MAX(`HEADSET`)
	END AS 'HEADSET',
	CASE WHEN MAX(`MESA DIGITALIZADORA`) IS NULL THEN 0
	ELSE MAX(`MESA DIGITALIZADORA`)
	END AS 'MESA_DIGITALIZADORA',
	CASE WHEN MAX(`NOTEBOOK`) IS NULL THEN 0
	ELSE MAX(`NOTEBOOK`)
	END AS 'NOTEBOOK',
	CASE WHEN MAX(`SMARTPHONE`) IS NULL THEN 0
	ELSE MAX(`SMARTPHONE`)
	END AS 'SMARTPHONE',
	CASE WHEN MAX(`TABLET`) IS NULL THEN 0
	ELSE MAX(`TABLET`)
	END AS 'TABLET',
	`data` AS 'DATA'
FROM
	CTE
GROUP BY `data`
ORDER BY `data`;

SELECT * FROM vw_doacao_historica;
	

-- View qtd doacao por estado
CREATE OR REPLACE VIEW vw_qtd_doacao_estado AS
	SELECT COUNT(*) as 'qt_produtos', u.uf, pd.data_conclusao AS 'data'
		FROM produto_doacao pd 
			INNER JOIN usuario u 
				ON pd.fk_doador = u.id_usuario 
		WHERE pd.data_conclusao != 'NULL'
	GROUP BY u.uf;

SELECT * FROM vw_qtd_doacao_estado;


-- View Qtd. Reportes por estado
CREATE OR REPLACE VIEW vw_qtd_reportes_estado AS
	SELECT COUNT(*) AS 'qt_reportes', u.uf, r.data AS 'data'
		FROM reporte r
			INNER JOIN usuario u 
				ON r.fk_reportador = u.id_usuario 
	GROUP BY u.uf;

SELECT * FROM vw_qtd_reportes_estado;

-- View Filtro de datas
CREATE OR REPLACE VIEW vw_todas_datas AS
	WITH CTE AS (
	SELECT `data` AS 'data_historica' FROM vw_doacao_historica
	UNION SELECT data_de_doacao FROM vw_produtos_vendidos_doados
	UNION SELECT `data` FROM vw_qtd_desligamento_motivo_desligamento
	UNION SELECT`data` FROM vw_qtd_doacao_estado
	UNION SELECT `data` FROM vw_qtd_reportes_estado
	UNION SELECT `data` FROM vw_reporte_tipo_reporte
	) SELECT DISTINCT SUBSTR(data_historica, 1, 4) AS 'datas' FROM CTE
	ORDER BY 1;

SELECT * FROM vw_todas_datas;



	
	