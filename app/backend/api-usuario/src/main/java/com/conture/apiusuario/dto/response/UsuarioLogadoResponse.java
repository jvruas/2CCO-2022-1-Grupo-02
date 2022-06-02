package com.conture.apiusuario.dto.response;

import com.conture.apiusuario.entity.SituacaoAtual;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

public class UsuarioLogadoResponse {
	private Integer idUsuario;

	@Email
	@NotBlank
	@Column(unique=true)
	@Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
	private String email;

	@NotBlank
	@Size(max = 45, message = "O nome deve ter no máximo 45 letras")
	private String nome;

	@NotBlank
	@Size(max = 60, message = "O sobrenome deve ter no máximo 60 letras")
	private String sobrenome;

	@NotBlank
	@Size(min = 1, max = 1, message = "O genero deve ter 1 letra")
	private String genero;

	@Past
	private Date dataNascimento;

	@NotBlank
	@Size(min = 1, max = 1, message = "O cpf deve ter 1 letra")
	private String estadoCivil;

	// Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
	@PastOrPresent
	private Date dataCadastro;

	@NotBlank
	@Size(min = 1, max = 1, message = "A escolaridade deve ter 1 letra")
	private String grauEscolaridade;

	@CPF
	@NotBlank
	@Column(unique=true)
	@Size(min = 11, max = 11, message = "O cpf deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O CPF aceita apenas números")
	private String cpf;

	@NotNull
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
			SituacaoAtual situacaoAtual,
			String cpf

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
		this.situacaoAtual = situacaoAtual;
		this.cpf = cpf;
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
