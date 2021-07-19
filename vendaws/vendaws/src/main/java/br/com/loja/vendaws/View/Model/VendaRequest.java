package br.com.loja.vendaws.View.Model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VendaRequest {
    @Min(value = 1, message = "Codigo do produto não pode ser preenchido com valor menor que 1 !!!")
    private int codigo_produto;

    @NotNull(message = "Data da venda não pode ser nulo !!!")
    private LocalDate data_venda;
    
    @Min(value = 1, message = "Quantidade vendida não pode ser preenchido com valor menor que 1 !!!")
    private int quantidade_vendida;
    
    public int getCodigo_produto() {
        return codigo_produto;
    }
    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }
    public LocalDate getData_venda() {
        return data_venda;
    }
    public void setData_venda(LocalDate data_venda) {
        this.data_venda = data_venda;
    }
    public int getQuantidade_vendida() {
        return quantidade_vendida;
    }
    public void setQuantidade_vendida(int quantidade_vendida) {
        this.quantidade_vendida = quantidade_vendida;
    }
}