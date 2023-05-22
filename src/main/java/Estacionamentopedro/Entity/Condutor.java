package Estacionamentopedro.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalTime;

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "condutores_audit",schema = "audit")
@Table(name = "tb_condutores",schema = "public")
public class Condutor extends AbstractEntity{
    @NotEmpty
    @NotNull
    @Getter
    @Setter
    @Size(min = 3,max = 50)
    @Column(name = "nome",nullable = false,length = 50)
    private String nome;
    @NotEmpty
    @NotNull
    @Getter
    @Setter
    @Size(min = 3,max = 20)
    @Column(name = "cpf",nullable = false,length = 20,unique = true)
    private String cpf;

    @NotEmpty
    @NotNull
    @Getter
    @Setter
    @Size(min = 3,max = 20)
    @Column(name = "telefone",nullable = false,length = 20)
    private String telefone;

    @NotEmpty
    @NotNull
    @Getter
    @Setter
    @Column(name = "tempo_pago",nullable = false)
    private LocalTime tempoPago;
    @NotEmpty
    @NotNull
    @Getter
    @Setter
    @Column(name = "tempo_desconto",nullable = false)
    private LocalTime tempoDesconto;
}
