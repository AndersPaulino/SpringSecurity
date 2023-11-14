package br.com.uniamerica.aulasecurity.app.controller;

import br.com.uniamerica.aulasecurity.app.entity.User;
import br.com.uniamerica.aulasecurity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> cadastrar(@RequestBody User user) {
        try {
            // Obtenha o BCryptPasswordEncoder do contexto
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.cadastrar(user);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

