package com.conture.apiusuario.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioLoginRequest {
	@NotNull
	@NotBlank
	@Email
	@Size(max = 80)
    private String email;

	@NotNull
	@NotBlank
	@Size(min=6, max = 18)
    private String senha;


	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
