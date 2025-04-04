package com.cemiterio.Cemiterio.FilterAuth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cemiterio.Cemiterio.User.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class Filtter extends OncePerRequestFilter {
    @Autowired
    IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var Server = request.getServletPath();
        if (Server.equals("/DeadUser/CreateDeadUser") || Server.equals("/Sector/CreateSector")) {
            var autorizacao = request.getHeader("Authorization");
            filterChain.doFilter(request, response);

            var Authencode = autorizacao.substring("Basic ".length()).trim();

            byte[] Autoencoder = Base64.getDecoder().decode(Authencode);

            var autorizacaoinit = new String(Autoencoder);
            String[] credenciais = autorizacaoinit.split(":");
            String usuario= credenciais[0];
            String senha = credenciais[1];
            System.out.println(senha);
            System.out.println(usuario);
            var user = this.userRepository.findByUsername(usuario);
            if (user == null) {
                response.sendError(401, "Não funciona usuario inexistente");
            } else {
                var senhaverifica = BCrypt.verifyer().verify(senha.toCharArray(), user.getPassword());
                if (senhaverifica.verified) {
                    request.getSession().setAttribute("user", user.getiduser());
                    filterChain.doFilter(request, response);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
