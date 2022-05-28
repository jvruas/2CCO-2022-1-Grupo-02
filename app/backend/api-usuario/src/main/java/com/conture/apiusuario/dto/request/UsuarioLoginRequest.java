package com.conture.apiusuario.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioLoginRequest {
	@Email
	@NotBlank
	@Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
    private String email;

	@NotBlank
	@Size(max = 18, message = "A senha deve ter no máximo 18 letras")
    private String senha;


	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
