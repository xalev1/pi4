package com.pi4.ecommerce.model;

public class Pedido {
    
    private int id;
    private String pedido;
    private int quantidade;
    private float valor;
    
    public Pedido(int id, String pedido, int quantidade, float valor ) {
	this.id = id;
	this.pedido = pedido;
	this.quantidade = quantidade;
        this.valor = valor;
    }

    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    
    
    
}
