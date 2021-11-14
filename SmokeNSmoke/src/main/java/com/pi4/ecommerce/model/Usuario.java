package com.pi4.ecommerce.model;

public class Usuario {
    
    private int id;
    private String nome;
    private String email;
    private String sexo;
    private String cargo;
    private String senha;
    private boolean ativo;

    public Usuario() {
    }

  public Usuario(int id, String nome, String email,String sexo,String cargo,String senha,boolean ativo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.sexo = sexo;
    this.cargo = cargo;
    this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", sexo=" + sexo + ", cargo=" + cargo + ", senha=" + senha + ", ativo=" + ativo + '}';
    }
  
}
