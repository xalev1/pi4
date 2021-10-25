package com.pi4.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_funcionario")
public class FuncionarioBackOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_fun")
    private long id_fun;

    @Column(name = "fun_nome", length =100)
    private String fun_nome;
    
    @Column(name = "fun_sexo", length =15)
    private String fun_sexo;

    @Column(name = "fun_cargo", length =30)
    private String fun_cargo;

    @Column(name = "fun_senhausu", length =100)
    private String fun_senhausu;

    @Column(name = "fun_flgativo", length =5)
    private String fun_flgativo;
    
    @Column(name = "fun_email", length =100)
    private String fun_email;

    public long getId_fun() {
        return id_fun;
    }

    public void setId_fun(long id_fun) {
        this.id_fun = id_fun;
    }

    public String getFun_nome() {
        return fun_nome;
    }

    public void setFun_nome(String fun_nome) {
        this.fun_nome = fun_nome;
    }

    public String getFun_sexo() {
        return fun_sexo;
    }

    public void setFun_sexo(String fun_sexo) {
        this.fun_sexo = fun_sexo;
    }

    public String getFun_cargo() {
        return fun_cargo;
    }

    public void setFun_cargo(String fun_cargo) {
        this.fun_cargo = fun_cargo;
    }

    public String getFun_senhausu() {
        return fun_senhausu;
    }

    public void setFun_senhausu(String fun_senhausu) {
        this.fun_senhausu = fun_senhausu;
    }

    public String getFun_flgativo() {
        return fun_flgativo;
    }

    public void setFun_flgativo(String fun_flgativo) {
        this.fun_flgativo = fun_flgativo;
    }

    public String getFun_email() {
        return fun_email;
    }

    public void setFun_email(String fun_email) {
        this.fun_email = fun_email;
    }
    
    


}
