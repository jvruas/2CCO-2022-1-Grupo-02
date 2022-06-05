package com.conture.apiproduto.controller;

import com.conture.apiproduto.model.dto.response.MatchResponse;
import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;
import com.conture.apiproduto.model.entity.*;
import com.conture.apiproduto.model.dto.request.PreferenciaDonatarioRequest;
import com.conture.apiproduto.model.dto.request.ProdutoDoacaoRequest;
import com.conture.apiproduto.repository.*;
import com.conture.apiproduto.api.rest.usuario.UsuarioService;
import com.conture.apiproduto.service.MatchService;
import com.conture.apiproduto.util.*;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.*;

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
			return status(404).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(produtoRequest.getIdDoador());
		} catch (FeignException response) {
			return status(401).build();
		}

		produtoRequest.setNome(produtoRequest.getNome().trim().toUpperCase());
		produtoRequest.setMarca(produtoRequest.getMarca().trim().toUpperCase());
		produtoRequest.setModelo(produtoRequest.getModelo().trim().toUpperCase());
		produtoRequest.setDescricao(produtoRequest.getDescricao().trim());

		this.produtoRepository.save(ProdutoDoacao.fromPattern(produtoRequest));
		return status(201).body(this.produtoRepository.findTop1ByRemovidoFalseAndStatusFalseAndFkDoadorOrderByIdProdutoDoacaoDesc(produtoRequest.getIdDoador()).get().getIdProdutoDoacao());
	}


	@PostMapping(value = "/{idProduto}/imagem-principal", consumes = "image/jpeg")
	public ResponseEntity<Integer> adicionarImagemPrincipalProduto(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest,
			@RequestBody @NotNull byte[] imagemPrincipal
	) {
		if (imagemPrincipal.length > 16_777_216 || imagemPrincipal.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		if (!this.produtoRepository.hasByStatusFalseAndRemovidoFalseAndIdAndFkDoador(idProduto, idDoadorRequest)) {
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
		return status(201).body(idProduto);
	}


	@PostMapping(value = "/{idProduto}/imagem-extra", consumes = "image/jpeg")
	public ResponseEntity<Integer> adicionarImagemExtra(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest,
			@RequestBody @NotNull byte[] imagem
	) {
		if (imagem.length > 16_777_216 || imagem.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		if (!this.produtoRepository.hasByStatusFalseAndRemovidoFalseAndIdAndFkDoador(idProduto, idDoadorRequest)) {
			return status(404).build();
		}

		if (this.imagemProdutoDoacaoRepository.countByProdutoDoacaoIdProdutoDoacao(idProduto) >= 4) {
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

		this.imagemProdutoDoacaoRepository.save(ImagemProdutoDoacao.fromPattern(idProduto, imagem));
		return status(201).body(this.imagemProdutoDoacaoRepository.findTop1ByProdutoDoacaoIdProdutoDoacaoOrderByIdImagemProdutoDoacaoDesc(idProduto).get().getIdImagemProdutoDoacao());
	}


	@PostMapping("/preferencia")
	public ResponseEntity<Integer> adicionarPreferenciaDonatario(@RequestBody @Valid PreferenciaDonatarioRequest preferenciaDonatario) {
		if (!this.produtoRepository.hasById(preferenciaDonatario.getFkProdutoDoacao())) {
			return status(404).build();
		}

		if (this.preferenciaRepository.existsByProdutoDoacaoIdProdutoDoacao(preferenciaDonatario.getFkProdutoDoacao())) {
			return status(409).build();
		}

		try {
			Integer idSituacaoAtual = this.usuarioService.getIdSituacaoAtual(preferenciaDonatario.getFkSituacaoAtual());
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(404).build();
		}

		this.preferenciaRepository.save(PreferenciaDonatario.fromPattern(preferenciaDonatario));
		return status(201).body(this.preferenciaRepository.findTop1ByProdutoDoacaoIdProdutoDoacaoOrderByIdPreferenciaDonatarioDesc(preferenciaDonatario.getFkProdutoDoacao()).get().getIdPreferenciaDonatario());
	}


	@PostMapping("/{idProduto}/match")
	public ResponseEntity<Integer> adicionarMatch(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDonatario") @NotNull @Min(1) Integer idDonatarioRequest
	) {
		if (!this.produtoRepository.hasById(idProduto)) {
			return status(404).build();
		}

		if (this.produtoRepository.getFkDoadorById(idProduto) == idDonatarioRequest) {
			return status(403).build();
		}

		if (this.matchRepository.existsByProdutoDoacaoIdProdutoDoacaoAndFkDonatario(idProduto, idDonatarioRequest)) {
			return status(409).build();
		}

		try {
			Integer idDonatario = this.usuarioService.getIdUsuarioLogado(idDonatarioRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		Match match = Match.fromPattern(idProduto, idDonatarioRequest);

		// TODO: Implement Method calcularPorcentagemTo because actually it is mocked service.
		match.setMatchPorcentagem(MatchService.calcularPorcentagemTo(idDonatarioRequest));

		this.matchRepository.save(match);
		return status(201).body(this.matchRepository.findTop1ByStatusFalseAndVisualizadoFalseAndProdutoDoacaoIdProdutoDoacaoAndFkDonatarioOrderByIdMatchDesc(idProduto, idDonatarioRequest).get().getIdMatch());
	}


	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoDoacaoResponse> pesquisarProdutoId(@PathVariable @NotNull @Min(1) Integer idProduto) {
		Optional<ProdutoDoacaoResponse> produto = this.produtoRepository.getProdutoDoacaoResponseById(idProduto);

		if (produto.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(produto.get());
	}


	@GetMapping("/categoria")
	public ResponseEntity<List<CategoriaProduto>> listarCategorias() {
		List<CategoriaProduto> listaCategoria = this.categoriaRepository.findAll();

		if (listaCategoria.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaCategoria);
	}


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


	@GetMapping("/nome")
	public ResponseEntity<List<ProdutoDoacaoResponse>> pesquisarProduto(@RequestParam @NotNull @Size(min = 1, max = 165) String nome) {
		String cleansedNome = nome.trim().toUpperCase();

		if (cleansedNome.equals("")) {
			return status(400).build();
		}

		List<ProdutoDoacaoResponse> listaProduto = this.produtoRepository.searchProduto(cleansedNome);

		this.pilhaHistorico.push(nome);

		if (listaProduto.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaProduto);
	}

	// TODO: Melhorar para funcionar para cada usuario logado
	@GetMapping("/historico")
	public ResponseEntity listarHistorico() {
		if (this.pilhaHistorico.isEmpty()) {
			return status(204).build();
		}

		List<String> listaHistorico = new ArrayList();
		PilhaObj<String> pilhaAuxiliar = new PilhaObj(10);

		while (!this.pilhaHistorico.isEmpty()) {
			pilhaAuxiliar.push(this.pilhaHistorico.pop());
			listaHistorico.add(pilhaAuxiliar.peek());
		}

		while (!pilhaAuxiliar.isEmpty()) {
			this.pilhaHistorico.push(pilhaAuxiliar.pop());
		}

		return status(200).body(listaHistorico);
	}

	@GetMapping("/status")
	public ResponseEntity<List<ProdutoDoacaoResponse>> filtrarStatusProduto(
			@RequestParam @NotNull @Min(1) Integer idDoador,
			@RequestParam @NotNull @Size(min = 5, max = 9) @Pattern(regexp = "(andamento)|(doado)|(recebido)|todos") String status
	) {
		List<ProdutoDoacaoResponse> listaProduto = new ArrayList();

		switch (status) {
			case "andamento": {
				listaProduto.addAll(this.matchRepository.getAllByStatusAndamento(idDoador));
				break;
			}
			case "doado": {
				listaProduto.addAll(this.produtoRepository.getAllByStatusDoado(idDoador));
				break;
			}
			case "recebido": {
				listaProduto.addAll(this.matchRepository.getAllByStatusRecebido(idDoador));
				break;
			}
			case "todos": {
				listaProduto.addAll(this.matchRepository.getAllByStatusAndamento(idDoador));
				listaProduto.addAll(this.produtoRepository.getAllByStatusDoado(idDoador));
				listaProduto.addAll(this.matchRepository.getAllByStatusRecebido(idDoador));

				Collections.sort(listaProduto, new SortDateAscending());
				break;
			}
		}

		if (listaProduto.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaProduto);
	}


	@GetMapping("/disponiveis")
	public ResponseEntity<List<ProdutoDoacaoResponse>> listarProdutosUsuario(@RequestParam @NotNull @Min(1) Integer idDoador) {
		List<ProdutoDoacaoResponse> lista = this.produtoRepository.getAllByStatusNaoDoado(idDoador);

		if (lista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return status(200).body(lista);
	}

	@GetMapping("/{idProduto}/match")
	public ResponseEntity<List<MatchResponse>> listarMatch(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest
	) {
		if (!this.produtoRepository.hasByStatusFalseAndRemovidoFalseAndIdAndFkDoador(idProduto, idDoadorRequest)) {
			return status(404).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		List<MatchResponse> listaMatch = this.matchRepository.getAllMatchResponseByStatusFalseAndIdProduto(idProduto);

		if (listaMatch.isEmpty()) {
			return status(204).build();
		}

		this.matchRepository.updateAllStatusByIdProduto(idProduto);

		return status(200).body(listaMatch);
	}


	@GetMapping("/{idProduto}/match/quantidade-total")
	public ResponseEntity<Long> contarTotalMatch(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest
	) {
		if (!this.produtoRepository.hasByStatusRemovidoFalseAndIdAndFkDoador(idProduto, idDoadorRequest)) {
			return status(404).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		Long quantidadeMatch = this.matchRepository.countByIdProduto(idProduto);
		return ResponseEntity.status(200).body(quantidadeMatch);
	}


	@GetMapping("/match/quantidade-nao-visualizada")
	public ResponseEntity<Long> contarMatchNaoVisualizado(
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest
	) {
		if (!this.produtoRepository.hasByIdDoador(idDoadorRequest)) {
			return status(204).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		Long quantidadeMatch = this.matchRepository.countByVisualizadoFalseAndIdDoador(idDoadorRequest);
		return ResponseEntity.status(200).body(quantidadeMatch);
	}


	@DeleteMapping("/{idProduto}")
	public ResponseEntity deletarProduto(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDoador") @NotNull @Min(1) Integer idDoadorRequest
	) {
		if (!this.produtoRepository.hasByStatusFalseAndRemovidoFalseAndIdAndFkDoador(idProduto, idDoadorRequest)) {
			return status(404).build();
		}

		try {
			Integer idDoador = this.usuarioService.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.produtoRepository.logicDelete(idProduto);
		return status(200).build();
	}


	// Date.from(Instant.now()
	@PatchMapping("/{idProduto}")
	public ResponseEntity concluirDoacao(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDonatario") @NotNull @Min(1) Integer idDonatarioRequest
	) {
		if (!this.produtoRepository.hasById(idProduto)) {
			return status(404).build();
		}

		if (this.produtoRepository.getFkDoadorById(idProduto) == idDonatarioRequest) {
			return status(403).build();
		}

		try {
			Integer idDonatario = this.usuarioService.getIdUsuarioLogado(idDonatarioRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.matchRepository.updateStatusTrueByIdProdutoAndIdDonatario(idProduto, idDonatarioRequest);
		

		return status(200).build();
	}

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
