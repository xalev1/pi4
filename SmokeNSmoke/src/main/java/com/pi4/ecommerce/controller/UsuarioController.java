package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.dao.UsuarioDAO;
import com.pi4.ecommerce.model.Usuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

  @GetMapping("/usuarios")
  public ModelAndView mostrarTela(HttpServletRequest request) {

    HttpSession sessao = request.getSession();

    Usuario u = (Usuario) sessao.getAttribute("user");
    
    
    ModelAndView mv = new ModelAndView("listaFuncionarios");
    UsuarioDAO usuarioDao = new UsuarioDAO();
    List<Usuario> usuarios = usuarioDao.getUsuarios();
    mv.addObject("usuarios", usuarios);
    return mv;
  }

  @GetMapping("/cadastrarUsuario")
  public ModelAndView exibirCadastro() {
    Usuario u = new Usuario();
    ModelAndView mv = new ModelAndView("cadastroFuncionario");
    mv.addObject("usuario", u);
    return mv;
  }

  @GetMapping("/alterarUsuario/{id}")
  public ModelAndView exibirAlterarUsuario(@PathVariable("id") int id) {
    ModelAndView mv = new ModelAndView("alterarFuncionario");
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Usuario u = usuarioDao.getUsuario(id);
    mv.addObject("usuario", u);
    return mv;
  }

  @PutMapping("/alterarUsuario/{id}")
  public ModelAndView alterarUsuario(
          @PathVariable("id") int id,
          @ModelAttribute(value = "usuario") Usuario u) {

    UsuarioDAO usuarioDao = new UsuarioDAO();
    usuarioDao.alterarUsuario(u);
    ModelAndView mv = new ModelAndView("redirect:/usuarios");
    return mv;
  }

  @PostMapping("/cadastrarUsuario")
  public ModelAndView adicionarUsuario(
          @ModelAttribute(value = "usuario") Usuario u,
          @RequestParam(value = "repetir-senha", required = true) String repetirSenha) {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    boolean usuarioExistente = usuarioDao.getIsUsuarioExiste(u.getEmail());
    if (!repetirSenha.equals(u.getSenha()) || u.getNome().length() < 5 || usuarioExistente ) {
      ModelAndView mv = new ModelAndView("cadastroFuncionario");
      mv.addObject("usuario", u);
      return mv;
    }

    usuarioDao.salvarUsuario(u);
    ModelAndView mv = new ModelAndView("redirect:/usuarios");
    return mv;
  }
  
  @GetMapping("/admin/login")
  public ModelAndView mostrarTela() {
    Usuario u = new Usuario();
    ModelAndView mv = new ModelAndView("loginBackOffice");
    mv.addObject("usuario", u);
    return mv;
  }

  @PostMapping("/admin/login")
  public ModelAndView login(
          @ModelAttribute(value = "usuario") Usuario u,
          HttpServletRequest request) {
    UsuarioDAO usuarioDao = new UsuarioDAO();
    ModelAndView mv;

    u = usuarioDao.getUsuario(u.getEmail(), u.getSenha());
    if (u != null ) {
      HttpSession sessao = request.getSession();
      sessao.setAttribute("user", u);
      mv = new ModelAndView("redirect:/admin/home");
      mv.addObject("u", u);
    } else {
      mv = new ModelAndView("loginBackOffice");
      mv.addObject("usuario", new Usuario());
    }
    return mv;
  }
  
  @GetMapping("/admin/logout")
    public ModelAndView exibirHome(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/admin/login");
        HttpSession sessao = request.getSession();
        sessao.removeAttribute("usuario");
        return mv;
    }

    @GetMapping("/admin/logoff")
    public ModelAndView deslogarUsuario(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("redirect:/admin/login");
        HttpSession sessao = request.getSession();
        sessao.removeAttribute("usuario");
        return mv;
    }

}
