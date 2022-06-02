package com.conture.apiproduto.utility;

import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.entity.ProdutoDoacao;
import com.conture.apiproduto.repository.CategoriaProdutoRepository;
import com.conture.apiproduto.repository.ProdutoRepository;
import com.conture.apiproduto.rest.usuario.ClienteUsuario;
import com.conture.apiproduto.rest.usuario.UsuarioResposta;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Txt {
	public Boolean leArquivoTxt(
			byte[] importacao,
			CategoriaProdutoRepository repositoryCategoria,
			ProdutoRepository repositoryProduto,
			ClienteUsuario repositoryUsuario
	) {
		BufferedReader entrada = null;
		Long fkUsuario;
		String tipoRegistro;
		String nome, marca, modelo, descricao, categoria, registro;
		Boolean defeito, entrega;
		CategoriaProduto fkCategoria;
		UsuarioResposta usuario;
		int contaRegCorpoLido = 0;

		List<ProdutoDoacao> listaLida = new ArrayList<>();

		String filePath = "arq.txt";
		File file = new File(filePath);

		try {
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			os.write(importacao);
			os.close();
			entrada = new BufferedReader(new FileReader(file));
		}
		catch (IOException erro) {
			System.out.println("Erro ao abrir o arquivo: " + erro);
		}

		// try-catch para abrir o arquivo
		// try-catch para ler e fechar o arquivo
			// Leitura do primeiro registro do arquivo
		try {

			registro = entrada.readLine();

			while (registro != null) { // enquanto não chegou ao final do arquivo
				// obtém os 2 primeiros caracteres do registro
				// 01234567
				// 00NOTA20221
				if (contaRegCorpoLido == 0) {
					tipoRegistro = registro.substring(0, 2);
				} else {
					tipoRegistro = registro.substring(0, 4);
				}
				if (tipoRegistro.equals("HD")) {
					System.out.println("É um registro de header");
					System.out.println("Tipo de arquivo: " + registro.substring(2, 9));
					System.out.println("Data e hora da gravação: " + registro.substring(9, 28));
					System.out.println("Versão do documento: " + registro.substring(28, 30));
					contaRegCorpoLido++;
				} else if (tipoRegistro.equals("TR")) {
					System.out.println("É um registro de trailer");
				}
				//	else {
				//		System.out.println("Quantidade de registros lidos não é compatível " +
				//				"com a quantidade de registros gravados");
				//	}
				//}
				else if (tipoRegistro.equals("CP02")) {
					System.out.println("É um registro de corpo");
					System.out.println(registro);
					fkUsuario = Long.valueOf(registro.substring(4, 8));
					nome = registro.substring(8, 68).trim();
					marca = registro.substring(68, 128).trim();
					modelo = registro.substring(128, 188).trim();
					categoria = registro.substring(188, 233).trim();
					defeito = Boolean.valueOf(registro.substring(233, 234));
					entrega = Boolean.valueOf(registro.substring(234, 235));
					descricao = registro.substring(235, 490).trim();

					contaRegCorpoLido++;

					System.out.println(marca);
					System.out.println(modelo);
					System.out.println(categoria);

					Optional<CategoriaProduto> categoriaEncontrada = repositoryCategoria.findByNomeIgnoreCase(categoria);
					fkCategoria = categoriaEncontrada.get();

					UsuarioResposta usuarioEncontrado = repositoryUsuario.getUsuario(fkUsuario);
					usuario = usuarioEncontrado;



					ProdutoDoacao produtoDoacao = new ProdutoDoacao(
							usuario,
							nome,
							marca, modelo,
							descricao, defeito,
							entrega, fkCategoria
					);


					// No projeto de PI, poderia fazer:
					// repository.save(a);

					// No nosso caso, vamos adicionar o objeto a na listaLida:
					listaLida.add(produtoDoacao);
				} else {
					System.out.println(registro.substring(0, 4));
					System.out.println("Tipo de registro inválido!");
				}

				registro = entrada.readLine();
			}
		}
		catch (IOException erro) {
			System.out.println("Erro ao ler o arquivo: " + erro);
		}

		// No Projeto de PI, pode-se fazer:
		// repository.saveAll(listaLida);

		// Vamos exibir a listaLida
		try {
			System.out.println("\nConteúdo da lista lida:");
			for (ProdutoDoacao p : listaLida) {
				System.out.println(p);
				repositoryProduto.save(p);
			}
			System.out.println(listaLida);
			file.delete();
			return true;
		}
		catch (IllegalStateException e){
			return false;
		}
	}

	public Boolean gravaArquivoTxt(List<ProdutoDoacao> lista) {
		int contaRegCorpo = 0;

		// Monta o registro de header
		String header = "HDPRODUTO";
		header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		header += "01";
		// Grava o registro de header
		gravaRegistro(header);

		// Monta e grava os registros de corpo
		String corpo;
		for (ProdutoDoacao a : lista) {
			String defeito;
			if(a.isDefeito()){
				defeito = "S";
			} else {
				defeito = "N";
			}

			corpo = "CP01";
			corpo += String.format("%-60.60s", a.getNome());
			corpo += String.format("%-60.60s", a.getMarca());
			corpo += String.format("%-60.60s", a.getModelo());
			corpo += String.format("%-255.255s", a.getDescricao());
			corpo += String.format("%-1.1s", defeito);
			corpo += String.format("%-19.19s", a.getDataCriacao());
			corpo += String.format("%-45.45s", a.getFkCategoriaProduto().getNome());
			corpo += String.format("%-45.45s", a.getUsuario().getNome());
			corpo += String.format("%-80.80s", a.getUsuario().getEmail());
			corpo += String.format("%-45.45s", a.getUsuario().getCpf());
			contaRegCorpo++;
			gravaRegistro(corpo);

			System.out.println("CORPO");
			System.out.println(corpo);
		}


		// Monta e grava o registro de trailer
		String trailer = "TR";
		trailer += String.format("%05d", contaRegCorpo);
		gravaRegistro(trailer);
		return true;
	}

	public static void gravaRegistro(String registro) {
		BufferedWriter saida = null;

		// try-catch para abrir o arquivo
		try {
			saida = new BufferedWriter(new FileWriter("produtos.txt", true));
		}
		catch (IOException erro) {
			System.out.println("Erro ao abrir o arquivo: " + erro);
		}

		// try-catch para gravar o registro e fechar o arquivo
		try {
			saida.append(registro + "\n");
			saida.close();
		}
		catch (IOException erro) {
			System.out.println("Erro ao gravar o arquivo: " + erro);
		}
	}
}
