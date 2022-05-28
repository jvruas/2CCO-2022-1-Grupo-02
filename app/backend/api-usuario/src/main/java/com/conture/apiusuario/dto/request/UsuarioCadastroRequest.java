package com.conture.apiusuario.dto.request;

import com.conture.apiusuario.entity.SituacaoAtual;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class UsuarioCadastroRequest {
	@NotNull
	@NotBlank
	@Email
	@Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
	private String email;

	@NotNull
	@NotBlank
	@Size(max = 18, message = "A senha deve ter no máximo 18 letras")
	private String senha;

	@NotNull
	@NotBlank
	@Size(max = 45, message = "O nome deve ter no máximo 45 letras")
	private String nome;

	@NotNull
	@NotBlank
	@Size(max = 60, message = "O sobrenome deve ter no máximo 60 letras")
	private String sobrenome;

	@NotNull
	@NotBlank
	@CPF
	@Size(min = 11, max = 11, message = "O cpf deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O CPF aceita apenas números")
	private String cpf;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "O genero deve ter 1 letra")
	private String genero;

	@NotNull
	@Past
	private Date dataNascimento;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "O cpf deve ter 1 letra")
	private String estadoCivil;

	@NotNull
	@NotBlank
	@Size(min = 11, max = 11, message = "O telefone deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O telefone aceita apenas números")
	private String telefone;

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
	private Integer fkSituacaoAtual;

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getGenero() {
		return genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
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

	public Integer getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}
}
