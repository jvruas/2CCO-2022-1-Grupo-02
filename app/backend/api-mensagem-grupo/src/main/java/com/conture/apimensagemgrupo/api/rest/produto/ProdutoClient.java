package com.conture.apimensagemgrupo.api.rest.produto;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "produto", url = "http://localhost:8081/produtos")
public interface ProdutoClient {


}
