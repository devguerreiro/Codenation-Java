package br.com.codenation.desafio.exceptions;

public class IdentificadorUtilizadoException extends RuntimeException {
    public IdentificadorUtilizadoException() {
        super("ID já utilizado.");
    }
}
