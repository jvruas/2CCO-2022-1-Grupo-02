package com.conture.apiusuario.dto.request;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioPerfilRequest {

	// Atributos
	@Id
	private Long idUsuario;

	@NotBlank
	@Size(max = 9, message = "O genero deve ter no máximo 9 letras")
	private String genero;

	@NotBlank
	@Size(max = 10, message = "O cpf deve ter no máximo 10 letras")
	private String estadoCivil;

	@NotBlank
	@Size(max = 11, message = "O telefone deve ter no máximo 11 letras")
	private String telefone;

	@NotBlank
	private String cep;

	@NotBlank
	@Size(max = 30, message = "A escolaridade deve ter no máximo 30 letras")
	private String escolaridade;

	private Long fkSituacaoAtual;

	// Getters
	public Long getIdUsuario() {
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

	public String getEscolaridade() {
		return escolaridade;
	}

	public Long getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}
}
