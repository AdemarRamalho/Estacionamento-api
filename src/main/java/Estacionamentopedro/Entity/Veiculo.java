package Estacionamentopedro.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.lang.reflect.Type;

@NoArgsConstructor
@Entity
@Audited
@AuditTable(value = "veiculo_audited",schema = "audit")
@Table(name = "tb_veiculos",schema = "public")
public class Veiculo extends AbstractEntity{

    @Getter
    @Setter
    @NotBlank(message = "Placa não pode ser nula, por favor inserir uma placa valida!")
    @Size(min = 6,max = 10,message = "Erro tamanho no maximo de 6 a 10 caracteres!")
    @Pattern(regexp = "^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$", message = "Placa invalida! favor digitar uma placa valida!")
    @Column(name = "placa",length = 10,nullable = false,unique = true)
    private String placa;
    @Getter
    @Setter
    @NotNull(message = "Modelo não pode ser nulo!")
    @ManyToOne
    @JoinColumn(name = "modelo_id",nullable = false)
    private Modelo modelo;

    @Getter
    @Setter
    @NotNull(message = "Cor não pode ser invalida!")
    @Enumerated(EnumType.STRING)
    @Column(name = "cor_id",nullable = false)
    private Cor cor;

    @Getter
    @Setter
    @NotNull(message = "Tipo invalido!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_id",nullable = false)
    private Tipo tipo;

    @Getter
    @Setter
    @NotNull(message = "Ano invalido!")
    @Column(name = "ano",nullable = false,length = 5)
    private int ano;
}