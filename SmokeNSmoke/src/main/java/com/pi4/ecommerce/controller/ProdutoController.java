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
    
  //BackOffice - Listar produtos
  @GetMapping("/produtos")
  public ModelAndView mostrarTela() {

    ModelAndView mv = new ModelAndView("listaProdutos");
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtos = produtoDao.getProdutos();
    mv.addObject("listarProdutos", produtos);
    return mv;
  }

  // BackOffice - Exibir formulário para cadastrar produtos 
  @GetMapping("/cadastrarProduto")
  public ModelAndView exibirCadastro() {

    Produto p = new Produto();

    ModelAndView mv = new ModelAndView("cadastroProduto");
    mv.addObject("produto", p);

    return mv;
  }
  
  // BackOffice - Exibir formulário para alterar produtos
  @GetMapping("/alterarProduto/{id}")
  public ModelAndView exibirAlterarProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("alterarProduto");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);
    
    return mv;
  }
  
  
  // BackOffice - Mostrar detalhes dos produtos cadastrados
  @GetMapping("/detalheProduto/{id}")
  public ModelAndView verProduto(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("detalheProduto");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);

    return mv;
  }

  // BackOffice - Alterar produtos
  @PutMapping("/alterarProduto/{id}")
  public ModelAndView alterarProduto(
          @PathVariable("id") int id,
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.alterarProduto(p);

    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    imagemProdutoDao.deletarImagensProduto(p.getId());

    if (imagens != null) imagemProdutoDao.salvarImagensProduto(p.getId(), imagens);  
    ModelAndView mv = new ModelAndView("redirect:/produtos");
    return mv;
  }
  
  // BackOffice - Cadastrar produtos
  @PostMapping("/cadastrarProduto")
  public ModelAndView adicionarProduto(
          @ModelAttribute(value = "produto") Produto p,
          @RequestParam(value = "imagem", required = false) String[] imagens) {

    ProdutoDAO produtoDao = new ProdutoDAO();
    produtoDao.salvarProduto(p);

    int produto_id = produtoDao.getUltimoProduto();
    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    
    if (imagens != null) imagemProdutoDao.salvarImagensProduto(produto_id, imagens);
    ModelAndView mv = new ModelAndView("redirect:/produtos");

    return mv;
  }
  
  // FrontEnd - Exibir página Home com os produtos cadastrados
 @GetMapping("/home")
  public ModelAndView exibirHome() {

    ModelAndView mv = new ModelAndView("home");
    ProdutoDAO produtoDao = new ProdutoDAO();
    List<Produto> produtos = produtoDao.getProdutos();
    
    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
    List<ImagemProduto> imagens = imagemProdutoDao.getFirstImagensProduto();
    
    mv.addObject("imagens", imagens);
    mv.addObject("listarProdutos", produtos);
    return mv;
  }
  
  // FrontEnd - Mostrar detalhes dos produtos cadastrados
  @GetMapping("/produto/{id}")
  public ModelAndView verProdutoHome(@PathVariable("id") int id) {

    ModelAndView mv = new ModelAndView("produto");
    ProdutoDAO produtoDao = new ProdutoDAO();
    Produto p = produtoDao.getProdutos(id);

    ImagemProdutoDAO imagensProdutoDAO = new ImagemProdutoDAO();
    List<ImagemProduto> listaImagens = imagensProdutoDAO.getImagensProduto(id);

    mv.addObject("produto", p);
    mv.addObject("listaImagens", listaImagens);

    return mv;
  }

}
