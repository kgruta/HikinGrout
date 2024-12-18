package com.hg.hiking_demo4.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RelacionExistenteException.class)
    public String manejarRelacionExistente(RelacionExistenteException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // Asegúrate de tener una vista 'error.html'
    }

    @ExceptionHandler(RelacionNoEncontradaException.class)
    public String manejarRelacionNoEncontrada(RelacionNoEncontradaException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // Asegúrate de tener una vista 'error.html'
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarIllegalArgument(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // Asegúrate de tener una vista 'error.html'
    }

    // Manejo de otras excepciones
    @ExceptionHandler(Exception.class)
    public String manejarExcepcionGeneral(Exception ex, Model model) {
        model.addAttribute("error", "Ocurrió un error: " + ex.getMessage());
        return "error"; // Asegúrate de tener una vista 'error.html'
    }
}
