package com.ck.dto;

import java.util.List;


public class PetStatusTypeDTO {

    private Integer petStatusTypeId;

    private String name;

    private List<PetStatusDTO> petStatusDTOS;

    public PetStatusTypeDTO() {
    }

    public Integer getPetStatusTypeId() {
        return petStatusTypeId;
    }

    public void setPetStatusTypeId(Integer petStatusTypeId) {
        this.petStatusTypeId = petStatusTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PetStatusDTO> getPetStatusDTOS() {
        return petStatusDTOS;
    }

    public void setPetStatusDTOS(List<PetStatusDTO> petStatusDTOS) {
        this.petStatusDTOS = petStatusDTOS;
    }
}
