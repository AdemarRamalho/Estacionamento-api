package br.com.uniamerica.Estacionamentopedro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.math.BigDecimal;
import java.time.LocalTime;

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "configuracoes_audit",schema = "audit")
@Table(name = "tb_configuracoes",schema = "public")
public class Configuracao extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "valor_hora")
    private BigDecimal valorHora;
    @Getter
    @Setter
    @Column(name = "valor_minuto_multa")
    private BigDecimal valorMinutoMulta;
    @Getter
    @Column(name = "inicio_expediente")
    private LocalTime inicioExpediente;
    @Getter
    @Column(name = "fim_expediente")
    private LocalTime fimExpediente;
    @Getter
    @Column(name = "tempo_para_desconto")
    private LocalTime tempoParaDesconto;
    @Getter
    @Column(name = "tempo_de_desconto")
    private LocalTime tempoDeDesconto;

    @Getter
    @Setter
    @Column(name = "gerar_desconto")
    private boolean gerarDesconto;

    @Getter
    @Setter
    @Column(name = "vagas_moto")
    private int vagasMoto;

    @Getter
    @Setter
    @Column(name = "vagas_carro")
    private int vagasCarro;

    @Getter
    @Setter
    @Column(name = "vagas_van")
    private int vagasVan;

}
