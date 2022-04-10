package com.example.conture.Entidade;

public class MensagemGrupo extends Mensagem {
    private Integer fkDoador;
    private Integer idProduto;
    private Integer idTopico;

    public MensagemGrupo(Integer idMensagem, Integer fkRemetente, String corpoMensagem, String data, Integer fkDoador, Integer idProduto, Integer idTopico) {
        super(idMensagem, fkRemetente, corpoMensagem, data);
        this.fkDoador = fkDoador;
        this.idProduto = idProduto;
        this.idTopico = idTopico;
    }

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
}
