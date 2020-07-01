package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "pet_type")
public class PetTypeEntity {
    @Id
    @Column( name = "pet_type_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer petTypeId;

    @Column( name = "pet_type_name")
    private String petTypeName;
    @OneToMany( mappedBy = "petTypeEntity")
    private List<PetEntity> petEntities;

    public PetTypeEntity() {
    }

    public PetTypeEntity(String petTypeName, List<PetEntity> petEntities) {
        this.petTypeName = petTypeName;
        this.petEntities = petEntities;
    }

    public List<PetEntity> getPetEntities() {
        return petEntities;
    }

    public void setPetEntities(List<PetEntity> petEntities) {
        this.petEntities = petEntities;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }

}
