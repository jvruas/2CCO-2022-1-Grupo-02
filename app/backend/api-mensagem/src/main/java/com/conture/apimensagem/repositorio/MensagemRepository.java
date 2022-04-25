package com.conture.apimensagem.repositorio;

import com.conture.apimensagem.entidade.Mensagem;
import com.conture.apimensagem.entidade.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByFkChatDiretoOrderByDataAsc(Long fkChatDireto);

    Mensagem findByIdMensagem(Long idMensagem);

    default boolean exists(Mensagem mensagem) {
        return false;
    }
}
