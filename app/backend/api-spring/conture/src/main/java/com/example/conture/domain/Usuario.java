package com.example.conture.domain;

public class Usuario {
    private String login;
    private String senha;
    private String nome;
    private Boolean autenticacao = false;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.autenticacao = autenticacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Boolean getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(Boolean autenticacao) {
        this.autenticacao = autenticacao;
    }
}
