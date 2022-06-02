package com.conture.apiproduto.dto.request;

import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.rest.usuario.UsuarioResposta;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class ProdutoDoacaoRequest {
    @ManyToOne
    private UsuarioResposta usuario;

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
            UsuarioResposta usuario,
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
        this.usuario = usuario;
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

    public UsuarioResposta getUsuario() {
        return usuario;
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
