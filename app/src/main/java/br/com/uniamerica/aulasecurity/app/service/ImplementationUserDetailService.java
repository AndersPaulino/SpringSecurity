package br.com.uniamerica.aulasecurity.app.service;

import br.com.uniamerica.aulasecurity.app.dto.UserDTO;
import br.com.uniamerica.aulasecurity.app.entity.User;
import br.com.uniamerica.aulasecurity.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImplementationUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public ImplementationUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(login);

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
