package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.*;
import com.conture.apiusuario.entity.*;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.utility.FilaObj;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private ReporteRepository reporteRepository;
	@Autowired
	private SituacaoAtualRepository situacaoAtualRepository;
	@Autowired
	private TipoReporteRepository tipoReporteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ImagemUsuarioRepository imagemUsuarioRepository;
	@Autowired
	private DesligamentoContaRepository desligamentoContaRepository;
	FilaObj<Avaliacao> filaAvaliacao = new FilaObj<>(20);


	@PostMapping
	public ResponseEntity<Integer> adicionarUsuario(@RequestBody @Valid UsuarioCadastroRequest novoUsuario) {

//		if (this.situacaoAtualRepository.existsById(novoUsuario.getFkSituacaoAtual())
//				|| this.usuarioRepository.existsByCpf(novoUsuario.getCpf())
//				|| this.usuarioRepository.existsByEmail(novoUsuario.getEmail())
//		) {
//			return status(409).build();
//		}

		if (!this.situacaoAtualRepository.existsById(novoUsuario.getFkSituacaoAtual())) {
			return status(400).build();
		}

		if (this.usuarioRepository.existsByCpf(novoUsuario.getCpf())
				|| this.usuarioRepository.existsByEmail(novoUsuario.getEmail())
		) {
			return status(409).build();
		}


		novoUsuario.setNome(novoUsuario.getNome().trim().toUpperCase());
		novoUsuario.setSobrenome(novoUsuario.getSobrenome().trim().toUpperCase());

		this.usuarioRepository.save(Usuario.fromPattern(novoUsuario));

		return status(201).body(this.usuarioRepository.getIdUserByCpf(novoUsuario.getCpf()).get());
	}


	@PostMapping("/login")
	public ResponseEntity<UsuarioLogadoResponse> login(@RequestBody @Valid UsuarioLoginRequest usuario) {
		Optional<UsuarioLogadoResponse> usuarioPesquisado = this.usuarioRepository.getByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

		if (usuarioPesquisado.isEmpty()) {
			return status(404).build();
		}

		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(usuarioPesquisado.get().getIdUsuario());

		if (usuarioLogado.isPresent()) {
			return status(409).build();
		}

		GerenciadorUsuario.login(usuarioPesquisado.get());

		return status(201).body(usuarioPesquisado.get());
	}


	@DeleteMapping("/{idUsuario}/login")
	public ResponseEntity logoff(@PathVariable @Min(1) Integer idUsuario) {
		if (!GerenciadorUsuario.logoff(idUsuario)) {
			return status(404).build();
		}

		return status(200).build();
	}


	@GetMapping("/{idUsuario}/login")
	public ResponseEntity<UsuarioLogadoResponse> existsUsuarioLogado(@RequestParam @Min(1) Integer idUsuario) {
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if (usuarioLogado.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(usuarioLogado.get());
	}


	@PostMapping("/reporte")
	public ResponseEntity reportarUsuario(@RequestBody @Valid ReporteRequest reporte) {
		Optional<UsuarioLogadoResponse> reportador =
				GerenciadorUsuario.buscaUsuarioLogado(reporte.getFkReportador());

		if (reportador.isEmpty()
				|| !this.usuarioRepository.hasUsuarioById(reporte.getFkReportado())
				|| !this.tipoReporteRepository.existsById(reporte.getFkTipoReporte())
		) {
			return status(401).build();
		}

		this.reporteRepository.save(Reporte.fromPattern(
				reportador.get().getIdUsuario(),
				reporte.getFkReportado(),
				reporte.getFkTipoReporte()
		));

		return status(201).build();
	}

	// TODO: Passar para a API de produto
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


	@DeleteMapping()
	public ResponseEntity deletarUsuario(
			@RequestBody @Valid UsuarioLoginRequest usuarioRequest,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[A,T,Q,P,N,X]") String motivoDesligamento
	) {
		Optional<UsuarioLogadoResponse> usuario = this.usuarioRepository.getByEmailAndSenha(usuarioRequest.getEmail(), usuarioRequest.getSenha());

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(usuario.get().getIdUsuario());

		if (usuarioLogado.isEmpty()) {
			return status(403).build();
		}

		Integer idUsuario = usuarioLogado.get().getIdUsuario();

		GerenciadorUsuario.logoff(idUsuario);

		this.imagemUsuarioRepository.deleteByUsuarioIdUsuario(idUsuario);
		this.desligamentoContaRepository.save(DesligamentoConta.fromPattern(motivoDesligamento, idUsuario));
		this.usuarioRepository.logicDelete(idUsuario);

		return status(200).build();
	}


	@GetMapping("/nome")
	public ResponseEntity<List<UsuarioLogadoResponse>> pesquisarUsuarioNome(
			@RequestParam String nome
	) {
		String cleansedNome = nome.trim().toUpperCase();

		if (cleansedNome.equals("")) {
			return status(400).build();
		}

		List<UsuarioLogadoResponse> listaUsuarioPesquisado = this.usuarioRepository.getByNome(cleansedNome);

		if (listaUsuarioPesquisado.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaUsuarioPesquisado);
	}


	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioLogadoResponse> pesquisarUsuarioId(@PathVariable @Min(1) Integer idUsuario) {
		Optional<UsuarioLogadoResponse> usuario = this.usuarioRepository.getUsuarioLogadoById(idUsuario);

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(usuario.get());
	}


	@GetMapping("/email")
	public ResponseEntity<Integer> pesquisarUsuarioEmail(@RequestParam @Size(max = 80) @Pattern(regexp = "[@]") String email) {
		if (email.trim().equals("")) {
			return status(400).build();
		}

		Optional<Integer> idUsuario = this.usuarioRepository.getByEmail(email);

		if (idUsuario.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(idUsuario.get());
	}


	@GetMapping("/tipos-de-reporte")
	public ResponseEntity<List<TipoReporte>> listarTiposReporte() {
		List<TipoReporte> lista = this.tipoReporteRepository.findAll();

		if (lista.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(lista);
	}


	@GetMapping("/situacao-atual")
	public ResponseEntity<List<SituacaoAtual>> listarSituacaoAtual() {
		List<SituacaoAtual> lista = this.situacaoAtualRepository.findAll();

		if (lista.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(lista);
	}

	// TODO: Passar para a API de produto
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


	@PatchMapping("/senha")
	public ResponseEntity atualizarSenha(@RequestBody @Valid UsuarioSenhaRequest usuarioSenha) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioSenha.getIdUsuario());

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		if (!usuario.get().getSenha().equals(usuarioSenha.getSenhaAtual())
		) {
			return status(403).build();
		}

		if (usuario.get().getSenha().equals(usuarioSenha.getSenhaNova())) {
			return status(409).build();
		}

		this.usuarioRepository.updateSenha(usuarioSenha.getIdUsuario(), usuarioSenha.getSenhaNova());

		return status(200).build();
	}


	@PutMapping("/{idUsuario}/perfil")
	public ResponseEntity atualizarPerfil(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestBody @Valid UsuarioPerfilRequest usuarioPerfil
	) {
		if (!this.situacaoAtualRepository.existsById(usuarioPerfil.getFkSituacaoAtual())) {
			return status(400).build();
		}

		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if (usuarioLogado.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		Usuario usuario = this.usuarioRepository.getUsuarioById(idUsuario).get();
		usuario.setGenero(usuarioPerfil.getGenero());
		usuario.setEstadoCivil(usuarioPerfil.getEstadoCivil());
		usuario.setCep(usuarioPerfil.getCep());
		usuario.setGrauEscolaridade(usuarioPerfil.getGrauEscolaridade());
		usuario.setTelefone(usuarioPerfil.getTelefone());
		usuario.setVerificado(usuarioPerfil.getVerificado());
		usuario.setSituacaoAtual(usuarioPerfil.getFkSituacaoAtual());

		this.usuarioRepository.save(usuario);
		return ResponseEntity.status(200).build();
	}


	@PostMapping(value = "/{idUsuario}/imagem", consumes = "image/jpeg")
	public ResponseEntity adicionarImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem,
			@RequestBody byte[] novaImagem
	) {
		if (novaImagem.length > 16_777_216 || novaImagem.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		if (!this.usuarioRepository.existsById(idUsuario)) {
			return status(404).build();
		}

		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(Usuario.fromPattern(idUsuario), tipoImagem);

		if (idImagemUsuario.isPresent()) {
			return status(409).build();
		}

		this.imagemUsuarioRepository.save(ImagemUsuario.fromPattern(tipoImagem, novaImagem, idUsuario));

		return status(201).build();
	}


	@PatchMapping(value = "/{idUsuario}/imagem", consumes = "image/jpeg")
	public ResponseEntity atualizarImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem,
			@RequestBody byte[] novaImagem
	) {
		if (novaImagem.length > 16_777_216 || novaImagem.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(Usuario.fromPattern(idUsuario), tipoImagem);

		if (idImagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.imagemUsuarioRepository.updateImagem(idImagemUsuario.get(), novaImagem);

		return status(200).build();
	}


	@GetMapping(value = "/{idUsuario}/imagem", produces = "image/jpeg")
	public ResponseEntity<byte[]> getImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem
	) {
		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(Usuario.fromPattern(idUsuario), tipoImagem);

		if (idImagemUsuario.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(this.imagemUsuarioRepository.getById(idImagemUsuario.get()).getImagemUsuario());
	}


	@DeleteMapping("/{idUsuario}/imagem")
	public ResponseEntity deletarImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem
	) {
		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(Usuario.fromPattern(idUsuario), tipoImagem);

		if (idImagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.imagemUsuarioRepository.deleteById(idImagemUsuario.get());

		return status(200).build();
	}
}
