package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.Produto;
import com.pi4.ecommerce.service.ProdutoServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl service;
    
    // Listar todos os produtos
    @GetMapping("/produtos")
    public String listarProdutos(Model model, @Param("keyword") String keyword){
        List<Produto> listarProdutos = service.listAll(keyword);
        model.addAttribute("listarProductos", listarProdutos);
        model.addAttribute("keyword", keyword);
        
        return findPaginated(1, model);
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
    
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value="pageNo") int pageNo, Model model){
        int pageSize = 10;
        Page<Produto> page = service.findPaginated(pageNo, pageSize);
        List<Produto> listarProdutos = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listarProdutos", listarProdutos);
        return "listaProdutos";
    }
}
