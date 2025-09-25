package com.senai.conta_bancaria_joel.application.dto;

import java.util.List;

public record ClienteResponseDTO (
    long id,
    String nome,
    String cpf,
    List<ContaResumoDTO> contas
){


}
