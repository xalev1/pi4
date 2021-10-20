package com.pi4.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
    
    @Column(name = "fun_endereco", length =150)
    private String fun_endereco;
    
    @Column(name = "fun_bairro", length =50)
    private String fun_bairro;
    
    @Column(name = "fun_cep", length =20)
    private String fun_cep;
    
    @Column(name = "fun_sexo", length =15)
    private String fun_sexo;
    
    @Column(name = "fun_dtnasc")
    private Date fun_dtnasc;
    
    @Column(name = "fun_numcpf", length =20)
    private String fun_numcpf;
    
    @Column(name = "fun_numfone", length =20)
    private String fun_numfone;
    
    @Column(name = "fun_numrg", length =20)
    private String fun_numrg;
    
    @Column(name = "fun_cargo", length =30)
    private String fun_cargo;
    
    @Column(name = "fun_horario", length =30)
    private String fun_horario;
    
    @Column(name = "fun_dtadmi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fun_dtadmi = new java.sql.Date(System.currentTimeMillis());
    
    @Column(name = "fun_dtdemi")
    private Date fun_dtdemi;
    
    @Column(name = "fun_nrreg", length =20)
    private String fun_nrreg;
    
    @Column(name = "fun_senhausu", length =100)
    private String fun_senhausu;
    
    @Column(name = "fun_salario", length =30)
    private String fun_salario;
    
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

    public String getFun_endereco() {
        return fun_endereco;
    }

    public void setFun_endereco(String fun_endereco) {
        this.fun_endereco = fun_endereco;
    }

    public String getFun_bairro() {
        return fun_bairro;
    }

    public void setFun_bairro(String fun_bairro) {
        this.fun_bairro = fun_bairro;
    }

    public String getFun_cep() {
        return fun_cep;
    }

    public void setFun_cep(String fun_cep) {
        this.fun_cep = fun_cep;
    }

    public String getFun_sexo() {
        return fun_sexo;
    }

    public void setFun_sexo(String fun_sexo) {
        this.fun_sexo = fun_sexo;
    }

    public Date getFun_dtnasc() {
        return fun_dtnasc;
    }

    public void setFun_dtnasc(Date fun_dtnasc) {
        this.fun_dtnasc = fun_dtnasc;
    }

    public String getFun_numcpf() {
        return fun_numcpf;
    }

    public void setFun_numcpf(String fun_numcpf) {
        this.fun_numcpf = fun_numcpf;
    }

    public String getFun_numfone() {
        return fun_numfone;
    }

    public void setFun_numfone(String fun_numfone) {
        this.fun_numfone = fun_numfone;
    }

    public String getFun_numrg() {
        return fun_numrg;
    }

    public void setFun_numrg(String fun_numrg) {
        this.fun_numrg = fun_numrg;
    }

    public String getFun_cargo() {
        return fun_cargo;
    }

    public void setFun_cargo(String fun_cargo) {
        this.fun_cargo = fun_cargo;
    }

    public String getFun_horario() {
        return fun_horario;
    }

    public void setFun_horario(String fun_horario) {
        this.fun_horario = fun_horario;
    }

    public Date getFun_dtadmi() {
        return fun_dtadmi;
    }

    public void setFun_dtadmi(Date fun_dtadmi) {
        this.fun_dtadmi = fun_dtadmi;
    }

    public Date getFun_dtdemi() {
        return fun_dtdemi;
    }

    public void setFun_dtdemi(Date fun_dtdemi) {
        this.fun_dtdemi = fun_dtdemi;
    }

    public String getFun_nrreg() {
        return fun_nrreg;
    }

    public void setFun_nrreg(String fun_nrreg) {
        this.fun_nrreg = fun_nrreg;
    }

    public String getFun_senhausu() {
        return fun_senhausu;
    }

    public void setFun_senhausu(String fun_senhausu) {
        this.fun_senhausu = fun_senhausu;
    }

    public String getFun_salario() {
        return fun_salario;
    }

    public void setFun_salario(String fun_salario) {
        this.fun_salario = fun_salario;
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
