package com.pi4.ecommerce.model;

public class Pedido {
    
    private int id;
    private int pedido;
    private int quantidade;
    private double valor;
    private String status;

    public Pedido() {
    }

    public Pedido(int id, int pedido, int quantidade, double valor, String status) {
        this.id = id;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}