package br.com.loja.vendaws.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.loja.vendaws.Shared.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    
}