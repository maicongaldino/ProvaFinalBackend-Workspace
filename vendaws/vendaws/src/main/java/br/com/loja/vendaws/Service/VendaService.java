package br.com.loja.vendaws.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.loja.vendaws.Shared.VendaDTO;

public interface VendaService {
    List<VendaDTO> obterTodos();
    List<VendaDTO> obterPorData(LocalDate data_inicial, LocalDate data_final);
    List<VendaDTO> obterPorCodigo_produto(int codigo_produto);
    Optional<VendaDTO> obterPorId(String id);
    Optional<VendaDTO> realizarVenda(VendaDTO produto);
    void cancelarVenda(String id_venda);
    void excluir_relatorioVenda(String id_venda);
}