package com.conture.apiproduto.util.sort;

import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;

import java.util.List;

public class Filter {

	public static List<ProdutoDoacaoResponse> filtroNomeProduto(
			Iterator<ProdutoDoacaoResponse> iterator,
			String nomeCategoria,
			List<ProdutoDoacaoResponse> produtoDoacaoResponseList
	) {
		if (!iterator.hasNext()) {
			return produtoDoacaoResponseList;
		}

		ProdutoDoacaoResponse currentProdutoDoacao = iterator.getNext();

		if (!currentProdutoDoacao.getCategoriaProduto().equalsIgnoreCase(nomeCategoria)) {
			return filtroNomeProduto(iterator, nomeCategoria, produtoDoacaoResponseList);
		}

		produtoDoacaoResponseList.add(currentProdutoDoacao);

		return filtroNomeProduto(iterator, nomeCategoria, produtoDoacaoResponseList);
	}
}
