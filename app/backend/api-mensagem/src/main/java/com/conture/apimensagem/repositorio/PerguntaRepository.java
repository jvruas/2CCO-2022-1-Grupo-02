package com.conture.apimensagem.repositorio;

import com.conture.apimensagem.entidade.Mensagem;
import com.conture.apimensagem.entidade.Pergunta;
import com.conture.apimensagem.entidade.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    List<Pergunta>
    findByFkDonatarioAndFkDoadorAndFkProdutoDoacaoOrderByDataAsc(
            Long fkDonatario,
            Long fkDoador,
            Long fkProdutoDoacao
    );

    Pergunta findByIdPergunta(Long idPergunta);

    default boolean exists(Pergunta pergunta) {
        return false;
    }

    //Pergunta deleteByIdPergunta(Long idPergunta);
}
