package br.com.uniamerica.Estacionamentopedro.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "movimentacao_audit",schema = "audit")
@Table(name = "tb_movimentacoes",schema = "public")
public class Movimentacao extends AbstractEntity{
    @Getter
    @Setter
    @NotBlank(message = "Veiculo Não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;

    @Getter
    @Setter
    @NotBlank(message = "Condutor Não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "condutor_id",nullable = false)
    private Condutor condutor;

    @Getter@Setter
    @NotNull(message = "Entrada não pode ser nula!")
    @Column(name = "entrada",nullable = false)
    private LocalDateTime entrada;

    @Getter@Setter
    @Column(name = "saida")
    private LocalDateTime saida;

    @Getter@Setter
    @Column(name = "tempo_horas")
    private int tempoHoras;

    @Getter@Setter
    @Column(name = "tempo_desconto")
    private LocalDateTime tempoDesconto;

    @Getter@Setter
    @Column(name = "tempo_multa")
    private LocalDateTime tempoMulta;

    @Getter
    @Setter
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Getter
    @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;


    @Getter
    @Setter
    @Column(name = "valor_total")
    private BigDecimal valorTotal;


    @Getter
    @Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;


    @Getter
    @Setter
    @Column(name = "valor_hora_multa")
    private BigDecimal valorHoraMulta;


    /**
     * Método automatico, executado no pré-cadastro dos dados
     */

}
