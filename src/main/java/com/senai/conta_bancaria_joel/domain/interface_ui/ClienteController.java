package com.senai.conta_bancaria_joel.domain.interface_ui;

import com.senai.conta_bancaria_joel.application.dto.ClienteResponseDTO;
import com.senai.conta_bancaria_joel.application.service.ClienteService;
import com.senai.conta_bancaria_joel.application.service.ContaService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(*/"/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ClienteResponseDTO criarCliente(@RequestBody ClienteResponseDTO dto) {
        return service.criarCliente(dto);
    }
}
