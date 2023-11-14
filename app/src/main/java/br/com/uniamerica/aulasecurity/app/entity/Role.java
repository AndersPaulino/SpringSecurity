package br.com.uniamerica.aulasecurity.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_role")
@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nomeRole;/*Papel da Role no sistema, exemplo: ROLE_GERENTE*/


    @Override
    public String getAuthority() {/*Retorna o nome do papel ou acesso ou autorização, exemplo: ROLE_GERENTE*/
        return this.nomeRole;
    }

}
