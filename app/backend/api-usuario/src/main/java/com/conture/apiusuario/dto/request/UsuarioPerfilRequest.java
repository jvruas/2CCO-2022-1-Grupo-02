package com.conture.apiusuario.dto.request;

import com.conture.apiusuario.entity.SituacaoAtual;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class UsuarioPerfilRequest {
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[F,M,X]")
	private String genero;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[S,C,$,D,V]")
	private String estadoCivil;

	@NotNull
	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "^[0-9]+$")
	private String cep;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[A,I,F,M,S,P,E,D]")
	private String grauEscolaridade;

	@NotNull
	@NotBlank
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String telefone;

	@NotNull
	@Positive
	private Integer fkSituacaoAtual;


	public String getGenero() {
		return genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public Integer getFkSituacaoAtual() { return fkSituacaoAtual; }
}
