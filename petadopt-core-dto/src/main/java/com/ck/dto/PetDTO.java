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
    private RescueOrderDTO rescueOrderDTO;
    private List<RequestAdoptionPetDTO> requestAdoptionPetDTOS;
    private FosterPetDTO fosterPetDTO;
    private PetEntryStatusDTO petEntryStatusDTO;
    private PetEntryTypeDTO petEntryTypeDTO;
    private Timestamp entryDate;
    private List<PetStatusDTO> petStatusDTOS;
    public PetDTO() {
    }


    public List<PetStatusDTO> getPetStatusDTOS() {
        return petStatusDTOS;
    }

    public void setPetStatusDTOS(List<PetStatusDTO> petStatusDTOS) {
        this.petStatusDTOS = petStatusDTOS;
    }

    public PetEntryStatusDTO getPetEntryStatusDTO() {
        return petEntryStatusDTO;
    }

    public void setPetEntryStatusDTO(PetEntryStatusDTO petEntryStatusDTO) {
        this.petEntryStatusDTO = petEntryStatusDTO;
    }

    public PetEntryTypeDTO getPetEntryTypeDTO() {
        return petEntryTypeDTO;
    }

    public void setPetEntryTypeDTO(PetEntryTypeDTO petEntryTypeDTO) {
        this.petEntryTypeDTO = petEntryTypeDTO;
    }

    public Timestamp getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Timestamp entryDate) {
        this.entryDate = entryDate;
    }

    public FosterPetDTO getFosterPetDTO() {
        return fosterPetDTO;
    }

    public void setFosterPetDTO(FosterPetDTO fosterPetDTO) {
        this.fosterPetDTO = fosterPetDTO;
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

    public RescueOrderDTO getRescueOrderDTO() {
        return rescueOrderDTO;
    }

    public void setRescueOrderDTO(RescueOrderDTO rescueOrderDTO) {
        this.rescueOrderDTO = rescueOrderDTO;
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
