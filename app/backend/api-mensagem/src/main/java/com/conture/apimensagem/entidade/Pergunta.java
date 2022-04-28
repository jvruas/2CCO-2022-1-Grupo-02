package com.conture.apimensagem.entidade;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPergunta;

	@Positive
    @NotNull
    private Long fkDonatario;

    @NotBlank
    @Size(min = 3, max = 100)
    private String mensagem;

	@PastOrPresent
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Positive
    @NotNull
    private Long fkDoador;

	@Positive
    @NotNull
    private Long fkProdutoDoacao;

	public Date getData() { return data; }

	public void setData(Date data) { this.data = data; }

	public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(Long idPergunta) {
        this.idPergunta = idPergunta;
    }

    public Long getFkDonatario() {
        return fkDonatario;
    }

    public void setFkDonatario(Long fkDonatario) {
        this.fkDonatario = fkDonatario;
    }

    public Long getFkDoador() {
        return fkDoador;
    }

    public void setFkDoador(Long fkDoador) {
        this.fkDoador = fkDoador;
    }

    public Long getFkProdutoDoacao() {
        return fkProdutoDoacao;
    }

    public void setFkProdutoDoacao(Long fkProdutoDoacao) {
        this.fkProdutoDoacao = fkProdutoDoacao;
    }
}
