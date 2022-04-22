package com.conture.apiproduto.controller;

import com.conture.apiproduto.ListaObj;
import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.entity.Match;
import com.conture.apiproduto.entity.PreferenciaDonatario;
import com.conture.apiproduto.entity.ProdutoDoacao;
import com.conture.apiproduto.repositorio.CategoriaProdutoRepository;
import com.conture.apiproduto.repositorio.MatchRepository;
import com.conture.apiproduto.repositorio.PreferenciaDonatarioRepository;
import com.conture.apiproduto.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
public class
ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PreferenciaDonatarioRepository preferenciaRepository;

	@Autowired
	private MatchRepository matchRepository;

	 @Autowired
	 private CategoriaProdutoRepository categoriaRepository;

	 ListaObj<ProdutoDoacao> listaPesquisa = new ListaObj<>(10);

	@PostMapping("/adicionar-produto")
	public ResponseEntity adicionarProduto(@RequestBody @Valid ProdutoDoacao produto) {
		produtoRepository.save(produto);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/adicionar-preferencia")
	public ResponseEntity adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatario preferencia){
		preferenciaRepository.save(preferencia);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/adicionar-match")
	public ResponseEntity adicionarMatch(@RequestBody @Valid Match match){
		matchRepository.save(match);
		return  ResponseEntity.status(201).build();
	}

	@GetMapping("/pesquisar/{idProdutoDoacao}/{fkDoador}")
	public ResponseEntity pesquisarProdutoId(@PathVariable Long idProdutoDoacao, @PathVariable Long fkDoador) {
		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador));

		if (produto.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		// TODO: Verificar se necessita de tratativa de erro para quando os IDs nao existem.

		return ResponseEntity.status(200).body(produto.get());
	}

	@GetMapping("/listar-categorias")
	public  ResponseEntity listarCategorias() {
		List<ProdutoDoacao> listaCategoria = produtoRepository.findAll();

		if (listaCategoria.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaCategoria);
	}

	// FIXME: Iterator
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity listarProdutoCategoria(@PathVariable String categoria) {
		// TODO: Fazer trativa de erros para input errado no metodo.

		Optional<CategoriaProduto> categoriaPesquisada = Optional.ofNullable(this.categoriaRepository.findByNome(categoria));

		if (categoriaPesquisada.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		List<ProdutoDoacao> listaProduto = this.produtoRepository.findByFkCategoriaProduto(categoriaPesquisada.get().getIdCategoriaProduto());

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(listaProduto);
	}

	// FIXME: Iterator
	@GetMapping("/marca/{marca}")
	public ResponseEntity listarProdutoMarca(@PathVariable String marca) {
		// TODO: Fazer trativa de erros para input errado no metodo.

		List<ProdutoDoacao> listaProduto = produtoRepository.findByMarca(marca);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(listaProduto);
	}

	// FIXME: Iterator
	@GetMapping("/nome/{nome}")
	public ResponseEntity listarProdutoNome(@PathVariable String nome) {
		List<ProdutoDoacao> listaProduto = produtoRepository.findByNome(nome);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(listaProduto);
	}

	@GetMapping("/listar-produto-doador/{fkDoador}/{status}")
	public ResponseEntity listarStatusProdutoDoador(@PathVariable Long fkDoador, @PathVariable String status){
		// TODO: Fazer trativa de erros para input errado no metodo.

		// TODO: Fazer trativa de erros para input errado no metodo.
		// TODO: Transformar A: andamento; R: recebido; D: doado

		List<ProdutoDoacao> listaProduto = produtoRepository.findByFkDoadorAndStatus(fkDoador, status);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		// TODO: Lembrar de devolver DTO diferente do metodo listarStatusProdutoDonatario.

		return  ResponseEntity.status(200).body(listaProduto);
	}

	@GetMapping("/listar-produto-donatario/{idDonatario}/{status}")
	public ResponseEntity listarStatusProdutoDonatario(@PathVariable Long fkDoador, @PathVariable String status){
		// TODO: Fazer trativa de erros para input errado no metodo.

		// TODO: Fazer trativa de erros para input errado no metodo.
		// TODO: Transformar A: andamento; R: recebido; D: doado

		List<ProdutoDoacao> listaProduto = produtoRepository.findByFkDoadorAndStatus(fkDoador, status);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		// TODO: Lembrar de devolver DTO diferente do metodo listarStatusProdutoDoador.
		return  ResponseEntity.status(200).body(listaProduto);
	}

	@GetMapping("/listar-match/{fkDoador}/{idProdutoDoacao}")
	public ResponseEntity listarMatch(@PathVariable Long fkDoador, @PathVariable Long fkProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		List<Match> listaMatch = matchRepository.findByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);

		if (listaMatch.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaMatch);
	}

	@GetMapping("/contar-match/{fkDoador}/{idProdutoDoacao}")
	public ResponseEntity contarMatch(@PathVariable Long fkDoador, @PathVariable Long fkProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		Optional<Long> contador = Optional.ofNullable(matchRepository.countByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao));

		if (contador.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		return  ResponseEntity.status(200).body(contador.get());
	}

	@DeleteMapping("/deletarProduto/{fkDoador}/{idProdutoDoacao}")
	public ResponseEntity deletarProduto(@PathVariable Long fkDoador, @PathVariable Long idProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador));

		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		produtoRepository.deleteByFkDoadorAndIdProdutoDoacao(fkDoador, idProdutoDoacao);

		return ResponseEntity.status(200).build();
	}

	//@PatchMapping("/atualizarStatusDoador/")----------

	// TODO: FINALIZAR LOGICA
	@DeleteMapping("/deletarMatch/{idProdutoDoacao}/{fkDonatario}/{fkDoador}")
	public ResponseEntity deletarMatch(@PathVariable Long idProdutoDoacao,@PathVariable Long fkDonatario,@PathVariable Long fkDoador) {
		ProdutoDoacao match = produtoRepository.deleteByMatch(idProdutoDoacao, fkDonatario,fkDoador);
		produtoRepository.save(match);
		return ResponseEntity.status(201).build();
	}

	@PutMapping("/atualizar-quantidade-visualizacao/{idProdutoDoacao}/{fkDoador}")
	public ResponseEntity atualizarQuantidadeVisualizacao(@PathVariable Long idProdutoDoacao, @PathVariable Long fkDoador) {

	}
}
