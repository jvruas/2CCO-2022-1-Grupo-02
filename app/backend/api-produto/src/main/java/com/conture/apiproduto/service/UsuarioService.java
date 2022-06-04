package com.conture.apiproduto.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "usuario", url = "http://localhost:8080/usuarios")
public interface UsuarioService {
	@GetMapping("/{idUsuario}")
	Integer getUsuario(@PathVariable Integer idUsuario);
}
