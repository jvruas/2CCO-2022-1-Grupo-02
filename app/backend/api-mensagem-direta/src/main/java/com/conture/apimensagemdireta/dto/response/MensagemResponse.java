package com.conture.apimensagemdireta.dto.response;

import com.conture.apimensagemdireta.api.rest.usuario.UsuarioResposta;
import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class MensagemResponse {

	// Atributos
	private boolean visualizado;

	private Date data;

	private String mensagem;

	private UsuarioResposta remetente;

	public MensagemResponse(
			boolean visualizado,
			Date data,
			String mensagem,
			UsuarioResposta remetente
	) {
		this.visualizado = visualizado;
		this.data = data;
		this.mensagem = mensagem;
		this.remetente = remetente;
	}

	// Getters e Setters
	public boolean isVisualizado() {
		return visualizado;
	}

	public void setVisualizado(boolean visualizado) {
		this.visualizado = visualizado;
	}

	public Date getData() {
		return data;
	}

	public String getMensagem() {
		return mensagem;
	}

	public UsuarioResposta getRemetente() { return remetente; }
}
