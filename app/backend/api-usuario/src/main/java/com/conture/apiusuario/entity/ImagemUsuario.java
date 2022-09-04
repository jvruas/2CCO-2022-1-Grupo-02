package com.conture.apiusuario.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class ImagemUsuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idImagemUsuario;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "O Tipo de imagem deve ter 1 letra")
	@Pattern(regexp = "[B,P]")
	private String tipoImagem;

	@NotNull
	@JsonIgnore
	@Column(length = 16_777_216)
	private byte[] imagemUsuario;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private Usuario usuario;


	public ImagemUsuario(){}

	private ImagemUsuario(
			String tipoImagem,
			byte[] imagemUsuario,
			Integer fkUsuario
	) {
		this.tipoImagem = tipoImagem;
		this.imagemUsuario = imagemUsuario;
		this.setUsuario(fkUsuario);
	}

	public static ImagemUsuario fromPattern(
			String tipoImagem,
			byte[] imagemUsuario,
			Integer fkUsuario
	) {
		return new ImagemUsuario(tipoImagem, imagemUsuario, fkUsuario);
	}

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

	public void setUsuario(Integer fkUsuario) {
		this.usuario = Usuario.fromPattern(fkUsuario);
	}
}
