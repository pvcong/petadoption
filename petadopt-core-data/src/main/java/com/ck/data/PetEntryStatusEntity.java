package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pet_entry_status")
public class PetEntryStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer petEntryStatusId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "petEntryStatusEntity")
    private List<PetEntity> petEntities;

    public Integer getPetEntryStatusId() {
        return petEntryStatusId;
    }

    public void setPetEntryStatusId(Integer petEntryStatusId) {
        this.petEntryStatusId = petEntryStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }
}
