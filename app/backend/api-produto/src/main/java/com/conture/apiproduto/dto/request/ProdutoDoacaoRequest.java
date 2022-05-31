package com.conture.apiproduto.dto.request;

import com.conture.apiproduto.entity.CategoriaProduto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class ProdutoDoacaoRequest {
    @NotNull
    @Positive
    private Long fkDoador;

    @NotBlank
    @NotNull
    @Size(min = 2)
    private String nome;

    @NotBlank
    @NotNull
    private String marca;

    @NotBlank
    @NotNull
    private String modelo;


    @NotBlank
    @NotNull
    @Size(min = 5)
    private String descricao;


    private boolean defeito;

    private boolean status;

    private boolean entrega;

    @NotNull
    @PositiveOrZero
    private int quantidadeVisualizacao;


	@ManyToOne
    private CategoriaProduto fkCategoriaProduto;

    public ProdutoDoacaoRequest(
            Long fkDoador,
            String nome,
            String marca,
            String modelo,
            String descricao,
            boolean defeito,
            boolean status,
            boolean entrega,
            int quantidadeVisualizacao,
            CategoriaProduto fkCategoriaProduto
    ) {
        this.fkDoador = fkDoador;
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.descricao = descricao;
        this.defeito = defeito;
        this.status = status;
        this.entrega = entrega;
        this.quantidadeVisualizacao = quantidadeVisualizacao;
        this.fkCategoriaProduto = fkCategoriaProduto;
    }

    public Long getFkDoador() {
        return fkDoador;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isDefeito() {
        return defeito;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public int getQuantidadeVisualizacao() {
        return quantidadeVisualizacao;
    }

    public CategoriaProduto getFkCategoriaProduto() {
        return fkCategoriaProduto;
    }
}
