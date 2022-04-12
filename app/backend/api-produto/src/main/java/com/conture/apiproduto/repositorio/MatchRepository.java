package com.conture.apiproduto.repositorio;

import com.conture.apiproduto.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
}
