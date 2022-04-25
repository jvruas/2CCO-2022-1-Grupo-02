package com.conture.apiproduto.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoDoacaoStatusRequest {


	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	@Id
	private Long idProdutoDoacao;


	private boolean status;


	public Long getFkDoador() {
		return fkDoador;
	}

	public void setFkDoador(Long fkDoador) {
		this.fkDoador = fkDoador;
	}

	public Long getIdProdutoDoacao() {
		return idProdutoDoacao;
	}

	public void setIdProdutoDoacao(Long idProdutoDoacao) {
		this.idProdutoDoacao = idProdutoDoacao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
