package com.ck.data;

import javax.persistence.*;
import java.lang.reflect.GenericArrayType;
import java.sql.Timestamp;

@Entity
@Table( name = "request_adopt_pet")
public class RequestAdoptionPetEntity {
    @Id
    @Column( name = "request_adopt_pet_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer requesrAdoptionPerId;
    @Column( name = "content")
    private String content;
    @Column( name = "fullName")
    private String fullName;
    @Column( name = "currentJob")
    private String currentJob;
    @Column( name = "address")
    private String address;
    @Column( name = "phoneNumber")
    private String phoneNumber;
    @Column( name = "status")
    private String status;
    @Column( name = "createdDate")
    private Timestamp createdDate;
    @Column( name = "modifiedDate")
    private Timestamp modifiedDate;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "pet_id")
    private PetEntity petEntity;

    public RequestAdoptionPetEntity() {
    }

    public RequestAdoptionPetEntity(String content, String fullName, String currentJob, String address, String phoneNumber, String status, Timestamp createdDate, Timestamp modifiedDate, PetEntity petEntity) {
        this.content = content;
        this.fullName = fullName;
        this.currentJob = currentJob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.petEntity = petEntity;
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

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(PetEntity petEntity) {
        this.petEntity = petEntity;
    }
}
