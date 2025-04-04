package com.cemiterio.Cemiterio.DeadUser;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cemiterio.Cemiterio.User.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/DeadUser")
public class DeadUserController {
    @Autowired
    private IDeadUserRepository iDeadUserRepository;
    @GetMapping("/Text")
    public String Text() {
        return "Sentimos muito a sua perda porem esperamos pode ajudar com tudo necessário";
    }

    @PostMapping("/CreateDeadUser")
    public ResponseEntity   CreateDeadUser(@RequestBody DeadUserModel deadUserModel, HttpServletRequest request) {
        var created = this.iDeadUserRepository.findByName(deadUserModel.getName());
        if (created != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome ja existente");
        } else {
//            var hahsdescription= BCrypt.withDefaults().hashToString(12, deadUserModel.getDescription().toCharArray());
//            deadUserModel.setDescription(hahsdescription);

            var fkuser=request.getSession().getAttribute("user");
            deadUserModel.setFkuser((UUID) fkuser);
            var salvar = this.iDeadUserRepository.save(deadUserModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
        }


    }
    @GetMapping("/ListDeadUser")
    public List<DeadUserModel> listDeadUser() {
        return this.iDeadUserRepository.findAll();
    }

    @PutMapping("/Update")
    public ResponseEntity update(@RequestBody DeadUserModel deadUserModel) {
        var criado= this.iDeadUserRepository.save(deadUserModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }
    @DeleteMapping("/Delete/{iduser}")
    public void delete(@PathVariable UUID iduser) {
        iDeadUserRepository.deleteById(iduser);
    }
}
