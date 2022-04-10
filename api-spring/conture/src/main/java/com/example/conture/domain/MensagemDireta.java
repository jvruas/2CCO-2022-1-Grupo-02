package com.example.conture.Entidade;

public class MensagemDireta extends Mensagem{
    private Integer fkDestinario;

    public MensagemDireta(Integer idMensagem, Integer fkRemetente, String corpoMensagem, String data, Integer fkDestinario) {
        super(idMensagem, fkRemetente, corpoMensagem, data);
        this.fkDestinario = fkDestinario;
    }

    public Integer getFkDestinario() {
        return fkDestinario;
    }

    public void setFkDestinario(Integer fkDestinario) {
        this.fkDestinario = fkDestinario;
    }
}
