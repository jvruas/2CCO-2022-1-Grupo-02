package com.conture.apiusuario.dto.response;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class UsuarioLogadoResponse {
	@NotNull
	@Positive
	private Integer idUsuario;

	@NotBlank
	@Email
	@Size(max = 80)
	private String email;

	@NotBlank
	@Size(max = 45)
	private String nome;

	@NotBlank
	@Size(max = 60)
	private String sobrenome;

	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[F,M,X]")
	private String genero;

	@Past
	private Date dataNascimento;

	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[S,C,$,D,V]")
	private String estadoCivil;

	@NotBlank
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String telefone;

	@NotBlank
	@Size(min = 8, max = 8)
	@Pattern(regexp = "^[0-9]+$")
	private String cep;

	@PastOrPresent
	private Date dataCadastro;

	@NotBlank
	@Size(min = 1, max = 1)
	@Pattern(regexp = "[A,I,F,M,S,P,E,D]")
	private String grauEscolaridade;

	@NotBlank
	@CPF
	@Size(min = 11, max = 11)
	@Pattern(regexp = "^[0-9]+$")
	private String cpf;

	@NotBlank
	private String situacaoAtual;


	public UsuarioLogadoResponse(
			Integer idUsuario,
			String email,
			String nome,
			String sobrenome,
			String genero,
			Date dataNascimento,
			String estadoCivil,
			String telefone,
			String cep,
			Date dataCadastro,
			String grauEscolaridade,
			String cpf,

			String situacaoAtual
	) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.telefone = telefone;
		this.cep = cep;
		this.dataCadastro = dataCadastro;
		this.grauEscolaridade = grauEscolaridade;
		this.cpf = cpf;

		this.situacaoAtual = situacaoAtual;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
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

	public String getTelefone() { return telefone; }

	public String getCep() { return this.cep; }

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public String getSituacaoAtual() {
		return situacaoAtual;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof UsuarioLogadoResponse)) return false;
		UsuarioLogadoResponse that = (UsuarioLogadoResponse) object;
		return this.idUsuario.equals(that.getIdUsuario());
	}

	@Override
	public int hashCode() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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

	public void setCep(String cep) { this.cep = cep; }

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSituacaoAtual(String situacaoAtual) {
		this.situacaoAtual = situacaoAtual;
	}
}
