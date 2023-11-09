package br.com.uniamerica.aulasecurity.app.security;

import br.com.uniamerica.aulasecurity.app.service.ImplementationUserDetailService;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter implements HttpSessionListener {
    private ImplementationUserDetailService implementationUserDetailService;

    @Autowired
    public WebConfigSecurity(ImplementationUserDetailService implementationUserDetailService){
        this.implementationUserDetailService = implementationUserDetailService;
    }

    /*Irá consultar o user no banco de dados com o spring security*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(implementationUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET, "/salvarAcesso","/deleteAcesso")
                .antMatchers(HttpMethod.POST, "/salvarAcesso","/deleteAcesso");
        /*Ignorando URL no momento para não autenticar*/
    }
}
/*Mapeia URL, enderecos, autoriza ou bloqueia acesso a URL*/