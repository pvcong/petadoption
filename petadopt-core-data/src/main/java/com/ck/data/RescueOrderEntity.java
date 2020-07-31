package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "rescue_order")
public class RescueOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer rescueOrderId;
    @Column(name = "content")
    private String content;
    @Column(name = "region")
    private String region;
    @Column(name = "date_rescue")
    private Timestamp dateRescue;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;
    @OneToMany(mappedBy = "rescueOrderEntity")
    private List<PetEntity> petEntities;

    public RescueOrderEntity() {
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }

    public Integer getRescueOrderId() {
        return rescueOrderId;
    }

    public void setRescueOrderId(Integer rescueOrderId) {
        this.rescueOrderId = rescueOrderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Timestamp getDateRescue() {
        return dateRescue;
    }

    public void setDateRescue(Timestamp dateRescue) {
        this.dateRescue = dateRescue;
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

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }
}
