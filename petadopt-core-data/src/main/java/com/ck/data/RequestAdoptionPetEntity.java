package com.ck.data;

import javax.persistence.*;
import java.lang.reflect.GenericArrayType;
import java.sql.Timestamp;
import java.util.List;

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

    @Column( name = "address")
    private String address;
    @Column( name = "phoneNumber")
    private String phoneNumber;

    @Column( name = "createdDate")
    private Timestamp createdDate;
    @Column( name = "modifiedDate")
    private Timestamp modifiedDate;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "pet_id")
    private PetEntity petEntity;
    @OneToMany( mappedBy = "requestAdoptionPetEntity")
    private List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities;

    public RequestAdoptionPetEntity() {
    }


    public List<RequestAdoptionPetStatusDetailEntity> getRequestAdoptionPetStatusDetailEntities() {
        return requestAdoptionPetStatusDetailEntities;
    }

    public void setRequestAdoptionPetStatusDetailEntities(List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities) {
        this.requestAdoptionPetStatusDetailEntities = requestAdoptionPetStatusDetailEntities;
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

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(PetEntity petEntity) {
        this.petEntity = petEntity;
    }
}
