package com.example.conture.domain;

public abstract class Mensagem {
    private Integer idMensagem;
    private Integer fkRemetente;
    private String corpoMensagem;
    private String data;

    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Integer getFkRemetente() {
        return fkRemetente;
    }

    public void setFkRemetente(Integer fkRemetente) {
        this.fkRemetente = fkRemetente;
    }

    public String getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(String corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public abstract String exibirNotificacao();

}
