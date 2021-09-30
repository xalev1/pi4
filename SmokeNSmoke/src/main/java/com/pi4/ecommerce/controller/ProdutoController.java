package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.repository.ProdutoRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @RequestMapping(value = "/alterarProduto/{id_produto}")
    public ModelAndView editarProduto(@PathVariable("id_produto")long id_produto) {
        Produto produto = pr.findById(id_produto);
        ModelAndView mv = new ModelAndView("alterarProduto");
        mv.addObject("produto", produto);
        return mv;
    }
    
    @RequestMapping(value = "/alterarProduto/{id_produto}", method = RequestMethod.POST)
    public String updateProduto(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
        pr.save(produto);
        return "redirect:/listaPodutos";
    }

}
