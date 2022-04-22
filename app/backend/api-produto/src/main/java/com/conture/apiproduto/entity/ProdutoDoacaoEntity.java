package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class ProdutoDoacaoEntity {

	@NotNull
	@NotBlank
	private int fkDoador;

	@Id
	@NotNull
	@NotBlank
	private int idProdutoDoacao;


	@NotBlank
	@NotNull
	@Min(2)
	private String nome;

	@NotBlank
	@NotNull
	private String marca;

	@NotBlank
	@NotNull
	private String modelo;

	@NotBlank
	@NotNull
	@Min(10)
	private String descricao;


	private boolean defeito;

	private boolean status;

	private boolean entrega;

	@NotBlank
	@NotNull
	@Positive
	@Min(1)
	private Integer quantidadeVizualicao;



	@PastOrPresent
	private LocalDateTime dataCriacao;


	@PastOrPresent
	private LocalDateTime dataConclusao;

	@NotBlank
	@NotNull
	private int fkCategoriaProduto;


	public int getFkDoador() {
		return fkDoador;
	}

	public void setFkDoador(int fkDoador) {
		this.fkDoador = fkDoador;
	}

	public int getIdProdutoDoacao() {
		return idProdutoDoacao;
	}

	public void setIdProdutoDoacao(int idProdutoDoacao) {
		this.idProdutoDoacao = idProdutoDoacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isDefeito() {
		return defeito;
	}

	public void setDefeito(boolean defeito) {
		this.defeito = defeito;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isEntrega() {
		return entrega;
	}

	public void setEntrega(boolean entrega) {
		this.entrega = entrega;
	}

	public Integer getQuantidadeVizualicao() {
		return quantidadeVizualicao;
	}

	public void setQuantidadeVizualicao(Integer quantidadeVizualicao) {
		this.quantidadeVizualicao = quantidadeVizualicao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(LocalDateTime dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public int getFkCategoriaProduto() {
		return fkCategoriaProduto;
	}

	public void setFkCategoriaProduto(int fkCategoriaProduto) {
		this.fkCategoriaProduto = fkCategoriaProduto;
	}
}
