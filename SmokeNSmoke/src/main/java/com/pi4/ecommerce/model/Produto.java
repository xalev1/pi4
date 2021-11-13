package com.pi4.ecommerce.model;

public class Produto {
    
    private int id;
    private String nome;
    private String descricao;
    private double preco_custo ;
    private double preco_venda;
    private int quantidade;
    private boolean ativo;

    public Produto() {}

    public Produto(int id, String nome, String descricao, double preco_custo, double preco_venda, int quantidade, boolean ativo) {
	this.id = id;
	this.nome = nome;
	this.descricao = descricao;
	this.preco_custo = preco_custo;
	this.preco_venda = preco_venda;
	this.quantidade = quantidade;
	this.ativo = ativo;
    }

    public Produto(String nome, String descricao, double preco_custo, double preco_venda, int quantidade, boolean ativo) {
	this.nome = nome;
	this.descricao = descricao;
	this.preco_custo = preco_custo;
	this.preco_venda = preco_venda;
	this.quantidade = quantidade;
	this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco_custo() {
        return preco_custo;
    }

    public void setPreco_custo(double preco_custo) {
        this.preco_custo = preco_custo;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    


    @Override
    public String toString() {
	return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco_custo=" + preco_custo + ", preco_venda=" + preco_venda + ", quantidade=" + quantidade + ", ativo=" + ativo + '}';
    }
    
}
