package com.conture.apiproduto.model.dto.request;

import javax.validation.constraints.*;

public class PreferenciaDonatarioRequest {
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[F,M,X]")
	private String genero;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[J,A,I,X]")
	private String faixaEtaria;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[S,C,$,D,V,X]")
	private String estadoCivil;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[A,I,F,M,S,P,E,D,X]")
	private String grauEscolaridade;

	@NotNull
	@Positive
	private Integer fkSituacaoAtual;

	@NotNull
	@Positive
	private Integer fkProdutoDoacao;


	public String getGenero() {
		return genero;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public Integer getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}

	public Integer getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}
}
