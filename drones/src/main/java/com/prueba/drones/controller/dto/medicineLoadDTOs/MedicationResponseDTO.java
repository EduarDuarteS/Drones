package com.prueba.drones.controller.dto.medicineLoadDTOs;

public class MedicationResponseDTO {

    private String code;
    private String name;
    private Double weight;
    private String image;

    public MedicationResponseDTO(String code, String name, Double weight, String image) {
        this.code = code;
        this.name = name;
        this.weight = weight;
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
