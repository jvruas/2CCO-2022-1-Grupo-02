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

	@Column(name = "nome")
    private String nome;

	@Column(name = "qtd_visualizacao")
    private Integer qtdVisualizacao;

	@Column(name = "qtd_produtos")
    private Integer qtdProdutos;

	@Column(name = "media")
    private Double media;

	@Column(name = "media_match")
    private Double mediaMatch;

	@Column(name = "data_de_doacao")
    private String data;


	public VwProdutosVendidosDoados(){

	}


	public VwProdutosVendidosDoados(String id, String nome, Integer qtdVisualizacao, Integer qtdProdutos, Double media,
			Double mediaMatch, String data) {
		this.id = id;
		this.nome = nome;
		this.qtdVisualizacao = qtdVisualizacao;
		this.qtdProdutos = qtdProdutos;
		this.media = media;
		this.mediaMatch = mediaMatch;
		this.data = data;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
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


	public Double getMedia() {
		return media;
	}


	public void setMedia(Double media) {
		this.media = media;
	}


	public Double getMediaMatch() {
		return mediaMatch;
	}


	public void setMediaMatch(Double mediaMatch) {
		this.mediaMatch = mediaMatch;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

	
	
}
