package br.com.uniamerica.aulasecurity.app.security;

;
import br.com.uniamerica.aulasecurity.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserService implementationUserDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebConfigSecurity(UserService implementationUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.implementationUserDetailService = implementationUserDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/user/signup").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(implementationUserDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
/* Mapeia URL, endereços, autoriza ou bloqueia acesso a URL */
