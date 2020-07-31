package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class EmployeeDTO {

    private Integer employeeId;

    private String fullName;

    private String firstName;

    private String lastName;

    private String gender;

    private Timestamp birthday;

    private String address;
    private String region;
    private String phoneNumber;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private DepartmentDTO departmentDTO;
    private List<RescueOrderDTO> rescueOrderEntities;
    private List<FosterPetDTO> fosterPetDTOS;
    private Timestamp joinDate;

    public EmployeeDTO() {
    }

    public List<FosterPetDTO> getFosterPetDTOS() {
        return fosterPetDTOS;
    }

    public void setFosterPetDTOS(List<FosterPetDTO> fosterPetDTOS) {
        this.fosterPetDTOS = fosterPetDTOS;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public DepartmentDTO getDepartmentDTO() {
        return departmentDTO;
    }

    public void setDepartmentDTO(DepartmentDTO departmentDTO) {
        this.departmentDTO = departmentDTO;
    }

    public List<RescueOrderDTO> getRescueOrderEntities() {
        return rescueOrderEntities;
    }

    public void setRescueOrderEntities(List<RescueOrderDTO> rescueOrderEntities) {
        this.rescueOrderEntities = rescueOrderEntities;
    }
}
