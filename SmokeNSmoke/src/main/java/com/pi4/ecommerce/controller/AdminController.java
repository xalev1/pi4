package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.ProdutoDAO;
import com.pi4.ecommerce.dao.UsuarioDAO;
import com.pi4.ecommerce.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public ModelAndView mostrarTela() {
	ModelAndView mv = new ModelAndView("admin");
	return mv;
    }
    
    @PostMapping("/produtos")
    public ModelAndView listaProdutos() {
	ModelAndView mv = new ModelAndView("redirect:/produtos");
        return mv;
    }
    
    @GetMapping("/admin/cadastroBackOffice")
    public ModelAndView mostrarTelaCadastro() {
	ModelAndView mv = new ModelAndView("CadastroBackOffice");
	return mv;
    }
    
    
    @PostMapping("/admin/cadastroBackOffice")
    public ModelAndView adicionarProduto(@ModelAttribute(value = "usuario") Usuario u) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.salvarUsuario(u);
        ModelAndView mv = new ModelAndView("redirect:/admin/cadastroBackOffice");

        return mv;
      }
    

}