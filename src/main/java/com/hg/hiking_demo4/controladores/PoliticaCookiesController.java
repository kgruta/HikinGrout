package com.hg.hiking_demo4.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliticaCookiesController {

    /**
     * Muestra la página de política de cookies.
     *
     * @return La plantilla HTML de la política de cookies.
     */
    @GetMapping("/politica-cookies")
    public String mostrarPoliticaCookies() {
        return "politica-cookies"; // Nombre del archivo HTML de la política de cookies (politica-cookies.html)
    }

    /**
     * Muestra la página de información legal.
     *
     * @return La plantilla HTML de información legal.
     */
    @GetMapping("/informacion-legal")
    public String mostrarInformacionLegal() {
        return "informacion-legal"; // Nombre del archivo HTML de la información legal (informacion-legal.html)
    }
}
