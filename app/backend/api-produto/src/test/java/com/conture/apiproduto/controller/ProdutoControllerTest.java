package com.conture.apiproduto.controller;

import com.conture.apiproduto.api.rest.usuario.UsuarioClient;
import com.conture.apiproduto.model.dto.request.ProdutoDoacaoRequest;
import com.conture.apiproduto.repository.*;
import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
	@DisplayName("Metodo 'adicionarProduto' deve estar assinalado com PostMapping")
	void adicionarProdutoCase1() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		assertTrue(adicionarProduto.isAnnotationPresent(PostMapping.class));
	}

	@Test
	@DisplayName("[adicionarProduto] -> Primeiro parametro deve estar assinalado com @RequestBody")
	void adicionarProdutoCase2() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		Annotation[] primeiroParametro = adicionarProduto.getParameterAnnotations()[0];

		assertEquals(RequestBody.class, adicionarProduto.getParameterAnnotations()[0][0].annotationType());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Segundo parametro do metodo deve estar assinalado com @Valid")
	void adicionarProdutoCase3() throws NoSuchMethodException {
		Method adicionarProduto = produtoControllerClass.getDeclaredMethod("adicionarProduto", ProdutoDoacaoRequest.class);

		Annotation[] primeiroParametro = adicionarProduto.getParameterAnnotations()[0];

		assertEquals(Valid.class, adicionarProduto.getParameterAnnotations()[0][1].annotationType());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Quando input 'fkCategoriaProduto' não existe na entidade categoriaProduto deve retornar 404 NOT FOUND")
	void adicionarProdutoCase4() {
		when(this.categoriaRepository.existsById(anyInt())).thenReturn(false);

		assertEquals(status(404).build().getStatusCode(), this.produtoController.adicionarProduto(mock(ProdutoDoacaoRequest.class)).getStatusCode());
	}

	@Test
	@DisplayName("[adicionarProduto] -> Quando input 'fkCategoriaProduto' não existe na entidade categoriaProduto deve retornar 404 NOT FOUND")
	void adicionarProdutoCase5() throws NoSuchFieldException, IllegalAccessException, FeignException {
		FeignException feignException = mock(FeignException.class);

		Class<FeignException> feignExceptionClass = FeignException.class;

		Field status = feignExceptionClass.getDeclaredField("status");

		status.setAccessible(true);

		status.setInt(feignException, -1);

		when(this.categoriaRepository.existsById(anyInt())).thenReturn(true);

		when(this.usuarioClient.getIdUsuarioLogado(anyInt())).thenThrow(feignExceptionClass);


//		FeignException feignException1 = () -> {
//			this.produtoController.adicionarProduto(mock(ProdutoDoacaoRequest.class));
//		};


//		assertEquals(status(-1).build(), );
	}


	@Test
	void testAdicionarProduto() {

	}

	@Test
	void adicionarMatch() {
	}

	@Test
	void adicionarAvaliacao() {
	}

	@Test
	void pesquisarProduto() {
	}

	@Test
	void listarHistorico() {
	}

	@Test
	void deletarProduto() {
	}

	@Test
	void concluirDoacao() {
	}
}
