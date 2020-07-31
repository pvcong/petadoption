package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class RescueOrderDTO {

    private Integer rescueOrderId;

    private String content;
    private String region;
    private Timestamp dateRescue;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private EmployeeDTO employeeDTO;
    private List<PetDTO> petDTOS;

    public RescueOrderDTO() {
    }

    public List<PetDTO> getPetDTOS() {
        return petDTOS;
    }

    public void setPetDTOS(List<PetDTO> petDTOS) {
        this.petDTOS = petDTOS;
    }

    public Integer getRescueOrderId() {
        return rescueOrderId;
    }

    public void setRescueOrderId(Integer rescueOrderId) {
        this.rescueOrderId = rescueOrderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Timestamp getDateRescue() {
        return dateRescue;
    }

    public void setDateRescue(Timestamp dateRescue) {
        this.dateRescue = dateRescue;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public EmployeeDTO getEmployeeDTO() {
        return employeeDTO;
    }

    public void setEmployeeDTO(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
}
