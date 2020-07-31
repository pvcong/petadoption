package com.ck.dto;

import javax.persistence.*;

public class PetEntryTypeDTO {

    private Integer petEntryTypeId;
    private PetDTO petDTO;
    private String name;
    public PetEntryTypeDTO() {
    }

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetDTO(PetDTO petDTO) {
        this.petDTO = petDTO;
    }

    public Integer getPetEntryTypeId() {
        return petEntryTypeId;
    }

    public void setPetEntryTypeId(Integer petEntryTypeId) {
        this.petEntryTypeId = petEntryTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
