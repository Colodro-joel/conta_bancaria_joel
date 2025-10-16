package com.senai.conta_bancaria_joel.domain.Exception;

public class RendimentoInvalidoException extends RuntimeException {
    public RendimentoInvalidoException() {
        super("Rendimento inválido para essa conta!");
    }
}
