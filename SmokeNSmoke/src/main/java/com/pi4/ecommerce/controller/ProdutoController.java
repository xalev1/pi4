package com.pi4.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController {
    
    @RequestMapping("/cadastrarProduto")
    public String form(){
        return "cadastroProduto";
    }
    
}
