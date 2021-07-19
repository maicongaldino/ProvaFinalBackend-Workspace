package br.com.loja.produtows.View.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.produtows.View.Model.ProdutoRequest;
import br.com.loja.produtows.View.Model.ProdutoResponse;
import br.com.loja.produtows.View.Model.ProdutoUpdate;
import br.com.loja.produtows.service.ProdutoService;
import br.com.loja.produtows.shared.ProdutoDTO;


@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodasOsProdutos() {
        List<ProdutoDTO> produtoDTO = service.obterTodos();

        List<ProdutoResponse> produtoResponse = produtoDTO.stream()
            .map(p -> new ModelMapper().map(p, ProdutoResponse.class))
            .collect(Collectors.toList());
            
        return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ProdutoResponse> obterUmProdutoNome(@PathVariable String nome) {
        Optional<ProdutoDTO> pro = service.obterPorNome(nome);

        if (pro.isPresent())
        {
            return new ResponseEntity<>(new ModelMapper().map(pro.get(), ProdutoResponse.class), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/codigo/{codigo}")
    public ResponseEntity<ProdutoResponse> obterUmProdutoCodigo(@PathVariable int codigo){
        Optional<ProdutoDTO> pro = service.obterPorCodigo(codigo);

        if (pro.isPresent())
        {
            return new ResponseEntity<>(new ModelMapper().map(pro.get(), ProdutoResponse.class), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarUmProduto(@RequestBody @Valid ProdutoRequest produto) {
        ModelMapper mapa = new ModelMapper();
        ProdutoDTO produtoDTO = mapa.map(produto, ProdutoDTO.class);

        Optional<ProdutoDTO> verificacaoCodigo = service.obterPorCodigo(produtoDTO.getCodigo_produto());
        Optional<ProdutoDTO> verificacaoNome = service.obterPorNome(produtoDTO.getNome());

        if (verificacaoCodigo.isPresent() || verificacaoNome.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        produtoDTO = service.criarProduto(produtoDTO);

        return new ResponseEntity<>(mapa.map(produtoDTO, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        Optional<ProdutoDTO> produtoDTO = service.obterPorCodigo(codigo);

        if (produtoDTO.isPresent())
        {
            service.removerProduto(codigo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/codigo/{codigo}")
    public ResponseEntity<ProdutoResponse> atualizarProdutoPorCodigo(@PathVariable int codigo, @RequestBody @Valid ProdutoUpdate produto) {
        ModelMapper mapa = new ModelMapper();

        Optional<ProdutoDTO> verificacaoCodigo = service.obterPorCodigo(codigo);

        if (verificacaoCodigo.isPresent())
        {
            ProdutoDTO produtoDTO = mapa.map(produto, ProdutoDTO.class);
            produtoDTO = service.atualizarProdutoPorCodigo(codigo, produtoDTO);
    
            return new ResponseEntity<>(mapa.map(produtoDTO, ProdutoResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/nome/{nome}")
    public ResponseEntity<ProdutoResponse> atualizarProdutoPorNome(@PathVariable String nome, @RequestBody @Valid ProdutoUpdate produto) {
        ModelMapper mapa = new ModelMapper();

        Optional<ProdutoDTO> verificacaoNome = service.obterPorNome(produto.getNome());

        if (verificacaoNome.isPresent())
        {
            ProdutoDTO produtoDTO = mapa.map(produto, ProdutoDTO.class);
            produtoDTO = service.atualizarProdutoPorNome(nome, produtoDTO);
    
            return new ResponseEntity<>(mapa.map(produtoDTO, ProdutoResponse.class), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}