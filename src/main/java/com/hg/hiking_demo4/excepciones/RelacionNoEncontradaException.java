package com.hg.hiking_demo4.excepciones;

public class RelacionNoEncontradaException extends RuntimeException {
    public RelacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
