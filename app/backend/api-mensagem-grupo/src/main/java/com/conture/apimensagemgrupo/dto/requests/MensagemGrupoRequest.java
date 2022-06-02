package com.conture.apimensagemgrupo.dto.requests;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

public class MensagemGrupoRequest {

	@NotBlank
	@Size(min = 3, max = 100)
	private String mensagem;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Positive
	@NotNull
	private  Integer fkUsuario;

	@Positive
	@NotNull
	private Integer fkProdutoDoacao;

	@Positive
	private Integer fkMensagemPrincipal;



	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getFkUsuario() {
		return fkUsuario;
	}

	public void setFkUsuario(Integer fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	public Integer getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Integer fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Integer getFkMensagemPrincipal() {
		return fkMensagemPrincipal;
	}

	public void setFkMensagemPrincipal(Integer fkMensagemPrincipal) {
		this.fkMensagemPrincipal = fkMensagemPrincipal;
	}
}
