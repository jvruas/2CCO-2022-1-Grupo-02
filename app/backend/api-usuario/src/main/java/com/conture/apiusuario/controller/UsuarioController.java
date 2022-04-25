package com.conture.apiusuario.controller;

import com.conture.apiusuario.dto.request.*;
import com.conture.apiusuario.entity.*;
import com.conture.apiusuario.repository.*;
import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.utility.GerenciadorUsuario;
import com.conture.apiusuario.utility.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	// Repository
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


	// EndPoint
    @PostMapping
    public ResponseEntity adicionarUsuario(@RequestBody @Valid Usuario novoUsuario){
        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginRequest usuario){
        Optional<UsuarioLogadoResponse> usuarioLogado = Optional.ofNullable(this.usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha()));

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
		Reporte novoReporte = new Reporte();
		novoReporte.setFkReportador(reporte.getFkReportador());
		novoReporte.setFkReportado(reporte.getFkReportado());
		novoReporte.setFkTipoReporte(reporte.getFkTipoReporte());

		this.reporteRepository.save(novoReporte);
		return ResponseEntity.status(200).build();
	}

	@PostMapping("/avaliacao")
	public ResponseEntity avaliarUsuario(@RequestBody @Valid AvaliacaoRequest avaliacao){
		Optional<Usuario> avaliado = Optional.ofNullable(usuarioRepository.findByIdUsuario(avaliacao.getFkDoador()));
		Optional<UsuarioLogadoResponse> avaliador = GerenciadorUsuario.buscaUsuarioLogado(avaliacao.getFkDonatario());

		if(avaliado.isEmpty() || avaliador.isEmpty()){
			return ResponseEntity.status(404).build();
		}

		Avaliacao novaAvaliacao = new Avaliacao();
		novaAvaliacao.setFkDoador(avaliacao.getFkDoador());
		novaAvaliacao.setFkDonatario(avaliacao.getFkDonatario());
		novaAvaliacao.setValor(avaliacao.getValor());
		novaAvaliacao.setComentario(avaliacao.getComentario());

		this.avaliacaoRepository.save(novaAvaliacao);
		return ResponseEntity.status(200).build();
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
		List<Usuario> listaUsuarios = this.usuarioRepository.findByNome(nome);

		if(listaUsuarios.isEmpty()){
			return ResponseEntity.status(204).build();
		}
		return ResponseEntity.status(200).body(listaUsuarios);

	}

	@GetMapping("/id")
	public ResponseEntity pesquisarUsuarioId(@RequestParam @Valid Long idUsuario){
		Optional<Usuario> usuario = Optional.ofNullable(this.usuarioRepository.findByIdUsuario(idUsuario));

		if(usuario.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(usuario);

	}

	@GetMapping("/tipos-de-reporte")
	public ResponseEntity listarTiposReporte(){
		List<TipoReporte> lista = this.tipoReporteRepository.findAll();

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/situacao-atual")
	public ResponseEntity listarSituacoesAtuais(){
		List<SituacaoAtual> lista = this.situacaoAtualRepository.findAll();

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@GetMapping("/avaliacoes")
	public ResponseEntity listarAvaliacoes(@RequestParam Long fkDoador){
		List<Avaliacao> lista = this.avaliacaoRepository.findByFkDoador(fkDoador);

		if(lista.isEmpty()){
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).body(lista);
	}

	@PutMapping("/senha")
	public ResponseEntity atualizarSenha(@RequestBody UsuarioSenhaRequest novaSenha) {
		Optional<UsuarioLogadoResponse> usuarioLogado = GerenciadorUsuario.buscaUsuarioLogado(novaSenha.getIdUsuario());
		Usuario usuario = usuarioRepository.findByIdUsuario(novaSenha.getIdUsuario());

		if (usuarioLogado.isEmpty() || !usuario.getSenha().equals(novaSenha.getSenhaAtual())) {
			return ResponseEntity.status(404).build();
		}

		usuario.setSenha(novaSenha.getSenhaNova());
		this.usuarioRepository.save(usuario);
		return ResponseEntity.status(200).build();
	}

	@PutMapping("/perfil")
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

	//IN PROGESS ...
	@GetMapping("/gerar-csv")
	public static void gravaArquivoCsv(ListaObj<Usuario> lista) {
		String nomeArq = "usuarios";
		FileWriter arq = null;
		Formatter saida = null;
		Boolean deuRuim = false;
		nomeArq += ".csv";

		try {
			arq = new FileWriter(nomeArq);
			saida = new Formatter(arq);
		}
		catch (IOException erro) {
			System.out.println("Erro ao abrir o arquivo!");
			System.exit(1);
		}

		try {
			for (int i = 0; i < lista.getTamanho(); i++) {
				Usuario user = lista.getElemento(i);
				saida.format("%d;%s;%s;%s;%s;%s;%s;%s\n",user.getIdUsuario(),user.getNome(),
						user.getSobrenome(),
						user.getEmail(), user.getCpf(),
						user.getDataNascimento(), user.getGenero(),
						user.getDataCadastro());
			}
		}
		catch (FormatterClosedException erro) {
			System.out.println("Erro ao gravar arquivo");
			deuRuim = true;
		}
		finally {
			saida.close();
			try {
				arq.close();
			}
			catch (IOException erro) {
				System.out.println("Erro ao fechar o arquivo");
				deuRuim = true;
			}
			if (deuRuim) {
				System.exit(1);
			}
		}
	}

	public static void leArquivoCsv(String nomeArq) {
		FileReader arq = null; // obj que representa o arquivo que será aberto para leitura
		Scanner entrada = null; // obj que será usado para ler do arquivo
		Boolean deuRuim = false;
		nomeArq += ".csv";

		// Bloco try-catch para abrir o arquivo
		try {
			arq = new FileReader(nomeArq);
			// defino que o delimitador de campo na leitura será o ; ou o \n (final do registro)
			entrada = new Scanner(arq).useDelimiter(";|\\n");
		}
		catch (FileNotFoundException erro) {
			System.out.println("Arquivo não encontrado");
			System.exit(1);
		}

		// Bloco try-catch para ler o arquivo
		try {
			// Exibe uma linha com os títulos das colunas
			System.out.printf("%4s %-15s %-9s %5s\n","ID","NOME","PORTE","PESO");
			while (entrada.hasNext()) {     // enquanto não chega ao final do arquivo
				// Leio o valor de cada campo, como quando leio do teclado usando Scanner
				int id = entrada.nextInt();
				String nome = entrada.next();
				String porte = entrada.next();   // aqui o next lê a String delimitada por ; então lê mesmo qdo tem nomes separados por branco
				Double peso = entrada.nextDouble();
				// Exibe os dados em formato de colunas
				System.out.printf("%4d %-15s %-9s %5.2f\n",id, nome, porte, peso);
			}
		}
		catch (NoSuchElementException erro) {
			System.out.println("Arquivo com problemas");
			deuRuim = true;
		}
		catch (IllegalStateException erro) {
			System.out.println("Erro na leitura do arquivo");
			deuRuim = true;
		}
		finally {
			entrada.close();
			try {
				arq.close();
			}
			catch (IOException erro) {
				System.out.println("Erro ao fechar o arquivo");
				deuRuim = true;
			}
			if (deuRuim) {
				System.exit(1);
			}
		}
	}

}
