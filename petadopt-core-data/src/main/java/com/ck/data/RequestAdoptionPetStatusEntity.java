package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Table( name = "request_adopt_pet_status")
@Entity
public class RequestAdoptionPetStatusEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( name = "name")
    private String name;
    @OneToMany(mappedBy = "requestAdoptionPetStatusEntity")
    private List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities;
    public RequestAdoptionPetStatusEntity() {
    }

    public List<RequestAdoptionPetStatusDetailEntity> getRequestAdoptionPetStatusDetailEntities() {
        return requestAdoptionPetStatusDetailEntities;
    }

    public void setRequestAdoptionPetStatusDetailEntities(List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities) {
        this.requestAdoptionPetStatusDetailEntities = requestAdoptionPetStatusDetailEntities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
