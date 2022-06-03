package com.conture.apiusuario.dto.response;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.util.Date;

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
}
