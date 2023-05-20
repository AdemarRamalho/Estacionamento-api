package br.com.uniamerica.Estacionamentopedro.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "modelo_audit",schema = "audit")
@Table(name = "tb_modelos",schema = "public")
public class Modelo extends AbstractEntity{
    @Getter
    @Setter
    @NotNull
    @Size(min = 2,max = 50)
    @Column(name = "nome",nullable = false,unique = true)
    private String nome;

    @Getter
    @Setter
//    @NotNull
    @ManyToOne
    @JoinColumn(name = "marca_id",nullable = false)
    private Marca marca;
}
