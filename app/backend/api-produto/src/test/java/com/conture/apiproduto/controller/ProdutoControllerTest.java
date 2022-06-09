package com.conture.apiproduto.controller;

import com.conture.apiproduto.api.rest.usuario.UsuarioClient;
import com.conture.apiproduto.model.dto.request.MatchRequest;
import com.conture.apiproduto.model.dto.request.ProdutoDoacaoRequest;
import com.conture.apiproduto.model.entity.Match;
import com.conture.apiproduto.model.entity.ProdutoDoacao;
import com.conture.apiproduto.repository.*;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.status;

@SpringBootTest(classes = {ProdutoController.class})
class ProdutoControllerTest {
	@Autowired
	ProdutoController produtoController;

	private Class<ProdutoController> produtoControllerClass = ProdutoController.class;

	@MockBean
	private ProdutoRepository produtoRepository;

	@MockBean
	private ImagemProdutoDoacaoRepository imagemProdutoDoacaoRepository;

	@MockBean
	private PreferenciaDonatarioRepository preferenciaRepository;

	@MockBean
	private MatchRepository matchRepository;

	@MockBean
	private AvaliacaoRepository avaliacaoRepository;

	@MockBean
	private CategoriaProdutoRepository categoriaRepository;

	@MockBean
	private UsuarioClient usuarioClient;

