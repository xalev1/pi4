package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.EnderecoDAO;
import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.PedidoResumidoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.model.Carrinho;
import com.pi4.ecommerce.model.Cliente;
import com.pi4.ecommerce.model.Endereco;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.MeioPagamento;
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
    
    @GetMapping("/pedidos")
    public ModelAndView listarPedidos() {

        ModelAndView mv = new ModelAndView("listaPedidos");
        PedidoResumidoDAO pedidoResumidoDao = new PedidoResumidoDAO();

        List<PedidoResumido> pedidos = pedidoResumidoDao.getAllPedidos();
        mv.addObject("pedidos", pedidos);
        return mv;
    }

//    @GetMapping("/Meus-pedidos/{id}")
//    public ModelAndView exibirDetalhesPedido(@PathVariable("id") int id, HttpServletRequest request) {
//
//        
////        HttpSession sessao = request.getSession();
////
////        Cliente c = (Cliente) sessao.getAttribute("cliente");
////        
////        PedidoResumidoDAO pedidoResumidoDao = new PedidoResumidoDAO();
////        List<PedidoResumido> pedidos = pedidoResumidoDao.getPedidos(c.getId());
////   
////
////        return mv;
//    }

}
