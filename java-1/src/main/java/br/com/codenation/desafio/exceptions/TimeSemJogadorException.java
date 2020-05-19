package br.com.codenation.desafio.exceptions;

public class TimeSemJogadorException extends RuntimeException {
    public TimeSemJogadorException() {
        super("Time não possui jogador.");
    }
}
