package com.conture.apiusuario.utility;

import com.conture.apiusuario.resposta.UsuarioLogado;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorUsuario {

    private static List<UsuarioLogado> listaLogados = new ArrayList();

    public static void login(UsuarioLogado usuario) {
        listaLogados.add(usuario);
    }

    public static void logoff(UsuarioLogado usuario) {
        listaLogados.remove(usuario);
    }

    public static List<UsuarioLogado> listarUsuariosLogados() {
        return listaLogados;
    }

    public static boolean isEmpty() {
        return listaLogados.isEmpty();
    }
}
