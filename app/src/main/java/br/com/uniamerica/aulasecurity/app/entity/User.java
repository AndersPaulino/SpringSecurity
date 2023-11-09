package br.com.uniamerica.aulasecurity.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_user", schema = "public")
public class User extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_name")
    private String name;

    @Getter @Setter
    @Column(name = "cl_login")
    private String login;

    @Getter @Setter
    @Column(name = "cl_password")
    private String password;
}
