package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Avaliacao {

   	// Atributos
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fkProdutoDoacao;

	@NotNull
	@Positive
	private Long fkDonatario;

	@NotNull
	@Positive
	private Long fkDoador;

	@NotNull
	@Positive
    private Long valor;

	@Size(max = 300, message = "O comentario deve ter no máximo 300 letras")
    private String comentario;

    @CreationTimestamp // Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
    private LocalDateTime data;

	// Getters e Setters
	public Long getFkProdutoDoacao() {
		return fkProdutoDoacao;
	}

	public void setFkProdutoDoacao(Long fkProdutoDoacao) {
		this.fkProdutoDoacao = fkProdutoDoacao;
	}

	public Long getFkDoador() {
		return fkDoador;
	}

	public void setFkDoador(Long fkDoador) {
		this.fkDoador = fkDoador;
	}

	public Long getFkDonatario() {
		return fkDonatario;
	}

	public void setFkDonatario(Long fkDonatario) {
		this.fkDonatario = fkDonatario;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
}
