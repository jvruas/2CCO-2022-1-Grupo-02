package com.conture.apiproduto.util.sort;

import com.conture.apiproduto.model.dto.response.ProdutoDoacaoHistoricoResponse;
import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;

import java.util.Comparator;

public class SortDateAscending implements Comparator<ProdutoDoacaoHistoricoResponse> {
	@Override
	public int compare(ProdutoDoacaoHistoricoResponse produto1, ProdutoDoacaoHistoricoResponse produto2) {
		return produto2.getDataCriacao().compareTo(produto1.getDataCriacao());
	}
}

