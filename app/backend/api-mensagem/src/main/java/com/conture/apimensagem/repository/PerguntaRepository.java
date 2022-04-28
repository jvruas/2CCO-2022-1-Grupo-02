package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    List<Pergunta>
    findByFkDonatarioAndFkDoadorAndFkProdutoDoacaoOrderByDataDesc(
            Long fkDonatario,
            Long fkDoador,
            Long fkProdutoDoacao
    );

	List<Pergunta> findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(Long fkDoador, Long fkProdutoDoacao);

    Pergunta findByIdPergunta(Long idPergunta);

    //Pergunta deleteByIdPergunta(Long idPergunta);
}
