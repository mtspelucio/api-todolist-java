package br.com.mtspelucio.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.mtspelucio.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

            var servletPath = request.getServletPath();
            if(servletPath.startsWith("/tasks/")){

                //pega cabeçalho da requisição
                var authorization = request.getHeader("Authorization");

                //retira o Basic e os espaços da variavel
                var authEncoded = authorization.substring("Basic".length()).trim();

                //coverte os dados para base64
                byte[] authDecoded = Base64.getDecoder().decode(authEncoded);


                //converte os dados para string
                var authString = new String(authDecoded);
                
                //separa os dados para um array
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
    
                var user = this.userRepository.findByUsername(username);

                //validando usuário
                if(user == null){
                    response.sendError(401);
                } else {
                    //validando senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if(passwordVerify.verified){
                        //enviando idUser
                        request.setAttribute("idUser", user.getId());
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }
                }
            } else {
                filterChain.doFilter(request, response);
            }

        
        }
    
}
