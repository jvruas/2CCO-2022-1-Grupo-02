package com.conture.apimensagemdireta.api.rest.usuario;

import java.util.Date;

public class UsuarioResposta {

	// Atributos
	private Integer idUsuario;
	private String email;
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

	// Construtor
	public UsuarioResposta(
			Integer idUsuario,
			String email,
			String nome,
			String sobrenome,
			String cpf,
			String genero,
			Date dataNascimento,
			String estadoCivil,
			String telefone,
			String cep,
			Date dataCadastro,
			String grauEscolaridade,
			boolean removido
	) {
		this.idUsuario = idUsuario;
		this.email = email;
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

	// Getters
	public Integer getIdUsuario() {
		return idUsuario;
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
}
