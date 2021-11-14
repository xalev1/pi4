package com.pi4.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}