package com.conture.apiproduto.controller;

import com.conture.apiproduto.ListaObj;
import com.conture.apiproduto.dto.request.MatchIdentifierRequest;
import com.conture.apiproduto.dto.request.ProdutoDoacaoStatusRequest;
import com.conture.apiproduto.dto.request.VisualizacaoRequest;
import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.entity.Match;
import com.conture.apiproduto.entity.PreferenciaDonatario;
import com.conture.apiproduto.entity.ProdutoDoacao;
import com.conture.apiproduto.repository.CategoriaProdutoRepository;
import com.conture.apiproduto.repository.MatchRepository;
import com.conture.apiproduto.repository.PreferenciaDonatarioRepository;
import com.conture.apiproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController{
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PreferenciaDonatarioRepository preferenciaRepository;

	@Autowired
	private MatchRepository matchRepository;

	 @Autowired
	 private CategoriaProdutoRepository categoriaRepository;

	 ListaObj<ProdutoDoacao> listaPesquisa = new ListaObj<>(10);

	@PostMapping()
	public ResponseEntity adicionarProduto(@RequestBody @Valid ProdutoDoacao produto) {
		produtoRepository.save(produto);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/preferencia")
	public ResponseEntity adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatario preferencia){
		preferenciaRepository.save(preferencia);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/match")
	public ResponseEntity adicionarMatch(@RequestBody @Valid Match match){
		matchRepository.save(match);
		return  ResponseEntity.status(201).build();
	}

	@GetMapping()
	public ResponseEntity pesquisarProdutoId(@RequestParam Long idProdutoDoacao, @RequestParam Long fkDoador) {
		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador));

		if (produto.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		// TODO: Verificar se necessita de tratativa de erro para quando os IDs nao existem.

		return ResponseEntity.status(200).body(produto.get());
	}

	@GetMapping("/categorias")
	public  ResponseEntity listarCategorias() {
		List<ProdutoDoacao> listaCategoria = produtoRepository.findAll();

		if (listaCategoria.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaCategoria);
	}

	// FIXME: Iterator
	@GetMapping("/categorias/{categoria}")
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
	@GetMapping("/marcas/{marca}")
	public ResponseEntity listarProdutoMarca(@PathVariable String marca) {
		// TODO: Fazer trativa de erros para input errado no metodo.

		List<ProdutoDoacao> listaProduto = produtoRepository.findByMarca(marca);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(listaProduto);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity listarProdutoNome(@PathVariable String nome) {
		List<ProdutoDoacao> listaProduto = produtoRepository.findByNome(nome);

		if (listaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(listaProduto);
	}

	@GetMapping("/doador/status/{fkDoador}/{status}")
	public ResponseEntity filtrarStatusProdutoDoador(@PathVariable Long fkDoador, @PathVariable String status){
		// TODO: Fazer trativa de erros para input errado no metodo.

		// TODO: Fazer trativa de erros para input errado no metodo.
		// TODO: Transformar A: andamento; R: recebido; D: doado T: todos

		if (status.equalsIgnoreCase("D")){
			List<ProdutoDoacao> listaProdutos = produtoRepository.findByFkDoadorAndStatus(fkDoador,true);
			if (listaProdutos.isEmpty()){
				return ResponseEntity.status(204).build();
			}
		return ResponseEntity.status(200).body(listaProdutos);
		}

		List<Match> listaMatch = matchRepository.findByFkDoadorAndStatus(fkDoador, status);
		if (listaMatch.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		List<ProdutoDoacao> listaProdutos = new ArrayList<>();
		for ( Match match : listaMatch) {
			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndFkDoador(match.getFkProdutoDoacao(), match.getFkDoador()));
		}

		// TODO: Lembrar de devolver DTO diferente do metodo listarStatusProdutoDonatario.
		return  ResponseEntity.status(200).body(listaProdutos);
	}

	@GetMapping("/donatario/status/{fkDonatario}/{status}")
	public ResponseEntity filtrarStatusProdutoDonatario(@PathVariable Long fkDonatario, @PathVariable String status){
		// TODO: Fazer trativa de erros para input errado no metodo.

		// TODO: Fazer trativa de erros para input errado no metodo.
		// TODO: Transformar A: andamento; R: recebido; D: doado

		// TODO: Lembrar de devolver DTO diferente do metodo listarStatusProdutoDoador.
		if (status.equals("D")){
			List<ProdutoDoacao> listaProdutos = produtoRepository.findByFkDoadorAndStatus(fkDonatario,true);
			if (listaProdutos.isEmpty()){
				return ResponseEntity.status(204).build();
			}
			return ResponseEntity.status(200).body(listaProdutos);
		}

		List<Match> listaMatch = matchRepository.findByFkDoadorAndStatus(fkDonatario, status);
		if (listaMatch.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		List<ProdutoDoacao> listaProdutos = new ArrayList<>();
		for ( Match match : listaMatch) {
			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndFkDoador(match.getFkProdutoDoacao(), match.getFkDonatario()));
		}
		return  ResponseEntity.status(200).body(listaProdutos);
	}


	@GetMapping("/doador/{fkDoador}")
	public ResponseEntity listarProdutosUsuario(@PathVariable Long fkDoador){
		List<ProdutoDoacao> lista = produtoRepository.findByFkDoador(fkDoador);
		if (lista.isEmpty()){
			return ResponseEntity.status(204).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/{fkDoador}/{fkProdutoDoacao}/match")
	public ResponseEntity listarMatch(@PathVariable Long fkDoador, @PathVariable Long fkProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		List<Match> listaMatch = matchRepository.findByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);

		if (listaMatch.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaMatch);
	}

	@GetMapping("/{fkDoador}/{idProdutoDoacao}/match/quantidade")
	public ResponseEntity contarMatch(@PathVariable Long fkDoador, @PathVariable Long fkProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		Optional<Long> contador = Optional.ofNullable(matchRepository.countByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao));

		if (contador.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		return  ResponseEntity.status(200).body(contador.get());
	}

	@DeleteMapping("/{fkDoador}/{idProdutoDoacao}")
	public ResponseEntity deletarProduto(@PathVariable Long fkDoador, @PathVariable Long idProdutoDoacao){
		// TODO: Fazer trativa de erros para input errado no metodo.

		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador));

		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		produtoRepository.deleteByFkDoadorAndIdProdutoDoacao(fkDoador, idProdutoDoacao);
		return ResponseEntity.status(200).build();
	}

	@PatchMapping("/status")
	public  ResponseEntity atualizarStatusDoacao(@RequestBody @Valid ProdutoDoacaoStatusRequest statusProduto){
		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(statusProduto.getFkDoador(), statusProduto.getIdProdutoDoacao()));
		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		if (produto.get().isStatus()){
			return ResponseEntity.status(406).build();
		}
		produtoRepository.updateProdutoDoacaoSetStatus(statusProduto.getFkDoador(), statusProduto.getIdProdutoDoacao(),statusProduto.isStatus());
		return ResponseEntity.status(200).build();

	}

	@DeleteMapping("/match")
	public ResponseEntity deletarMatch(@RequestBody @Valid MatchIdentifierRequest matchIdentifier) {
		Optional<Match> match = Optional.ofNullable(matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(),matchIdentifier.getFkProdutoDoacao(),matchIdentifier.getFkDonatario()));
		if (match.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		matchRepository.deleteByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(),matchIdentifier.getFkProdutoDoacao(),matchIdentifier.getFkDonatario());
		return ResponseEntity.status(200).build();
	}

	@PatchMapping("/visualizacao")
	public ResponseEntity incrementarVisualizacao(@RequestBody @Valid VisualizacaoRequest visualizacao ) {
		Optional<ProdutoDoacao> produto = Optional.ofNullable(produtoRepository.findByIdProdutoDoacaoAndFkDoador(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador()));
		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		produtoRepository.updateProdutoDoacaoSetQuantidadeVisualizacao(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador(), visualizacao.getQuantidadeVisualizacao() + produto.get().getQuantidadeVisualizacao());
		return ResponseEntity.status(200).build();
	}


	@PatchMapping("/match/{fkDoador}/{fkProdutoDoacao}/{fkDonatario}")
	public ResponseEntity atualizarMatch(@RequestBody @Valid MatchIdentifierRequest matchIdentifier ){
		Optional<Match> match = Optional.ofNullable(matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario()));
		if (match.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		matchRepository.updateMatchSetStatus(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
		return ResponseEntity.status(200).build();
	}
}
