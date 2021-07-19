package br.com.loja.produtows.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.produtows.model.Produto;
import br.com.loja.produtows.repository.ProdutoRepository;
import br.com.loja.produtows.shared.ProdutoDTO;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<ProdutoDTO> obterTodos() {
        List<Produto> produtos = repository.findAll();

        return produtos.stream()
            .map(p -> new ModelMapper().map(p, ProdutoDTO.class))
            .collect(Collectors.toList());
    }
    
    @Override
    public Optional<ProdutoDTO> obterPorNome(String nome) {
        Optional<Produto> prod = repository.findByNomeStartingWithIgnoreCase(nome);

        if (prod.isPresent())
        {
            return Optional.of(new ModelMapper().map(prod.get(), ProdutoDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public ProdutoDTO criarProduto(ProdutoDTO produto) {
        ModelMapper mapa = new ModelMapper();
        Produto pro = mapa.map(produto, Produto.class);
        pro = repository.save(pro);
        return mapa.map(pro, ProdutoDTO.class);
    }

    @Override
    public void removerProduto(int codigo) {
        Optional<Produto> prod = repository.findByCodigo(codigo);

        if (prod.isPresent())
        {
            repository.deleteById(prod.get().getId());
        }
    }

    @Override
    public Optional<ProdutoDTO> obterPorCodigo(int codigo) {
        Optional<Produto> pro = repository.findByCodigo(codigo);

        if (pro.isPresent()) {
            return Optional.of(new ModelMapper().map(pro.get(), ProdutoDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public ProdutoDTO atualizarProdutoPorCodigo(int codigo, ProdutoDTO produto) {
        ModelMapper mapa = new ModelMapper();

        Produto pro = mapa.map(produto, Produto.class);

        Optional<ProdutoDTO> produtoDTO = obterPorCodigo(codigo);

        if (produtoDTO.isPresent())
        {
            pro.setId(produtoDTO.get().getId());
            pro.setCodigo_produto(produtoDTO.get().getCodigo_produto());
        }
        
        repository.save(pro);
        return mapa.map(pro, ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO atualizarProdutoPorNome(String nome, ProdutoDTO produto) {
        ModelMapper mapa = new ModelMapper();

        Produto pro = mapa.map(produto, Produto.class);

        Optional<ProdutoDTO> produtoDTO = obterPorNome(nome);

        if (produtoDTO.isPresent())
        {
            pro.setId(produtoDTO.get().getId());
        }
        
        repository.save(pro);
        return mapa.map(pro, ProdutoDTO.class);
    }

    @Override
    public void removerQuantidade(int codigo_produto, int quantidade) {
        Optional<ProdutoDTO> verificacaoCodigo = obterPorCodigo(codigo_produto);

        if (verificacaoCodigo.isPresent())
        {
            verificacaoCodigo.get().setQuantidade_estoque(verificacaoCodigo.get().getQuantidade_estoque() - quantidade);

            repository.save(new ModelMapper().map(verificacaoCodigo, Produto.class));
        }
    }
}