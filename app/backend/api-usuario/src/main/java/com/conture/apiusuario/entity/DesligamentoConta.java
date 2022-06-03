package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class DesligamentoConta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDesligamentoConta;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 1, message = "O movito do desligamento deve ter 1 letra")
	@Pattern(regexp = "[A,T,Q,P,N,X]")
	private String motivoDesligamentoConta;

	@PastOrPresent
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@NotNull
	@ManyToOne
	private Usuario usuario;


	public DesligamentoConta() {}

	private DesligamentoConta(
			String motivoDesligamentoConta,
			Integer fkUsuario
	) {
		this.motivoDesligamentoConta = motivoDesligamentoConta;
		this.setUsuario(fkUsuario);
	}

	public static DesligamentoConta fromPattern(
			String motivoDesligamentoConta,
			Integer fkUsuario
	) {
		return new DesligamentoConta(motivoDesligamentoConta, fkUsuario);
	}

	public Integer getIdDesligamentoConta() {
		return idDesligamentoConta;
	}

	public void setIdDesligamentoConta(Integer idDesligamentoConta) {
		this.idDesligamentoConta = idDesligamentoConta;
	}

	public String getMotivoDesligamentoConta() {
		return motivoDesligamentoConta;
	}

	public void setMotivoDesligamentoConta(String motivoDesligamentoConta) {
		this.motivoDesligamentoConta = motivoDesligamentoConta;
	}

	public Date getData() {
		return data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer fkUsuario) {
		this.usuario = Usuario.fromPattern(fkUsuario);
	}
}
