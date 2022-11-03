package com.conture.apiproduto.model.dto.response;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class ProdutoDoacaoResponse {
	@NotNull
	@Positive
	private Integer idProdutoDoacao;

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
	@PositiveOrZero
	private int quantidadeVisualizacao;

	@NotNull
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConclusao;

	@NotNull
	private boolean status;

	@NotNull
	@NotBlank
	@Size(max = 45)
	private String categoriaProduto;

	@NotNull
	@Positive
	private Integer fkDoador;


	public ProdutoDoacaoResponse(
			Integer idProdutoDoacao,
			String nome,
			String marca,
			String modelo,
			String descricao,
			Boolean defeito,
			Boolean entrega,
			int quantidadeVisualizacao,
			Date dataCriacao,
			Date dataConclusao,
			boolean status,
			String categoriaProduto,
			Integer fkDoador
	) {
		this.idProdutoDoacao = idProdutoDoacao;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.descricao = descricao;
		this.defeito = defeito;
		this.entrega = entrega;
		this.quantidadeVisualizacao = quantidadeVisualizacao;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.status = status;
		this.categoriaProduto = categoriaProduto;
		this.fkDoador = fkDoador;
	}

	public Integer getIdProdutoDoacao() { return idProdutoDoacao; }

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

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public boolean isStatus() {
		return status;
	}

	public String getCategoriaProduto() {
		return categoriaProduto;
	}

	public Integer getFkDoador() {
		return fkDoador;
	}
}
