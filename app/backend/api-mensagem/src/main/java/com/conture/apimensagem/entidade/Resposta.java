package com.conture.apimensagem.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResposta;
    @PastOrPresent
    private LocalDateTime data;
    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;
    @NotNull
    private Long fkPergunta;
    @NotNull
    private Long fkDoador;
    @NotNull
    private Long fkProdutoDoacao;

    public Long getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Long idResposta) {
        this.idResposta = idResposta;
    }

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

    public Long getFkPergunta() {
        return fkPergunta;
    }

    public void setFkPergunta(Long fkPergunta) {
        this.fkPergunta = fkPergunta;
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
