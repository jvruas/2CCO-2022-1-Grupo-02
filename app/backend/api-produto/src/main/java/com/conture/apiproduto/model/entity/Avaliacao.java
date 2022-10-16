package com.conture.apiproduto.model.entity;

import com.conture.apiproduto.model.dto.request.AvaliacaoRequest;
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
	@Min(0)
	private Integer valor;

	@Size(max = 300)
	private String comentario;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@OneToOne
	@JoinColumn(name="fk_match")
	private Match match;


	public Avaliacao() {
	}

	private Avaliacao(
			Integer valor,
			String comentario,
			Integer fkMatch
	) {
		this.valor = valor;
		this.comentario = comentario;
		this.setMatch(fkMatch);
	}


	public static Avaliacao fromPattern(
			AvaliacaoRequest avaliacaoRequest,
			Integer fkMatch
	) {
		return new Avaliacao(
				avaliacaoRequest.getValor(),
				avaliacaoRequest.getComentario(),
				fkMatch
		);
	}

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

	public void setMatch(Integer fkMatch) {
		this.match = Match.fromPattern(fkMatch);
	}
}
