package br.com.uniamerica.Estacionamentopedro.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@Entity
@Audited
@AuditTable(value = "veiculo_audited",schema = "audit")
@Table(name = "tb_veiculos",schema = "public")
public class Veiculo extends AbstractEntity{

    @Getter
    @Setter
    @NotBlank(message = "Placa não pode ser nula, por favor inserir uma placa valida!")
    @Size(min = 8,max = 10)
    @Column(name = "placa",length = 10,nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @NotBlank(message = "Modelo não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "modelo_id",nullable = false)
    private Modelo modelo;

    @Getter
    @Setter
    @NotNull(message = "Cor não pode ser invalida!")
    @Enumerated
    @Column(name = "cor_id",nullable = false)
    private Cor cor;

    @Getter
    @Setter
    @NotNull(message = "Tipo invalido!")
    @Enumerated
    @Column(name = "tipo_id",nullable = false)
    private Tipo tipo;

    @Getter
    @Setter
    @NotNull(message = "Ano invalido!")
    @Size(min = 4,max = 4)
    @Column(name = "ano",nullable = false,length = 5)
    private int ano;
}
