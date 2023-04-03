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
        entity.setImage(Base64.getDecoder().decode(dto.getImage()));
        return entity;
    }
}
