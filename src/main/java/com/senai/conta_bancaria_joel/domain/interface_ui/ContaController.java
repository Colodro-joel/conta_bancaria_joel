package com.senai.conta_bancaria_joel.domain.interface_ui;

import com.senai.conta_bancaria_joel.application.dto.ContaResumoDTO;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
@RequiredArgsConstructor
public class ContaController {
    private final ContaRepository repository;

    @Transactional(readOnly = true)
    public List<ContaResumoDTO> listarTodasContas() {
        return repository.findAllByAtivaTrue().stream()
                .filter(ContaBancária::isAtiva)
                .map(ContaResumoDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public ContaResumoDTO buscarContaPorNumero(String numero) {
        return ContaResumoDTO.fromEntity(
                repository.findByNumeroAndAtivaTrue(numero)
                        .orElseThrow(() -> new RuntimeException("Conta não encontrada"))
        );
    }
}
