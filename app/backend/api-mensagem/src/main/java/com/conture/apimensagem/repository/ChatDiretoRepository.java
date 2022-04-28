package com.conture.apimensagem.repository;

import com.conture.apimensagem.entidade.ChatDireto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatDiretoRepository extends JpaRepository<ChatDireto, Long> {
	boolean existsByIdChatDireto(Long idChatDireto);
}
