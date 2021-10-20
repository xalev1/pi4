
package com.pi4.ecommerce.service;

import com.pi4.ecommerce.entity.FuncionarioBackOffice;
import com.pi4.ecommerce.repository.FuncionarioRepository;
import com.pi4.ecommerce.repository.ListaFuncionariosBackOffice;
import com.pi4.ecommerce.util.Util;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceBackOffice {
    
    @Autowired
    private ListaFuncionariosBackOffice fr;
    
    public void salvarUsuario(FuncionarioBackOffice funcionario)throws Exception{
        try {
            if(fr.findByEmail(funcionario.getFun_email()) != null){
                throw new Exception("Esse E-Mail já está cadastrado " + funcionario.getFun_email());
            }
            funcionario.setFun_senhausu(Util.md5(funcionario.getFun_senhausu()));
            
        } catch (Exception e) {
            throw  new Exception("Erro na criptografia" + e);
        }
        fr.save(funcionario);
        
    }
    
    public FuncionarioBackOffice loginBackOffice(String email, String senha) throws ServiceException{
     
        FuncionarioBackOffice userLogin = fr.buscarLogin(email,senha);
        return userLogin;
    }
    
}
