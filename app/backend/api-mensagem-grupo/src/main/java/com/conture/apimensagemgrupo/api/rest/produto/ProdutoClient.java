package com.conture.apimensagemgrupo.api.rest.produto;

import com.conture.apimensagemgrupo.api.rest.usuario.UsuarioResposta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "produto", url = "http://localhost:8081/produtos")
public interface ProdutoClient {

	@GetMapping("/{idProduto}")
	Optional<ProdutoResposta> getProdutoById(@PathVariable Integer idProduto);

}
