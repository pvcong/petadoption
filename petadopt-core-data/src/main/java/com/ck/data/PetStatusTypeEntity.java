package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pet_status_type")
public class PetStatusTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer petStatusTypeId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "petStatusTypeEntity")
    private List<PetStatusEntity> petStatusEntities;

    public PetStatusTypeEntity() {
    }

    public Integer getPetStatusTypeId() {
        return petStatusTypeId;
    }

    public void setPetStatusTypeId(Integer petStatusTypeId) {
        this.petStatusTypeId = petStatusTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PetStatusEntity> getPetStatusEntities() {
        return petStatusEntities;
    }

    public void setPetStatusEntities(List<PetStatusEntity> petStatusEntities) {
        this.petStatusEntities = petStatusEntities;
    }
}
