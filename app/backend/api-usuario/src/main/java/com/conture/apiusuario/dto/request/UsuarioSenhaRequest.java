package com.conture.apiusuario.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UsuarioSenhaRequest {
	@NotNull
	@Positive
	private Integer idUsuario;
	@NotNull
	@NotBlank
	@Size(min = 6, max = 18)
	private String senhaAtual;
	@NotNull
	@NotBlank
	@Size(min = 6, max = 18)
	private String senhaNova;


	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
}
