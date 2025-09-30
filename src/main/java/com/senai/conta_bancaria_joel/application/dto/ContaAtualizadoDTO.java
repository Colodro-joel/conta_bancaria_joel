package com.senai.conta_bancaria_joel.application.dto;

import java.math.BigDecimal;

public record ContaAtualizadoDTO(
        BigDecimal agencia,
        BigDecimal numero,
        BigDecimal ativa
    ) {
}

