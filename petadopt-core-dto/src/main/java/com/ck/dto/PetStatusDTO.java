package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;


public class PetStatusDTO {

    private Integer petStatusId;
    private Timestamp date;
    private String content;
    private PetDTO petDTO;
    private PetStatusTypeDTO petStatusTypeDTO;

    public PetStatusDTO() {
    }

    public Integer getPetStatusId() {
        return petStatusId;
    }

    public void setPetStatusId(Integer petStatusId) {
        this.petStatusId = petStatusId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetDTO(PetDTO petDTO) {
        this.petDTO = petDTO;
    }

    public PetStatusTypeDTO getPetStatusTypeDTO() {
        return petStatusTypeDTO;
    }

    public void setPetStatusTypeDTO(PetStatusTypeDTO petStatusTypeDTO) {
        this.petStatusTypeDTO = petStatusTypeDTO;
    }
}
