package com.conture.apimensagem.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ChatDireto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChatDireto;

	@Positive
	@NotNull
    private Long fkUsuarioRemetente;

	@Positive
	@NotNull
    private Long fkUsuarioDestinatario;

    public Long getIdChatDireto() {
        return idChatDireto;
    }

    public void setIdChatDireto(Long idChatDireto) {
        this.idChatDireto = idChatDireto;
    }

    public Long getFkUsuarioRemetente() {
        return fkUsuarioRemetente;
    }

    public void setFkUsuarioRemetente(Long fkUsuarioRemetente) {
        this.fkUsuarioRemetente = fkUsuarioRemetente;
    }

    public Long getFkUsuarioDestinatario() {
        return fkUsuarioDestinatario;
    }

    public void setFkUsuarioDestinatario(Long fkUsuarioDestinatario) { this.fkUsuarioDestinatario = fkUsuarioDestinatario; }
}
