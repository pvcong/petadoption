package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class FosterPetDTO {


    private Integer fosterPetId;

    private String content;

    private Timestamp fosterDate;

    private List<PetDTO> petDTOS;
    private EmployeeDTO employeeDTO;

    public FosterPetDTO() {
    }

    public List<PetDTO> getPetDTOS() {
        return petDTOS;
    }

    public void setPetDTOS(List<PetDTO> petDTOS) {
        this.petDTOS = petDTOS;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }

    public Integer getFosterPetId() {
        return fosterPetId;
    }

    public void setFosterPetId(Integer fosterPetId) {
        this.fosterPetId = fosterPetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getFosterDate() {
        return fosterDate;
    }

    public void setFosterDate(Timestamp fosterDate) {
        this.fosterDate = fosterDate;
    }


}
