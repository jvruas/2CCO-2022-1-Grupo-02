package com.conture.apiusuario.utility;

import com.conture.apiusuario.dto.response.UsuarioLogadoResponse;
import com.conture.apiusuario.utility.datastructure.Hashtable;

import java.util.Optional;

public class GerenciadorUsuario {
	private static Hashtable<UsuarioLogadoResponse> hashTableLogados = new Hashtable<>(10);

	public static void login(UsuarioLogadoResponse usuario) {
		hashTableLogados.add(usuario);
	}

	public static boolean logoff(Integer idUsuario) {
		return hashTableLogados.remove(new UsuarioLogadoResponse(idUsuario, null, null, null, null, null, null, null, null, null, null, null, null));
	}

	public static boolean isEmpty() {
		return hashTableLogados.isEmpty();
	}

	public static Optional<UsuarioLogadoResponse> buscaUsuarioLogado(Integer idUsuario) {
		UsuarioLogadoResponse usuario = hashTableLogados.get(new UsuarioLogadoResponse(idUsuario, null, null, null, null, null, null, null, null, null, null, null, null));

		if (usuario == null) {
			return Optional.ofNullable(null);
		}

		return Optional.of(usuario);
	}
}
