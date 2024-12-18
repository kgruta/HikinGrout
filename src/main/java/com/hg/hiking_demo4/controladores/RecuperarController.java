package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.servicio.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Flujo:
 * - GET /recuperar: muestra formulario para ingresar el email.
 * - POST /recuperar: genera el token, envía correo, muestra confirmación.
 * - GET /restablecer?token=xxx: valida token y muestra formulario nueva contraseña.
 * - POST /restablecer: actualiza contraseña y muestra confirmación.
 */

@Controller
public class RecuperarController {

    @Autowired
    private PasswordResetService passwordResetService;

    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperar() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String procesarRecuperar(@RequestParam String email, Model model) {
        try {
            passwordResetService.crearTokenPasswordReset(email);
            model.addAttribute("mensaje", "Se ha enviado un correo a " + email + " con instrucciones para restablecer tu contraseña.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensaje", "No se encontró un usuario con el email proporcionado.");
        }
        return "confirmacionRecuperar";
    }

    @GetMapping("/restablecer")
    public String mostrarFormularioRestablecer(@RequestParam String token, Model model) {
        try {
            passwordResetService.validarToken(token);
            model.addAttribute("token", token);
            return "restablecer";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "errorToken";
        }
    }

    @PostMapping("/restablecer")
    public String procesarRestablecer(@RequestParam String token,
                                      @RequestParam("nuevaContrasena") String nuevaContrasena,
                                      Model model) {
        try {
            passwordResetService.restablecerContrasena(token, nuevaContrasena);
            model.addAttribute("mensaje", "Tu contraseña ha sido restablecida con éxito.");
            return "confirmacionRestablecer";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "errorToken";
        }
    }
}
