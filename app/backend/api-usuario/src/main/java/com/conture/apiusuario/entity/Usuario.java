package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Email
    @NotBlank
    @Size(max = 80, message = "O e-mail deve ter no máximo 80 letras")
    private String email;

    @NotBlank
    @Size(max = 18, message = "A senha deve ter no máximo 18 letras")
    private String senha;

    @NotBlank
    @Size(max = 45, message = "O nome deve ter no máximo 45 letras")
    private String nome;

    @NotBlank
    @Size(max = 60, message = "O sobrenome deve ter no máximo 60 letras")
    private String sobrenome;

    @CPF
    @NotBlank
    @Size(max = 11, message = "O cpf deve ter no máximo 11 letras")
    private String cpf;

    @NotBlank
    @Size(max = 9, message = "O genero deve ter no máximo 9 letras")
    private String genero;

    private LocalDate dataNascimento;

    @NotBlank
    @Size(max = 10, message = "O cpf deve ter no máximo 10 letras")
    private String estadoCivil;

    @NotBlank
    private String cep;

    @CreationTimestamp // Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
    private LocalDate dataCadastro;

    @NotBlank
    @Size(max = 30, message = "A escolaridade deve ter no máximo 30 letras")
    private String escolaridade;

    private Long fkSituacaoAtual;

    // Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

	public Long getFkSituacaoAtual() {
		return fkSituacaoAtual;
	}

	public void setFkSituacaoAtual(Long fkSituacaoAtual) {
		this.fkSituacaoAtual = fkSituacaoAtual;
	}
}
