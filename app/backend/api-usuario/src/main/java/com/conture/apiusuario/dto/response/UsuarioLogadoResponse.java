package com.conture.apiusuario.dto.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UsuarioLogadoResponse {

    // Atributos
	private Long idUsuario;

	@Email
	@NotBlank
	@Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
	private String email;

	@NotBlank
	@Size(max = 45, message = "O nome deve ter no máximo 45 letras")
	private String nome;

	@NotBlank
	@Size(max = 60, message = "O sobrenome deve ter no máximo 60 letras")
	private String sobrenome;

	@NotBlank
	@Size(max = 9, message = "O genero deve ter no máximo 9 letras")
	private String genero;

	private LocalDate dataNascimento;

	@NotBlank
	@Size(max = 10, message = "O cpf deve ter no máximo 10 letras")
	private String estadoCivil;

	// Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
	private LocalDate dataCadastro;

	@NotBlank
	@Size(max = 30, message = "A escolaridade deve ter no máximo 30 letras")
	private String escolaridade;

	private Long fkSituacaoAtual;

	// Construtor
	public UsuarioLogadoResponse(Long idUsuario, String email, String nome, String sobrenome, String genero, LocalDate dataNascimento, String estadoCivil, LocalDate dataCadastro, String escolaridade, Long fkSituacaoAtual) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.dataCadastro = dataCadastro;
		this.escolaridade = escolaridade;
		this.fkSituacaoAtual = fkSituacaoAtual;
	}

	// Getters e Setters
	public Long getIdUsuario() {
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

	public String getGenero() {
		return genero;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public Long getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}

	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UsuarioLogadoResponse)) return false;
        UsuarioLogadoResponse that = (UsuarioLogadoResponse) object;
        return this.idUsuario.equals(that.getIdUsuario());
    }
}
