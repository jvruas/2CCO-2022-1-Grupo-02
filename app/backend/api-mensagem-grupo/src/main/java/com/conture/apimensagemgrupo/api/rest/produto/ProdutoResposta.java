package com.conture.apimensagemgrupo.api.rest.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class ProdutoResposta {

	// Atributos
	private Integer idProdutoDoacao;

	private String nome;

	private String marca;

	private String modelo;

	private Date dataCriacao;

	private Integer fkDoador;

	// Construtor
	public ProdutoResposta(Integer idProdutoDoacao, String nome, String marca, String modelo, Date dataCriacao, Integer fkDoador) {
		this.idProdutoDoacao = idProdutoDoacao;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.dataCriacao = dataCriacao;
		this.fkDoador = fkDoador;
	}

	// Getters
	public Integer getIdProdutoDoacao() {
		return idProdutoDoacao;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public Integer getFkDoador() {
		return fkDoador;
	}
}
