package com.cemiterio.Cemiterio.Sector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ISectorRepository extends JpaRepository<SectorModel, UUID> {
    SectorModel findBysectorname(String sectorname);
}
