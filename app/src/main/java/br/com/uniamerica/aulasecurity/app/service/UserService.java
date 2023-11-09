package br.com.uniamerica.aulasecurity.app.service;

import br.com.uniamerica.aulasecurity.app.entity.User;
import br.com.uniamerica.aulasecurity.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public  void validarUser(User user){
        String name = user.getName();
        String login = user.getLogin();
        String password = user.getPassword();

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome do Usuário não informado!");
        }

        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Login do Usuário não informado!");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Senha do Usuário não informado!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(User user){
        validarUser(user);
        userRepository.save(user);
    }
}
