package com.conture.apiproduto.model.dto.request;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AtualizarMatchIdentifierRequest {


	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	private Long fkProdutoDoacao;

	@NotNull
	@Positive
	@Id
	private Long fkDonatario;

	private String status;

	public AtualizarMatchIdentifierRequest(
			Long fkDoador,
			Long fkProdutoDoacao,
			Long fkDonatario,
		   	String status
	) {
		this.fkDoador = fkDoador;
		this.fkProdutoDoacao = fkProdutoDoacao;
		this.fkDonatario = fkDonatario;
		this.status = status;
	}

	public Long getFkDoador() { return fkDoador; }

	public Long getFkProdutoDoacao() { return fkProdutoDoacao; }

	public Long getFkDonatario() { return fkDonatario; }

	public String getStatus() { return status; }
}
