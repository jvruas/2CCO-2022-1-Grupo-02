package com.conture.apiusuario.dto.request;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioSenhaRequest {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@NotBlank
	@Size(max = 18, message = "A senha deve ter no máximo 18 letras")
	private String senhaAtual;

	@NotBlank
	@Size(max = 18, message = "A senha deve ter no máximo 18 letras")
	private String senhaNova;

	// Getters
	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}
}
