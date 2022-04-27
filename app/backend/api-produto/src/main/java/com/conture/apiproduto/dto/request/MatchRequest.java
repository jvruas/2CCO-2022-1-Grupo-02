package com.conture.apiproduto.dto.request;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class MatchRequest {
    @NotNull
    @Positive
    private Long fkDoador;

    @NotNull
    @Positive
    private Long fkProdutoDoacao;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long fkDonatario;

    @NotNull
    @Positive
    @Max(100)
    @Min(0)
    private Double matchPorcentagem;

    @NotBlank
    @NotNull
    @Size(min = 1,max = 1 )
    private String status;

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

    public Long getFkDonatario() {
        return fkDonatario;
    }

    public void setFkDonatario(Long fkDonatario) {
        this.fkDonatario = fkDonatario;
    }

    public Double getMatchPorcentagem() {
        return matchPorcentagem;
    }

    public void setMatchPorcentagem(Double matchPorcentagem) {
        this.matchPorcentagem = matchPorcentagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
