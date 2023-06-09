package br.com.uniamerica.Estacionamentopedro.entity;

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

@NoArgsConstructor
@Audited
@Entity
@AuditTable(value = "marcas_audit",schema = "audit")
@Table(name = "tb_marcas",schema = "public")
public class Marca extends AbstractEntity{
    @Getter
    @Setter
    @NotNull(message = "Nome da marca não pode ser nulo!")
    @NotEmpty
    @Size(min = 3,max = 50)
    @Column(name = "nome",nullable = false,length = 50,unique = true)
    private String nome;
}
