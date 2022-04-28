package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByFkChatDiretoOrderByDataDesc(Long fkChatDireto);

    Mensagem findByIdMensagem(Long idMensagem);

    default boolean exists(Mensagem mensagem) {
        return false;
    }
}
