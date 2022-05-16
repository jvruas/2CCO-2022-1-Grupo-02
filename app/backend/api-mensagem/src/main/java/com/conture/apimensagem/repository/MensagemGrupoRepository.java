package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface MensagemGrupoRepository extends JpaRepository<Pergunta, Integer> {

    List<Pergunta> findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(Long fkDoador, Long fkProdutoDoacao);

    List<Pergunta> findByIdPerguntaOrderByDataDesc(Long idPergunta);
    boolean existsByIdPergunta(Long idPergunta);

    List<Pergunta> findByIdPergunta(Long idPergunta);
    Pergunta deleteByIdPergunta(Long IdPergunta);



    @Query("update Pergunta r set r.mensagem = ?2, r.data = ?3 where r.idPergunta = ?1")
    @Transactional
    @Modifying
    void updateMensagemResposta(Long idPergunta, String mensagem, Date data);
}

