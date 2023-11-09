package br.com.uniamerica.aulasecurity.app.dto;

import br.com.uniamerica.aulasecurity.app.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class UserDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String name;
    private String login;
    private String password;

    public UserDTO(User user){
        id = user.getId();
        ativo = user.isAtivo();
        registro = user.getRegistro();
        atualizar = user.getAtualizar();
        name = user.getName();
        password = user.getPassword();
    }

    public UserDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String name, String login, String password){
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.name = name;
        this.login = login;
        this.password = password;
    }
}
