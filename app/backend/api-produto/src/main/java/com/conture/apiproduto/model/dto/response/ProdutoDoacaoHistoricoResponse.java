package com.conture.apiproduto.model.dto.response;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;

public class ProdutoDoacaoHistoricoResponse {
	@NotNull
	@NotBlank
	private String tipo;

	@NotNull
	@NotBlank
	@Size(max = 45)
	private String categoriaProduto;


	@NotNull
	@NotBlank
	@Size(max = 60)
	private String nome;


	@NotNull
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@NotNull
	@Positive
	private Integer fkDoador;

	public ProdutoDoacaoHistoricoResponse(
			String tipo,
			String categoriaProduto,
			String nome,
			Date dataCriacao,
			Date dataConclusao,
			Integer fkDoador
	) {
		this.tipo = tipo;
		this.categoriaProduto = categoriaProduto;
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.fkDoador = fkDoador;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCategoriaProduto() {
		return categoriaProduto;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public Integer getFkDoador() {
		return fkDoador;
	}
}
