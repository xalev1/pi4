package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.EnderecoDAO;
import com.pi4.ecommerce.dao.ImagemProdutoDAO;
import com.pi4.ecommerce.dao.PedidoDAO;
import com.pi4.ecommerce.dao.PedidoResumidoDAO;
import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.dao.VendaDAO;
import com.pi4.ecommerce.dao.VendaProdutoDAO;
import com.pi4.ecommerce.model.Carrinho;
import com.pi4.ecommerce.model.Cliente;
import com.pi4.ecommerce.model.Endereco;
import com.pi4.ecommerce.model.ImagemProduto;
import com.pi4.ecommerce.model.MeioPagamento;
import com.pi4.ecommerce.model.Pedido;
import com.pi4.ecommerce.model.PedidoResumido;
import com.pi4.ecommerce.model.Produto;
import com.pi4.ecommerce.model.Venda;
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

    @GetMapping("/Meus-pedidos/{id}")
    public ModelAndView exibirDetalhesPedido(@PathVariable("id") int id, HttpServletRequest request) {

        ModelAndView mv = null;
        EnderecoDAO enderecosDao = new EnderecoDAO();

        HttpSession sessao = request.getSession();

        Cliente c = (Cliente) sessao.getAttribute("cliente");
        Endereco e = (Endereco) sessao.getAttribute("endereco");
        List<Carrinho> carrinho = (List<Carrinho>) sessao.getAttribute("carrinho-compras");
        double total = (Double) sessao.getAttribute("total");
        MeioPagamento pagamento = (MeioPagamento) sessao.getAttribute("pagamento");

        mv = new ModelAndView("detalhes-pedidos");
        mv.addObject("cliente", c);
        mv.addObject("endereco", e);
        mv.addObject("carrinho", carrinho);
        mv.addObject("pagamento", pagamento);
        mv.addObject("total", sessao.getAttribute("total"));

        return mv;

    }
}
