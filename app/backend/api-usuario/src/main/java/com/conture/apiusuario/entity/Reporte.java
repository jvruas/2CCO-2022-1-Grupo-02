package com.conture.apiusuario.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Reporte {

    // Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReporte;

	@NotNull
	@Positive
    private Long fkReportador;

	@NotNull
	@Positive
    private Long fkReportado;

    @CreationTimestamp // Indica que o atributo receberá automaticamente a data e hora do sistema no momento da criação de um registro
    private LocalDateTime data;

	@NotNull
	@Positive
    private Long fkTipoReporte;

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
