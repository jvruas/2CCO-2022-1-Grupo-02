package com.example.conture.domain;

public class MensagemDireta extends Mensagem{
    private Integer fkDestinatario;

    public Integer getFkDestinatario() {
        return fkDestinatario;
    }

    public void setFkDestinatario(Integer fkDestinatario) {
        this.fkDestinatario = fkDestinatario;
    }
}
