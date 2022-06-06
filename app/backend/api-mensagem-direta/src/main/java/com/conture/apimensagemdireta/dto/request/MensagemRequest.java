package com.conture.apimensagemdireta.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class MensagemRequest {

    // Atributos
    @Positive
    @NotNull
    private Integer fkUsuarioRemetente;

    @Positive
    @NotNull
    private Integer fkUsuarioDestinatario;

    @NotBlank
    @Size(min = 1, max = 200, message = "O tamanho mínimo da mensagem é 1 caractere e o máximo de 200")
    private String mensagem;

	@JsonIgnore
    private boolean visualizacao;

    // Construtor
    public MensagemRequest(Integer fkUsuarioRemetente, Integer fkUsuarioDestinatario, String mensagem) {
        this.fkUsuarioRemetente = fkUsuarioRemetente;
        this.fkUsuarioDestinatario = fkUsuarioDestinatario;
        this.mensagem = mensagem;
        this.visualizacao = false;
    }

    // Getters
    public Integer getFkUsuarioRemetente() {
        return fkUsuarioRemetente;
    }

    public Integer getFkUsuarioDestinatario() {
        return fkUsuarioDestinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public boolean isVisualizacao() {
        return visualizacao;
    }
}
