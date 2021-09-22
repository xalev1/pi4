package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository pr;

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.GET)
    public String form() {
        return "cadastroProduto";
    }

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
    public String form(Produto produto) {

        pr.save(produto);
        return "redirect:/cadastrarProduto";
    }

    @RequestMapping("/produtos")
    public ModelAndView listaProdutos() {
        ModelAndView mv = new ModelAndView("listaProdutos");
        Iterable<Produto> produtos = pr.findAll();
        mv.addObject("produtos", produtos);
        return mv;

    }
    
    @RequestMapping("/detalheProduto/{id_produto}")
    public ModelAndView detalheProduto(@PathVariable("id_produto") long id_produto){
        Produto produto = pr.findById(id_produto);
        ModelAndView mv = new ModelAndView("detalheProduto");
        mv.addObject("produto", produto);
        return mv;
        
    }

}
