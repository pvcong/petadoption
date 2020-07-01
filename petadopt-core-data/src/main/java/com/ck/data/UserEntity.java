package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "user")
public class UserEntity {
    @Id
    @Column( name = "user_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Integer userId;
    @Column( name = "user_name",unique = true)
    private String userName;
    @Column( name = "password")
    private String password;
    @Column( name = "full_name")
    private String fullName;
    @Column( name =  "phone_number",unique = true)
    private String phoneNumber;
    @Column( name = "email",unique = true)
    private String email;
    @Column( name = "gender")
    private String gender;
    @Column( name = "birthday")
    private Timestamp birthday;
    @Column( name = "avatar")
    private String avatar;
    @Column( name = "createdDate")
    private Timestamp createdDate;
    @Column( name = "modifiedDate")
    private Timestamp modifiedDate;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "role_id")
    private RoleEntity roleEntity;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<PetEntity> petEntities;
    @OneToMany( mappedBy = "userEntity")
    private List<NewsEntity> newsEntities;
    public UserEntity() {
    }

    public UserEntity(String userName, String password, String fullName, String phoneNumber, String email, String gender, Timestamp birthday, String avatar, Timestamp createdDate, Timestamp modifiedDate, RoleEntity roleEntity, List<PetEntity> petEntities) {
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
        this.roleEntity = roleEntity;
        this.petEntities = petEntities;
    }

    public List<NewsEntity> getNewsEntities() {
        return newsEntities;
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
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

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }
}
