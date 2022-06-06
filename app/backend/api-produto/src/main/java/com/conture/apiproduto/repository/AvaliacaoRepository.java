package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.dto.response.AvaliacaoResponse;
import com.conture.apiproduto.model.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
	@Query("select case when count(a) = 1 then true else false end from Avaliacao a where a.match.idMatch = ?1")
	boolean hasByIdMatch(Integer idMatch);

	Optional<Avaliacao> findTop1ByMatchIdMatchAndMatchStatusTrueOrderByIdAvaliacaoDesc(Integer idMatch);

	@Query("select new com.conture.apiproduto.model.dto.response.AvaliacaoResponse(a.valor, a.comentario, a.data, a.match.fkDonatario) from Avaliacao a where a.match.produtoDoacao.fkDoador = ?1 and a.match.produtoDoacao.status = true and a.match.produtoDoacao.removido = false order by a.data desc")
	List<AvaliacaoResponse> getAllByIdDoador(Integer idDoador);
}
