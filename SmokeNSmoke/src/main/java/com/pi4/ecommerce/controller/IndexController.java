
package com.pi4.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/carrinho")
        public String carrinho() {
        return "carrinho";
    }
}
