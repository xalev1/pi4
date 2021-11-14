package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.UsuarioDAO;
import com.pi4.ecommerce.model.Usuario;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    
    @GetMapping("/admin/login")
    public ModelAndView mostrarTelaLogin() {
	ModelAndView mv = new ModelAndView("LoginBackOffice");
	return mv;
    }
    
    @PostMapping("/admin/login")
    public ModelAndView login( Usuario u, BindingResult br, HttpSession session) throws NoSuchAlgorithmException{
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario",new Usuario());
        if(br.hasErrors()){
            mv.setViewName("LoginBackOffice");
        }
        UsuarioDAO userLogin = new UsuarioDAO();
        userLogin.getLogin(u.getEmail(), u.getSenha());
        if(userLogin == null){
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");
            mv.setViewName("LoginBackOffice");
        }
        else{
        session.setAttribute("usuarioLogado", userLogin);
        return mostrarTela();
        }
        return mv;
    }
    
    
    @GetMapping("/admin/home")
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