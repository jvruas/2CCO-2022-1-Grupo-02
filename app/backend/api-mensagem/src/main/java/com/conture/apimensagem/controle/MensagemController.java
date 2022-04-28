package com.conture.apimensagem.controle;

import com.conture.apimensagem.dto.requests.MensagemRequest;
import com.conture.apimensagem.dto.requests.PerguntaRequest;
import com.conture.apimensagem.dto.requests.RespostaRequest;
import com.conture.apimensagem.entidade.ChatDireto;
import com.conture.apimensagem.servicos.ListaObj;
import com.conture.apimensagem.entidade.Mensagem;
import com.conture.apimensagem.entidade.Pergunta;
import com.conture.apimensagem.entidade.Resposta;
import com.conture.apimensagem.repository.ChatDiretoRepository;
import com.conture.apimensagem.repository.MensagemRepository;
import com.conture.apimensagem.repository.PerguntaRepository;
import com.conture.apimensagem.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
	@Autowired
	private MensagemRepository repositoryMensagem;
	@Autowired
	private RespostaRepository repositoryResposta;
	@Autowired
	private ChatDiretoRepository repositoryChatDireto;
	@Autowired
	private PerguntaRepository repositoryPergunta;

    @PostMapping("/direta")
    public ResponseEntity adicionarMensagemDireta(@RequestBody @Valid MensagemRequest mensagemRequest) {
		Mensagem mensagem = new Mensagem();

		Optional<ChatDireto> chatDireto = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(mensagemRequest.getFkUsuarioRemetente(), mensagemRequest.getFkUsuarioDestinatario());

		if (chatDireto.isEmpty()) {
			chatDireto = Optional.of(new ChatDireto());

			chatDireto.get().setFkUsuarioRemetente(mensagemRequest.getFkUsuarioRemetente());
			chatDireto.get().setFkUsuarioDestinatario(mensagemRequest.getFkUsuarioDestinatario());

			this.repositoryChatDireto.save(chatDireto.get());
		}

		mensagem.setFkChatDireto(chatDireto.get().getIdChatDireto());
		mensagem.setMensagem(mensagemRequest.getMensagem());

		repositoryMensagem.save(mensagem);
		return ResponseEntity.status(201).build();
    }

    @PostMapping("/grupo/pergunta")
    public ResponseEntity adicionarMensagemPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest) {
		Pergunta pergunta = new Pergunta();

		pergunta.setFkDonatario(perguntaRequest.getFkDonatario());
		pergunta.setMensagem(perguntaRequest.getMensagem());
		pergunta.setFkDoador(perguntaRequest.getFkDoador());
		pergunta.setFkProdutoDoacao(perguntaRequest.getFkProdutoDoacao());

		repositoryPergunta.save(pergunta);
		return ResponseEntity.status(201).build();
    }

    @PostMapping("/grupo/resposta")
    public ResponseEntity adicionarMensagemGrupo(@RequestBody @Valid RespostaRequest respostaRequest) {
		if (!this.repositoryPergunta.existsByIdPergunta(respostaRequest.getFkPergunta())) {
			return ResponseEntity.status(404).build();
		}

		Resposta resposta = new Resposta();

		resposta.setFkPergunta(respostaRequest.getFkPergunta());
		resposta.setMensagem(respostaRequest.getMensagem());
		resposta.setFkDoador(respostaRequest.getFkDoador());
		resposta.setFkProdutoDoacao(respostaRequest.getFkProdutoDoacao());

		repositoryResposta.save(resposta);
		return ResponseEntity.status(201).build();
	}

    @GetMapping("/direta")
    public ResponseEntity listarMensagemDireta(
			@RequestParam Long fkUsuarioRemetente,
			@RequestParam Long fkUsuarioDestinatario
	){
		Optional<ChatDireto> chatDireto1 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioRemetente, fkUsuarioDestinatario);
		Optional<ChatDireto> chatDireto2 = this.repositoryChatDireto.findByFkUsuarioRemetenteAndFkUsuarioDestinatario(fkUsuarioDestinatario, fkUsuarioRemetente);

		if (chatDireto1.isEmpty() && chatDireto2.isEmpty()) {
			return ResponseEntity.status(400).build();
		}

		List<Mensagem> mensagems = this.repositoryMensagem.acharPorFkChatDiretoOrderByDataDesc(chatDireto1.get().getIdChatDireto(), chatDireto2.get().getIdChatDireto());

		if (mensagems.isEmpty()){
			return ResponseEntity.status(204).build();
		}

		return ResponseEntity.status(200).body(mensagems);
	}

    @GetMapping("/grupo")
    public ResponseEntity listarMensagemGrupo(
			@RequestParam Long fkDoador,
			@RequestParam Long fkProdutoDoacao
    ) {
		List<Pergunta> perguntaList = this.repositoryPergunta.findByFkDoadorAndFkProdutoDoacaoOrderByDataAsc(fkDoador, fkProdutoDoacao);

		if (perguntaList.isEmpty()) {
			return ResponseEntity.status(204).build();
		}

		List<List<Object>> groupChatList = new ArrayList();

		for (int i = 0; i < perguntaList.size(); i++) {
			List<Object> topicList = new ArrayList();

			topicList.add(perguntaList.get(i));

			List<Resposta> respostaList = this.repositoryResposta.findByFkPerguntaOrderByDataDesc(perguntaList.get(i).getIdPergunta());

			if (respostaList.isEmpty()) {
				continue;
			}

			topicList.add(respostaList);

			groupChatList.add(topicList);
		}

		return ResponseEntity.status(200).body(groupChatList);
	}

	@DeleteMapping("/direta/{idMensagem}")
	public ResponseEntity removerMensagemDireta(@PathVariable Long idMensagem){
		if (!this.repositoryMensagem.existsById(idMensagem)){
			return ResponseEntity.status(404).build();
		}

		this.repositoryMensagem.deleteById(idMensagem);
		return ResponseEntity.status(200).build();
	}

	@DeleteMapping("/grupo/resposta/{idResposta}")
	public ResponseEntity removerResposta(@PathVariable Long idResposta){
		if (!this.repositoryResposta.existsById(idResposta)){
			return ResponseEntity.status(404).build();
		}

		this.repositoryResposta.deleteById(idResposta);

		return ResponseEntity.status(200).build();
	}

    @PatchMapping("/grupo/resposta/{idResposta}")
    public ResponseEntity putMensagemGrupoResposta(
			@RequestParam String mensagem,
			@PathVariable Long idResposta
	) {
		if (!this.repositoryResposta.existsById(idResposta)) {
			return ResponseEntity.status(404).build();
		}

		this.repositoryResposta.updateMensagemResposta(idResposta, mensagem, Date.from(Instant.now()));

		return ResponseEntity.status(200).build();
	}

    @PatchMapping("/direta/{idMensagem}")
    public ResponseEntity putMensagemDireta(
			@RequestParam String mensagem,
			@PathVariable Long idMensagem
	) {
		if (!this.repositoryMensagem.existsById(idMensagem)){
			return ResponseEntity.status(404).build();
		}

		this.repositoryMensagem.updateMensagem(idMensagem, mensagem, Date.from(Instant.now()));

		return ResponseEntity.status(200).build();
	}

	@GetMapping("/grupo/relatorio/perguntas-frequentes")
	public ResponseEntity getRelatorio() {
		String relatorio = "";

		List<Pergunta> perguntas = repositoryPergunta.findAll();
		ListaObj<Pergunta> lista = new ListaObj((int) repositoryPergunta.count());

		System.out.println(lista.getTamanho());
		System.out.println(perguntas.size());

		for (int idx = 0; idx < perguntas.size(); idx++){
			lista.adiciona(perguntas.get(idx));
		}

		for (int i = 0; i < perguntas.size(); i++){
			relatorio += lista.getElemento(i).getIdPergunta() + ";" + lista.getElemento(i).getMensagem() + ";" +
			lista.getElemento(i).getData();
		}

		String csv = this.gravaArquivoCsv(lista, "perguntas");
		return ResponseEntity.status(200)
				.header("content-type", "text/csv")
				.header("content-disposition", "filename=\"perguntas-frequentes.csv\"")
				.body(csv);
	}

	private String gravaArquivoCsv(ListaObj<Pergunta> lista, String nomeArq) {
		FileWriter arq = null;
		Formatter saida = null;
		Boolean deuRuim = false;
		nomeArq += ".csv";
		String formatado="";

		try {
			arq = new FileWriter(nomeArq, false);
			saida = new Formatter(arq);
		}
		catch (IOException erro) {
			System.out.println("Erro ao abrir o arquivo!");
			System.exit(1);
		}

		try {
			formatado = "";
			System.out.println(lista.getTamanho());
			for (int i= 0; i< lista.getTamanho(); i++) {
				Pergunta listPerguntas = lista.getElemento(i);
				formatado += String.format("%d;%s;%s;\n",
						listPerguntas.getIdPergunta(), listPerguntas.getMensagem(), listPerguntas.getData());
			}
			System.out.println(formatado);
			saida.format(formatado);
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
		return formatado;
	}
}
