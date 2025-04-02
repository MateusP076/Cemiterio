package com.cemiterio.Cemiterio.DeadUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "Tb_DeadUser")
public class DeadUserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID idDeadUser;
    private String name;
    private String description;
    private String gravedigger;
    private String fksector;
    private String fkuser;

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

    public String getFksector() {
        return fksector;
    }

    public void setFksector(String fksector) {
        this.fksector = fksector;
    }

    public String getFkuser() {
        return fkuser;
    }

    public void setFkuser(String fkuser) {
        this.fkuser = fkuser;
    }

    public String getGravedigger() {
        return gravedigger;
    }

    public void setGravedigger(String gravedigger) {
        this.gravedigger = gravedigger;
    }
}
