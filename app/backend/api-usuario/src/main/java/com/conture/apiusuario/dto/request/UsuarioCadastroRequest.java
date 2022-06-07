package com.conture.apiusuario.dto.request;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;

public class UsuarioCadastroRequest {
	@NotNull
	@NotBlank
	@Email
	@Size(max = 80)
	private String email;

	@NotNull
	@NotBlank
	@Size(min = 6, max = 18)
	private String senha;

	@NotNull
	@NotBlank
	@Size(max = 45)
	private String nome;

	@NotNull
	@NotBlank
	@Size(max = 60)
	private String sobrenome;

	@NotNull
	@NotBlank
	@CPF
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String cpf;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[F,M,X]")
	private String genero;

	@NotNull
	@Past
	private Date dataNascimento;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[S,C,$,D,V]")
	private String estadoCivil;

	@NotNull
	@NotBlank
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String telefone;

	@NotNull
	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "^[0-9]+$")
	private String cep;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[A,I,F,M,S,P,E,D]")
	private String grauEscolaridade;

	@NotNull
	@Positive
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

	public void setNome(String nome) { this.nome = nome; }

	public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }


	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	public void setFkSituacaoAtual(Integer fkSituacaoAtual) {
		this.fkSituacaoAtual = fkSituacaoAtual;
	}
}
