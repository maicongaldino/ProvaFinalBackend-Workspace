package br.com.loja.produtows.View.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProdutoUpdate {
    @NotNull
    @NotBlank(message = "O nome deve possuir caracteres não brancos !!!")
    @NotEmpty(message = "O nome deve ser preenchido !!!")
    @Size(min = 3, message = "O nome deve possuir pelo menos 3 caracteres !!!")
    private String nome;

    @NotNull
    @Min(value = 1)
    private double valor;
    
    @NotNull
    @Min(value = 1)
    private int quantidade_estoque;
    
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