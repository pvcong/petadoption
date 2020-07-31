package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "request_adopt_pet_appointment")
public class RequestAdoptionPetAppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content")
    private String content;
    @Column(name = "appointment_date")
    private Timestamp appointmentDate;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private UserEntity userEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "request_adopt_pet_id")
    private RequestAdoptionPetEntity requestAdoptionPetEntity;
    @OneToMany(mappedBy = "requestAdoptionPetAppointmentEntity")

    private List<RequestAdoptionPetAppointmentStatusDetailEntity> requestAdoptionPetAppointmentStatusDetailEntities;
    public RequestAdoptionPetAppointmentEntity() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RequestAdoptionPetEntity getRequestAdoptionPetEntity() {
        return requestAdoptionPetEntity;
    }

    public void setRequestAdoptionPetEntity(RequestAdoptionPetEntity requestAdoptionPetEntity) {
        this.requestAdoptionPetEntity = requestAdoptionPetEntity;
    }
}
