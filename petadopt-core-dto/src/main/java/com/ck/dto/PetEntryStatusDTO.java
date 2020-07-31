package com.ck.dto;

import javax.persistence.*;


public class PetEntryStatusDTO {

    private Integer petEntryStatusId;
    private String name;
    private PetDTO petDTO;

    public PetEntryStatusDTO() {
    }

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetDTO(PetDTO petDTO) {
        this.petDTO = petDTO;
    }

    public Integer getPetEntryStatusId() {
        return petEntryStatusId;
    }

    public void setPetEntryStatusId(Integer petEntryStatusId) {
        this.petEntryStatusId = petEntryStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
