package com.conture.apimensagem.repositorio;

import com.conture.apimensagem.entidade.Mensagem;
import com.conture.apimensagem.entidade.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByFkPerguntaOrderByDataAsc(Long fkPergunta);

    Resposta findByIdResposta(Long idResposta);

    void deleteByFkPergunta(Long fkPergunta);

	int countByFkPergunta(Long fkPergunta);

    default boolean exists(Resposta resposta) {
        return false;
    }

    default boolean existsByFkPergunta(Long fkPergunta) {
        return false;
    }


    //Resposta deleteByIdResposta(Long idResposta);
}
