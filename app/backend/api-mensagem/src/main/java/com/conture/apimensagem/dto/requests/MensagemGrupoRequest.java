package com.conture.apimensagem.dto.requests;

import com.conture.apimensagem.entidade.MensagemGrupo;
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

	@ManyToOne
	@JoinColumn(name = "fk_mensagem_principal_id_mensagem_grupo")
	@Positive
	@NotNull
	private MensagemGrupo fkMensagemPrincipal;



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

	public MensagemGrupo getFkMensagemPrincipal() {
		return fkMensagemPrincipal;
	}

	public void setFkMensagemPrincipal(MensagemGrupo fkMensagemPrincipal) {
		this.fkMensagemPrincipal = fkMensagemPrincipal;
	}
}
