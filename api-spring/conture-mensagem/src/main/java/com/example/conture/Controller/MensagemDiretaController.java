package com.example.conture.Controller;

import com.example.conture.Entidade.Mensagem;
import com.example.conture.Entidade.MensagemDireta;
import com.example.conture.Entidade.MensagemGrupo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/mensagemDireta")
public class MensagemDiretaController {

    List<MensagemDireta> mensagens = new ArrayList<>();


    @PostMapping
    public List<MensagemDireta> cadastrar(@RequestBody MensagemDireta mensagem) {
        mensagens.add(mensagem);
        return mensagens;
//        return "Cadastrado com sucesso";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable int id) {
        for (MensagemDireta m : mensagens) {
            if (m.getIdMensagem().equals(id)) {
                mensagens.remove(m);
                return "Mensagem removida com sucesso";
            }
        }
        return "Mensagem não encontrada";
    }

    @GetMapping("/exibir")
    public List<MensagemDireta> exibirTodos() {
        return mensagens;
    }


    @GetMapping("/exibirId/{id}")
    public MensagemDireta exibirPorID(@PathVariable int id) {
        for (MensagemDireta m : mensagens) {
            if (m.getIdMensagem().equals(id)) {
                return m;
            }
        }
        return null;
    }

}
