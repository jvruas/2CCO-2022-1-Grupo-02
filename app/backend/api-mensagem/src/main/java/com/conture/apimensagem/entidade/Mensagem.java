package com.conture.apimensagem.entidade;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;

    @PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date data;

	@NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;

	@Positive
    @NotNull
    private Long fkChatDireto;

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

	public Date getData() { return data; }

	public void setData(Date data) { this.data = data; }

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
