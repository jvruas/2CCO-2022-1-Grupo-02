package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

// TODO: Passar para a API de produto
@Entity
public class Avaliacao {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAvaliacao;

	@NotNull
	@PositiveOrZero
	@Max(5)
    private Integer valor;

	@Size(max = 300, message = "O comentario deve ter no máximo 300 letras")
    private String comentario;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    private Date data;

	@NotNull
	@Positive
	private Integer fkMatch;


	public Integer getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getData() {
		return data;
	}

//	public void setData(Date data) {
//		this.data = data;
//	}

	public Integer getFkMatch() {
		return fkMatch;
	}

	public void setFkMatch(Integer fkMatch) {
		this.fkMatch = fkMatch;
	}
}
