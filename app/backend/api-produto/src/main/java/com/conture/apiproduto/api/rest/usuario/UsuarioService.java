package com.conture.apiproduto.api.rest.usuario;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@FeignClient(value = "usuario", url = "http://localhost:8080/usuarios")
@Service
public interface UsuarioService {
	@GetMapping("/{idUsuario}/login?responseType=usuario") // FIXME: talvez de problema
	Optional<UsuarioResposta> getUsuarioLogado(@PathVariable Integer idUsuario);

	@GetMapping("/{idUsuario}/login?responseType=id") // FIXME: talvez de problema
	Integer getIdUsuarioLogado(@PathVariable Integer idUsuario);

	@GetMapping("/situacao-atual/{idSituacaoAtual}")
	Integer getIdSituacaoAtual(@PathVariable Integer idSituacaoAtual);
}
