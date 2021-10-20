/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pi4.ecommerce.repository;

import com.pi4.ecommerce.entity.FuncionarioBackOffice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Alex Yudy Kitahara
 */
public interface ListaFuncionariosBackOffice extends JpaRepository<FuncionarioBackOffice, Long>{
   
    @Query("select i.fun_email from FuncionarioBackOffice i where i.fun_email = :email")
    public FuncionarioBackOffice findByEmail(String email);
    
    @Query("select a from FuncionarioBackOffice a where a.fun_email = :email and a.fun_senhausu = :senha")
    public FuncionarioBackOffice buscarLogin(String email, String senha);
    
}
