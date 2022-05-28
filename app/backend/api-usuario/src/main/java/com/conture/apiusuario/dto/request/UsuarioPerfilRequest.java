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
	@Size(min = 1, max = 1, message = "O genero deve ter 1 letra")
	private String genero;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "O cpf deve ter 1 letra")
	private String estadoCivil;
	@NotNull
	@NotBlank
	@Size(max = 8, message = "O cep deve ter no máximo 8 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O CEP aceita apenas números")
	private String cep;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "A escolaridade deve ter 1 letra")
	private String grauEscolaridade;
	@NotNull
	@NotBlank
	@Size(min = 11, max = 11, message = "O telefone deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O telefone aceita apenas números")
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
