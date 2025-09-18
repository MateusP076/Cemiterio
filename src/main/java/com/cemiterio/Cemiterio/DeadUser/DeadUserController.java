package com.cemiterio.Cemiterio.DeadUser;

import com.cemiterio.Cemiterio.DeadUser.DeadUserModel;
import com.cemiterio.Cemiterio.DeadUser.IDeadUserRepository;
import com.cemiterio.Cemiterio.Sector.ISectorRepository;
import com.cemiterio.Cemiterio.User.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/deaduser")
public class DeadUserController {

    @Autowired
    private IDeadUserRepository deadUserRepository;
    @Autowired
    private ISectorRepository sectorRepository;

    @Autowired
    private IUserRepository userRepository;
    @GetMapping("/cadastrodead")
    public ModelAndView cadastrodead(Model model){
        ModelAndView mv = new ModelAndView("Deaduser/create_deaduser");
        model.addAttribute("setores", sectorRepository.findAll());
        model.addAttribute("usuarios", userRepository.findAll());
        mv.addObject("deadUser", new DeadUserModel());
        return mv;
    }
    @GetMapping("/pesquisardead")
    public ModelAndView pesquisadead(){
        ModelAndView mv= new ModelAndView("Deaduser/search_deaduser");
        mv.addObject("deadUser", new DeadUserModel());
        return mv;
    }
    @GetMapping("/atualizadead")
    public ModelAndView atualizadead(){
        ModelAndView mv= new ModelAndView("Deaduser/update_deaduser");
        mv.addObject("deadUser", new DeadUserModel());
        return mv;
    }
    @GetMapping("/excluirdead")
    public ModelAndView excluirdead(){
        ModelAndView mv=new ModelAndView("Deaduser/delete_deaduser");
        mv.addObject("deadUser", new DeadUserModel());
        return mv;
    }
    // Formulário de criação
    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("deadUser", new DeadUserModel());
        return "deaduser/create_deaduser";
    }

    // Salvar novo DeadUser
    @PostMapping("/criar")
    public String criar(@ModelAttribute DeadUserModel deadUser, Model model) {
        deadUserRepository.save(deadUser);
        model.addAttribute("mensagem", "Registro salvo com sucesso!");
        model.addAttribute("deadUser", new DeadUserModel());
        return "redirect:/deaduser/form";
    }
    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("deadUser", new DeadUserModel());
        model.addAttribute("setores", sectorRepository.findAll());
        System.out.println("setores");
        model.addAttribute("usuarios", userRepository.findAll());
        System.out.println("usuarios");
        return "Deaduser/create_deaduser"; // nome do HTML
    }


    // Buscar DeadUser por ID
    @GetMapping("/buscar")
    public String buscar(@RequestParam(name = "idDeadUser", required = false) UUID id, Model model) {
        if (id != null) {
            Optional<DeadUserModel> resultado = deadUserRepository.findById(id);
            if (resultado.isPresent()) {
                model.addAttribute("deadUser", resultado.get());
            } else {
                model.addAttribute("erro", "Falecido não encontrado.");
            }
        }
        return "deaduser/search_deaduser";
    }

    // Editar - carregar formulário
    @GetMapping("/editar")
    public String editar(@RequestParam UUID idDeadUser, Model model) {
        Optional<DeadUserModel> resultado = deadUserRepository.findById(idDeadUser);
        if (resultado.isPresent()) {
            model.addAttribute("deadUser", resultado.get());
            return "deaduser/update_deaduser";
        } else {
            model.addAttribute("erro", "Falecido não encontrado.");
            return "deaduser/search-deaduser";
        }
    }

    // Atualizar DeadUser
    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute DeadUserModel deadUser, Model model) {
        deadUserRepository.save(deadUser); // save também atualiza se já existir ID
        model.addAttribute("mensagem", "Dados atualizados com sucesso.");
        model.addAttribute("deadUser", deadUser);
        return "deaduser/update_deaduser";
    }

    // Excluir - confirmar
    @GetMapping("/excluir")
    public String excluirForm(@RequestParam UUID idDeadUser, Model model) {
        Optional<DeadUserModel> resultado = deadUserRepository.findById(idDeadUser);
        if (resultado.isPresent()) {
            model.addAttribute("deadUser", resultado.get());
            return "deaduser/delete_deaduser";
        } else {
            model.addAttribute("erro", "Falecido não encontrado.");
            return "deaduser/search_deaduser";
        }
    }

    // Excluir - executar
    @PostMapping("/excluir")
    public String excluir(@ModelAttribute DeadUserModel deadUser, Model model) {
        deadUserRepository.deleteById(deadUser.getIdDeadUser());
        model.addAttribute("mensagem", "Falecido excluído com sucesso.");
        return "deaduser/delete_deaduser";
    }
}
