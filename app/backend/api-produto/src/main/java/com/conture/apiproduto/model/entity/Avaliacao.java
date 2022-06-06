package com.conture.apiproduto.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacao;

	@NotNull
	@Max(5)
	@Min(1)
	private Integer valor;

	@NotBlank
	@Size(max = 300)
	private String comentario;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@OneToOne
	private Match match;


	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public Integer getValor() {
		return valor;
	}

	public String getComentario() {
		return comentario;
	}

	public Date getData() {
		return data;
	}

	public Match getMatch() {
		return match;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
