package com.example.conture.Controller;

import com.example.conture.Entidade.Mensagem;
import com.example.conture.Entidade.MensagemGrupo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagemGrupo")
public class MensagemGrupoController {

        List<MensagemGrupo> mensagens = new ArrayList<>();


        @PostMapping
        public String cadastrar(@RequestBody MensagemGrupo mensagem) {
            mensagens.add(mensagem);
            return "Mensagem cadastrada com sucesso";
        }

        @DeleteMapping("/deletar/{id}")
        public String deletar(@PathVariable int id) {
            for (MensagemGrupo m : mensagens) {
                if (m.getIdMensagem().equals(id)) {
                    mensagens.remove(m);
                    return "Mensagem removida com sucesso";
                }
            }
            return "Mensagem não encontrada";
        }


        public List<MensagemGrupo> exibirTodos() {
            return mensagens;
        }


        @GetMapping("/exibirId/{id}")
        public MensagemGrupo exibirPorID(@PathVariable int id) {
            for (MensagemGrupo m : mensagens) {
                if (m.getIdMensagem().equals(id)) {
                    return m;
                }
            }
            return null;
        }

    }
