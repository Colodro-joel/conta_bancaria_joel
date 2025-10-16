package com.senai.conta_bancaria_joel.domain.Exception;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("Já está cadastrado esse tipo de conta");
    }

}
