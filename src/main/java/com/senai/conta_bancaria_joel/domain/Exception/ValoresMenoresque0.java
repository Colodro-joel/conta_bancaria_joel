package com.senai.conta_bancaria_joel.domain.Exception;

public class ValoresMenoresque0 extends RuntimeException {
    public ValoresMenoresque0(String operacao) {
        super("Não é possivel"+ operacao + "valores negativos.");
    }
}
