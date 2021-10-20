package com.pi4.ecommerce.controller;

import com.pi4.ecommerce.entity.FuncionarioBackOffice;
import com.pi4.ecommerce.repository.FuncionarioRepository;
import com.pi4.ecommerce.service.ServiceBackOffice;
import com.pi4.ecommerce.util.Util;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository fr; 
    @Autowired
    private ServiceBackOffice sb;
    
    
    @RequestMapping(value = "/CadastroFuncionario", method = RequestMethod.GET)
    public String form() {
        return "CadastroBackOffice";
    }
    
    @GetMapping("/")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("LoginBackOffice");
        mv.addObject("usuario", new FuncionarioBackOffice());
        return mv;
    }
    
    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("indexTeste");
        mv.addObject("FuncionarioBackOffice", new FuncionarioBackOffice());
        return mv;
    }
            
    @RequestMapping(value = "/CadastroFuncionario", method = RequestMethod.POST)
    public String form(FuncionarioBackOffice funcionario) throws Exception {
        sb.salvarUsuario(funcionario);
        return "redirect:/CadastroFuncionario";
    }
    
    @PostMapping("/login")
    public ModelAndView login(@Valid FuncionarioBackOffice usuario,BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceException{
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuario",new FuncionarioBackOffice());
        if(br.hasErrors()){
            mv.setViewName("LoginBackOffice");
        }
        FuncionarioBackOffice userLogin = sb.loginBackOffice(usuario.getFun_email(), Util.md5(usuario.getFun_senhausu()));
        if(userLogin == null){
            mv.addObject("msg", "Usuário não encontrado. Tente novamente");
            mv.setViewName("LoginBackOffice");
        }
        else{
        session.setAttribute("usuarioLogado", userLogin);
        return index();
        }
        return mv;
    }
}
