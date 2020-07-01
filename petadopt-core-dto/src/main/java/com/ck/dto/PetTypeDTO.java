package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;


public class PetTypeDTO {

    private Integer petTypeId;


    private String petTypeName;

    private List<PetDTO> petEntities;

    public PetTypeDTO() {
    }

    public PetTypeDTO(Integer petTypeId, String petTypeName, List<PetDTO> petEntities) {
        this.petTypeId = petTypeId;
        this.petTypeName = petTypeName;
        this.petEntities = petEntities;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }

    public List<PetDTO> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetDTO> petEntities) {
        this.petEntities = petEntities;
    }
}
