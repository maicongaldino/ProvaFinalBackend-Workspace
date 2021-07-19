package br.com.loja.produtows.service;

import java.util.List;
import java.util.Optional;

import br.com.loja.produtows.shared.ProdutoDTO;

public interface ProdutoService {
    List<ProdutoDTO> obterTodos();
    Optional<ProdutoDTO> obterPorNome(String nome);
    ProdutoDTO criarProduto(ProdutoDTO produto);
    void removerProduto(int codigo);
    Optional<ProdutoDTO> obterPorCodigo(int codigo);
    ProdutoDTO atualizarProdutoPorCodigo(int codigo, ProdutoDTO produto);
    ProdutoDTO atualizarProdutoPorNome(String nome, ProdutoDTO produto);
    void removerQuantidade(int codigo_produto, int quantidade);
}