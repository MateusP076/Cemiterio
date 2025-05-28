package com.cemiterio.Cemiterio.DeadUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.cemiterio.Cemiterio.Sector.SectorModel;
import com.cemiterio.Cemiterio.User.UserModel;

import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Entity(name = "Tb_DeadUser")
public class DeadUserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID idDeadUser;
    private String name;
    private String description;
    private String gravedigger;
    @ManyToOne
    @JoinColumn(name = "fksector", nullable = false)
    private SectorModel fksector;

    @ManyToOne
    @JoinColumn(name = "fkuser", nullable = false)
    private UserModel fkuser;


    public UUID getIdDeadUser() {
        return idDeadUser;
    }

    public void setIdDeadUser(UUID idDeadUser) {
        this.idDeadUser = idDeadUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SectorModel getFksector() {
        return fksector;
    }

    public void setFksector(SectorModel fksector) {
        this.fksector = fksector;
    }

    public UserModel getFkuser() {
        return fkuser;
    }

    public void setFkuser(UserModel fkuser) {
        this.fkuser = fkuser;
    }

    public String getGravedigger() {
        return gravedigger;
    }

    public void setGravedigger(String gravedigger) {
        this.gravedigger = gravedigger;
    }
}
