package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reporte")
public class Reporte {

    // Atributos
    //@Id
	@NotNull
	@Positive
    private Long fkReportador;

    //@Id
	@NotNull
	@Positive
    private Long fkReportado;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    // @Temporal(TemporalType.TIMESTAMP) // Indica o tipo de dado temporal que será guardado no campo do atributo mapeado
    @CreationTimestamp // Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
    private LocalDateTime data;

	@NotNull
	@Positive
    private Long fkTipoReporte;

	// Construtor
	public Reporte(Long fkReportador, Long fkReportado, Long fkTipoReporte) {
		this.fkReportador = fkReportador;
		this.fkReportado = fkReportado;
		this.fkTipoReporte = fkTipoReporte;
	}

	// Getters e Setters
    public Long getFkReportador() {
        return fkReportador;
    }

    public void setFkReportador(Long fkReportador) {
        this.fkReportador = fkReportador;
    }

    public Long getFkReportado() {
        return fkReportado;
    }

    public void setFkReportado(Long fkReportado) {
        this.fkReportado = fkReportado;
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getFkTipoReporte() {
        return fkTipoReporte;
    }

    public void setFkTipoReporte(Long fkTipoReporte) {
        this.fkTipoReporte = fkTipoReporte;
    }
}
