package com.conture.apimensagem.controle;

import com.conture.apimensagem.servicos.ListaObj;
import com.conture.apimensagem.entidade.Mensagem;
import com.conture.apimensagem.entidade.Pergunta;
import com.conture.apimensagem.entidade.Resposta;
import com.conture.apimensagem.repositorio.ChatDiretoRepository;
import com.conture.apimensagem.repositorio.MensagemRepository;
import com.conture.apimensagem.repositorio.PerguntaRepository;
import com.conture.apimensagem.repositorio.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

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

    @PostMapping("direta/")
    public ResponseEntity postMensagemDireta(@RequestBody @Valid Mensagem mensagem){
        if (!repositoryMensagem.exists(mensagem)){
            repositoryMensagem.save(mensagem);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(409).build();
    }

    @PostMapping("grupo/pergunta")
    public ResponseEntity postMensagemGrupoPergunta(@RequestBody Pergunta pergunta){
        if (!repositoryPergunta.exists(pergunta)){
            repositoryPergunta.save(pergunta);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(409).build();
    }

    @PostMapping("grupo/resposta")
    public ResponseEntity postMensagemGrupoResposta(@RequestBody Resposta resposta){
        if (!repositoryResposta.exists(resposta)){
            repositoryResposta.save(resposta);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(409).build();
    }

    @GetMapping("direta/{idChatDireto}")
    public ResponseEntity getMensagemDireta(@PathVariable Long idChatDireto){
        List<Mensagem> mensagems =
                repositoryMensagem.findByFkChatDiretoOrderByDataAsc(idChatDireto);

        if (!mensagems.isEmpty()){
            return ResponseEntity.status(200).body(mensagems);
        }
        return ResponseEntity.status(204).build();
    }


    @GetMapping("grupo/pergunta/{fkDonatario}/{fkDoador}/{fkProdutoDoacao}")
    public ResponseEntity getMensagemGrupoPergunta(@PathVariable Long fkDonatario,
                                                   @PathVariable Long fkDoador,
                                                   @PathVariable Long fkProdutoDoacao
    ){

        List<Pergunta> perguntas =
        repositoryPergunta
                .findByFkDonatarioAndFkDoadorAndFkProdutoDoacaoOrderByDataAsc(
                        fkDonatario,
                        fkDoador,
                        fkProdutoDoacao
                );

        if (!perguntas.isEmpty()){
            return ResponseEntity.status(200).body(perguntas);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("grupo/resposta/{idPergunta}")
    public ResponseEntity getMensagemGrupoResposta(@PathVariable Long idPergunta){

        List<Resposta> respostas =
                repositoryResposta
                        .findByFkPerguntaOrderByDataAsc(
                                idPergunta
                        );

        if (!respostas.isEmpty()){
            return ResponseEntity.status(200).body(respostas);
        }
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("direta/{idMensagem}")
    public ResponseEntity deleteMensagemDireta(@PathVariable Long idMensagem){
        if (repositoryMensagem.existsById(idMensagem)){
            repositoryMensagem.deleteById(idMensagem);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("grupo/pergunta/{idPergunta}")
    public ResponseEntity deleteMensagemGrupoPergunta(@PathVariable Long idPergunta){
        if (repositoryPergunta.existsById(idPergunta)){
            if (repositoryResposta.countByFkPergunta(idPergunta)>=1){
				List<Resposta> respostas =
				repositoryResposta.findByFkPerguntaOrderByDataAsc(idPergunta);
				for (Resposta r:
					 respostas) {
					repositoryResposta.deleteById(r.getIdResposta());
				}
            }
            repositoryPergunta.deleteById(idPergunta);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @DeleteMapping("grupo/resposta/{idResposta}")
    public ResponseEntity deleteMensagemGrupoResposta(@PathVariable Long idResposta){
        if (repositoryResposta.existsById(idResposta)){
            repositoryResposta.deleteById(idResposta);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @PutMapping("grupo/pergunta/{idPergunta}")
    public ResponseEntity putMensagemGrupoPergunta(@RequestBody Pergunta pergunta,
                                                   @PathVariable Long idPergunta) {

        if (repositoryPergunta.existsById(idPergunta)){
            Pergunta pergunta1 = repositoryPergunta.findByIdPergunta(idPergunta);
            pergunta1.setMensagem(pergunta.getMensagem());
            repositoryPergunta.save(pergunta1);

            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
    @PutMapping("grupo/resposta/{idResposta}")
    public ResponseEntity putMensagemGrupoResposta(@RequestBody Resposta resposta,
                                                   @PathVariable Long idResposta) {

        if (repositoryResposta.existsById(idResposta)){
            Resposta resposta1 = repositoryResposta.findByIdResposta(idResposta);
            resposta1.setMensagem(resposta.getMensagem());
            repositoryResposta.save(resposta1);

            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }
    @PutMapping("direta/{idMensagem}")
    public ResponseEntity putMensagemDireta(@RequestBody Mensagem mensagem,
											@PathVariable Long idMensagem) {

        if (repositoryMensagem.existsById(idMensagem)){
            Mensagem mensagem1 = repositoryMensagem.findByIdMensagem(idMensagem);
            mensagem1.setMensagem(mensagem.getMensagem());
            repositoryMensagem.save(mensagem1);

            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

	@GetMapping("/relatorio/perguntas-frequentes")
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

		String csv = gravaArquivoCsv(lista, "perguntas");
		return ResponseEntity.status(200)
				.header("content-type", "text/csv")
				.header("content-disposition", "filename=\"perguntas-frequentes.csv\"")
				.body(csv);
	}

	public String gravaArquivoCsv(ListaObj<Pergunta> lista, String nomeArq) {
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
