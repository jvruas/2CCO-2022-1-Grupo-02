package com.conture.apimensagemdireta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ChatDireto {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChatDireto;

    @Positive
    @NotNull
    private Integer fkUsuarioRemetente;

    @Positive
    @NotNull
    private Integer fkUsuarioDestinatario;

    // Getters e Setters
    public Integer getIdChatDireto() {
        return idChatDireto;
    }

    public void setIdChatDireto(Integer idChatDireto) {
        this.idChatDireto = idChatDireto;
    }

    public Integer getFkUsuarioRemetente() {
        return fkUsuarioRemetente;
    }

    public void setFkUsuarioRemetente(Integer fkUsuarioRemetente) {
        this.fkUsuarioRemetente = fkUsuarioRemetente;
    }

    public Integer getFkUsuarioDestinatario() {
        return fkUsuarioDestinatario;
    }

    public void setFkUsuarioDestinatario(Integer fkUsuarioDestinatario) {
        this.fkUsuarioDestinatario = fkUsuarioDestinatario;
    }
}
