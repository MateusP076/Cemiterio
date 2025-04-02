package com.cemiterio.Cemiterio.FilterAuth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cemiterio.Cemiterio.DeadUser.DeadUserModel;
import com.cemiterio.Cemiterio.Sector.ISectorRepository;
import com.cemiterio.Cemiterio.User.IUserRepository;
import com.cemiterio.Cemiterio.User.UserModel;
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
    ISectorRepository sectorRepository;
    DeadUserModel deadUserModel;
    UserModel userModel;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var Server=request.getServletPath();
        if(Server.equals("/DeadUser/CreateDeadUser") || Server.equals("/Sector/CreateSector")) {


            var autorizacao = request.getHeader("Authorization");
            filterChain.doFilter(request, response);
            System.out.println("autorizacao: ");
            System.out.println(autorizacao);

            var Autoencode = autorizacao.substring("Basic".length()).trim();
            System.out.println("autorizacao: ");
            System.out.println(Autoencode);

            byte[] Autoencoder = Base64.getDecoder().decode(Autoencode);
            System.out.println("Autorização");
            System.out.println(Autoencoder);

            var autorizacaoinit = new String(Autoencoder);
            System.out.println(autorizacaoinit);
            String[] credenciais = autorizacaoinit.split(":");
            String usuario = credenciais[0];
            String senha = credenciais[1];
            System.out.println(usuario);
            System.out.println(senha);

            var user = this.userRepository.findByUsername(usuario);
            if (user == null) {
                response.sendError(401, "Nao funciona, Usuario inexistente");
            } else {
                var senhaverifica = BCrypt.verifyer().verify(senha.toCharArray(), user.getPassword());
                if (senhaverifica.verified) {
                    request.setAttribute("fkuser", user.getIduser());
                    filterChain.doFilter(request, response);
                    //var deadsector = request.getAttribute("fksector");

                   // var sector = this.sectorRepository.findBysectorname(deadsector.toString());
                  //  if (sector == null) {
                    //    response.sendError(401, "Nao funciona, Setor inexistente");
                   // } else {
                     //       var digger = request.getAttribute("gravedigger");
                       //     var gravedigger = this.userRepository.findByUsername(digger.toString());
                         //   if (gravedigger == null) {
                           //     response.sendError(401, "Nao funciona, Coveiro inexistente");
                            //} else {
                              //  var role = this.userModel.getRole();
                                //if (role == "coveiro") {

                               // } else {
                              //      response.sendError(401, "Usario escolhido não é um coveiro");
                             //   }
                           // }
                        //}

                } else {
                    response.sendError(401, "Senha Incorreta");
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
