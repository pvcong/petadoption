package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "pet_id")
    private Integer petId;
    @Column( name = "pet_name")
    private String petName;
    @Column(name = "image")
    private String image;
    @Column( name = "description")
    private String description;
    @Column( name = "weight")
    private Integer weight;
    @Column( name = "age")
    private Integer age;
    @Column( name = "address")
    private String address;
    @Column( name = "color")
    private String color;
    @Column( name = "breed")
    private String breed;
    @Column( name = "status")
    private String status;
    @Column( name = "gender")
    private String gender;
    @Column( name = "created_date")
    private Timestamp createdDate;
    @Column( name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "user_id")
    private UserEntity userEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "pet_type_id")
    private PetTypeEntity petTypeEntity;
    @OneToOne(mappedBy = "petEntity",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PetAboutEntity petAboutEntity;
    @OneToMany( mappedBy = "petEntity")
    private List<RequestAdoptionPetEntity> requestAdoptionPetEntities;

    public PetEntity() {
    }

    public PetEntity(Integer petId, String petName, String image, String description, Integer weight, Integer age, String address, String color, String breed, String status, String gender, Timestamp createdDate, Timestamp modifiedDate, UserEntity userEntity, PetTypeEntity petTypeEntity, PetAboutEntity petAboutEntity, List<RequestAdoptionPetEntity> requestAdoptionPetEntities) {
        this.petId = petId;
        this.petName = petName;
        this.image = image;
        this.description = description;
        this.weight = weight;
        this.age = age;
        this.address = address;
        this.color = color;
        this.breed = breed;
        this.status = status;
        this.gender = gender;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userEntity = userEntity;
        this.petTypeEntity = petTypeEntity;
        this.petAboutEntity = petAboutEntity;
        this.requestAdoptionPetEntities = requestAdoptionPetEntities;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RequestAdoptionPetEntity> getRequestAdoptionPetEntities() {
        return requestAdoptionPetEntities;
    }

    public void setRequestAdoptionPetEntities(List<RequestAdoptionPetEntity> requestAdoptionPetEntities) {
        this.requestAdoptionPetEntities = requestAdoptionPetEntities;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String pet_name) {
        this.petName = pet_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PetTypeEntity getPetTypeEntity() {
        return petTypeEntity;
    }

    public void setPetTypeEntity(PetTypeEntity petTypeEntity) {
        this.petTypeEntity = petTypeEntity;
    }

    public PetAboutEntity getPetAboutEntity() {
        return petAboutEntity;
    }

    public void setPetAboutEntity(PetAboutEntity petAboutEntity) {
        this.petAboutEntity = petAboutEntity;
    }
}
