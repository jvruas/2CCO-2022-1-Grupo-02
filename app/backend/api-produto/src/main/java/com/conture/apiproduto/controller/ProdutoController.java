package com.conture.apiproduto.controller;

import com.conture.apiproduto.dto.request.*;
import com.conture.apiproduto.entity.PreferenciaDonatario;
import com.conture.apiproduto.repository.*;
import com.conture.apiproduto.service.rest.usuario.UsuarioService;
import com.conture.apiproduto.utility.*;
import com.conture.apiproduto.entity.ProdutoDoacao;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static org.springframework.http.ResponseEntity.*;
import static org.springframework.http.ResponseEntity.status;

@CrossOrigin
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ImagemProdutoDoacaoRepository imagemProdutoDoacaoRepository;

	@Autowired
	private PreferenciaDonatarioRepository preferenciaRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private CategoriaProdutoRepository categoriaRepository;

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private UsuarioService usuarioService;

	PilhaObj<String> pilhaHistorico = new PilhaObj<>(10);


	@PostMapping()
	public ResponseEntity<Integer> adicionarProduto(@RequestBody @Valid ProdutoDoacaoRequest produtoRequest) {
		if (!this.categoriaRepository.existsById(produtoRequest.getFkCategoriaProduto())) {
			return status(400).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(produtoRequest.getIdDoador());
		} catch (FeignException response) {
			return status(401).build();
		}

		this.produtoRepository.save(ProdutoDoacao.fromPattern(produtoRequest));
		return status(201).body(this.produtoRepository.findTop1ByRemovidoFalseAndStatusFalseOrderByIdProdutoDoacaoDesc().get().getIdProdutoDoacao());
	}


	@PostMapping(value = "/{idProduto}/imagem-principal", consumes = "image/jpeg")
	public ResponseEntity adicionarImagemPrincipalProduto(
			@PathVariable @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @Min(1) Integer idDoadorRequest,
			@RequestBody byte[] imagemPrincipal
	) {
		if (imagemPrincipal.length > 16_777_216 || imagemPrincipal.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		if (!this.produtoRepository.hasByIdAndFkDoador(idProduto, idDoadorRequest)) {
			return status(404).build();
		}

		if (this.produtoRepository.imagemPrincipalIsNotNull(idProduto)) {
			return status(409).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.produtoRepository.updateProdutoImagemPrincipal(idProduto, imagemPrincipal);
		return status(201).build();
	}

	@PostMapping("/preferencia")
	public ResponseEntity adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatarioRequest preferenciaDonatario) {
		if (!this.produtoRepository.hasById(preferenciaDonatario.getFkProdutoDoacao())) {
			return status(404).build();
		}

		if (this.preferenciaRepository.existsByProdutoDoacaoIdProdutoDoacao(preferenciaDonatario.getFkProdutoDoacao())) {
			return status(409).build();
		}

//		if () {
//
//		}

		this.preferenciaRepository.save(PreferenciaDonatario.fromPattern(preferenciaDonatario));
		return ResponseEntity.status(201).build();
	}


////	@PostMapping("/match")
////	public ResponseEntity adicionarMatch(@RequestBody @Valid MatchRequest matchRequest) {
////
////		Match match = new Match();
////		match.setFkDoador(matchRequest.getFkDoador());
////		match.setFkProdutoDoacao(matchRequest.getFkProdutoDoacao());
////		match.setFkDonatario(matchRequest.getFkDonatario());
////		match.setMatchPorcentagem(matchRequest.getMatchPorcentagem());
////		match.setStatus(matchRequest.getStatus());
////
////		matchRepository.save(match);
////		return ResponseEntity.status(201).build();
////	}
//
//	@GetMapping()
//	public ResponseEntity pesquisarProdutoId(@RequestParam Long idProdutoDoacao, @RequestParam Integer fkDoador) {
//		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(idProdutoDoacao, fkDoador);
//
//		if (produto.isEmpty()) {
//			return ResponseEntity.status(404).build();
//		}
//
//		return ResponseEntity.status(200).body(produto.get());
//	}
//
//	@GetMapping("/categoria")
//	public ResponseEntity listarCategorias() {
//		List<CategoriaProduto> listaCategoria = categoriaRepository.findAll();
//
//		if (listaCategoria.isEmpty()) {
//			return ResponseEntity.status(204).build();
//		}
//
//		return ResponseEntity.status(200).body(listaCategoria);
//	}
//
//	@GetMapping("/nome/categoria")
//	public ResponseEntity listarProdutoCategoria(@RequestParam String categoria) {
//
//		Optional<CategoriaProduto> categoriaProduto = this.categoriaRepository.findByNomeIgnoreCase(categoria);
//
//		if (categoriaProduto.isEmpty()) {
//			return ResponseEntity.status(204).build();
//		}
//
//		Iterator<ProdutoDoacao> iterator = new SearchProdutoCategoriaIterator(this.produtoRepository.findAll(), categoriaProduto.get().getIdCategoriaProduto());
//
//		List<ProdutoDoacao> produtosResponse = new ArrayList();
//
//		while (iterator.hasNext()) {
//			produtosResponse.add(iterator.getNext());
//		}
//
//		if (produtosResponse.get(0) == null) {
//			return ResponseEntity.status(204).build();
//		}
//
//		return ResponseEntity.status(200).body(produtosResponse);
//	}
//
//	@GetMapping("/marca")
//	public ResponseEntity listarProdutoMarca(@RequestParam String marca) {
//		Iterator<ProdutoDoacao> iterator = new SearchProdutoMarcaIterator(this.produtoRepository.findAll(), marca);
//
//		List<ProdutoDoacao> produtosResponse = new ArrayList();
//
//		while (iterator.hasNext()) {
//			produtosResponse.add(iterator.getNext());
//		}
//
//		if (produtosResponse.get(0) == null) {
//			return ResponseEntity.status(204).build();
//		}
//
//		return ResponseEntity.status(200).body(produtosResponse);
//	}
//
//	@GetMapping("/nome")
//	public ResponseEntity listarProdutoNome(@RequestParam String nome) {
//		List<ProdutoDoacao> listaProduto = produtoRepository.acharPeloNomeIgnoreCase(nome);
//		pilhaHistorico.push(nome);
//
//		return ResponseEntity.status(200).body(listaProduto);
//	}
//
//	@GetMapping("/historico")
//	public ResponseEntity listarHistorico() {
//		return ResponseEntity.status(200).body(pilhaHistorico);
//	}
//
////	@GetMapping("/doador/status")
////	public ResponseEntity filtrarStatusProdutoDoador(@RequestParam Long fkDoador, @RequestParam String status) {
////
////		if (status.equalsIgnoreCase("D")) {
////			List<ProdutoDoacao> listaProdutos = produtoRepository.findByUsuarioIdUsuarioAndStatus(fkDoador, true);
////			if (listaProdutos.isEmpty()) {
////				return ResponseEntity.status(204).build();
////			}
////			return ResponseEntity.status(200).body(listaProdutos);
////		}
////
////		List<Match> listaMatch = matchRepository.findByFkDoadorAndStatus(fkDoador, status);
////		if (listaMatch.isEmpty()) {
////			return ResponseEntity.status(204).build();
////		}
////
////		List<ProdutoDoacao> listaProdutos = new ArrayList<>();
////		for (Match match : listaMatch) {
////			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(match.getFkProdutoDoacao(), match.);
////		}
////
////		return ResponseEntity.status(200).body(listaProdutos);
////	}
//
////	@GetMapping("/donatario/status")
////	public ResponseEntity filtrarStatusProdutoDonatario(@RequestParam Long fkDonatario, @RequestParam String status) {
////
////		if (status.equals("D")) {
////			List<ProdutoDoacao> listaProdutos = produtoRepository.findByUsuarioIdUsuarioAndStatus(fkDonatario, true);
////			if (listaProdutos.isEmpty()) {
////				return ResponseEntity.status(204).build();
////			}
////			return ResponseEntity.status(200).body(listaProdutos);
////		}
////
////		List<Match> listaMatch = matchRepository.findByFkDoadorAndStatus(fkDonatario, status);
////		if (listaMatch.isEmpty()) {
////			return ResponseEntity.status(204).build();
////		}
////
////		List<ProdutoDoacao> listaProdutos = new ArrayList<>();
////		for (Match match : listaMatch) {
////			listaProdutos.add(produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(match.getFkProdutoDoacao(), match.getFkDonatario()).get());
////		}
////		return ResponseEntity.status(200).body(listaProdutos);
////	}
//
//
//	@GetMapping("/doador")
//	public ResponseEntity listarProdutosUsuario(@RequestParam Long fkDoador) {
//		List<ProdutoDoacao> lista = produtoRepository.findByUsuarioIdUsuario(fkDoador);
//		if (lista.isEmpty()) {
//			return ResponseEntity.status(204).build();
//		}
//		return ResponseEntity.status(200).body(lista);
//	}
//
//	@GetMapping("/lista/match")
//	public ResponseEntity listarMatch(@RequestParam Long fkDoador, @RequestParam Long fkProdutoDoacao) {
//
//		List<Match> listaMatch = matchRepository.findByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);
//
//		if (listaMatch.isEmpty()) {
//			return ResponseEntity.status(204).build();
//		}
//
//		return ResponseEntity.status(200).body(listaMatch);
//	}
//
//	@GetMapping("/match/quantidade")
//	public ResponseEntity contarMatch(@RequestParam Long fkDoador, @RequestParam Long fkProdutoDoacao) {
//
//		Long contador = matchRepository.countByFkDoadorAndFkProdutoDoacao(fkDoador, fkProdutoDoacao);
//		return ResponseEntity.status(200).body(contador);
//	}
//
////	@DeleteMapping("")
////	public ResponseEntity deletarProduto(@RequestParam Long fkDoador, @RequestParam Long idProdutoDoacao) {
////
////		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(idProdutoDoacao, fkDoador);
////
////		if (produto.isEmpty()) {
////			return ResponseEntity.status(404).build();
////		}
////
////		produtoRepository.deleteByUsuarioIdUsuarioAndIdProdutoDoacao(fkDoador, idProdutoDoacao);
////		return ResponseEntity.status(200).build();
////	}
//
////	@PatchMapping()
////	public ResponseEntity concluirDoacao(@RequestParam Long fkDoador, @RequestParam Long idProdutoDoacao) {
////		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(idProdutoDoacao, fkDoador);
////		if (produto.isEmpty()) {
////			return ResponseEntity.status(404).build();
////		}
////
////		if (produto.get().isStatus()) {
////			return ResponseEntity.status(406).build();
////		}
////
////		produtoRepository.updateProdutoDoacaoConcluido(fkDoador, idProdutoDoacao, Date.from(Instant.now()));
////		return ResponseEntity.status(200).build();
////	}
//
//	@DeleteMapping("/match")
//	public ResponseEntity deletarMatch(@RequestBody @Valid MatchIdentifierRequest matchIdentifier) {
//		Optional<Match> match = matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
//		if (match.isEmpty()) {
//			return ResponseEntity.status(404).build();
//		}
//		matchRepository.deleteByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
//		return ResponseEntity.status(200).build();
//	}
//
//
////	@PatchMapping("/visualizacao")
////	public ResponseEntity incrementarVisualizacao(@RequestBody @Valid VisualizacaoRequest visualizacao) {
////		Optional<ProdutoDoacao> produto = produtoRepository.findByIdProdutoDoacaoAndUsuarioIdUsuario(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador());
////		if (produto.isEmpty()) {
////			return ResponseEntity.status(404).build();
////		}
////		produtoRepository.updateProdutoDoacaoSetQuantidadeVisualizacao(visualizacao.getIdProdutoDoacao(), visualizacao.getFkDoador(), visualizacao.getQuantidadeVisualizacao() + produto.get().getQuantidadeVisualizacao());
////		return ResponseEntity.status(200).build();
////	}
//
//
//	@PatchMapping("/match")
//	public ResponseEntity atualizarMatch(@RequestBody @Valid AtualizarMatchIdentifierRequest matchIdentifier) {
//		Optional<Match> match = matchRepository.findByFkDoadorAndFkProdutoDoacaoAndFkDonatario(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario());
//		if (match.isEmpty()) {
//			return ResponseEntity.status(404).build();
//		}
//		matchRepository.updateMatchSetStatus(matchIdentifier.getFkDoador(), matchIdentifier.getFkProdutoDoacao(), matchIdentifier.getFkDonatario(), matchIdentifier.getStatus());
//		return ResponseEntity.status(200).body(matchIdentifier);
//	}
//
//	@PostMapping(value = "/importacao", consumes = "text/txt")
//	public ResponseEntity postImportacao(@RequestBody byte[] importacao) {
//		Txt importarTxt = new Txt();
//
//		if (importarTxt.leArquivoTxt(importacao, categoriaRepository, produtoRepository, usuarioService)) {
//			return ResponseEntity.status(201).build();
//		}
//		return ResponseEntity.status(404).build();
//	}
//
//	@GetMapping("/exportacao")
//	public ResponseEntity getExportacao() {
//		Txt exportarTxt = new Txt();
//		List<ProdutoDoacao> listaProduto;
//		listaProduto = produtoRepository.findAll();
//
//		if (exportarTxt.gravaArquivoTxt(listaProduto)) {
//			return ResponseEntity.status(201).build();
//		}
//
//		return ResponseEntity.status(404).build();
//	}
}
