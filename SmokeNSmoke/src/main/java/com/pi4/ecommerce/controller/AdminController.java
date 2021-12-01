package com.pi4.ecommerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping("/admin/home")
    public ModelAndView mostrarTela(HttpServletRequest request) {
        HttpSession sessao = request.getSession();
        Object x = sessao.getAttribute("user");
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("u", x);
        return mv;
    }

    @PostMapping("/produtos")
    public ModelAndView listaProdutos() {
        ModelAndView mv = new ModelAndView("redirect:/produtos");
        return mv;
    }

    @PostMapping("/usuarios")
    public ModelAndView listaUsuarios() {
        ModelAndView mv = new ModelAndView("redirect:/usuarios");
        return mv;
    }

    @PostMapping("/pedidos")
    public ModelAndView listaPedidos() {
        ModelAndView mv = new ModelAndView("redirect:/pedidos");
        return mv;
    }

}