	@Test
	@DisplayName("Metodo 'adicionarProduto' DEVE ESTAR assinalado com PostMapping")
	void adicionarProdutoCase1() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		assertTrue(adicionarProduto.isAnnotationPresent(PostMapping.class));
	}

	@Test
	@DisplayName("[adicionarProduto] -> Primeiro parametro DEVE ESTAR assinalado com @RequestBody")
	void adicionarProdutoCase2() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		Annotation[] primeiroParametro = adicionarProduto.getParameterAnnotations()[0];

		assertEquals(RequestBody.class, adicionarProduto.getParameterAnnotations()[0][0].annotationType());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Segundo parametro do metodo DEVE ESTAR assinalado com @Valid")
	void adicionarProdutoCase3() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		Annotation[] primeiroParametro = adicionarProduto.getParameterAnnotations()[0];

		assertEquals(Valid.class, adicionarProduto.getParameterAnnotations()[0][1].annotationType());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Quando input 'fkCategoriaProduto' não existe na entidade categoriaProduto DEVE RETORNAR 404 NOT FOUND")
	void adicionarProdutoCase4() {
		when(this.categoriaRepository.existsById(anyInt())).thenReturn(false);

		assertEquals(status(404).build().getStatusCode(), this.produtoController.adicionarProduto(mock(ProdutoDoacaoRequest.class)).getStatusCode());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Quando Doador não logado no escopo da API de usuarios DEVE RETORNAR 401 Unauthorized")
	void adicionarProdutoCase5() {
		when(this.categoriaRepository.existsById(anyInt())).thenReturn(true);

		when(this.usuarioClient.getIdUsuarioLogado(anyInt())).thenThrow(FeignException.class);

		assertEquals(status(401).build(), this.produtoController.adicionarProduto(mock(ProdutoDoacaoRequest.class)));
	}

	@Test
	@DisplayName("[adicionarProduto] -> Quando Categoria Existe e Doador logado DEVE RETORNAR 201 CREATED")
	void adicionarProdutoCase6() {
		when(this.categoriaRepository.existsById(1)).thenReturn(true);

		when(this.usuarioClient.getIdUsuarioLogado(1)).thenReturn(1);

		ProdutoDoacaoRequest produtoDoacaoRequest = new ProdutoDoacaoRequest(
				"Notebook Thinkpad t14 pela metade do preço",
				"Lenovo",
				"Thinkpad",
				"Sistema operacional: Windows 11 Pro 64 (Português BR) | Memoria RAM: 16 GB | Processador: Intel i5-1145G7",
				true,
				true,
				1,
				1
		);

		ProdutoDoacao produtoDoacao = ProdutoDoacao.fromPattern(produtoDoacaoRequest);

		when(this.produtoRepository.save(produtoDoacao)).thenReturn(mock(ProdutoDoacao.class));
		when(this.produtoRepository.findTop1ByRemovidoFalseAndStatusFalseAndFkDoadorOrderByIdProdutoDoacaoDesc(1)).thenReturn(Optional.ofNullable(produtoDoacao));

		assertEquals(201, this.produtoController.adicionarProduto(produtoDoacaoRequest).getStatusCodeValue());
	}


	@Test
	@DisplayName("[adicionarMatch] -> Quando 'idProduto' não existe dentro da database DEVE RETORNAR 404 NOT FOUND")
	void adicionarMatchCase1() {
		when(this.produtoRepository.hasById(anyInt())).thenReturn(false);

		assertEquals(404, this.produtoController.adicionarMatch(1, 1).getStatusCodeValue());
	}

	@Test
	@DisplayName("[adicionarMatch] -> Quando 'idDoador' igual a 'idDonatarioRequest' DEVE RETORNAR 403 FORBIDDEN")
	void adicionarMatchCase2() {
		when(this.produtoRepository.hasById(anyInt())).thenReturn(true);

		when(this.produtoRepository.getFkDoadorById(anyInt())).thenReturn(1);

		assertEquals(403, this.produtoController.adicionarMatch(1, 1).getStatusCodeValue());
	}

	@Test
	@DisplayName("[adicionarMatch] -> Quando Match já existente para determinado produto DEVE RETORNAR 409 CONFLICT")
	void adicionarMatchCase3() {
		when(this.produtoRepository.hasById(anyInt())).thenReturn(true);

		when(this.produtoRepository.getFkDoadorById(anyInt())).thenReturn(1);

		when(this.matchRepository.existsByProdutoDoacaoIdProdutoDoacaoAndFkDonatario(anyInt(), anyInt())).thenReturn(true);

		assertEquals(409, this.produtoController.adicionarMatch(1, 2).getStatusCodeValue());
	}

	@Test
	@DisplayName("[adicionarMatch] -> Quando Donatario não logado no escopo da API de usuarios DEVE RETORNAR 401 Unauthorized")
	void adicionarMatchCase4() {
		when(this.produtoRepository.hasById(anyInt())).thenReturn(true);

		when(this.produtoRepository.getFkDoadorById(anyInt())).thenReturn(1);

		when(this.matchRepository.existsByProdutoDoacaoIdProdutoDoacaoAndFkDonatario(anyInt(), anyInt())).thenReturn(false);

		when(this.usuarioClient.getIdUsuarioLogado(anyInt())).thenThrow(FeignException.class);

		assertEquals(401, this.produtoController.adicionarMatch(1, 2).getStatusCodeValue());
	}

	@Test
	@DisplayName("[adicionarMatch] -> Quando 'idProduto' existe, 'idDoador' diferente de 'idDonatarioRequest', Match desse donatario para esse produto não existe, Donatario logado no escopo da API de usuarios DEVE RETORNAR 201 Created")
	void adicionarMatchCase5() {
		when(this.produtoRepository.hasById(anyInt())).thenReturn(true);

		when(this.produtoRepository.getFkDoadorById(anyInt())).thenReturn(1);

		when(this.matchRepository.existsByProdutoDoacaoIdProdutoDoacaoAndFkDonatario(anyInt(), anyInt())).thenReturn(false);

		when(this.usuarioClient.getIdUsuarioLogado(anyInt())).thenReturn(2);

		Match match = Match.fromPattern(1,2);

		when(this.matchRepository.save(match)).thenReturn(mock(Match.class));

		when(this.matchRepository.findTop1ByStatusFalseAndVisualizadoFalseAndProdutoDoacaoIdProdutoDoacaoAndFkDonatarioOrderByIdMatchDesc(1,2)).thenReturn(Optional.of(match));

		assertEquals(201, this.produtoController.adicionarMatch(1, 2).getStatusCodeValue());
	}

	@Test
	@DisplayName("[pesquisarProduto] -> Quando 'nome' igual a '' DEVE RETORNAR 400 BAD REQUEST")
	void pesquisarProdutoCase1() {
		assertEquals(400, this.produtoController.pesquisarProduto("").getStatusCodeValue());
	}

	@Test
	@DisplayName("[concluirDoacao] -> Quando não existente produto com 'idProduto' e 'idDoadorRequest' informados DEVE RETORNAR 404 NOT FOUND")
	void concluirDoacaoCase1() {
		when(this.produtoRepository.hasByIdAndIdDoador(anyInt(), anyInt())).thenReturn(false);

		assertEquals(404, this.produtoController.concluirDoacao(1, 2, 3).getStatusCodeValue());
	}

	@Test
	@DisplayName("[concluirDoacao] -> Quando 'idDoadorRequest' igual a 'idDonatarioRequest' DEVE RETORNAR 403 FORBIDDEN")
	void concluirDoacaoCase2() {
		when(this.produtoRepository.hasByIdAndIdDoador(anyInt(), anyInt())).thenReturn(true);

		assertEquals(403, this.produtoController.concluirDoacao(1, 2, 2).getStatusCodeValue());
	}

	@Test
	@DisplayName("[concluirDoacao] -> Quando não encontrado Match com 'idProduto' e 'idDonatarioRequest' informados DEVE RETORNAR 404 NOT FOUND")
	void concluirDoacaoCase3() {
		when(this.produtoRepository.hasByIdAndIdDoador(anyInt(), anyInt())).thenReturn(true);

		when(this.matchRepository.getIdMatchByIdProdutoAndIdDonatario(anyInt(), anyInt())).thenReturn(Optional.ofNullable(null));

		assertEquals(404, this.produtoController.concluirDoacao(1, 2, 3).getStatusCodeValue());
	}

	@Test
	@DisplayName("[concluirDoacao] -> Quando Donatario não logado no escopo da API Usuario RETORNAR 401 Unauthorized")
	void concluirDoacaoCase4() {
		when(this.produtoRepository.hasByIdAndIdDoador(anyInt(), anyInt())).thenReturn(true);

		when(this.matchRepository.getIdMatchByIdProdutoAndIdDonatario(anyInt(), anyInt())).thenReturn(Optional.of(1));

		when(this.usuarioClient.getIdUsuarioLogado(anyInt())).thenThrow(FeignException.class);

		assertEquals(401, this.produtoController.concluirDoacao(1, 2, 3).getStatusCodeValue());
	}
}
