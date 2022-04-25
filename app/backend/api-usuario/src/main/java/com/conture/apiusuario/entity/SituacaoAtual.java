package com.conture.apiusuario.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class SituacaoAtual {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSituacaoAtual;

	@NotBlank
    @Size(max = 45, message = "A situação atual deve ter no máximo 45 letras")
    private String nome;

    // Getters e Settes
    public int getIdSituacaoAtual() {
        return idSituacaoAtual;
    }

    public void setIdSituacaoAtual(int idSituacaoAtual) {
        this.idSituacaoAtual = idSituacaoAtual;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
