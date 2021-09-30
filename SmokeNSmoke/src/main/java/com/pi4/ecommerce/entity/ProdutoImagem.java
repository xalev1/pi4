package com.pi4.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProdutoImagem {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_img;
    private String nome_img;
    
    @ManyToOne
    private Produto produto;

    public long getId_img() {
        return id_img;
    }

    public void setId_img(long id_img) {
        this.id_img = id_img;
    }

    public String getNome_img() {
        return nome_img;
    }

    public void setNome_img(String nome_img) {
        this.nome_img = nome_img;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
}
