package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.Produto;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


public class HomeController {
    
//   @GetMapping("/home")
//  public ModelAndView exibirHome() {
//    ModelAndView mv = new ModelAndView("home");
//    
//    ProdutoDAO produtoDao = new ProdutoDAO();
//    ImagemProdutoDAO imagemProdutoDao = new ImagemProdutoDAO();
// 
//    List<Produto> produtos = produtoDao.getProdutos();
//    List<ImagemProduto> imagens = imagemProdutoDao.getFirstImagensProduto();
// 
//    mv.addObject("produto", produtos);
//    mv.addObject("imagens", imagens);
//
//    return mv;
//  }
    
}
