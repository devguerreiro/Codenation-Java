package com.challenge.exceptions;

public class LoginInvalidException extends RuntimeException {
    public LoginInvalidException() {
        super("Usuário ou senha inválido");
    }
}
