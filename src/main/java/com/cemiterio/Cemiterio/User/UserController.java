package com.cemiterio.Cemiterio.User;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

import static at.favre.lib.crypto.bcrypt.BCrypt.withDefaults;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private IUserRepository iuserRepository;
    @GetMapping("/info")
    public String info() {
        return "Para mais realizar o cadastro de usuario acesse (/User/NewUser)";
    }
    @PostMapping("/NewUser")
    public ResponseEntity newUser(@RequestBody UserModel userModel, HttpServletRequest request) {
        var created = this.iuserRepository.findByUsername(userModel.getUsername());
        if (created != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existente");
        } else {
            var hahssenha= BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
            userModel.setPassword(hahssenha);
            var salvar = this.iuserRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
        }
    }
    @GetMapping("/List")
    public List<UserModel> listall(){
        List<UserModel> usuariocad = iuserRepository.findAll();
        return usuariocad;
    }
    @PutMapping("/Update")
    public ResponseEntity update(@RequestBody UserModel userModel) {
        var criado= this.iuserRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
    @DeleteMapping("/Delete/{iduser}")
    public void delete(@PathVariable UUID iduser) {
        iuserRepository.deleteById(iduser);
    }


    @GetMapping("/cadastro")
    public ModelAndView Cadastro(){
        ModelAndView mv= new ModelAndView("cadastroUser");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }


    @GetMapping("/atualizaUser")
    public ModelAndView Atualizar(){
      ModelAndView mv= new ModelAndView("userAtualizar");
      return mv;  
    }
    @GetMapping("/pesquisarUser")
    public ModelAndView Pesquisar(){
        ModelAndView mv= new ModelAndView( "pesquisarUser");
        return mv;
    }

    @GetMapping("/excluirUser")
    public ModelAndView Exluir(){
        ModelAndView mv= new ModelAndView("excluirUser");
        return mv;
    }
    
    @GetMapping("/inicio")
    public ModelAndView PaginaInicial(){
        ModelAndView mv= new ModelAndView("index");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }
}
