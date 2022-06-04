package com.conture.apiproduto.service;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UsuarioResposta {

	@Id
	private Integer idUsuario;
	private String email;
	private String senha;
	private String nome;
	private String sobrenome;
	private String cpf;
	private String genero;
	private Date dataNascimento;
	private String estadoCivil;
	private String telefone;
	private String cep;
	private Date dataCadastro;
	private String grauEscolaridade;
	private boolean removido;

	public Integer getIdUsuario() {
		return idUsuario;
	}

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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public boolean isRemovido() {
		return removido;
	}

	public UsuarioResposta() {
	}

	public UsuarioResposta(Integer idUsuario, String email, String senha, String nome, String sobrenome, String cpf, String genero, Date dataNascimento, String estadoCivil, String telefone, String cep, Date dataCadastro, String grauEscolaridade, boolean removido) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.telefone = telefone;
		this.cep = cep;
		this.dataCadastro = dataCadastro;
		this.grauEscolaridade = grauEscolaridade;
		this.removido = removido;
	}
}
