package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.*;
import com.conture.apiusuario.entity.*;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.services.S3Service;
import com.conture.apiusuario.utility.Email;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(allowedHeaders = "*")
public class UsuarioController {
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

	@Autowired
	private S3Service s3Service;

	private Email email = new Email();


	//	TODO:TESTES
	@PostMapping
	public ResponseEntity<Integer> adicionarUsuario(@RequestBody @Valid UsuarioCadastroRequest novoUsuario) {
		if (!this.situacaoAtualRepository.existsById(novoUsuario.getFkSituacaoAtual())
				|| novoUsuario.getFkSituacaoAtual() == 1
		) {
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

	//TODO:TESTES
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


	//TODO:
	@DeleteMapping("/{idUsuario}/login")
	public ResponseEntity logoff(@PathVariable @Min(1) Integer idUsuario) {
		if (!GerenciadorUsuario.logoff(idUsuario)) {
			return status(404).build();
		}

		return status(200).build();
	}


	//TODO:
	@GetMapping("/{idUsuario}/login")
	public ResponseEntity existsUsuarioLogado(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 2, max = 7) @Pattern(regexp = "(usuario)|(id)") String responseType
	) {
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if (usuarioLogado.isEmpty()) {
			return status(404).build();
		}

		if (responseType.equals("id")) {
			return status(200).body(usuarioLogado.get().getIdUsuario());
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

	@GetMapping("/cpf")
	public ResponseEntity<Integer> pesquisarUsuarioCpf(@RequestParam @Size(min = 11, max = 11) @Pattern(regexp = "[^[0-9]+$]") String cpf) {
		if (cpf.trim().equals("")) {
			return status(400).build();
		}

		Optional<Integer> idUsuario = this.usuarioRepository.getByCpf(cpf);

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

	// TODO: IMPROVE
	@PostMapping("conta/validacao-email")
	public ResponseEntity<Integer> enviarEmail(
			@RequestParam String emailDestinatario,
			@RequestParam Integer idUsuario
	) throws FileNotFoundException {
		Usuario usuario = usuarioRepository.getById(idUsuario);
<<<<<<< HEAD
		if (usuario.getVerificado()) {
			return status(400).body("usuario ja validado");
=======

		if (usuario.getVerificado()){
			return status(409).build();
>>>>>>> development
		}

		String codigo = email.gerarCodigo();
		usuario.setCodigo(codigo);
		usuarioRepository.save(usuario);
		String corpoEmail = email.gerarEmail(codigo);
		email.sendEmail(corpoEmail, emailDestinatario);

		return status(200).body(idUsuario);
	}

	@PostMapping("conta/validacao-codigo")
	public ResponseEntity validarUsuario(@RequestParam Integer idUsuario,
										 @RequestParam String codigo) {
		Usuario usuario = usuarioRepository.getById(idUsuario);
		String codigoGerado = usuario.getCodigo();
		if (codigoGerado.equals(codigo)) {
			usuario.setVerificado(true);
			usuarioRepository.save(usuario);
			return status(200).build();
		}
		return status(400).build();
	}

	@GetMapping("/situacao-atual")
	public ResponseEntity<List<SituacaoAtual>> listarSituacaoAtual() {
		List<SituacaoAtual> lista = this.situacaoAtualRepository.findAll();

		if (lista.isEmpty()) {
			return status(204).build();
		}

		return status(200).body(lista);
	}

	@GetMapping("/situacao-atual/{idSituacaoAtual}")
	public ResponseEntity<Integer> getSituacaoAtual(@PathVariable @Min(1) Integer idSituacaoAtual) {
		if (!this.situacaoAtualRepository.existsById(idSituacaoAtual)) {
			return status(404).build();
		}

		return status(200).body(idSituacaoAtual);
	}


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

	@PatchMapping("/atualizar-senha")
	public ResponseEntity atualizarEsqueciSenha(@RequestParam @Min(1) Integer idUsuario,
												@RequestParam @Size(min = 6, max = 18) String novaSenha) {

		Optional<Usuario> usuario = this.usuarioRepository.getUsuarioById(idUsuario);

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		if (usuario.get().getSenha().equals(novaSenha)) {
			return status(409).build();
		}

		this.usuarioRepository.updateSenha(idUsuario, novaSenha);

		return status(200).build();
	}

	@PutMapping("/{idUsuario}/perfil")
	public ResponseEntity atualizarPerfil(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestBody @Valid UsuarioPerfilRequest usuarioPerfil
	) {
		if (!this.situacaoAtualRepository.existsById(usuarioPerfil.getFkSituacaoAtual())
				|| usuarioPerfil.getFkSituacaoAtual() == 1
		) {
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

		Optional<Usuario> usuario = this.usuarioRepository.getUsuarioById(idUsuario);

		if (usuario.isEmpty()) {
			return status(404).build();
		}

		Optional<Integer> idImagemUsuario = this.imagemUsuarioRepository.getImagemID(Usuario.fromPattern(idUsuario), tipoImagem);

		if (idImagemUsuario.isPresent()) {
			return status(409).build();
		}

		String imageName = String.format("%s_%s", tipoImagem, UUID.nameUUIDFromBytes(usuario.get().getCpf().getBytes()).toString());

		this.imagemUsuarioRepository.save(ImagemUsuario.fromPattern(tipoImagem, this.s3Service.getDefaultBucketName(), imageName, idUsuario));

		this.s3Service.putObject(imageName, novaImagem);

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

		Optional<ImagemUsuario> imagemUsuario = this.imagemUsuarioRepository.getImagemByIdUsuarioAndTipoImagem(Usuario.fromPattern(idUsuario), tipoImagem);

		if (imagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.s3Service.putObject(imagemUsuario.get().getObjectName(), novaImagem);

		return status(200).build();
	}

	@GetMapping(value = "/{idUsuario}/imagem", produces = "image/jpeg")
	public ResponseEntity<byte[]> getImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem
	) {
		Optional<ImagemUsuario> imagemUsuario = this.imagemUsuarioRepository.getImagemByIdUsuarioAndTipoImagem(Usuario.fromPattern(idUsuario), tipoImagem);

		if (imagemUsuario.isEmpty()) {
			return status(404).build();
		}

		byte[] image;

		try {
			image = this.s3Service.getObject(imagemUsuario.get().getBucketName(), imagemUsuario.get().getObjectName());
		} catch (IOException ioException) {
			return status(503).build();
		}

		return status(200).body(image);
	}

	@DeleteMapping("/{idUsuario}/imagem")
	public ResponseEntity deletarImagem(
			@PathVariable @Min(1) Integer idUsuario,
			@RequestParam @Size(min = 1, max = 1) @Pattern(regexp = "[B,P]") String tipoImagem
	) {

		Optional<ImagemUsuario> imagemUsuario = this.imagemUsuarioRepository.getImagemByIdUsuarioAndTipoImagem(Usuario.fromPattern(idUsuario), tipoImagem);

		if (imagemUsuario.isEmpty()) {
			return status(404).build();
		}

		this.s3Service.deleteObject(imagemUsuario.get().getBucketName(), imagemUsuario.get().getObjectName());

		this.imagemUsuarioRepository.deleteById(imagemUsuario.get().getIdImagemUsuario());

		return status(200).build();
	}
}
