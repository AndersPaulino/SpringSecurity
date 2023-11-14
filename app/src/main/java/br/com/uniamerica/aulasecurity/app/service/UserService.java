package br.com.uniamerica.aulasecurity.app.service;

import br.com.uniamerica.aulasecurity.app.entity.User;
import br.com.uniamerica.aulasecurity.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

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
    public void cadastrar(User user) {
        validarUser(user);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(userName);

        if (user == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
