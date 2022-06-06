package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.entity.PreferenciaDonatario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenciaDonatarioRepository extends JpaRepository<PreferenciaDonatario, Integer> {

	boolean existsByProdutoDoacaoIdProdutoDoacao(Integer idProdutoDoacao);

	Optional<PreferenciaDonatario> findTop1ByProdutoDoacaoIdProdutoDoacaoOrderByIdPreferenciaDonatarioDesc(Integer idProdutoDoacao);
}
