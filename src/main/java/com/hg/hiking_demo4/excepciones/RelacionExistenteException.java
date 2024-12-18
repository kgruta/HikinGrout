package com.hg.hiking_demo4.excepciones;


public class RelacionExistenteException extends RuntimeException {
    public RelacionExistenteException(String mensaje) {
        super(mensaje);
    }
}
