package com.hg.hiking_demo4.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/")
    public String inicio(Model model) {

        return "inicio"; // Plantilla de Thymeleaf llamada 'inicio.html'
    }
}
