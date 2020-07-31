package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer employeeId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth_day")
    private Timestamp birthday;
    @Column(name = "address")
    private String address;
    @Column(name = "region")
    private String region;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentEntity;
    @OneToMany(mappedBy = "employeeEntity")
    private List<RescueOrderEntity> rescueOrderEntities;
    @OneToMany(mappedBy = "employeeEntity")
    private List<FosterPetEntity> fosterPetEntities;
    @Column(name = "join_date")
    private Timestamp joinDate;
    public EmployeeEntity() {
    }

    public List<FosterPetEntity> getFosterPetEntities() {
        return fosterPetEntities;
    }

    public void setFosterPetEntities(List<FosterPetEntity> fosterPetEntities) {
        this.fosterPetEntities = fosterPetEntities;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
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

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public List<RescueOrderEntity> getRescueOrderEntities() {
        return rescueOrderEntities;
    }

    public void setRescueOrderEntities(List<RescueOrderEntity> rescueOrderEntities) {
        this.rescueOrderEntities = rescueOrderEntities;
    }
}
