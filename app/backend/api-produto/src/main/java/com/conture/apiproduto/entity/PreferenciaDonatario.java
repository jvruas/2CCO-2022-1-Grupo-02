package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class PreferenciaDonatario {
	@NotNull
	@Positive
	private Long fkDoador;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Positive
	private Long fkSituacaoAtual;

	public Long getFkDoador() { return fkDoador; }

	public Long getFkDoacao() { return fkDoacao; }

	public String getGenero() { return genero; }

	public String getFaixaEtaria() { return faixaEtaria; }

	public String getEstadoCivil() { return estadoCivil; }

	public String getGrauEscolaridade() { return grauEscolaridade; }

	public Long getFkSituacaoAtual() { return fkSituacaoAtual; }

	public void setFkDoador(Long fkDoador) { this.fkDoador = fkDoador; }

	public void setGenero(String genero) { this.genero = genero; }

	public void setFaixaEtaria(String faixaEtaria) { this.faixaEtaria = faixaEtaria; }

	public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

	public void setGrauEscolaridade(String grauEscolaridade) { this.grauEscolaridade = grauEscolaridade; }

	public void setFkSituacaoAtual(Long fkSituacaoAtual) { this.fkSituacaoAtual = fkSituacaoAtual; }
}
