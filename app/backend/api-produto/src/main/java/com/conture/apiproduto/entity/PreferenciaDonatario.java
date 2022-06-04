package com.conture.apiproduto.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class PreferenciaDonatario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPreferenciaDonatario;

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
	@OneToOne
	private ProdutoDoacao produtoDoacao;


	public Integer getIdPreferenciaDonatario() {
		return idPreferenciaDonatario;
	}

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

	public ProdutoDoacao getProdutoDoacao() {
		return produtoDoacao;
	}
}
