package br.com.uniamerica.aulasecurity.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user", schema = "public")
public class User extends AbstractEntity implements UserDetails {
    @Getter @Setter
    @Column(name = "cl_name")
    private String name;

    @Getter @Setter
    @Column(name = "cl_login")
    private String login;

    @Getter @Setter
    @Column(name = "cl_password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_role", uniqueConstraints = @UniqueConstraint(
            columnNames = {"user_id", "role_id"},
            name = "unique_role_user"),
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id", table = "tb_user", unique = false,
                    foreignKey = @ForeignKey(name = "user_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "tb_role", unique = false, updatable = false,
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
    private List<Role> roles; // Os papéis ou acessos


    /*São os acessos do usuário, ROLE_GERENTE*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
