package com.conture.apiproduto.dto.request;

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



	public Long getFkDoador() {
		return fkDoador;
	}

	public void setFkDoador(Long fkDoador) {
		this.fkDoador = fkDoador;
	}

	public Long getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Long fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Long getFkDonatario() {
		return fkDonatario;
	}

	public void setFkDonatario(Long fkDonatario) {
		this.fkDonatario = fkDonatario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
