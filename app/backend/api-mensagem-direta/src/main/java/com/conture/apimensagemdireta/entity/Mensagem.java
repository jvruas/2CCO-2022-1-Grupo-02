package com.conture.apimensagemdireta.entity;

import com.conture.apimensagemdireta.dto.response.MensagemResponse;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Mensagem {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMensagem;

    @NotNull
    private boolean visualizado;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    @NotBlank
    @Size(min = 1, max = 200, message = "O tamanho mínimo da mensagem é 1 caractere e o máximo de 200")
    private String mensagem;

    @ManyToOne
	@JoinColumn(name="fk_chat_direto")
	private ChatDireto fkChatDireto;

    public MensagemResponse converterMsgResponse(Mensagem m) {
        return new MensagemResponse(m.isVisualizado(), m.getData(), m.getMensagem(), m.getFkChatDireto().getFkUsuarioRemetente());
    }

    // Getters e Setters
    public Integer getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Integer idMensagem) {
        this.idMensagem = idMensagem;
    }

    public boolean isVisualizado() {
        return visualizado;
    }

    public void setVisualizado(boolean visualizado) {
        this.visualizado = visualizado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ChatDireto getFkChatDireto() {
        return fkChatDireto;
    }

    public void setFkChatDireto(ChatDireto fkChatDireto) {
        this.fkChatDireto = fkChatDireto;
    }
}
