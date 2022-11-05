package com.conture.apiproduto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProdutoDoacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagemProdutoDoacao;

	@NotNull
	@NotBlank
	private String bucketName;

	@NotNull
	@NotBlank
	private String objectName;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_produto_doacao")
	private ProdutoDoacao produtoDoacao;


	public ImagemProdutoDoacao() {
	}

	private ImagemProdutoDoacao(
			Integer fkProdutoDoacao,
			String bucketName,
			String objectName
	) {
		this.setProdutoDoacao(fkProdutoDoacao);
		this.bucketName = bucketName;
		this.objectName = objectName;
	}


	public static ImagemProdutoDoacao fromPattern(
			Integer fkProdutoDoacao,
			String bucketName,
			String objectName
	) {
		return new ImagemProdutoDoacao(fkProdutoDoacao, bucketName, objectName);
	}

	public Integer getIdImagemProdutoDoacao() {
		return idImagemProdutoDoacao;
	}

	public String getBucketName() {
		return bucketName;
	}

	public String getObjectName() {
		return objectName;
	}

	public ProdutoDoacao getProdutoDoacao() {
		return produtoDoacao;
	}

	public void setIdImagemProdutoDoacao(Integer idImagemProdutoDoacao) {
		this.idImagemProdutoDoacao = idImagemProdutoDoacao;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setProdutoDoacao(Integer fkProdutoDoacao) {
		this.produtoDoacao = ProdutoDoacao.fromPattern(fkProdutoDoacao);
	}
}
