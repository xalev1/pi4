package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.ClienteDAO;
import com.pi4.ecommerce.dao.EnderecoDAO;
import com.pi4.ecommerce.model.Cliente;
import com.pi4.ecommerce.model.Endereco;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

    //BackOffice - Listar clientes
    @GetMapping("/clientes")
    public ModelAndView mostrarTela() {

        ModelAndView mv = new ModelAndView("listaClientes");
        ClienteDAO clienteDao = new ClienteDAO();
        List<Cliente> clientes = clienteDao.getCliente();
        mv.addObject("listarClientes", clientes);
        return mv;
    }

    @GetMapping("/Cliente/Novo")
    public ModelAndView exibirCadastro() {
        Cliente c = new Cliente();
        ModelAndView mv = new ModelAndView("cliente");
        mv.addObject("cliente", c);
        return mv;
    }

    @PostMapping("/Cliente/Novo")
    public ModelAndView cadastrarCliente(@ModelAttribute(value = "cliente") Cliente c,
            @RequestParam(value = "cep_faturamento", required = false) String cepFaturamento,
            @RequestParam(value = "logradouro_faturamento", required = false) String logradouroFaturamento,
            @RequestParam(value = "numero_faturamento", required = false) String numeroFaturamento,
            @RequestParam(value = "complemento_faturamento", required = false) String complementoFaturamento,
            @RequestParam(value = "bairro_faturamento", required = false) String bairroFaturamento,
            @RequestParam(value = "cidade_faturamento", required = false) String cidadeFaturamento,
            @RequestParam(value = "estado_faturamento", required = false) String estadoFaturamento,
            @RequestParam(value = "cep_entrega", required = false) String cepEntrega,
            @RequestParam(value = "logradouro_entrega", required = false) String logradouroEntrega,
            @RequestParam(value = "numero_entrega", required = false) String numeroEntrega,
            @RequestParam(value = "complemento_entrega", required = false) String complementoEntrega,
            @RequestParam(value = "bairro_entrega", required = false) String bairroEntrega,
            @RequestParam(value = "cidade_entrega", required = false) String cidadeEntrega,
            @RequestParam(value = "estado_entrega", required = false) String estadoEntrega,
            @RequestParam(value = "cep", required = false) String[] cep,
            @RequestParam(value = "logradouro", required = false) String[] logradouro,
            @RequestParam(value = "numero", required = false) String[] numero,
            @RequestParam(value = "complemento", required = false) String[] complemento,
            @RequestParam(value = "bairro", required = false) String[] bairro,
            @RequestParam(value = "cidade", required = false) String[] cidade,
            @RequestParam(value = "estado", required = false) String[] estado) {

        ModelAndView mv = new ModelAndView("login");
        ClienteDAO clienteDao = new ClienteDAO();
        EnderecoDAO enderecoDao = new EnderecoDAO();

        clienteDao.salvarCliente(c);
        int clienteId = clienteDao.getUltimoCliente();

        Endereco faturamento = new Endereco();
        faturamento.setCliente_id(clienteId);
        faturamento.setCep(cepFaturamento);
        faturamento.setLogradouro(logradouroFaturamento);
        faturamento.setNumero(numeroFaturamento);
        faturamento.setComplemento(complementoFaturamento);
        faturamento.setBairro(bairroFaturamento);
        faturamento.setCidade(cidadeFaturamento);
        faturamento.setEstado(estadoFaturamento);
        faturamento.setIs_faturamento(true);

        enderecoDao.salvarEnderecoCliente(clienteId, faturamento);

        Endereco entrega = new Endereco();
        entrega.setCliente_id(clienteId);
        entrega.setCep(cepEntrega);
        entrega.setLogradouro(logradouroEntrega);
        entrega.setNumero(numeroEntrega);
        entrega.setComplemento(complementoEntrega);
        entrega.setBairro(bairroEntrega);
        entrega.setCidade(cidadeEntrega);
        entrega.setEstado(estadoEntrega);
        entrega.setIs_faturamento(false);
        enderecoDao.salvarEnderecoCliente(clienteId, entrega);

        if (cep != null) {
            Endereco enderecoAdicional = new Endereco();
            for (int i = 0; i < cep.length; i++) {
                enderecoAdicional.setCliente_id(clienteId);
                enderecoAdicional.setCep(cep[i]);
                enderecoAdicional.setLogradouro(logradouro[i]);
                enderecoAdicional.setNumero(numero[i]);
                enderecoAdicional.setComplemento(complemento[i]);
                enderecoAdicional.setBairro(bairro[i]);
                enderecoAdicional.setCidade(cidade[i]);
                enderecoAdicional.setEstado(estado[i]);
                enderecoAdicional.setIs_faturamento(false);
                enderecoDao.salvarEnderecoCliente(clienteId, enderecoAdicional);
            }
        }

        return mv;
    }

    @GetMapping("/DadosPessoais")
    public ModelAndView exibirAlterarCliente(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("cliente-alterar");
        HttpSession sessao = request.getSession();

        ClienteDAO clienteDao = new ClienteDAO();
        Cliente c = (Cliente) sessao.getAttribute("cliente");

        c = clienteDao.getCliente(c.getId());
        EnderecoDAO enderecoDao = new EnderecoDAO();

        Endereco faturamento = enderecoDao.getEnderecoFaturamento(c.getId());
        Endereco entrega = enderecoDao.getEnderecoEntrega(c.getId());
        List<Endereco> listaEnderecos = enderecoDao.getEnderecos(c.getId());

        mv.addObject("cliente", c);
        mv.addObject("faturamento", faturamento);
        mv.addObject("entrega", entrega);
        mv.addObject("listaEnderecos", listaEnderecos);

        return mv;
    }

    @PostMapping("/DadosPessoais")
    public ModelAndView alterarCliente(@ModelAttribute(value = "cliente") Cliente c,
            @RequestParam(value = "cep_faturamento", required = false) String cepFaturamento,
            @RequestParam(value = "logradouro_faturamento", required = false) String logradouroFaturamento,
            @RequestParam(value = "numero_faturamento", required = false) String numeroFaturamento,
            @RequestParam(value = "complemento_faturamento", required = false) String complementoFaturamento,
            @RequestParam(value = "bairro_faturamento", required = false) String bairroFaturamento,
            @RequestParam(value = "cidade_faturamento", required = false) String cidadeFaturamento,
            @RequestParam(value = "estado_faturamento", required = false) String estadoFaturamento,
            @RequestParam(value = "cep_entrega", required = false) String cepEntrega,
            @RequestParam(value = "logradouro_entrega", required = false) String logradouroEntrega,
            @RequestParam(value = "numero_entrega", required = false) String numeroEntrega,
            @RequestParam(value = "complemento_entrega", required = false) String complementoEntrega,
            @RequestParam(value = "bairro_entrega", required = false) String bairroEntrega,
            @RequestParam(value = "cidade_entrega", required = false) String cidadeEntrega,
            @RequestParam(value = "estado_entrega", required = false) String estadoEntrega,
            @RequestParam(value = "cep", required = false) String[] cep,
            @RequestParam(value = "logradouro", required = false) String[] logradouro,
            @RequestParam(value = "numero", required = false) String[] numero,
            @RequestParam(value = "complemento", required = false) String[] complemento,
            @RequestParam(value = "bairro", required = false) String[] bairro,
            @RequestParam(value = "cidade", required = false) String[] cidade,
            @RequestParam(value = "estado", required = false) String[] estado,
            HttpServletRequest request) {

        HttpSession sessao = request.getSession();
        Cliente cliente = (Cliente) sessao.getAttribute("cliente");
        c.setId(cliente.getId());

        ModelAndView mv = new ModelAndView("home");
        ClienteDAO clienteDao = new ClienteDAO();
        EnderecoDAO enderecoDao = new EnderecoDAO();

        clienteDao.alterarCliente(c);

        enderecoDao.removeEnderecos(c.getId());

        Endereco faturamento = new Endereco();
        faturamento.setCliente_id(c.getId());
        faturamento.setCep(cepFaturamento);
        faturamento.setLogradouro(logradouroFaturamento);
        faturamento.setNumero(numeroFaturamento);
        faturamento.setComplemento(complementoFaturamento);
        faturamento.setBairro(bairroFaturamento);
        faturamento.setCidade(cidadeFaturamento);
        faturamento.setEstado(estadoFaturamento);
        faturamento.setIs_faturamento(true);

        enderecoDao.salvarEnderecoCliente(c.getId(), faturamento);

        Endereco entrega = new Endereco();
        entrega.setCliente_id(c.getId());
        entrega.setCep(cepEntrega);
        entrega.setLogradouro(logradouroEntrega);
        entrega.setNumero(numeroEntrega);
        entrega.setComplemento(complementoEntrega);
        entrega.setBairro(bairroEntrega);
        entrega.setCidade(cidadeEntrega);
        entrega.setEstado(estadoEntrega);
        entrega.setIs_faturamento(false);
        enderecoDao.salvarEnderecoCliente(c.getId(), entrega);

        if (cep != null) {
            Endereco enderecoAdicional = new Endereco();
            for (int i = 0; i < cep.length; i++) {
                enderecoAdicional.setCliente_id(c.getId());
                enderecoAdicional.setCep(cep[i]);
                enderecoAdicional.setLogradouro(logradouro[i]);
                enderecoAdicional.setNumero(numero[i]);
                enderecoAdicional.setComplemento(complemento[i]);
                enderecoAdicional.setBairro(bairro[i]);
                enderecoAdicional.setCidade(cidade[i]);
                enderecoAdicional.setEstado(estado[i]);
                enderecoAdicional.setIs_faturamento(false);
                enderecoDao.salvarEnderecoCliente(c.getId(), enderecoAdicional);
            }
        }

        return mv;
    }

    @GetMapping("/Login")
    public ModelAndView exibirHome() {
        Cliente c = new Cliente();
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("cliente", c);
        return mv;
    }

    @PostMapping("/Login")
    public ModelAndView login(
            @ModelAttribute(value = "cliente") Cliente c,
            HttpServletRequest request) {
        ClienteDAO clienteDao = new ClienteDAO();
        ModelAndView mv;

        c = clienteDao.getCliente(c.getEmail(), c.getSenha());
        if (c != null) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("cliente", c);
            mv = new ModelAndView("redirect:/home");
            mv.addObject("c", c);
        } else {
            mv = new ModelAndView("login");
            mv.addObject("cliente", new Cliente());
        }
        return mv;
    }

}
