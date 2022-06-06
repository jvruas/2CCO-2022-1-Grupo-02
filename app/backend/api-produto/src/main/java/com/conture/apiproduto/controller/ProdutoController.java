package com.conture.apiproduto.controller;

import com.conture.apiproduto.model.dto.response.MatchResponse;
import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;
import com.conture.apiproduto.model.entity.*;
import com.conture.apiproduto.model.dto.request.PreferenciaDonatarioRequest;
import com.conture.apiproduto.model.dto.request.ProdutoDoacaoRequest;
import com.conture.apiproduto.repository.*;
import com.conture.apiproduto.api.rest.usuario.UsuarioClient;
import com.conture.apiproduto.service.MatchService;

import com.conture.apiproduto.util.collection.FilaObj;
import com.conture.apiproduto.util.collection.PilhaObj;
import com.conture.apiproduto.util.file.Txt;
import com.conture.apiproduto.util.sort.Iterator;
import com.conture.apiproduto.util.sort.SortDateAscending;
import com.conture.apiproduto.util.sort.AscendingListIterator;
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
	private AvaliacaoRepository avaliacaoRepository;

	@Autowired
	private CategoriaProdutoRepository categoriaRepository;

	@Autowired
	private UsuarioClient usuarioClient;

	PilhaObj<String> pilhaHistorico = new PilhaObj<>(10);

	FilaObj<Avaliacao> filaAvaliacao = new FilaObj<>(20);


	@PostMapping()
	public ResponseEntity<Integer> adicionarProduto(@RequestBody @Valid ProdutoDoacaoRequest produtoRequest) {
		if (!this.categoriaRepository.existsById(produtoRequest.getFkCategoriaProduto())) {
			return status(404).build();
		}

		try {
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(produtoRequest.getIdDoador());
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
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
			Integer idSituacaoAtual = this.usuarioClient.getIdSituacaoAtual(preferenciaDonatario.getFkSituacaoAtual());
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
			Integer idDonatario = this.usuarioClient.getIdUsuarioLogado(idDonatarioRequest);
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


	//	@PostMapping("/avaliacao")
//	public ResponseEntity avaliarUsuario(@RequestBody @Valid AvaliacaoRequest avaliacao){
//		Optional<Usuario> avaliado = Optional.ofNullable(usuarioRepository.findByIdUsuario(avaliacao.getFkDoador()));
//		Optional<UsuarioLogadoResponse> avaliador = GerenciadorUsuario.buscaUsuarioLogado(avaliacao.getFkDonatario());
//
//		//if(avaliado.isEmpty() || avaliador.isEmpty()){
//		//	return ResponseEntity.status(404).build();
//		//}
//
//		Avaliacao novaAvaliacao = new Avaliacao();
//		novaAvaliacao.setFkDoador(avaliacao.getFkDoador());
//		novaAvaliacao.setFkDonatario(avaliacao.getFkDonatario());
//		novaAvaliacao.setValor(avaliacao.getValor());
//		novaAvaliacao.setComentario(avaliacao.getComentario());
//
//
//		this.avaliacaoRepository.save(novaAvaliacao);
//		return ResponseEntity.status(200).build();
//	}


	//	@GetMapping("/avaliacoes")
//	public ResponseEntity listarAvaliacoes(@RequestParam Integer fkDoador) {
//		List<Avaliacao> lista = this.avaliacaoRepository.findByFkDoador(fkDoador);
//
//		for (int i = 0; i < lista.size(); i++) {
//			this.filaAvaliacao.insert(lista.get(i));
//		}
//
//		return ResponseEntity.status(200).body(this.filaAvaliacao);
//	}


	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoDoacaoResponse> pesquisarProdutoId(@PathVariable @NotNull @Min(1) Integer idProduto) {
		Optional<ProdutoDoacaoResponse> produto = this.produtoRepository.getProdutoDoacaoResponseById(idProduto);

		if (produto.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(produto.get());
	}


	@GetMapping("/todas-categorias")
	public ResponseEntity<List<CategoriaProduto>> listarCategorias() {
		List<CategoriaProduto> listaCategoria = this.categoriaRepository.findAll();

		if (listaCategoria.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaCategoria);
	}


	@GetMapping("/categoria")
	public ResponseEntity<List<ProdutoDoacaoResponse>> listarProdutoCategoria(
			@RequestParam @NotNull @Min(1) Integer idDoador,
			@RequestParam @NotNull @Min(1) Integer idCategoria
	) {
		Optional<CategoriaProduto> categoriaProduto = this.categoriaRepository.findById(idCategoria);

		if (categoriaProduto.isEmpty()) {
			return status(404).build();
		}

		List<ProdutoDoacaoResponse> listaProduto = this.produtoRepository.getAllByStatusNaoDoado(idDoador);

		if (listaProduto.isEmpty()) {
			return status(204).build();
		}

		List<ProdutoDoacaoResponse> listaProdutoResponse = new ArrayList();

		Iterator<ProdutoDoacaoResponse> iterator = new AscendingListIterator(listaProduto);

		while (iterator.hasNext()) {
			ProdutoDoacaoResponse currentProdutoDoacao = iterator.getNext();
			if (currentProdutoDoacao.getCategoriaProduto().equalsIgnoreCase(categoriaProduto.get().getNome())) {
				listaProdutoResponse.add(currentProdutoDoacao);
			}
		}

		if (listaProdutoResponse.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaProdutoResponse);
	}


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
	public ResponseEntity<List<String>> listarHistorico() {
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
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
			Integer idDoador = this.usuarioClient.getIdUsuarioLogado(idDoadorRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.produtoRepository.logicDelete(idProduto);
		return status(200).build();
	}


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

		Optional<Integer> idMatch = this.matchRepository.getIdMatchByIdProdutoAndIdDonatario(idProduto, idDonatarioRequest);

		if (idMatch.isEmpty()) {
			return status(404).build();
		}

		try {
			Integer idDonatario = this.usuarioClient.getIdUsuarioLogado(idDonatarioRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.matchRepository.updateStatusTrueById(idMatch.get());
		this.produtoRepository.updateStatusTrueAndDataConclusaoNowById(idProduto, Date.from(Instant.now()));

		return status(200).build();
	}


	// TODO: Adicionar delete logico para entidade Match
	@DeleteMapping("/{idProduto}/match")
	public ResponseEntity deletarMatch(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam(name = "idDonatario") @NotNull @Min(1) Integer idDonatarioRequest
	) {
		if (!this.produtoRepository.hasById(idProduto)) {
			return status(404).build();
		}

		Optional<Integer> idMatch = this.matchRepository.getIdMatchByIdProdutoAndIdDonatario(idProduto, idDonatarioRequest);

		if (idMatch.isEmpty()) {
			return status(404).build();
		}

		try {
			Integer idDonatario = this.usuarioClient.getIdUsuarioLogado(idDonatarioRequest);
		} catch (FeignException response) {
			if (response.status() == -1) { // Service Unavailable
				return status(503).build();
			}

			return status(401).build();
		}

		this.matchRepository.deleteById(idMatch.get());

		return status(200).build();
	}


	@PatchMapping("/{idProduto}/visualizacao")
	public ResponseEntity incrementarVisualizacao(
			@PathVariable @NotNull @Min(1) Integer idProduto,
			@RequestParam @NotNull @Min(1) Integer quantidadeVisualizacao
	) {
		Optional<Integer> quantidadeVisualizacaoAtual = this.produtoRepository.getQuantidadeVisualizacaoById(idProduto);

		if (quantidadeVisualizacaoAtual.isEmpty()) {
			return status(404).build();
		}

		this.produtoRepository.updateQuantidadeVisualizacaoById(idProduto, quantidadeVisualizacaoAtual.get() + quantidadeVisualizacao);
		return ResponseEntity.status(200).build();
	}


	@PostMapping(value = "/importacao", consumes = "text/txt")
	public ResponseEntity postImportacao(@RequestBody byte[] importacao) {
		Txt importarTxt = new Txt();

		if (importarTxt.leArquivoTxt(importacao, this.categoriaRepository, this.produtoRepository, this.usuarioClient)) {
			return ResponseEntity.status(201).build();
		}

		return ResponseEntity.status(404).build();
	}


	@GetMapping("/exportacao")
	public ResponseEntity getExportacao() {
		Txt exportarTxt = new Txt();
		List<ProdutoDoacao> listaProduto;
		listaProduto = this.produtoRepository.findAll();

		if (exportarTxt.gravaArquivoTxt(listaProduto, this.usuarioClient)) {
			return ResponseEntity.status(201).build();
		}

		return ResponseEntity.status(404).build();
	}
}
