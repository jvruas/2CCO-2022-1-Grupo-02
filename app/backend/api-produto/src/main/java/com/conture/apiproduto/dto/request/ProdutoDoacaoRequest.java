package com.conture.apiproduto.dto.request;

import javax.validation.constraints.*;

public class ProdutoDoacaoRequest {
	@NotNull
	@NotBlank
	@Size(max = 60)
	private String nome;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String marca;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String modelo;

	@NotBlank
	@NotNull
	@Size(max = 255)
	private String descricao;

	@NotNull
//	@Column(length = 16_777_216)
	private byte[] imagemPrincipal;

	@NotNull
	private Boolean defeito;

	@NotNull
	private Boolean entrega;

	@NotNull
	@Positive
	private Integer fkCategoriaProduto;

	@NotNull
	@Positive
	private Integer fkDoador;


	public ProdutoDoacaoRequest(
			String nome,
			String marca,
			String modelo,
			String descricao,
			@NotNull byte[] imagemPrincipal,
			Boolean defeito,
			Boolean entrega,
			Integer fkCategoriaProduto,
			Integer fkDoador
	) {
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.descricao = descricao;
		this.imagemPrincipal = imagemPrincipal;
		this.defeito = defeito;
		this.entrega = entrega;
		this.fkCategoriaProduto = fkCategoriaProduto;
		this.fkDoador = fkDoador;
	}


	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getModelo() {
		return modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public byte[] getImagemPrincipal() {
		return imagemPrincipal;
	}

	public Boolean getDefeito() {
		return defeito;
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public Integer getFkCategoriaProduto() {
		return fkCategoriaProduto;
	}

	public Integer getFkDoador() {
		return fkDoador;
	}
}
