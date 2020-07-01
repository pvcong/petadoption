package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;


public class RequestAdoptionPetDTO {

    private Integer requesrAdoptionPerId;

    private String content;

    private String fullName;

    private String currentJob;

    private String address;

    private String phoneNumber;

    private String status;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private PetDTO petDTO;

    public RequestAdoptionPetDTO() {
    }

    public RequestAdoptionPetDTO(Integer requesrAdoptionPerId, String content, String fullName, String currentJob, String address, String phoneNumber, String status, Timestamp createdDate, Timestamp modifiedDate, PetDTO petDTO) {
        this.requesrAdoptionPerId = requesrAdoptionPerId;
        this.content = content;
        this.fullName = fullName;
        this.currentJob = currentJob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.petDTO = petDTO;
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

    public String getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
