package Estacionamentopedro.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(?![ ])(?!.*[ ]{2})((?:e|da|do|das|dos|de|d'|D'|la|las|el|los)\\s*?|(?:[A-Z][^\\s]*\\s*?)(?!.*[ ]$))+$",message = "Nome tem que ter no minimo 3 Caractecteres e no Maximo 50!, Favor nome e Sobrenome!")
    @Size(min = 3,max = 50)
    @Column(name = "nome",nullable = false,length = 50)
    private String nome;
    @NotEmpty
    @NotNull(message = "CPF tem que ser valido! e não nulo!")
    @Getter
    @Setter
    @Size(min = 3,max = 20)
    @Column(name = "cpf",nullable = false,length = 20,unique = true)
    private String cpf;

    @NotEmpty
    @NotNull(message = "Telefone tem que ter no minimo 3 Caracteres e no Maximo 20 E não pode ser nulo!")
    @Getter
    @Setter
    @Size(min = 3,max = 20)
    @Column(name = "telefone",nullable = false,length = 20)
    private String telefone;

    @Getter
    @Setter
    @Column(name = "tempo_pago")
    private Long tempoPago;

    @Getter
    @Setter
    @Column(name = "tempo_desconto")
    private LocalTime tempoDesconto;
}