package com.conture.apiproduto.utility;

import com.conture.apiproduto.entity.CategoriaProduto;
import com.conture.apiproduto.entity.ProdutoDoacao;
import com.conture.apiproduto.repository.CategoriaProdutoRepository;
import com.conture.apiproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Txt {

	@Autowired
	private CategoriaProdutoRepository repositoryCategoria;

	@Autowired
	private ProdutoRepository repositoryProduto;

	public Boolean leArquivoTxt(String nomeArq, String registro) {
		BufferedReader entrada = null;
		Long fkDoador;
		String tipoRegistro;
		String nome, marca, modelo, descricao, categoria;
		Boolean defeito, entrega;
		Long fkCategoria;
		int contaRegCorpoLido = 0;
		int qtdRegCorpoGravado;

		List<ProdutoDoacao> listaLida = new ArrayList<>();

		// try-catch para abrir o arquivo
		try {
			entrada = new BufferedReader(new FileReader(nomeArq));
		}
		catch (IOException erro) {
			System.out.println("Erro ao abrir o arquivo: " + erro);
		}

		// try-catch para ler e fechar o arquivo
		try {
			// Leitura do primeiro registro do arquivo

			while (registro != null) { // enquanto não chegou ao final do arquivo
				// obtém os 2 primeiros caracteres do registro
				// 01234567
				// 00NOTA20221
				tipoRegistro = registro.substring(0,2);
				if (tipoRegistro.equals("HD")) {
					System.out.println("É um registro de header");
					System.out.println("Tipo de arquivo: " + registro.substring(2,9));
					System.out.println("Data e hora da gravação: " + registro.substring(9,28));
					System.out.println("Versão do documento: " + registro.substring(28,30));
				}
				else if (tipoRegistro.equals("TR")) {
					System.out.println("É um registro de trailer");
					qtdRegCorpoGravado = Integer.parseInt(registro.substring(2,12));
					if (contaRegCorpoLido == qtdRegCorpoGravado) {
						System.out.println("Quantidade de registros lidos é compatível " +
								"com a quantidade de registros gravados");
					}
					else {
						System.out.println("Quantidade de registros lidos não é compatível " +
								"com a quantidade de registros gravados");
					}
				}
				else if (tipoRegistro.equals("CP02")) {
					System.out.println("É um registro de corpo");
					fkDoador = Long.valueOf(registro.substring(4,8));
					nome = registro.substring(8,68).trim();
					marca = registro.substring(68,128).trim();
					modelo = registro.substring(128,188).trim();
					categoria = registro.substring(188,208).trim();
					defeito = Boolean.valueOf(registro.substring(208,209));
					entrega = Boolean.valueOf(registro.substring(210,211));
					descricao = registro.substring(128,188).trim();
					contaRegCorpoLido++;

					Optional<CategoriaProduto> categoriaEncontrada =
							repositoryCategoria
									.findByNomeIgnoreCase(categoria);

					ProdutoDoacao produtoDoacao = new ProdutoDoacao(
							fkDoador, nome,
							marca, modelo,
							descricao, defeito,
							entrega,
							categoriaEncontrada.get()
									.getIdCategoriaProduto()
					);


					// No projeto de PI, poderia fazer:
					// repository.save(a);

					// No nosso caso, vamos adicionar o objeto a na listaLida:
					listaLida.add(produtoDoacao);
				}
				else {
					System.out.println("Tipo de registro inválido!");
				}

				// Lê o próximo registro
				registro = entrada.readLine();
			}

			entrada.close();
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
			return true;
		}
		catch (IllegalStateException e){
			return false;
		}
	}
}
