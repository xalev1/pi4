package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.Produto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController {

  @GetMapping("/Backoffice/Produtos")
  public ModelAndView mostrarTela() {

    ModelAndView mv = new ModelAndView("backoffice-produtos");
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtos = produtoDao.getProdutos();
    mv.addObject("games", produtos);
    return mv;
  }

  @GetMapping("/Backoffice/Produtos/Novo")
  public ModelAndView exibirCadastro() {

    Produto p = new Produto();

    ModelAndView mv = new ModelAndView("backoffice-produtos-novo");

    mv.addObject("produto", p);

    return mv;
  }

  @GetMapping("/Backoffice/Produtos/{id}")
  public ModelAndView exibirAlterarProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("backoffice-produtos-alterar");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);
    
    return mv;
  }
  
  @GetMapping("/Backoffice/Produtos/Visualizar/{id}")
  public ModelAndView verProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("detalhes");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);

    return mv;
  }

  @PutMapping("/Backoffice/Produtos/{id}")
  public ModelAndView alterarProduto(
          @PathVariable("id") int id,
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.alterarProduto(p);

    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    imagemProdutoDao.deletarImagensProduto(p.getId());

    if (imagens != null) imagemProdutoDao.salvarImagensProduto(p.getId(), imagens);
    
    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Produtos");

    return mv;
  }

  @PostMapping("/Backoffice/Produtos/Novo")
  public ModelAndView adicionarProduto(
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.salvarProduto(p);

    int produto_id = produtoDao.getUltimoProduto();

    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    
    if (imagens != null) imagemProdutoDao.salvarImagensProduto(produto_id, imagens);
    

    ModelAndView mv = new ModelAndView("redirect:/Backoffice/Produtos");

    return mv;
  }

}
