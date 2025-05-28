package com.cemiterio.Cemiterio.Sector;

import com.cemiterio.Cemiterio.Sector.*;
import com.cemiterio.Cemiterio.Sector.ISectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/sector")
public class SectorController {

    @Autowired
    private ISectorRepository sectorRepository;

    @Controller
    @RequestMapping("/sector")
public class HomeController {
    
    @GetMapping("/novo")
    public ModelAndView cadastrosector(){
    ModelAndView mv=new ModelAndView("sector/createsector");
    mv.addObject("sector", new SectorModel());
    return mv;   
    }
    @GetMapping("/pesquisa")
    public ModelAndView pesquisacdastro(){
        ModelAndView mv= new ModelAndView("sector/pesquisarsector");
        mv.addObject("sector", new SectorModel());
        return mv;
    }
    @GetMapping("/Atualiza")
    public ModelAndView atualizasector(){
        ModelAndView mv= new ModelAndView("sector/atualizarsector");
        mv.addObject("sector", new SectorModel());
        return mv;
    }
    @GetMapping("Exluirsector")
    public ModelAndView excluirsector(){
        ModelAndView mv= new ModelAndView("sector/excluirsector");
        mv.addObject("sector", new SectorModel());
        return mv;
    }
    @GetMapping("/")
    public String home() {
        return "Telainiciosector"; 
    }
}

    // Página de cadastro
    // @GetMapping("/novo")
    // public String novoSectorForm(Model model) {
    //     model.addAttribute("sector", new SectorModel());
    //     return "sector/createsector";
    // }

    // Salvar novo setor
    @PostMapping("/criar")
    public String criarSector(SectorModel sector, Model model) {
        sectorRepository.save(sector);
        model.addAttribute("mensagem", "Setor cadastrado com sucesso!");
        return "sector/createsector";
    }

    // Página de pesquisa
    @GetMapping("/buscar")
    public String buscarSector(@RequestParam("id_sector") UUID id_sector, Model model) {
        Optional<SectorModel> sector = sectorRepository.findById(id_sector);
        if (sector.isPresent()) {
            model.addAttribute("sector", sector.get());
        } else {
            model.addAttribute("erro", "Setor não encontrado.");
        }
        return "sector/pesquisarsector";
    }

    // Página para buscar e editar
    @GetMapping("/editar")
    public String editarSectorForm(@RequestParam("id_sector") UUID id, Model model) {
        Optional<SectorModel> sector = sectorRepository.findById(id);
        if (sector.isPresent()) {
            model.addAttribute("sector", sector.get());
        } else {
            model.addAttribute("erro", "Setor não encontrado.");
        }
        return "sector/atualizarsector";
    }

    // Atualizar setor
    @PostMapping("/atualizar")
    public String atualizarSector(SectorModel sector, Model model) {
        if (sectorRepository.existsById(sector.getId_sector())) {
            sectorRepository.save(sector);
            model.addAttribute("mensagem", "Setor atualizado com sucesso!");
        } else {
            model.addAttribute("erro", "Setor não encontrado para atualizar.");
        }
        return "sector/atualizarsector";
    }

    // Página para buscar setor a excluir
    @GetMapping("/excluir")
    public String excluirForm(@RequestParam("id_sector") UUID id, Model model) {
        Optional<SectorModel> sector = sectorRepository.findById(id);
        if (sector.isPresent()) {
            model.addAttribute("sector", sector.get());
        } else {
            model.addAttribute("erro", "Setor não encontrado.");
        }
        return "sector/excluirsector";
    }

    // Executar exclusão
    @PostMapping("/excluir")
    public String excluirSector(@RequestParam("id_sector") UUID id, Model model) {
        if (sectorRepository.existsById(id)) {
            sectorRepository.deleteById(id);
            model.addAttribute("mensagem", "Setor excluído com sucesso.");
        } else {
            model.addAttribute("erro", "Erro ao excluir setor.");
        }
        return "sector/excluirsector";
    }
}
