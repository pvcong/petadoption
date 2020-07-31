package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class RequestAdoptionPetDTO {

    private Integer requesrAdoptionPerId;

    private String content;

    private String fullName;


    private String address;

    private String phoneNumber;



    private Timestamp createdDate;

    private Timestamp modifiedDate;
    private List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS;
    private List<RequestAdoptionPetAppointmentDTO> requestAdoptionPetAppointmentDTOS;

    private PetDTO petDTO;
    public RequestAdoptionPetDTO() {
    }

    public List<RequestAdoptionPetAppointmentDTO> getRequestAdoptionPetAppointmentDTOS() {
        return requestAdoptionPetAppointmentDTOS;
    }

    public void setRequestAdoptionPetAppointmentDTOS(List<RequestAdoptionPetAppointmentDTO> requestAdoptionPetAppointmentDTOS) {
        this.requestAdoptionPetAppointmentDTOS = requestAdoptionPetAppointmentDTOS;
    }

    public List<RequestAdoptionPetStatusDetailDTO> getRequestAdoptionPetStatusDetailDTOS() {
        return requestAdoptionPetStatusDetailDTOS;
    }

    public void setRequestAdoptionPetStatusDetailDTOS(List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS) {
        this.requestAdoptionPetStatusDetailDTOS = requestAdoptionPetStatusDetailDTOS;
    }

    public Integer getRequesrAdoptionPerId() {
        return requesrAdoptionPerId;
    }

    public void setRequesrAdoptionPerId(Integer requesrAdoptionPerId) {
        this.requesrAdoptionPerId = requesrAdoptionPerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public PetDTO getPetDTO() {
        return petDTO;
    }

    public void setPetDTO(PetDTO petDTO) {
        this.petDTO = petDTO;
    }
}
