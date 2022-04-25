package com.conture.apimensagem.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPergunta;
    @NotNull
    private Long fkDonatario;
    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;
    @PastOrPresent
    private LocalDateTime data;

    @NotNull
    private Long fkDoador;
    @NotNull
    private Long fkProdutoDoacao;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Long getFkDonatario() {
        return fkDonatario;
    }

    public void setFkDonatario(Long fkDonatario) {
        this.fkDonatario = fkDonatario;
    }

    public Long getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Long fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Long getFkProdutoDoacao() {
        return fkProdutoDoacao;
    }

    public void setFkProdutoDoacao(Long fkProdutoDoacao) {
        this.fkProdutoDoacao = fkProdutoDoacao;
    }
}
