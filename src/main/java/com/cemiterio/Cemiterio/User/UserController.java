package com.cemiterio.Cemiterio.User;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private IUserRepository iuserRepository;
    @GetMapping("/info")
    public String info() {
        return "Para mais realizar o cadastro de usuario acesse (/User/NewUser)";
    }
//    @PostMapping("/NewUser")
//    public ResponseEntity newUser(@RequestBody UserModel userModel, HttpServletRequest request) {
//        var created = this.iuserRepository.findByUsername(userModel.getUsername());
//        if (created != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existente");
//        } else {
//            var hahssenha= BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
//            userModel.setPassword(hahssenha);
//            var salvar = this.iuserRepository.save(userModel);
//            return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
//        }
//    }
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
        ResponseEntity.status(HttpStatus.CREATED).body("Excluido com sucesso");
    }


    @GetMapping("/cadastro")
    public ModelAndView Cadastro(){
        ModelAndView mv= new ModelAndView("Users/cadastroUser");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }


    @GetMapping("/atualizaUser")
    public ModelAndView Atualizar(){
      ModelAndView mv= new ModelAndView("Users/userAtualizar");
        mv.addObject("UserModel", new UserModel());
      return mv;  
    }
    @GetMapping("/pesquisarUser")
    public ModelAndView Pesquisar(){
        ModelAndView mv= new ModelAndView( "Users/pesquisarUser");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }

    @GetMapping("/excluirUser")
    public ModelAndView Exluir(){
        ModelAndView mv= new ModelAndView("Users/excluirUser");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }
    
    @GetMapping("/inicio")
    public ModelAndView PaginaInicial(){
        ModelAndView mv= new ModelAndView("Users/index");
        mv.addObject("UserModel", new UserModel());
        return mv;
    }
    @PostMapping("/cadastro")
    public String cadastro(UserModel userModel, Model model) {
        System.out.println("Aqui");
        var hahssenha= BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hahssenha);
        var save=iuserRepository.save(userModel);
        model.addAttribute("mensagem", "Setor cadastrado com sucesso!");
        return "Users/cadastroUser";
    }
    @GetMapping("/Listar")
    public String buscarUsuario(@RequestParam("iduser") UUID iduser, Model model) {
        Optional<UserModel> usuario = iuserRepository.findById(iduser);
        if (usuario.isPresent()) {
            model.addAttribute("UserModel", usuario.get());
            System.out.println(usuario.get());
            System.out.println("coisas");
            System.out.println(usuario.get().getEmail());
            usuario.stream().toList().forEach(System.out::println);
            model.addAttribute("username", usuario.get().getUsername());
            model.addAttribute("password", usuario.get().getPassword());
            model.addAttribute("email", usuario.get().getEmail());
            model.addAttribute("phone", usuario.get().getPhone());
            model.addAttribute("address", usuario.get().getAddress());
            model.addAttribute("role", usuario.get().getRole());

        } else {
            model.addAttribute("UserModel", new UserModel());
            model.addAttribute("notFound", true);// ou retornar erro
        }
        return "Users/pesquisarUser";       
        
    }
    @GetMapping("/Listaratualiza")
    public String buscarUsuarioatualizar(@RequestParam("iduser") UUID iduser, Model model) {
        Optional<UserModel> usuario = iuserRepository.findById(iduser);
        if (usuario.isPresent()) {
            model.addAttribute("UserModel", usuario.get());
            System.out.println(usuario.get());
            System.out.println("coisas");
            System.out.println(usuario.get().getEmail());
            usuario.stream().toList().forEach(System.out::println);
            model.addAttribute("username", usuario.get().getUsername());
            model.addAttribute("password", usuario.get().getPassword());
            model.addAttribute("email", usuario.get().getEmail());
            model.addAttribute("phone", usuario.get().getPhone());
            model.addAttribute("address", usuario.get().getAddress());
            model.addAttribute("role", usuario.get().getRole());

        } else {
            model.addAttribute("UserModel", new UserModel());
            model.addAttribute("notFound", true);// ou retornar erro
        }
        return "Users/userAtualizar";       
        
    }
    @GetMapping("/Excluir")
    public String buscarUsuarioExcluir(@RequestParam("iduser") UUID iduser, Model model) {
        Optional<UserModel> usuario = iuserRepository.findById(iduser);
        
        if (usuario.isPresent()) {
            model.addAttribute("UserModel", usuario.get());
        } else {
            model.addAttribute("erro", "Usuário não encontrado.");
            model.addAttribute("UserModel", new UserModel()); // ← adiciona objeto vazio para evitar erro do Thymeleaf
        }
    
        return "Users/excluirUser";       
    }
    // Salva a atualização
    @PostMapping("/atualizar")
    public String atualizarUsuario(@ModelAttribute("usuario") UserModel userModel) {
        var hahssenha= BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hahssenha);   
        iuserRepository.save(userModel);
        return "redirect:/User/Listar?iduser=" + userModel.getiduser();
    }
    
    @PostMapping("/Excluir")
    public String excluirSector(@RequestParam("iduser") UUID iduser, Model model) {
        if (iuserRepository.existsById(iduser)) {
            iuserRepository.deleteById(iduser);
            model.addAttribute("mensagem", "Usuário excluído com sucesso.");
        } else {
            model.addAttribute("erro", "Erro ao excluir usuário.");
        }
        return "Users/excluirUser";
    }
}
