package com.example.conture.domain;

public class MensagemDireta extends Mensagem{
    private Integer fkDestinatario;

    public Integer getFkDestinatario() {
        return fkDestinatario;
    }

    public void setFkDestinatario(Integer fkDestinatario) {
        this.fkDestinatario = fkDestinatario;
    }

    @Override
    public String exibirNotificacao() {
        return String.format(
                "Nova notificação direta\n\t%s\nRemetente: %d\t\t\t%s",
                super.getCorpoMensagem(),
                super.getFkRemetente(),
                super.getData()
        );
    }
}
