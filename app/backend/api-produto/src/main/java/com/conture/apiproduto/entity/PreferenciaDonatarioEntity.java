package com.conture.apiproduto.entity;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PreferenciaDonatarioEntity {

	@NotBlank
	@NotNull
	private int fkDoador;

	@NotBlank
	@NotNull
	private int fkDoacao;


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


	@NotBlank
	@NotNull
	private int fkSituacaoAtual;
}
