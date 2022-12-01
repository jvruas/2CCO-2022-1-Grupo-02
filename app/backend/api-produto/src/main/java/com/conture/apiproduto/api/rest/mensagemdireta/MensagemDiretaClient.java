package com.conture.apiproduto.api.rest.mensagemdireta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@FeignClient(value = "mensagem-direta", url = "http://localhost:8082/mensagem-direta")
public interface MensagemDiretaClient {
	@PostMapping("/chat")
	public ResponseEntity adicionarChat(
			@RequestParam @NotNull @Min(1) Integer idDoador,
			@RequestParam @NotNull @Min(1) Integer idDonatario
	);
}
