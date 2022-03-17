package com.example.conture.domain;

public class MensagemGrupo extends Mensagem {
    private Integer fkDoador;
    private Integer idProduto;
    private Integer idTopico;

    public Integer getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Integer fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdTopico() {
        return idTopico;
    }

    public void setIdTopico(Integer idTopico) {
        this.idTopico = idTopico;
    }

    @Override
    public String exibirNotificacao() {
        return String.format(
                "\nNova notificação do topico %d no grupo %d%d\n\t%s\t\t\t%s",
                this.getIdTopico(),
                this.getFkDoador(),
                this.getIdProduto(),
                super.getCorpoMensagem(),
                super.getData()
        );
    }
}
