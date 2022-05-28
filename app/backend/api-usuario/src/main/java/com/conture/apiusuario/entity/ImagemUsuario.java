package com.conture.apiusuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ImagemUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagemUsuario;

	@NotNull
	@JsonIgnore
	@Column(length = 16_777_216)
	private byte[] imagemUsuario;

	@NotBlank
	@Size(min = 1, max = 1, message = "O Tipo de imagem deve ter 1 letra")
	private String tipoImagem;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Integer getIdImagemUsuario() { return idImagemUsuario; }

	public void setIdImagemUsuario(Integer idImagemUsuario) { this.idImagemUsuario = idImagemUsuario; }

	public byte[] getImagemUsuario() {
		return imagemUsuario;
	}

	public void setImagemUsuario(byte[] imagemUsuario) {
		this.imagemUsuario = imagemUsuario;
	}

	public String getTipoImagem() {
		return tipoImagem;
	}

	public void setTipoImagem(String tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
