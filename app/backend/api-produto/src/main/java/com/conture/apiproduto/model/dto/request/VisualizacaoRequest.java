package com.conture.apiproduto.model.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class VisualizacaoRequest {

	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
	@Id
	private Long idProdutoDoacao;

	@NotNull
	@PositiveOrZero
	private int quantidadeVisualizacao;

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

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public void setQuantidadeVisualizacao(int quantidadeVisualizacao) {
		this.quantidadeVisualizacao = quantidadeVisualizacao;
	}
}
