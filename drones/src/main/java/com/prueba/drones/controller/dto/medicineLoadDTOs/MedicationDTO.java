package com.prueba.drones.controller.dto.medicineLoadDTOs;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class MedicationDTO {

    @NotBlank(message = "Code is mandatory")
    @Pattern(regexp = "[A-Z0-9_]+", message = "Code must only contain upper case letters, numbers and underscores")
    private String code;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "[A-Za-z0-9-_]+", message = "Name must only contain letters, numbers, dashes and underscores")
    private String name;

    @NotNull(message = "Weight is mandatory")
    private Double weight;

    private String image;

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
