package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;
import java.util.List;


public class UserDTO {

    private  Integer userId;

    private String userName;

    private String password;

    private String fullName;
    private String phoneNumber;
    private String email;

    private String gender;

    private Timestamp birthday;

    private String avatar;
    private Timestamp createdDate;
    private Timestamp modifiedDate;


    private RoleDTO roleDTO;
    private List<PetDTO> petDTOs;
    private List<NewsDTO> newsDTOS;
    public UserDTO() {
    }

    public UserDTO(Integer userId, String userName, String password, String fullName, String phoneNumber, String email, String gender, Timestamp birthday, String avatar, Timestamp createdDate, Timestamp modifiedDate, RoleDTO roleDTO, List<PetDTO> petDTOs) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.birthday = birthday;
        this.avatar = avatar;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.roleDTO = roleDTO;
        this.petDTOs = petDTOs;
    }

    public List<NewsDTO> getNewsDTOS() {
        return newsDTOS;
    }

    public void setNewsDTOS(List<NewsDTO> newsDTOS) {
        this.newsDTOS = newsDTOS;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public List<PetDTO> getPetDTOs() {
        return petDTOs;
    }

    public void setPetDTOs(List<PetDTO> petDTOs) {
        this.petDTOs = petDTOs;
    }
}
