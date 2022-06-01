package com.conture.apiusuario.dto.request;

import com.conture.apiusuario.entity.SituacaoAtual;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class UsuarioPerfilRequest {
	@NotNull
	@Positive
	private Integer idUsuario;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	private String genero;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	private String estadoCivil;
	@NotNull
	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "^[0-9]+$")
	private String cep;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	private String grauEscolaridade;
	@NotNull
	@NotBlank
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String telefone;
	@NotNull
	@NotNull
	@Positive
	private Integer fkSituacaoAtual;


	public Integer getIdUsuario() {
		return idUsuario;
	}

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
