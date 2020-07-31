package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "foster_pet")
public class FosterPetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer fosterPetId;
    @Column(name = "content")
    private String content;
    @Column(name = "foster_date")
    private Timestamp fosterDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fosterPetEntity")
    private List<PetEntity> petEntities;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;
    public FosterPetEntity() {
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public Integer getFosterPetId() {
        return fosterPetId;
    }

    public void setFosterPetId(Integer fosterPetId) {
        this.fosterPetId = fosterPetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getFosterDate() {
        return fosterDate;
    }

    public void setFosterDate(Timestamp fosterDate) {
        this.fosterDate = fosterDate;
    }


}
