package com.conture.apiproduto.model.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class MatchIdentifierRequest {


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
}
