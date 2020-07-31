package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pet_entry_type")
public class PetEntryTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer petEntryTypeId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "petEntryTypeEntity")
    private List<PetEntity> petEntities;
    public PetEntryTypeEntity() {
    }

    public Integer getPetEntryTypeId() {
        return petEntryTypeId;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }

    public void setPetEntryTypeId(Integer petEntryTypeId) {
        this.petEntryTypeId = petEntryTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
