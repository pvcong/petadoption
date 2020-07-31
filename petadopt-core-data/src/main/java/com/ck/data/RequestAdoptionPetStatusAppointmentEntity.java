package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "request_adopt_pet_status_appointment")
public class RequestAdoptionPetStatusAppointmentEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( name = "name")
    private String name;
    @OneToMany(mappedBy = "requestAdoptionPetStatusAppointmentEntity")
    private List<RequestAdoptionPetAppointmentStatusDetailEntity> requestAdoptionPetAppointmentStatusDetailEntities;

    public RequestAdoptionPetStatusAppointmentEntity() {
    }

    public List<RequestAdoptionPetAppointmentStatusDetailEntity> getRequestAdoptionPetAppointmentStatusDetailEntities() {
        return requestAdoptionPetAppointmentStatusDetailEntities;
    }

    public void setRequestAdoptionPetAppointmentStatusDetailEntities(List<RequestAdoptionPetAppointmentStatusDetailEntity> requestAdoptionPetAppointmentStatusDetailEntities) {
        this.requestAdoptionPetAppointmentStatusDetailEntities = requestAdoptionPetAppointmentStatusDetailEntities;
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
