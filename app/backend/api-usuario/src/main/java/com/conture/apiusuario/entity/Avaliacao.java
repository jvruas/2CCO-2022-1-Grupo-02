package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao {

   // Atributos

    @Id
    private int fkDoador;

    //@Id
    private int fkProdutoDoacao;

    //@Id
    private int fkDonatario;

    private int valor;

    private String comentario;

    //@Temporal(TemporalType.TIMESTAMP) // Indica o tipo de dado temporal que será guardado no campo do atributo mapeado
    @CreationTimestamp // Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
    private LocalDateTime data;

    // Getters e Setters
    public int getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(int fkDoador) {
        this.fkDoador = fkDoador;
    }

    public int getFkProdutoDoacao() {
        return fkProdutoDoacao;
    }

    public void setFkProdutoDoacao(int fkProdutoDoacao) {
        this.fkProdutoDoacao = fkProdutoDoacao;
    }

    public int getFkDonatario() {
        return fkDonatario;
    }

    public void setFkDonatario(int fkDonatario) {
        this.fkDonatario = fkDonatario;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
