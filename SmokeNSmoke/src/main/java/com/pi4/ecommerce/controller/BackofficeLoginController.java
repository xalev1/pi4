package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.UsuarioDAO;
import com.pi4.ecommerce.model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BackofficeLoginController {

//  @GetMapping("/admin/login")
//  public ModelAndView mostrarTela() {
//    Usuario u = new Usuario();
//    ModelAndView mv = new ModelAndView("loginBackOffice");
//    mv.addObject("usuario", u);
//    return mv;
//  }
//
//  @PostMapping("/admin/login")
//  public ModelAndView login(
//          @ModelAttribute(value = "usuario") Usuario u,
//          HttpServletRequest request) {
//    UsuarioDAO usuarioDao = new UsuarioDAO();
//    ModelAndView mv;
//
//    u = usuarioDao.getUsuario(u.getEmail(), u.getSenha());
//    if (u != null ) {
//      HttpSession sessao = request.getSession();
//      sessao.setAttribute("user", u);
//      mv = new ModelAndView("redirect:/admin/home");
//      mv.addObject("u", u);
//    } else {
//      mv = new ModelAndView("loginBackOffice");
//      mv.addObject("usuario", new Usuario());
//    }
//    return mv;
//  }
}
