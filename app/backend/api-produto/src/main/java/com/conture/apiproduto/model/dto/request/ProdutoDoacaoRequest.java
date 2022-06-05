package com.conture.apiproduto.model.dto.request;

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
	private Boolean defeito;

	@NotNull
	private Boolean entrega;

	@NotNull
	@Positive
	private Integer fkCategoriaProduto;

	@NotNull
	@Positive
	private Integer idDoador;


	public ProdutoDoacaoRequest(
			String nome,
			String marca,
			String modelo,
			String descricao,
			Boolean defeito,
			Boolean entrega,
			Integer fkCategoriaProduto,
			Integer fkDoador
	) {
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.descricao = descricao;
		this.defeito = defeito;
		this.entrega = entrega;
		this.fkCategoriaProduto = fkCategoriaProduto;
		this.idDoador = fkDoador;
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

	public Boolean getDefeito() {
		return defeito;
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public Integer getFkCategoriaProduto() {
		return fkCategoriaProduto;
	}

	public Integer getIdDoador() {
		return idDoador;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
