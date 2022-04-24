package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class ProdutoDoacao {

	@NotNull

	private Long fkDoador;

	@Id
	@NotNull

	private Long idProdutoDoacao;


	@NotBlank
	@NotNull
	@Size(min = 2)
	private String nome;

	@NotBlank
	@NotNull
	private String marca;

	@NotBlank
	@NotNull
	private String modelo;

	@NotBlank
	@NotNull
	@Size(min = 5)
	private String descricao;


	private boolean defeito;

	private boolean status;

	private boolean entrega;



	@NotNull
	@Positive
	@Min(1)
	private int quantidadeVisualizacao;



//	@PastOrPresent
//	private LocalDateTime dataCriacao;
//
//
//	@PastOrPresent
//	private LocalDateTime dataConclusao;


	@NotNull
	private Long fkCategoriaProduto;


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

	public int getQuantidadeVisualizacao() {
		return quantidadeVisualizacao;
	}

	public void setQuantidadeVisualizacao(int quantidadeVisualizacao) {
		this.quantidadeVisualizacao = quantidadeVisualizacao;
	}

	//	public LocalDateTime getDataCriacao() {
//		return dataCriacao;
//	}
//
//	public void setDataCriacao(LocalDateTime dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//
//	public LocalDateTime getDataConclusao() {
//		return dataConclusao;
//	}
//
//	public void setDataConclusao(LocalDateTime dataConclusao) {
//		this.dataConclusao = dataConclusao;
//	}

	public Long getFkCategoriaProduto() {
		return fkCategoriaProduto;
	}

	public void setFkCategoriaProduto(Long fkCategoriaProduto) {
		this.fkCategoriaProduto = fkCategoriaProduto;
	}
}
