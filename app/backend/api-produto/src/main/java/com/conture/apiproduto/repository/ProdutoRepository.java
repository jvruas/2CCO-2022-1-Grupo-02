package com.conture.apiproduto.repository;

import com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse;
import com.conture.apiproduto.model.entity.ProdutoDoacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoDoacao, Integer>{

	Optional<ProdutoDoacao> findTop1ByRemovidoFalseAndStatusFalseAndFkDoadorOrderByIdProdutoDoacaoDesc(Integer idDoador);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	boolean hasById(Integer idProdutoDoacao);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.fkDoador = ?2 and p.removido = false and p.status = false")
	boolean hasByIdAndIdDoador(Integer idProdutoDoacao, Integer idDoador);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = true")
	boolean hasDoadoById(Integer idProdutoDoacao);

	@Query("select case when count(p) >= 1 then true else false end from ProdutoDoacao p where p.fkDoador = ?1 and p.removido = false and p.status = false")
	boolean hasByIdDoador(Integer idDoador);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.fkDoador = ?2 and p.removido = false and p.status = false")
	boolean hasByStatusFalseAndRemovidoFalseAndIdAndFkDoador(Integer idProdutoDoacao, Integer idDoador);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.fkDoador = ?2 and p.removido = false")
	boolean hasByStatusRemovidoFalseAndIdAndFkDoador(Integer idProdutoDoacao, Integer idDoador);

	@Query("select p.fkDoador from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	Integer getFkDoadorById(Integer idProdutoDoacao);

	@Query("select p.fkDoador from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = true")
	Integer getFkDoadorByDoadoTrueAndId(Integer idProdutoDoacao);

	@Query("select p.quantidadeVisualizacao from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	Optional<Integer> getQuantidadeVisualizacaoById(Integer idProdutoDoacao);

	@Query("select case when count(p) = 1 then true else false end from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.imagemPrincipal <> null and p.removido = false and p.status = false")
	boolean imagemPrincipalIsNotNull(Integer idProdutoDoacao);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(p.nome, p.marca, p.modelo, p.descricao, p.defeito, p.entrega, p.quantidadeVisualizacao, p.dataCriacao, p.dataConclusao, p.status, p.categoriaProduto.nome, p.fkDoador) from ProdutoDoacao p where p.idProdutoDoacao = ?1 and p.removido = false")
	Optional<ProdutoDoacaoResponse> getProdutoDoacaoResponseById(Integer idProdutoDoacao);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(p.nome, p.marca, p.modelo, p.descricao, p.defeito, p.entrega, p.quantidadeVisualizacao, p.dataCriacao, p.dataConclusao, p.status, p.categoriaProduto.nome, p.fkDoador) from ProdutoDoacao p where concat(p.categoriaProduto.nome,' ',p.marca,' ',p.modelo) like concat('%',?1,'%') or concat(p.categoriaProduto.nome,' ',p.modelo,' ',p.marca) like concat('%',?1,'%') or concat(p.marca,' ',p.modelo,' ',p.categoriaProduto.nome) like concat('%',?1,'%') or concat(p.modelo,' ',p.marca,' ',p.categoriaProduto.nome) like concat('%',?1,'%') or concat(p.modelo,' ',p.categoriaProduto.nome,' ',p.marca) like concat('%',?1,'%') or concat(p.marca,' ',p.categoriaProduto.nome,' ',p.modelo) like concat('%',?1,'%') and p.removido = false and p.status = false order by p.quantidadeVisualizacao desc, p.nome asc")
	List<ProdutoDoacaoResponse> searchProduto(String nome);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(p.nome, p.marca, p.modelo, p.descricao, p.defeito, p.entrega, p.quantidadeVisualizacao, p.dataCriacao, p.dataConclusao, p.status, p.categoriaProduto.nome, p.fkDoador) from ProdutoDoacao p where p.fkDoador = ?1 and p.status = true and p.removido = false order by p.dataConclusao desc")
	List<ProdutoDoacaoResponse> getAllByStatusDoado(Integer idDoador);

	@Query("select new com.conture.apiproduto.model.dto.response.ProdutoDoacaoResponse(p.nome, p.marca, p.modelo, p.descricao, p.defeito, p.entrega, p.quantidadeVisualizacao, p.dataCriacao, p.dataConclusao, p.status, p.categoriaProduto.nome, p.fkDoador) from ProdutoDoacao p where p.fkDoador = ?1 and p.status = false and p.removido = false order by p.dataCriacao desc")
	List<ProdutoDoacaoResponse> getAllByStatusNaoDoado(Integer idDoador);

	@Query("update ProdutoDoacao p set p.quantidadeVisualizacao = ?2 where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	@Transactional
	@Modifying
	void updateQuantidadeVisualizacaoById(Integer idProdutoDoacao, Integer quantidadeVisualizacao);

	@Query("update ProdutoDoacao p set p.removido = true where p.idProdutoDoacao = ?1")
	@Transactional
	@Modifying
	void logicDelete(Integer idProdutoDoacao);

	@Query("update ProdutoDoacao p set p.status = true, p.dataConclusao = ?2 where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	@Transactional
	@Modifying
	void updateStatusTrueAndDataConclusaoNowById(Integer idProdutoDoacao, Date dataConclusao);

	@Query("update ProdutoDoacao p set p.imagemPrincipal = ?2 where p.idProdutoDoacao = ?1 and p.removido = false and p.status = false")
	@Transactional
	@Modifying
	void updateProdutoImagemPrincipal(Integer idProdutoDoacao, byte[] imagemPrincipal);
}
