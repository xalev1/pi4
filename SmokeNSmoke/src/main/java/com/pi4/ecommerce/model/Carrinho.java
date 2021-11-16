
package com.pi4.ecommerce.model;

public class Carrinho {
  private int id;
  private String nome;
  private String url_imagem;
  private double preco_venda;
  private int quantidade;

  public Carrinho() {
  }

    public Carrinho(int id, String nome, String url_imagem, double preco_venda, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.url_imagem = url_imagem;
        this.preco_venda = preco_venda;
        this.quantidade = quantidade;
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

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
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

    @Override
    public String toString() {
        return "Carrinho{" + "id=" + id + ", nome=" + nome + ", url_imagem=" + url_imagem + ", preco_venda=" + preco_venda + ", quantidade=" + quantidade + '}';
    }

}
