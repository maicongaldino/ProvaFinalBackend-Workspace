package br.com.loja.produtows.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.loja.produtows.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    @Query(value = "{'codigo_produto' : ?0}")
    Optional<Produto> findByCodigo(int codigo_procurar);

    Optional<Produto> findByNomeStartingWithIgnoreCase(String nome);
}