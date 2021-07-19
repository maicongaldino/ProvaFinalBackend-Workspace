package br.com.loja.vendaws.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.loja.vendaws.Model.Venda;

@Repository
public interface VendaRepository extends MongoRepository<Venda, String> {

    @Query(value = "{ $and: [ { 'data_venda' : { $gte: ?0 } }, { 'data_venda' : { $lte : ?1 } } ] }")
    List<Venda> obterPorPeriodoData(LocalDate data_inicial, LocalDate data_final);

    @Query(value = "{'codigo_produto' : ?0}")
    List<Venda> obterPorCodigoProduto(int codigo_produto);
}