package com.prueba.drones.mappers;


import java.util.Base64;

import com.prueba.drones.controller.dto.medicineLoadDTOs.MedicationDTO;
import com.prueba.drones.model.Medication;

public class MedicationMapper {

    public static Medication mapToEntity(MedicationDTO dto) {
        Medication entity = new Medication();
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setWeight(dto.getWeight());
        if(dto.getImage() != null && !dto.getImage().isEmpty()) {
            entity.setImage(Base64.getDecoder().decode(dto.getImage()));
        } else {
            entity.setImage(null);
        }
        return entity;
    }

    public static MedicationDTO mapToDTO(Medication entity) {
        MedicationDTO dto = new MedicationDTO();
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setWeight(entity.getWeight());
        if(entity.getImage() != null) {
            dto.setImage(Base64.getEncoder().encodeToString(entity.getImage()));
        } else {
            dto.setImage(null);
        }
        return dto;
    }
    
}
