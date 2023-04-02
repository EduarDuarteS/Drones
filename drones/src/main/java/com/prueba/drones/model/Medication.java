package com.prueba.drones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medication_code")
    private String code;

    @Column(name = "medication_name")
    private String name;

    @Column(name = "medication_weight")
    private double weight;

    @Lob
    @Column(name = "medication_image")
    private byte[] image;

    public Medication() {
    }

    public Medication(String code, String name, double weight, byte[] image) {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
