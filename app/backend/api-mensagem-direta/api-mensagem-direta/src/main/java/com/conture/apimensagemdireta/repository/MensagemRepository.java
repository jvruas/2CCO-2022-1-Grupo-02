package com.conture.apimensagemdireta.repository;

import com.conture.apimensagemdireta.entity.ChatDireto;
import com.conture.apimensagemdireta.entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {

    @Query("select m from Mensagem m where m.fkChatDireto = ?1 or m.fkChatDireto = ?2 ORDER BY m.data ASC")
    List<Mensagem> acharPorFkChatDiretoOrderByDataDesc(ChatDireto fkChatDireto1, ChatDireto fkChatDireto2);


    List<Mensagem> findByFkChatDiretoAndVisualizado(ChatDireto fkChatDireto, Boolean Visualizado);

}
