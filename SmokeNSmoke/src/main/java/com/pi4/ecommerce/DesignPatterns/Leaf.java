package com.pi4.ecommerce.DesignPatterns;

public class Leaf implements Component{
    // Item para adicionar ao carrinho
    private String NomeProduto;
    private double Valor;
    private int Quantidade;
    private String Imagem;
    
    public Leaf(String nome,double preco,int quantia,String imagem){
        this.NomeProduto = nome;
        this.Valor = preco;
        this.Quantidade = quantia;
        this.Imagem = imagem;
    }

    @Override
    public String toString() {
        return "Leaf{" + "NomeProduto=" + NomeProduto + ", Valor=" + Valor + ", Quantidade=" + Quantidade + ", Imagem=" + Imagem + '}';
    }
    
    @Override
    public double getPreco() {
        return Quantidade;
    }
    
}
