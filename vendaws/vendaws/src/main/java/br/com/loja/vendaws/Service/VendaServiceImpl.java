package br.com.loja.vendaws.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.vendaws.Model.Venda;
import br.com.loja.vendaws.Repository.ProdutoRepository;
import br.com.loja.vendaws.Repository.VendaRepository;
import br.com.loja.vendaws.Shared.Produto;
import br.com.loja.vendaws.Shared.VendaDTO;

@Service
public class VendaServiceImpl implements VendaService {
    @Autowired
    private VendaRepository repository;

    @Autowired
    private ProdutoRepository repositoryProduto;

    @Override
    public List<VendaDTO> obterTodos() {
        List<Venda> vendas = repository.findAll();

        return vendas.stream()
            .map(v -> new ModelMapper().map(v, VendaDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<VendaDTO> obterPorData(LocalDate data_inicial, LocalDate data_final) {
        List<Venda> vendas = repository.obterPorPeriodoData(data_inicial, data_final);

        return vendas.stream()
            .map(v -> new ModelMapper().map(v, VendaDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public List<VendaDTO> obterPorCodigo_produto(int codigo_produto) {
        List<Venda> vendas = repository.obterPorCodigoProduto(codigo_produto);

        return vendas.stream()
            .map(v -> new ModelMapper().map(v, VendaDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<VendaDTO> obterPorId(String id) {
        Optional<Venda> venda = repository.findById(id);

        if (venda.isPresent())
        {
            return Optional.of(new ModelMapper().map(venda.get(), VendaDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<VendaDTO> realizarVenda(VendaDTO venda) {
        ModelMapper mapa = new ModelMapper();
        Venda ven = mapa.map(venda, Venda.class);

        List<Produto> produtos = repositoryProduto.findAll();

        for (Produto produto : produtos) {
            if (produto.getCodigo_produto() == venda.getCodigo_produto())
            {
                if (venda.getQuantidade_vendida() <= produto.getQuantidade_estoque())
                {
                    produto.setQuantidade_estoque(produto.getQuantidade_estoque() - venda.getQuantidade_vendida());
                    repositoryProduto.save(produto);
                    ven.setValor_Total(produto.getValor() * ven.getQuantidade_vendida());
                    ven.setNome_produto(produto.getNome());
                    ven = repository.save(ven);

                    VendaDTO saida = mapa.map(ven, VendaDTO.class);
            
                    return Optional.of(saida);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public void cancelarVenda(String id_venda) {
        Optional<VendaDTO> venda = obterPorId(id_venda);
        
        if (venda.isPresent())
        {
            List<Produto> produtos = repositoryProduto.findAll();

            for (Produto produto : produtos)
            {
                if (produto.getCodigo_produto() == venda.get().getCodigo_produto())
                {
                    produto.setQuantidade_estoque(produto.getQuantidade_estoque() + venda.get().getQuantidade_vendida());
                    repositoryProduto.save(produto);
                }
            }
        }

        repository.deleteById(id_venda);
    }

    @Override
    public void excluir_relatorioVenda(String id_venda) {
        repository.deleteById(id_venda);
    }
}