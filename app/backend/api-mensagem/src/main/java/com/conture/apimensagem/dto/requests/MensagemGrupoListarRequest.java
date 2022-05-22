package com.conture.apimensagem.dto.requests;

public class MensagemGrupoListarRequest {

	private Integer fkProdutoDoacao;

	private Integer inicio;

	private Integer quantidade;

	public Integer getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Integer fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio() {
		this.inicio = inicio;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
