package com.cemiterio.Cemiterio.Sector;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cemiterio.Cemiterio.DeadUser.DeadUserModel;
import com.cemiterio.Cemiterio.User.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Sector")
public class SectorController {
    @Autowired
    ISectorRepository sectorRepository;

    @PostMapping("/CreateSector")
    public ResponseEntity CreateDeadUser(@RequestBody SectorModel sectorModel, HttpServletRequest request) {
        var created = this.sectorRepository.findBysectorname(sectorModel.getSectorname());
        if (created != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor já foi criado");
        } else {
            var salvar = this.sectorRepository.save(sectorModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
        }
    }
    @GetMapping("/ListSector")
    public List<SectorModel> listSector() {
        return this.sectorRepository.findAll();
    }
}

