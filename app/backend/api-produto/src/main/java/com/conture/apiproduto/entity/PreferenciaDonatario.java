package com.conture.apiproduto.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PreferenciaDonatario {

	@NotBlank
	@NotNull

	private Long fkDoador;

	@NotBlank
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


	@NotBlank
	@NotNull
	private Long fkSituacaoAtual;
}
