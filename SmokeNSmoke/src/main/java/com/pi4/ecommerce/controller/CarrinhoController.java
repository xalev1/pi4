package com.pi4.ecommerce.controller;
import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.model.Carrinho;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoController {
    // Carrinho adicionado
  @GetMapping("/Carrinho")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    ModelAndView mv = new ModelAndView("carrinho");

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");

    if (carrinho == null) {
      carrinho = new ArrayList<Carrinho>();
    }

    double total = 0;
    for (Carrinho Carrinho : carrinho) {
        total = total + Carrinho.getPreco_venda()* Carrinho.getQuantidade();
      }
    
    if (sessao.getAttribute("cep") != null) {
      total = total + 20;
    }
    
    sessao.setAttribute("total", total);
    
    mv.addObject("total", total);
    
    sessao.setAttribute("carrinho-compras", carrinho);
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    

    return mv;
  }

  @PostMapping("/Carrinho")
  public ModelAndView adicionarProduto(
            @RequestParam(value = "produtoId") int produtoId, HttpServletRequest request) {

    Carrinho Carrinho = new Carrinho();

    ProdutoDAO produtoDao = new ProdutoDAO();
    ImagemProdutoDAO imagemDao = new ImagemProdutoDAO();

    Produto p = produtoDao.getProdutos(produtoId);
    List<ImagemProduto> img = imagemDao.getImagensProduto(produtoId);

    Carrinho.setId(produtoId);
    Carrinho.setNome(p.getNome());
    Carrinho.setUrl_imagem(img.get(0).getUrl_imagem());
    Carrinho.setQuantidade(1);
    Carrinho.setPreco_venda(p.getPreco_venda());

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");
    if (carrinho == null) {
      carrinho = new ArrayList<Carrinho>();
    }
    carrinho.add(Carrinho);
    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
 
    mv.addObject("cep", sessao.getAttribute("cep"));
    
    mv.addObject("carrinho", carrinho);
    return mv;
  }


  @PostMapping("/Carrinho/Cep")
  public ModelAndView addCep(@RequestParam(value = "cep") String cep, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");

    if (cep.length() < 8) {
      sessao.setAttribute("cep", null);
    } else {
      sessao.setAttribute("cep", cep);
    }


    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("cep", cep);
    mv.addObject("carrinho", carrinho);
    return mv;

  }
  
  @PostMapping("/Carrinho/{id}/removeQtde")
  public ModelAndView removeQtdeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");

    for (Carrinho Carrinho : carrinho) {
      if (Carrinho.getId() == id) {
        if (Carrinho.getQuantidade()!= 1) Carrinho.setQuantidade(Carrinho.getQuantidade()- 1);
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }
  
  @PostMapping("/Carrinho/{id}/addQtde")
  public ModelAndView addQtdeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");

    for (Carrinho Carrinho : carrinho) {
      if (Carrinho.getId() == id) {
        Carrinho.setQuantidade(Carrinho.getQuantidade()+ 1);
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }

  @DeleteMapping("/Carrinho/{id}")
  public ModelAndView removeProduto(@PathVariable("id") int id, HttpServletRequest request) {

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");

    if (carrinho.size() == 1) {
      carrinho.clear();
    } else {
      for (Carrinho Carrinho : carrinho) {
        if (Carrinho.getId() == id) {
          carrinho.remove(Carrinho);
          break;
        }
      }
    }

    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
    mv.addObject("carrinho", carrinho);
    mv.addObject("cep", sessao.getAttribute("cep"));
    return mv;

  }

}
