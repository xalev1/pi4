package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService service;
    
    // Listar todos os produtos
    @GetMapping("/produtos")
    public String listarProdutos(Model model){
        model.addAttribute("listarProdutos", service.getAllProducts());
        return "listaProdutos";
    }
    // Mostrar formulário de cadastro
    @GetMapping("/cadastrarProduto")
    public String cadastrarProdutoForm(Model model){
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "cadastroProduto";
    }
    // Salvar produto
    @PostMapping("/cadastrarProduto") 
    public String cadastrarProduto(@ModelAttribute("produto") Produto produto){
        service.saveProduct(produto);
        return "redirect:/produtos";
    }
    // Pegar dados do produto e mostrar no formulário de alterar produto
    @GetMapping("/alterarProduto/{id_produto}")
    public String alterarProdutoForm(@PathVariable (value="id_produto") long id_produto, Model model){
        Produto produto = service.getProductById(id_produto);
        model.addAttribute("produto", produto);
        return "alterarProduto"; 
    }
    // Mostrar detalhes do Produto
    @GetMapping("/detalheProduto/{id_produto}")
    public String detalheProduto (@PathVariable (value="id_produto") long id_produto, Model model){
        Produto produto = service.getProductById(id_produto);
        model.addAttribute("produto", produto);
        return "detalheProduto";
    }
}
