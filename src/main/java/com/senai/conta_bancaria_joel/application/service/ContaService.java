package com.senai.conta_bancaria_joel.application.service;

import com.senai.conta_bancaria_joel.application.dto.ContaResumoDTO;
import com.senai.conta_bancaria_joel.application.dto.TransferenciaDTO;
import com.senai.conta_bancaria_joel.application.dto.ValorSaqueDepositoDTO;
import com.senai.conta_bancaria_joel.domain.Exception.EntidadeNãoEncontradaException;
import com.senai.conta_bancaria_joel.domain.Exception.RendimentoInvalidoException;
import com.senai.conta_bancaria_joel.domain.Exception.TipodeContaInvalidaException;
import com.senai.conta_bancaria_joel.domain.entity.ContaBancária;
import com.senai.conta_bancaria_joel.domain.entity.ContaCorrente;
import com.senai.conta_bancaria_joel.domain.entity.ContaPoupança;
import com.senai.conta_bancaria_joel.domain.repository.ContaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class ContaService {
    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
        public List<ContaResumoDTO> listarTodasContas() {
            return repository.findAllByAtivaTrue().stream()
                    .map(ContaResumoDTO::fromEntity).toList();
        }

        @Transactional(readOnly = true)
        public ContaResumoDTO buscarContaPorNumero(String numero) {
            return ContaResumoDTO.fromEntity(
                    buscarContaPorNumero(numero)
            );
        }

        public ContaResumoDTO atualizarConta(String numeroDaConta, ContaResumoDTO dto) {
            ContaBancária conta = buscarContaAtivaPorNumero(numeroDaConta);

            if (conta instanceof ContaPoupança poupanca){
                poupanca.setRendimento(dto.rendimento());
            } else if (conta instanceof ContaCorrente corrente) {
                corrente.setLimite(dto.limite());
                corrente.setTaxa(dto.taxa());
            } else {
                throw new TipodeContaInvalidaException("");
            }
            conta.setSaldo(dto.saldo());

            return ContaResumoDTO.fromEntity(repository.save(conta));
        }

        public void deletarConta(String numeroDaConta) {
            ContaBancária conta = repository.findByNumeroAndAtivaTrue(numeroDaConta)
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
            conta.setAtiva(false);
            repository.save(conta);
        }

        public ContaResumoDTO sacar(String numeroDaConta, ValorSaqueDepositoDTO dto) {
            ContaBancária conta = repository.findByNumeroAndAtivaTrue(numeroDaConta)
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

            conta.sacar(dto.valor());
            return ContaResumoDTO.fromEntity(repository.save(conta));
        }

        public ContaResumoDTO depositar(String numeroDaConta, ValorSaqueDepositoDTO dto) {
            ContaBancária conta = repository.findByNumeroAndAtivaTrue(numeroDaConta)
                    .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

            conta.depositar(dto.valor());
            return ContaResumoDTO.fromEntity(repository.save(conta));
        }

    private ContaBancária buscarContaAtivaPorNumero(String numero) {
        return repository.findByNumeroAndAtivaTrue(numero)
                .orElseThrow(() -> new EntidadeNãoEncontradaException("ContaBancária"));
    }

    public ContaResumoDTO transferir(String numeroDaConta, TransferenciaDTO dto) {
        ContaBancária contaOrigem = buscarContaAtivaPorNumero(numeroDaConta);
        ContaBancária contaDestino = buscarContaAtivaPorNumero(dto.contaDestino());

        contaOrigem.sacar(dto.valor());
        contaDestino.depositar(dto.valor());

        repository.save(contaDestino);
        return ContaResumoDTO.fromEntity(repository.save(contaOrigem));
    }

    public ContaResumoDTO aplicarRendimento(String numero) {
        ContaBancária conta = buscarContaAtivaPorNumero(numero);
        if (conta instanceof ContaPoupança poupança) {
            poupança.aplicarRendimento();
            return ContaResumoDTO.fromEntity(repository.save(poupança));
        }
        throw new RendimentoInvalidoException("");
    }

}
