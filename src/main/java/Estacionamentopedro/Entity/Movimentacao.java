package Estacionamentopedro.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "movimentacao_audit",schema = "audit")
@Table(name = "tb_movimentacoes",schema = "public")
public class Movimentacao extends AbstractEntity{
    @Getter
    @Setter
    @NotNull(message = "Veiculo Não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false, unique = true)
    private Veiculo veiculo;

    @Getter
    @Setter
    @NotNull(message = "Condutor Não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "condutor_id",nullable = false)
    private Condutor condutor;

    @Getter@Setter
    @Column(name = "entrada",nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entrada;

    @Getter@Setter
    @Column(name = "saida")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime saida;

    @Getter@Setter
    @Column(name = "tempo_horas")
    private Long tempoHoras;

    @Getter@Setter
    @Column(name = "tempo_minutos")
    private Long tempoMinutos;

    @Getter@Setter
    @Column(name = "tempo_desconto")
    private Long tempoDesconto;

    @Getter@Setter
    @Column(name = "tempo_multa")
    private Long tempoMulta;

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
    private Long valorTotal;


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
    public void setEntradaMov() {
        this.entrada = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "----------------------------------------------------------\n                RELATORIO DE MOVIMENTACAO\n----------------------------------------------------------\n" +
                "\nPlaca: " + veiculo.getPlaca() +
                "\nVeiculo: " + veiculo.getModelo().getNome() +
                "\nCondutor: " + condutor.getNome()+
                "\nHorario de entrada: " + entrada +
                "\nHorario de saida: " + saida +
                "\nMinutos após horario núcleo: " + tempoMulta +
                "\n\n------------------------ Descontos -----------------------\n" +
                "\nHoras acumuladas: " + condutor.getTempoPago() +
                "\nValor do desconto: " +
                "\n\n------------------------- Valores ------------------------\n" +
                "\nValor de tempo de permanência: " + valorHora +
                "\nValor da multa: " + valorMulta +
                "\n\n                                        TOTAL: R$ " + valorTotal;
    }
}