package com.conture.apiproduto.controller;

import com.conture.apiproduto.dto.request.*;
import com.conture.apiproduto.utility.Iterator;
import com.conture.apiproduto.utility.ListaObj;
import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.entity.Match;
import com.conture.apiproduto.entity.PreferenciaDonatario;
import com.conture.apiproduto.entity.ProdutoDoacao;
import com.conture.apiproduto.repository.CategoriaProdutoRepository;
import com.conture.apiproduto.repository.MatchRepository;
import com.conture.apiproduto.repository.PreferenciaDonatarioRepository;
import com.conture.apiproduto.repository.ProdutoRepository;

import com.conture.apiproduto.utility.SearchProdutoCategoriaIterator;
import com.conture.apiproduto.utility.SearchProdutoMarcaIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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

	 ListaObj<String> listaHistorico = new ListaObj<>(10);


	@PostMapping()
	public ResponseEntity adicionarProduto(@RequestBody @Valid ProdutoDoacaoRequest produtoRequest) {

		ProdutoDoacao produto = new ProdutoDoacao();

		produto.setFkDoador(produtoRequest.getFkDoador());
		produto.setNome(produtoRequest.getNome());
		produto.setMarca(produtoRequest.getMarca());
		produto.setModelo(produtoRequest.getModelo());
		produto.setDescricao(produtoRequest.getDescricao());
		produto.setDefeito(produtoRequest.isDefeito());
		produto.setStatus(produtoRequest.isStatus());
		produto.setEntrega(produtoRequest.isEntrega());
		produto.setQuantidadeVisualizacao(produtoRequest.getQuantidadeVisualizacao());
		produto.setFkCategoriaProduto(produtoRequest.getFkCategoriaProduto());

		this.produtoRepository.save(produto);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/preferencia")
	public ResponseEntity adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatario preferencia){
		preferenciaRepository.save(preferencia);
		return ResponseEntity.status(201).build();
	}

	@PostMapping("/match")
	public ResponseEntity adicionarMatch(@RequestBody @Valid MatchRequest matchRequest){

		Match match = new Match();
		match.setFkDoador(matchRequest.getFkDoador());
		match.setFkProdutoDoacao(matchRequest.getFkProdutoDoacao());
		match.setFkDonatario(matchRequest.getFkDonatario());
		match.setMatchPorcentagem(matchRequest.getMatchPorcentagem());
		match.setStatus(matchRequest.getStatus());

		matchRepository.save(match);
		return  ResponseEntity.status(201).build();
	}

	@GetMapping()
	public ResponseEntity pesquisarProdutoId(@RequestParam Long idProdutoDoacao, @RequestParam Long fkDoador) {
		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador);

		if (produto.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		return ResponseEntity.status(200).body(produto.get());
	}

	@GetMapping("/categoria")
	public  ResponseEntity listarCategorias() {
		List<ProdutoDoacao> listaCategoria = produtoRepository.findAll();

		if (listaCategoria.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaCategoria);
	}

	@GetMapping("/nome/categoria")
	public ResponseEntity listarProdutoCategoria(@RequestParam String categoria) {

		Optional<CategoriaProduto> categoriaProduto = this.categoriaRepository.findByNomeIgnoreCase(categoria);

		if (categoriaProduto.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		Iterator<ProdutoDoacao> iterator = new SearchProdutoCategoriaIterator(this.produtoRepository.findAll(), categoriaProduto.get().getIdCategoriaProduto());

		List<ProdutoDoacao> produtosResponse = new ArrayList();

		while (iterator.hasNext()) {
			produtosResponse.add(iterator.getNext());
		}

		if (produtosResponse.get(0) == null) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(produtosResponse);
	}

	@GetMapping("/marca")
	public ResponseEntity listarProdutoMarca(@RequestParam String marca) {
		Iterator<ProdutoDoacao> iterator = new SearchProdutoMarcaIterator(this.produtoRepository.findAll(), marca);

		List<ProdutoDoacao> produtosResponse = new ArrayList();

		while (iterator.hasNext()) {
			produtosResponse.add(iterator.getNext());
		}

		if (produtosResponse.get(0) == null) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(produtosResponse);
	}

	@GetMapping("/nome")
	public ResponseEntity listarProdutoNome(@RequestParam String nome) {
		List<ProdutoDoacao> listaProduto = produtoRepository.acharPeloNomeIgnoreCase(nome);
		listaHistorico.adicionaNoInicio(nome);
		return ResponseEntity.status(200).body(listaProduto);

	}
	@GetMapping("/historico")
	public ResponseEntity listarHistorico(){

		ArrayList<String> historico = new ArrayList<>();
		for (int i = 0; i < listaHistorico.getTamanho(); i++) {
			historico.add(listaHistorico.getElemento(i));
		}

	return ResponseEntity.status(200).body(historico);
	}

	@GetMapping("/doador/status")
	public ResponseEntity filtrarStatusProdutoDoador(@RequestParam Long fkDoador, @RequestParam String status){

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
			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndFkDoador(match.getFkProdutoDoacao(), match.getFkDoador()).get());
		}

		return  ResponseEntity.status(200).body(listaProdutos);
	}

	@GetMapping("/donatario/status")
	public ResponseEntity filtrarStatusProdutoDonatario(@RequestParam Long fkDonatario, @RequestParam String status){

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
			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndFkDoador(match.getFkProdutoDoacao(), match.getFkDonatario()).get());
		}
		return  ResponseEntity.status(200).body(listaProdutos);
	}


	@GetMapping("/doador")
	public ResponseEntity listarProdutosUsuario(@RequestParam Long fkDoador){
		List<ProdutoDoacao> lista = produtoRepository.findByFkDoador(fkDoador);
		if (lista.isEmpty()){
			return ResponseEntity.status(204).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/lista/match")
	public ResponseEntity listarMatch(@RequestParam Long fkDoador, @RequestParam Long fkProdutoDoacao){

		List<Match> listaMatch = matchRepository.findByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);

		if (listaMatch.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return  ResponseEntity.status(200).body(listaMatch);
	}

	@GetMapping("/match/quantidade")
	public ResponseEntity contarMatch(@RequestParam Long fkDoador, @RequestParam Long fkProdutoDoacao){

		Long contador = matchRepository.countByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);
		return  ResponseEntity.status(200).body(contador);
	}

	@DeleteMapping("")
	public ResponseEntity deletarProduto(@RequestParam Long fkDoador, @RequestParam Long idProdutoDoacao){

		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador);

		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		produtoRepository.deleteByFkDoadorAndIdProdutoDoacao(fkDoador, idProdutoDoacao);
		return ResponseEntity.status(200).build();
	}

	@PatchMapping()
	public  ResponseEntity concluirDoacao(@RequestParam Long fkDoador, @RequestParam Long idProdutoDoacao){
		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndFkDoador(idProdutoDoacao, fkDoador);
		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		if (produto.get().isStatus()){
			return ResponseEntity.status(406).build();
		}

		produtoRepository.updateProdutoDoacaoConcluido(fkDoador, idProdutoDoacao, Date.from(Instant.now()));
		return ResponseEntity.status(200).build();
	}

	@DeleteMapping("/match")
	public ResponseEntity deletarMatch(@RequestBody @Valid MatchIdentifierRequest matchIdentifier) {
		Optional<Match> match = matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(),matchIdentifier.getFkProdutoDoacao(),matchIdentifier.getFkDonatario());
		if (match.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		matchRepository.deleteByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(),matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
		return ResponseEntity.status(200).build();
	}


	@PatchMapping("/visualizacao")
	public ResponseEntity incrementarVisualizacao(@RequestBody @Valid VisualizacaoRequest visualizacao ) {
		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndFkDoador(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador());
		if (produto.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		produtoRepository.updateProdutoDoacaoSetQuantidadeVisualizacao(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador(), visualizacao.getQuantidadeVisualizacao() + produto.get().getQuantidadeVisualizacao());
		return ResponseEntity.status(200).build();
	}


	@PatchMapping("/match")
	public ResponseEntity atualizarMatch(@RequestBody @Valid AtualizarMatchIdentifierRequest matchIdentifier ){
		Optional<Match> match = matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
		if (match.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		matchRepository.updateMatchSetStatus(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario(),matchIdentifier.getStatus());
		return ResponseEntity.status(200).body(matchIdentifier);
	}
}
