package com.conture.apiview.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Immutable
@Subselect("select uuid() as id, vw.* from vw_produtos_vendidos_doados vw")
@Table(name = "vw_produtos_vendidos_doados")
public class VwProdutosVendidosDoados implements Serializable{

	@JsonIgnore
	@Id
	private String id;

	@Column(name = "produto")
    private String produto;

	@Column(name = "data_de_doacao")
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataDoacao;

	@Column(name = "qtd_visualizacao")
    private Integer qtdVisualizacao;

	@Column(name = "qtd_produtos")
    private Integer qtdProdutos;


	public VwProdutosVendidosDoados(){

	}


	public VwProdutosVendidosDoados(String id, String produto, Date dataDoacao, Integer qtdVisualizacao,
			Integer qtdProdutos) {
		this.id = id;
		this.produto = produto;
		this.dataDoacao = dataDoacao;
		this.qtdVisualizacao = qtdVisualizacao;
		this.qtdProdutos = qtdProdutos;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProduto() {
		return produto;
	}


	public void setProduto(String produto) {
		this.produto = produto;
	}


	public Date getDataDoacao() {
		return dataDoacao;
	}


	public void setDataDoacao(Date dataDoacao) {
		this.dataDoacao = dataDoacao;
	}


	public Integer getQtdVisualizacao() {
		return qtdVisualizacao;
	}


	public void setQtdVisualizacao(Integer qtdVisualizacao) {
		this.qtdVisualizacao = qtdVisualizacao;
	}


	public Integer getQtdProdutos() {
		return qtdProdutos;
	}


	public void setQtdProdutos(Integer qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}

	
	
}
