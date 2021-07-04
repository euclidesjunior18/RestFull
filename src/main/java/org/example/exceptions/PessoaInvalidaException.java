package org.example.exceptions;

public class PessoaInvalidaException extends RuntimeException{
    public PessoaInvalidaException(String message) {
        super(message);
    }
}
