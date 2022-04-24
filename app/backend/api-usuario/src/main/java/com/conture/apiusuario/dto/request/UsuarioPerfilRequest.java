package com.conture.apiusuario.dto.request;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
	private String cep;

	@NotBlank
	@Size(max = 30, message = "A escolaridade deve ter no máximo 30 letras")
	private String escolaridade;

	private Long fkSituacaoAtual;

	// Getters e Setters
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public Long getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}

	public void setFkSituacaoAtual(Long fkSituacaoAtual) {
		this.fkSituacaoAtual = fkSituacaoAtual;
	}
}
