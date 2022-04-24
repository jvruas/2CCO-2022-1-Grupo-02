package com.conture.apiusuario.resposta;

import java.time.LocalDate;
import java.util.Objects;

public class UsuarioLogado {

    // Atributos
    private Long idUsuario;

    private String nome;

    private String sobrenome;

    private String genero;

    private LocalDate dataNascimento;

    private String estadoCivil;

    private String cep;

    private LocalDate dataCadastro;

    private String escolaridade;

    private int fkSituacaoAtual;

    // Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public int getFkSituacaoAtual() {
        return fkSituacaoAtual;
    }

    public void setFkSituacaoAtual(int fkSituacaoAtual) {
        this.fkSituacaoAtual = fkSituacaoAtual;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UsuarioLogado)) return false;
        UsuarioLogado that = (UsuarioLogado) object;
        return this.idUsuario.equals(that.getIdUsuario());
    }
}
