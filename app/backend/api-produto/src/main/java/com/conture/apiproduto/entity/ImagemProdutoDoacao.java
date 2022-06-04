package com.conture.apiproduto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProdutoDoacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagemProdutoDoacao;

	@NotNull
	@JsonIgnore
	@Column(length = 16_777_216)
	private byte[] imagem;

	@NotNull
	@ManyToOne
	private ProdutoDoacao produtoDoacao;


	public Integer getIdImagemProdutoDoacao() {
		return idImagemProdutoDoacao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public ProdutoDoacao getProdutoDoacao() {
		return produtoDoacao;
	}

	public void setIdImagemProdutoDoacao(Integer idImagemProdutoDoacao) {
		this.idImagemProdutoDoacao = idImagemProdutoDoacao;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public void setProdutoDoacao(ProdutoDoacao produtoDoacao) {
		this.produtoDoacao = produtoDoacao;
	}
}
