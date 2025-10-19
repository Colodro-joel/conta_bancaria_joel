package com.senai.conta_bancaria_joel.domain.entity;

import com.senai.conta_bancaria_joel.domain.Exception.SaldoInsuficienteException;
import com.senai.conta_bancaria_joel.domain.Exception.TransferirParaMesmaContaException;
import com.senai.conta_bancaria_joel.domain.Exception.ValoresMenoresque0Exception;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
                @UniqueConstraint(name = "uk_cliente_tipo", columnNames = {"cliente_id", "tipo_conta"})
        }
)
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class ContaBancária {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;

    @Column(nullable = false)
    private Boolean ativa;

    public abstract String getTipo();

    public void sacar(BigDecimal valor) {
        validatePositiveAmount(valor, "saque");

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValoresMenoresque0Exception("saque");
        }
        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException("saque");
        }
        saldo = saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor) {
        validatePositiveAmount(valor, "depósito");
        saldo = saldo.add(valor);
    }

    protected static void validatePositiveAmount(BigDecimal valor, String operacao) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValoresMenoresque0Exception(operacao);
        }
    }

    public void transferir(BigDecimal valor, ContaBancária contaDestino) {
        if (this.id.equals(contaDestino.getId())) {
            throw new TransferirParaMesmaContaException();
        }
        this.sacar(valor);
        contaDestino.depositar(valor);
    }

}