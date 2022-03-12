package com.example.conture.controller;

import com.example.conture.domain.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @PostMapping("/autenticacao/{usuario}/{senha}")
    public String logar(@PathVariable String usuario, @PathVariable String senha) {
        boolean usuarioEncontrado = false;
        int posicaoUsuario = 0;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getLogin().equals(usuario) && listaUsuarios.get(i).getSenha().equals(senha)) {
                usuarioEncontrado = true;
                posicaoUsuario = i;
            }
        }
        if (usuarioEncontrado) {
            listaUsuarios.get(posicaoUsuario).setAutenticacao(true);
            return "Usuário " + listaUsuarios.get(posicaoUsuario).getNome() + " agora está logado na plataforma!";
        } else {
            listaUsuarios.get(posicaoUsuario).setAutenticacao(false);
            return "Usuário " + usuario + " não encontrado";
        }
    }

    @PostMapping
    public String cadastrar(@RequestBody Usuario novoUsuario) {
        listaUsuarios.add(novoUsuario);
        return "Usuário " + novoUsuario.getNome() + " cadastrado no sistema";
    }

    @PutMapping("/autenticacao/{usuario}")
    public String deslogar(@PathVariable String usuario) {
        boolean usuarioEncontrado = false;
        int posicaoUsuario = 0;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getLogin().equals(usuario)) {
                usuarioEncontrado = true;
                posicaoUsuario = i;
            }
        }
        if (usuarioEncontrado) {
            if (listaUsuarios.get(posicaoUsuario).getAutenticacao().equals(true)) {
                listaUsuarios.get(posicaoUsuario).setAutenticacao(false);
                return "Logoff do usuário " + listaUsuarios.get(posicaoUsuario).getNome() + " concluído";
            } else {
                return "Usuário " + listaUsuarios.get(posicaoUsuario).getNome() + " não está logado na plataforma!";
            }
        } else {
            return "Usuário " + usuario + " não encontrado";
        }
    }
}
