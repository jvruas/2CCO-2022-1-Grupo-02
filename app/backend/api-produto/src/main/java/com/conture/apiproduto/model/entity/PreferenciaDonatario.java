package com.conture.apiproduto.model.entity;

import com.conture.apiproduto.model.dto.request.PreferenciaDonatarioRequest;

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


	public PreferenciaDonatario() {
	}

	private PreferenciaDonatario(
			String genero,
			String faixaEtaria,
			String estadoCivil,
			String grauEscolaridade,
			Integer fkSituacaoAtual,
			Integer fkProdutoDoacao
	) {
		this.genero = genero;
		this.faixaEtaria = faixaEtaria;
		this.estadoCivil = estadoCivil;
		this.grauEscolaridade = grauEscolaridade;
		this.fkSituacaoAtual = fkSituacaoAtual;
		this.setProdutoDoacao(fkProdutoDoacao);
	}


	public static PreferenciaDonatario fromPattern(PreferenciaDonatarioRequest preferenciaDonatarioRequest) {
		return new PreferenciaDonatario(
				preferenciaDonatarioRequest.getGenero(),
				preferenciaDonatarioRequest.getFaixaEtaria(),
				preferenciaDonatarioRequest.getEstadoCivil(),
				preferenciaDonatarioRequest.getGrauEscolaridade(),
				preferenciaDonatarioRequest.getFkSituacaoAtual(),
				preferenciaDonatarioRequest.getFkProdutoDoacao()
		);
	}

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

	public void setIdPreferenciaDonatario(Integer idPreferenciaDonatario) {
		this.idPreferenciaDonatario = idPreferenciaDonatario;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	public void setFkSituacaoAtual(Integer fkSituacaoAtual) {
		this.fkSituacaoAtual = fkSituacaoAtual;
	}

	public void setProdutoDoacao(Integer fkProdutoDoacao) {
		this.produtoDoacao = ProdutoDoacao.fromPattern(fkProdutoDoacao);
	}
}
