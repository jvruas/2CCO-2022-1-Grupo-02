package com.conture.apiusuario.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioLoginRequest {

    // Atributos
	@Email
	@NotBlank
	@Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
    private String email;

	@NotBlank
	@Size(max = 18, message = "A senha deve ter no máximo 18 letras")
    private String senha;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
