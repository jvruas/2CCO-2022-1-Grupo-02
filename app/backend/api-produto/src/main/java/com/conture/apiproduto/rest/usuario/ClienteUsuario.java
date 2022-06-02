package com.conture.apiproduto.rest.usuario;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "usuario", url = "http://localhost:8080/usuarios")
public interface ClienteUsuario {
	@GetMapping("/{idUsuario}")
	UsuarioResposta getUsuario(@PathVariable long idUsuario);
}
