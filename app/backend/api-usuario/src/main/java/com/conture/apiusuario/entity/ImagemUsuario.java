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
	@NotBlank
	private String bucketName;

	@NotNull
	@NotBlank
	private String objectName;

	@NotNull
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private Usuario usuario;


	public ImagemUsuario(){}

	private ImagemUsuario(
			String tipoImagem,
			String bucketName,
			String objectName,
			Integer fkUsuario
	) {
		this.tipoImagem = tipoImagem;
		this.bucketName = bucketName;
		this.objectName = objectName;
		this.setUsuario(fkUsuario);
	}

	public static ImagemUsuario fromPattern(
			String tipoImagem,
			String bucketName,
			String objectName,
			Integer fkUsuario
	) {
		return new ImagemUsuario(tipoImagem, bucketName, objectName, fkUsuario);
	}

	public Integer getIdImagemUsuario() { return idImagemUsuario; }

	public void setIdImagemUsuario(Integer idImagemUsuario) { this.idImagemUsuario = idImagemUsuario; }

	public String getBucketName() {
		return this.bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
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
