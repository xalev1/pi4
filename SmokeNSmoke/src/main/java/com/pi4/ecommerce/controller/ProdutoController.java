package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository pr;
    
    @RequestMapping(value ="/cadastrarProduto" , method=RequestMethod.GET)
    public String form(){
        return "cadastroProduto";
    }
    
    @RequestMapping(value ="/cadastrarProduto" , method=RequestMethod.POST)
    public String form(Produto produto){
        
        pr.save(produto);
        return "redirect:/cadastrarProduto";
    }
    
}
