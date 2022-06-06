package com.conture.apiproduto.util.sort;

import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;

import java.util.Comparator;

public class SortDateAscending implements Comparator<ProdutoDoacaoResponse> {
	@Override
	public int compare(ProdutoDoacaoResponse produto1, ProdutoDoacaoResponse produto2) {
		return produto2.getDataCriacao().compareTo(produto1.getDataCriacao());
	}
}

