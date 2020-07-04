package com.ck.dto;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class PetDTO {

    private Integer petId;
    private String petName;
    private String image;

    private String description;

    private Integer weight;

    private Integer age;

    private String address;

    private String color;

    private String breed;

    private String gender;
    private String status;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private UserDTO userDTO;

    private PetTypeDTO petTypeDTO;

    private PetAboutDTO petAboutDTO;

    private List<RequestAdoptionPetDTO> requestAdoptionPetDTOS;

    public PetDTO() {
    }

    public PetDTO(Integer petId, String petName, String image, String description, Integer weight, Integer age, String address, String color, String breed, String gender, String status, Timestamp createdDate, Timestamp modifiedDate, UserDTO userDTO, PetTypeDTO petTypeDTO, PetAboutDTO petAboutDTO, List<RequestAdoptionPetDTO> requestAdoptionPetDTOS) {
        this.petId = petId;
        this.petName = petName;
        this.image = image;
        this.description = description;
        this.weight = weight;
        this.age = age;
        this.address = address;
        this.color = color;
        this.breed = breed;
        this.gender = gender;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userDTO = userDTO;
        this.petTypeDTO = petTypeDTO;
        this.petAboutDTO = petAboutDTO;
        this.requestAdoptionPetDTOS = requestAdoptionPetDTOS;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<RequestAdoptionPetDTO> getRequestAdoptionPetDTOS() {
        return requestAdoptionPetDTOS;
    }

    public void setRequestAdoptionPetDTOS(List<RequestAdoptionPetDTO> requestAdoptionPetDTOS) {
        this.requestAdoptionPetDTOS = requestAdoptionPetDTOS;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public PetTypeDTO getPetTypeDTO() {
        return petTypeDTO;
    }

    public void setPetTypeDTO(PetTypeDTO petTypeDTO) {
        this.petTypeDTO = petTypeDTO;
    }

    public PetAboutDTO getPetAboutDTO() {
        return petAboutDTO;
    }

    public void setPetAboutDTO(PetAboutDTO petAboutDTO) {
        this.petAboutDTO = petAboutDTO;
    }
}
