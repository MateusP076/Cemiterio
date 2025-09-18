package com.cemiterio.Cemiterio.Sector;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "TB_Sector")
@Table(name="TB_Sector")
public class SectorModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id_sector;
    private String sectorname;
    private String sectordescription;
    private String sectorcondition;

    public UUID getId_sector() {
        return id_sector;
    }

    public void setId_sector(UUID id_sector) {
        this.id_sector = id_sector;
    }

    public String getSectorname() {
        return sectorname;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public String getSectorDescription() {
        return sectordescription;
    }

    public void setSectorDescription(String sectorDescription) {
        this.sectordescription = sectorDescription;
    }

    public String getSectorCondition() {
        return sectorcondition;
    }

    public void setSectorCondition(String sectorCondition) {
        this.sectorcondition = sectorCondition;
    }
}
