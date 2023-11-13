package br.com.uniamerica.aulasecurity.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;


/*Funções:
* Criar a Autenticação
* Retornar a Autenticação JWT
* */
@Service
@Component
public class JWTTokenAutenticationService {
    /*Token de validade de 4 dias (345600000 milissegundos)*/
    private static final long EXPIRATION_TIME = 345600000;

    /*Chave de senha para juntar com o JWT*/
    private static final String SECRET = "9denov15:35";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";
    /*Gera o token e da a resposta para o cliente com o JWT*/
    public void addAuthentication(HttpServletResponse response, String login) throws Exception{
        /*Montagem do Token*/
        String JWT = Jwts.builder()/*Chama o gerador de Token*/
                .setSubject(login)/*Adiciona o user*/
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /*Tempo de expiração a contar da data de HJ + 4 dias*/
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;/*Exe: Bearer agangjaajnagjnajnfajnjangalmagmakna*/

        response.addHeader(HEADER_STRING, token);/*Dá a resposta na tela para o cliente, outra API, navegador, aplicativo, etc...*/

        liberacaoCors(response);

        response.getWriter().write("{\"Autorization\": \"" + token + "\"}"); /*Retorna o cabeçalho e o corpo da resposta, usado para ver no postman para teste*/

    }
    /*Fazendo liberação contra erro de Cors no navegador*/
    public void liberacaoCors(HttpServletResponse response){
        if (response.getHeader("Acess-Control-Allow-Origin") == null){
            response.addHeader("Acess-Control-Allow-Origin","*");
        }
        if (response.getHeader("Acess-Control-Allow-Headers") == null){
            response.addHeader("Acess-Control-Allow-Headers","*");
        }
        if (response.getHeader("Acess-Control-Request-Headers") == null){
            response.addHeader("Acess-Control-Request-Headers","*");
        }
        if (response.getHeader("Acess-Control-Allow-Methods") == null){
            response.addHeader("Acess-Control-Allow-Methods","*");
        }
    }
}
