package br.com.loja.vendaws.View.Controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.vendaws.Service.VendaService;
import br.com.loja.vendaws.Shared.VendaDTO;
import br.com.loja.vendaws.View.Model.VendaRequest;
import br.com.loja.vendaws.View.Model.VendaResponse;

@RestController
@RequestMapping("/api/venda")
public class VendaController {
    @Autowired
    private VendaService service;

    @GetMapping
    public ResponseEntity<List<VendaResponse>> obterTodos() {
        List<VendaDTO> vendaDTO = service.obterTodos();

        if (vendaDTO.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<VendaResponse> vendaResponse = vendaDTO.stream()
            .map(p -> new ModelMapper().map(p, VendaResponse.class))
            .collect(Collectors.toList());
            
        return new ResponseEntity<>(vendaResponse, HttpStatus.FOUND);
    }

    @GetMapping(value = "/periodo/{data_inicial}/{data_final}")
    public ResponseEntity<List<VendaResponse>> obterPorPeriodo(@PathVariable LocalDate data_inicial, @PathVariable LocalDate data_final) {
        List<VendaDTO> vendaDTO = service.obterPorData(data_inicial, data_final);

        if (vendaDTO.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<VendaResponse> vendaResponse = vendaDTO.stream()
        .map(p -> new ModelMapper().map(p, VendaResponse.class))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(vendaResponse, HttpStatus.FOUND);
    }

    @GetMapping(value = "/codigo_produto/{codigo_produto}")
    public ResponseEntity<List<VendaResponse>> obterPorProduto(@PathVariable int codigo_produto) {
        List<VendaDTO> vendaDTO = service.obterPorCodigo_produto(codigo_produto);

        if (vendaDTO.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<VendaResponse> vendaResponse = vendaDTO.stream()
        .map(p -> new ModelMapper().map(p, VendaResponse.class))
        .collect(Collectors.toList());
        
        return new ResponseEntity<>(vendaResponse, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<VendaResponse> realizarVenda(@RequestBody @Valid VendaRequest venda) {
        ModelMapper mapper = new ModelMapper();

        VendaDTO vendaAdicionar = mapper.map(venda, VendaDTO.class);
        
        Optional<VendaDTO> vendaDTO = service.realizarVenda(vendaAdicionar);

        if (vendaDTO.isPresent())
        {
            return new ResponseEntity<>(mapper.map(vendaDTO.get(), VendaResponse.class), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(value = "/cancelarVenda/id_venda/{id_venda}")
    public ResponseEntity<Void> cancelarVenda(@PathVariable String id_venda) {
        Optional<VendaDTO> venda = service.obterPorId(id_venda);
        if (venda.isPresent())
        {
            service.cancelarVenda(id_venda);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/excluirRelatorio/id_venda/{id_venda}")
    public ResponseEntity<Void> excluir_relatorioVenda(@PathVariable String id_venda) {
        Optional<VendaDTO> venda = service.obterPorId(id_venda);
        if (venda.isPresent())
        {
            service.excluir_relatorioVenda(id_venda);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}