package com.conture.apiusuario.entity;

import com.conture.apiusuario.dto.request.UsuarioCadastroRequest;
import com.conture.apiusuario.dto.request.UsuarioPerfilRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Email
    @NotBlank
	@Column(unique=true)
    @Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
    private String email;

    @NotBlank
    @Size(min = 6, max = 18, message = "A senha deve ter no máximo 18 letras")
    private String senha;

    @NotBlank
    @Size(max = 45, message = "O nome deve ter no máximo 45 letras")
    private String nome;

    @NotBlank
    @Size(max = 60, message = "O sobrenome deve ter no máximo 60 letras")
    private String sobrenome;

    @CPF
    @NotBlank
	@Column(unique=true)
    @Size(min = 11, max = 11, message = "O cpf deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O CPF aceita apenas números")
	private String cpf;

    @NotBlank
    @Size(min = 1, max = 1, message = "O genero deve ter 1 letra")
    private String genero;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;

    @NotBlank
    @Size(min = 1, max = 1, message = "O cpf deve ter 1 letra")
    private String estadoCivil;

	@NotBlank
	@Size(min = 11, max = 11, message = "O telefone deve ter 11 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O telefone aceita apenas números")
	private String telefone;

    @NotBlank
	@Size(min = 8, max = 8, message = "O cep deve ter no máximo 8 letras")
	@Pattern(regexp = "^[0-9]+$", message = "O CEP aceita apenas números")
	private String cep;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @NotBlank
    @Size(min = 1, max = 1, message = "A escolaridade deve ter 1 letra")
    private String grauEscolaridade;

	@NotNull
	@JsonIgnore
	private boolean removido;

	@ManyToOne()
	private SituacaoAtual situacaoAtual;

	public Usuario() {}

	private Usuario (Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	private Usuario(
			String email,
			String senha,
			String nome,
			String sobrenome,
			String cpf,
			String genero,
			Date dataNascimento,
			String estadoCivil,
			String telefone,
			String cep,
			String grauEscolaridade,
			Integer fkSituacaoAtual
	) {
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

		SituacaoAtual situacaoAtual = new SituacaoAtual();

		situacaoAtual.setIdSituacaoAtual(fkSituacaoAtual);

		this.situacaoAtual = situacaoAtual;
	}

	public static Usuario fromPattern(Integer idUsuario) {
		return new Usuario(idUsuario);
	}

	public static Usuario fromPattern(UsuarioCadastroRequest novoUsuario) {
		return new Usuario(
				novoUsuario.getEmail(),
				novoUsuario.getSenha(),
				novoUsuario.getNome(),
				novoUsuario.getSobrenome(),
				novoUsuario.getCpf(),
				novoUsuario.getGenero(),
				novoUsuario.getDataNascimento(),
				novoUsuario.getEstadoCivil(),
				novoUsuario.getTelefone(),
				novoUsuario.getCep(),
				novoUsuario.getGrauEscolaridade(),
				novoUsuario.getFkSituacaoAtual()
		);
	}

	public Integer getIdUsuario() { return idUsuario; }

	public String getEmail() { return email; }

	public String getSenha() { return senha; }

	public String getNome() { return nome; }

	public String getSobrenome() { return sobrenome; }

	public String getCpf() { return cpf; }

	public String getGenero() { return genero; }

	public Date getDataNascimento() { return dataNascimento; }

	public String getEstadoCivil() { return estadoCivil; }

	public String getTelefone() { return telefone; }

	public String getCep() { return cep; }

	public Date getDataCadastro() { return dataCadastro; }

	public String getGrauEscolaridade() { return grauEscolaridade; }

	public boolean isRemovido() { return removido; }

	public SituacaoAtual getSituacaoAtual() { return situacaoAtual; }

	public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

	public void setEmail(String email) { this.email = email; }

	public void setSenha(String senha) { this.senha = senha; }

	public void setNome(String nome) { this.nome = nome; }

	public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

	public void setCpf(String cpf) { this.cpf = cpf; }

	public void setGenero(String genero) { this.genero = genero; }

	public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

	public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

	public void setTelefone(String telefone) { this.telefone = telefone; }

	public void setCep(String cep) { this.cep = cep; }

	public void setGrauEscolaridade(String grauEscolaridade) { this.grauEscolaridade = grauEscolaridade; }

	public void setRemovido(boolean removido) { this.removido = removido; }

	public void setSituacaoAtual(Integer fksituacaoAtual) {
		SituacaoAtual novaSituacaoAtual = new SituacaoAtual();
		novaSituacaoAtual.setIdSituacaoAtual(fksituacaoAtual);
		this.situacaoAtual = novaSituacaoAtual;
	}
}
