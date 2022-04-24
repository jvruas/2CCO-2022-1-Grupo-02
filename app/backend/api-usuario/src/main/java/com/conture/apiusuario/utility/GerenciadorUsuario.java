package com.conture.apiusuario.utility;

import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;

import java.util.List;
import java.util.Optional;

public class GerenciadorUsuario {

    private static ListaObj<UsuarioLogadoResponse> listaLogados = new ListaObj<>(50);

    public static void login(UsuarioLogadoResponse usuario) {
        listaLogados.adiciona(usuario);
    }

    public static boolean logoff(Long idUsuario) {
        return listaLogados.removeElemento(new UsuarioLogadoResponse(idUsuario, null, null, null, null, null, null, null, null, null));
    }

    public static List<UsuarioLogadoResponse> listarUsuariosLogados() {
        return listaLogados.transformarEmLista();
    }

    public static boolean isEmpty() {
        return listaLogados.getTamanho() == 0;
    }

	public static Optional<UsuarioLogadoResponse> buscaUsuarioLogado(Long idUsuario){
		int indice = listaLogados.busca(new UsuarioLogadoResponse(idUsuario, null, null, null, null, null, null, null, null, null));

		if(indice == -1){
			return Optional.ofNullable(null);
		}
		return Optional.ofNullable(listaLogados.getElemento(indice));
	}

}
