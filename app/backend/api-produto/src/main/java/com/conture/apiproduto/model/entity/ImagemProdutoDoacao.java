package com.conture.apiproduto.model.entity;

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
	@JoinColumn(name="fk_produto_doacao")
	private ProdutoDoacao produtoDoacao;


	public ImagemProdutoDoacao() {
	}

	private ImagemProdutoDoacao(
			Integer fkProdutoDoacao,
			@NotNull byte[] imagem
	) {
		this.setProdutoDoacao(fkProdutoDoacao);
		this.imagem = imagem;
	}


	public static ImagemProdutoDoacao fromPattern(
			Integer fkProdutoDoacao,
			@NotNull byte[] imagem
	) {
		return new ImagemProdutoDoacao(fkProdutoDoacao, imagem);
	}

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

	public void setProdutoDoacao(Integer fkProdutoDoacao) {
		this.produtoDoacao = ProdutoDoacao.fromPattern(fkProdutoDoacao);
	}
}
