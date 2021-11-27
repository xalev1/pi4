package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.PedidoResumidoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.model.Carrinho;
import com.pi4.ecommerce.model.Cliente;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.PedidoResumido;
import com.pi4.ecommerce.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {

  @GetMapping("/Meus-pedidos")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    HttpSession sessao = request.getSession();

    Cliente c = (Cliente) sessao.getAttribute("cliente");
    
    
    ModelAndView mv = new ModelAndView("meus-pedidos");
    PedidoResumidoDAO pedidoResumidoDao = new PedidoResumidoDAO();
    
    List<PedidoResumido> pedidos = pedidoResumidoDao.getPedidos(c.getId());
    mv.addObject("pedidos", pedidos);
    return mv;
  }
  
  @GetMapping("/Meus-pedidos/{id}")
  public ModelAndView exibirDetalhesPedido(@PathVariable("id") int id, HttpServletRequest request) {

    Carrinho produtoCarrinho = new Carrinho();

    ProdutoDAO produtoDao = new ProdutoDAO();
    ImagemProdutoDAO imagemDao = new ImagemProdutoDAO();

    Produto p = produtoDao.getProdutos(id);
    List<ImagemProduto> img = imagemDao.getImagensProduto(id);

    produtoCarrinho.setId(id);
    produtoCarrinho.setNome(p.getNome());
    produtoCarrinho.setUrl_imagem(img.get(0).getUrl_imagem());
    produtoCarrinho.setQuantidade(1);
    produtoCarrinho.setPreco_venda(p.getPreco_venda());

    HttpSession sessao = request.getSession();
    List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");
    if (carrinho == null) {
      carrinho = new ArrayList<Carrinho>();
    }
    carrinho.add(produtoCarrinho);
    sessao.setAttribute("carrinho-compras", carrinho);

    ModelAndView mv = new ModelAndView("redirect:/Carrinho");
 
    mv.addObject("cep", sessao.getAttribute("cep"));
    mv.addObject("carrinho", carrinho);
    return mv;
  }
  
}
