package br.com.loja.produtows.View.Model;

public class ProdutoResponse {
    private int codigo_produto;
    private String nome;
    private double valor;
    private int quantidade_estoque;
    
    public int getCodigo_produto() {
        return codigo_produto;
    }
    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }
    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }
}