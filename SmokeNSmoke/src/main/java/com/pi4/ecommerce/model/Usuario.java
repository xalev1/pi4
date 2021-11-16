package com.pi4.ecommerce.model;

public class Usuario {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String cargo;
    private String senha;
    private boolean ativo;
    private boolean registro_deletado;
    
    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String cpf, String cargo, String senha, boolean ativo, boolean registro_deletado) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
        this.senha = senha;
        this.ativo = ativo;
        this.registro_deletado = registro_deletado;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public boolean isRegistro_deletado() {
        return registro_deletado;
    }

    public void setRegistro_deletado(boolean registro_deletado) {
        this.registro_deletado = registro_deletado;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", cargo=" + cargo + ", senha=" + senha + ", ativo=" + ativo + ", registro_deletado=" + registro_deletado + '}';
    }

    
    
    
    
}
