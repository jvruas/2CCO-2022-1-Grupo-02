package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PreferenciaDonatario {


	@NotNull
	private Long fkDoador;


	@NotNull
	@Id
	private Long fkDoacao;


	@NotBlank
	@NotNull
	private String genero;


	@NotBlank
	@NotNull
	private String faixaEtaria;

	@NotBlank
	@NotNull
	private String estadoCivil;

	@NotBlank
	@NotNull
	private String grauEscolaridade;



	@NotNull
	private Long fkSituacaoAtual;

	public Long getFkDoador() {
		return fkDoador;
	}

	public void setFkDoador(Long fkDoador) {
		this.fkDoador = fkDoador;
	}

	public Long getFkDoacao() {
		return fkDoacao;
	}

	public void setFkDoacao(Long fkDoacao) {
		this.fkDoacao = fkDoacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	public Long getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}

	public void setFkSituacaoAtual(Long fkSituacaoAtual) {
		this.fkSituacaoAtual = fkSituacaoAtual;
	}
}
