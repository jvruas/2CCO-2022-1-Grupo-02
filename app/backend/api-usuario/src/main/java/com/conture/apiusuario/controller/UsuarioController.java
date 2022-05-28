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
		if (!this.situacaoAtualRepository.existsById(novoUsuario.getFkSituacaoAtual())
				|| this.usuarioRepository.existsByCpf(novoUsuario.getCpf())
				|| this.usuarioRepository.existsByEmail(novoUsuario.getEmail())
		) {
			return status(400).build();
		}

		this.usuarioRepository.save(Usuario.fromPattern(novoUsuario));

		return status(201).body(this.usuarioRepository.getIdUserByCpf(novoUsuario.getCpf()));
	}


	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogadoResponse> login(@RequestBody @Valid UsuarioLoginRequest usuario) {
		Optional<UsuarioLogadoResponse> usuarioLogado = this.usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());

		if (usuarioLogado.isEmpty()) {
			return status(404).build();
		}

		GerenciadorUsuario.login(usuarioLogado.get());

		return status(201).body(usuarioLogado.get());
	}


	@DeleteMapping("/logoff")
	public ResponseEntity logoff(Integer idUsuario) {
		if (!GerenciadorUsuario.logoff(idUsuario)){
			return status(404).build();
		}

		return status(200).build();
	}

	// TODO: + existsUsuarioLogado(idUsuario: Integer): ResponseEntity<Boolean>


	@PostMapping("/reporte")
	public ResponseEntity reportarUsuario(@RequestBody @Valid ReporteRequest reporte) {
		Optional<UsuarioLogadoResponse> reportador =
				GerenciadorUsuario.buscaUsuarioLogado(reporte.getFkReportador());

		if (reportador.isEmpty()
				|| !this.usuarioRepository.existsById(reporte.getFkReportado())
				|| !this.tipoReporteRepository.existsById(reporte.getFkTipoReporte())
		) {
			return status(404).build();
		}

		this.reporteRepository.save(Reporte.fromPattern(
				reportador.get().getIdUsuario(),
				reporte.getFkReportado(),
				reporte.getFkTipoReporte()
		));


		return status(201).build();
	}

	// TODO: Fazer comunicação com API de produto
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


	@DeleteMapping
	public ResponseEntity deletarUsuario(
			@RequestParam Integer idUsuario,
			@RequestParam String motivoDesligamento
	) {
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if (usuarioLogado.isEmpty()) {
			return status(404).build();
		}

		GerenciadorUsuario.logoff(idUsuario);

		this.imagemUsuarioRepository.deleteByUsuarioIdUsuario(idUsuario);
		this.desligamentoContaRepository.save(DesligamentoConta.fromPattern(motivoDesligamento, idUsuario));
		this.usuarioRepository.logicDelete(idUsuario);

		return status(200).build();
	}


	@GetMapping("/nome")
	public ResponseEntity<List<UsuarioLogadoResponse>> pesquisarUsuarioNome(
			@RequestParam @Valid String nome
	) {
		List<UsuarioLogadoResponse> listaUsuarios = this.usuarioRepository
														.findByNomeIgnoreCaseContainsOrderByNome(nome);

		if (listaUsuarios.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(listaUsuarios);
	}


	@GetMapping("/id")
	public ResponseEntity<UsuarioLogadoResponse> pesquisarUsuarioId(@RequestParam @Valid Integer idUsuario) {
		Optional<UsuarioLogadoResponse> usuario = this.usuarioRepository.findByIdUsuario(idUsuario);

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		return status(200).body(usuario.get());
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

	// TODO: Fazer comunicação com API de produto
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
	public ResponseEntity atualizarSenha(@RequestBody UsuarioSenhaRequest usuarioSenha) {
		Optional<Usuario> usuario = this.usuarioRepository.findById(usuarioSenha.getIdUsuario());

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		if (!usuario.get().getSenha().equals(usuarioSenha.getSenhaAtual())
				|| usuario.get().getSenha().equals(usuarioSenha.getSenhaNova())
		) {
			return status(400).build();
		}

		this.usuarioRepository.updateSenha(usuarioSenha.getIdUsuario(), usuarioSenha.getSenhaNova());

		return status(200).build();
	}


	@PutMapping("/perfil")
	public ResponseEntity atualizarPerfil(@RequestBody @Valid UsuarioPerfilRequest usuarioPerfil) {
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(usuarioPerfil.getIdUsuario());

		if (usuarioLogado.isEmpty()) {
			return ResponseEntity.status(404).build();
		}

		Usuario usuario = usuarioRepository.getById(usuarioPerfil.getIdUsuario());
		usuario.setGenero(usuarioPerfil.getGenero());
		usuario.setEstadoCivil(usuarioPerfil.getEstadoCivil());
		usuario.setCep(usuarioPerfil.getCep());
		usuario.setGrauEscolaridade(usuarioPerfil.getGrauEscolaridade());
		usuario.setTelefone(usuarioPerfil.getTelefone());
		usuario.setSituacaoAtual(usuarioPerfil.getFkSituacaoAtual());

		this.usuarioRepository.save(usuario);
		return ResponseEntity.status(200).build();
	}

	@PostMapping(value = "/imagem/{idUsuario}", consumes = "image/jpeg")
	public ResponseEntity adicionarImagem(
			@PathVariable Integer idUsuario,
			@RequestParam String tipoImagem,
			@RequestBody byte[] novaImagem
	) {
		if (novaImagem.length > 16_777_216 || novaImagem.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(idUsuario, tipoImagem);

		if (idImagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.imagemUsuarioRepository.updateImagem(idImagemUsuario.get(), novaImagem);

		return status(200).build();
	}


	@PatchMapping(value = "/imagem/{idUsuario}", consumes = "image/jpeg")
	public ResponseEntity atualizarImagem(
			@PathVariable Integer idUsuario,
			@RequestParam String tipoImagem,
			@RequestBody byte[] novaImagem
	) {
		if (novaImagem.length > 16_777_216 || novaImagem.length == 0) { // Magical Number -> 16MB
			return status(400).build();
		}

		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(idUsuario, tipoImagem);

		if (idImagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.imagemUsuarioRepository.updateImagem(idImagemUsuario.get(), novaImagem);

		return status(200).build();
	}

	//+ adicionarImagem(idUsuario: Integer, tipoImagem: String): ResponseEntity<Integer>
	//+ deletarImagem(idUsuario: Integer, tipoImagem: String): ResponseEntity
}
