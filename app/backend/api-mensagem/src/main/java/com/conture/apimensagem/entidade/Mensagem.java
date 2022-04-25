package com.conture.apimensagem.entidade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;
    @PastOrPresent
    private LocalDateTime data;
    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;
    @NotNull
    private Long fkChatDireto;

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
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

    public Long getFkChatDireto() {
        return fkChatDireto;
    }

    public void setFkChatDireto(Long fkChatDireto) {
        this.fkChatDireto = fkChatDireto;
    }
}
