package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;


public class PetAboutDTO {

    private Integer petId;

    private String sterilization;

    private String inject_rabies;

    private String vaccination;

    private String friendly_people;

    private String friendly_cat;

    private String friendly_dog;

    private String toilet_place;

    private PetDTO petDTO;

    public PetAboutDTO() {
    }

    public PetAboutDTO(Integer petId, String sterilization, String inject_rabies, String vaccination, String friendly_people, String friendly_cat, String friendly_dog, String toilet_place, PetDTO petDTO) {
        this.petId = petId;
        this.sterilization = sterilization;
        this.inject_rabies = inject_rabies;
        this.vaccination = vaccination;
        this.friendly_people = friendly_people;
        this.friendly_cat = friendly_cat;
        this.friendly_dog = friendly_dog;
        this.toilet_place = toilet_place;
        this.petDTO = petDTO;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getSterilization() {
        return sterilization;
    }

    public void setSterilization(String sterilization) {
        this.sterilization = sterilization;
    }

    public String getInject_rabies() {
        return inject_rabies;
    }

    public void setInject_rabies(String inject_rabies) {
        this.inject_rabies = inject_rabies;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getFriendly_people() {
        return friendly_people;
    }

    public void setFriendly_people(String friendly_people) {
        this.friendly_people = friendly_people;
    }

    public String getFriendly_cat() {
        return friendly_cat;
    }

    public void setFriendly_cat(String friendly_cat) {
        this.friendly_cat = friendly_cat;
    }

    public String getFriendly_dog() {
        return friendly_dog;
    }

    public void setFriendly_dog(String friendly_dog) {
        this.friendly_dog = friendly_dog;
    }

    public String getToilet_place() {
        return toilet_place;
    }

    public void setToilet_place(String toilet_place) {
        this.toilet_place = toilet_place;
    }

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetDTO(PetDTO petDTO) {
        this.petDTO = petDTO;
    }
}
