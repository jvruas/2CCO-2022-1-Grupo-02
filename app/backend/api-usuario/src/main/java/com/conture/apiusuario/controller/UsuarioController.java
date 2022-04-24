package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.AvaliacaoRequest;
import com.conture.apiusuario.dto.request.UsuarioPerfilRequest;
import com.conture.apiusuario.entity.*;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.dto.request.ReporteRequest;
import com.conture.apiusuario.dto.request.UsuarioLoginRequest;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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


	@GetMapping
	public ResponseEntity listarUsuarios() {
		if (GerenciadorUsuario.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(GerenciadorUsuario.listarUsuariosLogados());
	}

    @PostMapping
    public ResponseEntity adicionarUsuario(@RequestBody @Valid Usuario novoUsuario){
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginRequest usuario){
        Optional<UsuarioLogadoResponse> usuarioLogado = Optional.ofNullable(usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()));

        if(usuarioLogado.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        GerenciadorUsuario.login(usuarioLogado.get());
        return ResponseEntity.status(201).body(usuarioLogado.get());
    }

	@DeleteMapping("/logoff")
	public ResponseEntity logoff(Long idUsuario){
		boolean resposta = GerenciadorUsuario.logoff(idUsuario);

		if (!resposta){
			return ResponseEntity.status(404).build();
		}

		return  ResponseEntity.status(200).build();
	}

	@PostMapping("/reporte")
	public ResponseEntity reportarUsuario(@RequestBody @Valid ReporteRequest reporte){
		Optional<Usuario> reportado = Optional.ofNullable(usuarioRepository.findByIdUsuario(reporte.getFkReportado()));
		Optional<TipoReporte> tipoReporte = Optional.ofNullable(tipoReporteRepository.findByIdTipoReporte(reporte.getFkTipoReporte()));
		Optional<UsuarioLogadoResponse> reportador = GerenciadorUsuario.buscaUsuarioLogado(reporte.getFkReportador());

		if (reportador.isEmpty() || reportado.isEmpty() || tipoReporte.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		this.reporteRepository.save(new Reporte(reporte.getFkReportador(), reporte.getFkReportado(), reporte.getFkTipoReporte()));
		return  ResponseEntity.status(200).build();
	}

	@PostMapping("/avaliacao")
	public ResponseEntity avaliarUsuario(@RequestBody @Valid AvaliacaoRequest avaliacao){
		Optional<Usuario> avaliado = Optional.ofNullable(usuarioRepository.findByIdUsuario(avaliacao.getFkDoador()));

		Optional<UsuarioLogadoResponse> avaliador = GerenciadorUsuario.buscaUsuarioLogado(avaliacao.getFkDonatario());

		if(avaliado.isEmpty() || avaliador.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		this.avaliacaoRepository.save(new Avaliacao(avaliacao.getFkDonatario(), avaliacao.getFkDoador(), avaliacao.getValor(), avaliacao.getComentario()));
		return  ResponseEntity.status(200).build();
	}

	@DeleteMapping
	public ResponseEntity deletarUsuario(@RequestParam Long idUsuario){
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if(usuarioLogado.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		GerenciadorUsuario.logoff(idUsuario);
		this.usuarioRepository.deleteByIdUsuario(idUsuario);
		return ResponseEntity.status(200).build();
	}

	@GetMapping("/nome")
	public ResponseEntity pesquisarUsuarioNome(@RequestParam @Valid String nome){
		List<Usuario> listaUsuarios = usuarioRepository.findByNome(nome);

		if(listaUsuarios.isEmpty()){
			return ResponseEntity.status(204).build();
		}
		return ResponseEntity.status(200).body(listaUsuarios);

	}

	@GetMapping("/id")
	public ResponseEntity pesquisarUsuarioId(@RequestParam @Valid Long idUsuario){
		Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByIdUsuario(idUsuario));

		if(usuario.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(usuario);

	}

	@GetMapping("/tipos-de-reporte")
	public ResponseEntity listarTiposReporte(){
		List<TipoReporte> lista = tipoReporteRepository.findAll();

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/situacao-atual")
	public ResponseEntity listarSituacoesAtuais(){
		List<SituacaoAtual> lista = situacaoAtualRepository.findAll();

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/avaliacoes")
	public ResponseEntity listarAvaliacoes(){
		List<Avaliacao> lista = avaliacaoRepository.findAll();

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@PatchMapping("/senha")
	public ResponseEntity atualizarSenha(@RequestParam Long idUsuario, @RequestParam @Valid String senha){
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(idUsuario);

		if(usuarioLogado.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
		usuario.setSenha(senha);

		this.usuarioRepository.save(usuario);
		return  ResponseEntity.status(200).build();
	}

	@PatchMapping("/perfil")
	public ResponseEntity atualizarPerfil(@RequestBody @Valid UsuarioPerfilRequest usuarioPerfil){
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(usuarioPerfil.getIdUsuario());

		if(usuarioLogado.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		Usuario usuario = usuarioRepository.findByIdUsuario(usuarioPerfil.getIdUsuario());
		usuario.setGenero(usuarioPerfil.getGenero());
		usuario.setEstadoCivil(usuarioPerfil.getEstadoCivil());
		usuario.setCep(usuarioPerfil.getCep());
		usuario.setEscolaridade(usuarioPerfil.getEscolaridade());
		usuario.setFkSituacaoAtual(usuarioPerfil.getFkSituacaoAtual());

		this.usuarioRepository.save(usuario);
		return  ResponseEntity.status(200).build();
	}



}
