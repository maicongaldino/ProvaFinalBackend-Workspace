package br.com.loja.vendaws.Model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("venda")
public class Venda {
    @Id
    private String id;
    private int codigo_produto;
    private String nome_produto;
    private LocalDate data_venda;
    private int quantidade_vendida;
    private double valor_Total;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getCodigo_produto() {
        return codigo_produto;
    }
    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }
    public String getNome_produto() {
        return nome_produto;
    }
    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
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
    public double getValor_Total() {
        return valor_Total;
    }
    public void setValor_Total(double valor_Total) {
        this.valor_Total = valor_Total;
    }
}