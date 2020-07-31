package com.ck.data;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.sql.Timestamp;

@Entity
@Table(name = "request_adopt_pet_appointment_status_detail")
public class RequestAdoptionPetAppointmentStatusDetailEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "content")
    private String content;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_status_id")
    private RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity;
    @Column(name = "created_date")
    private Timestamp createdDate;

    public RequestAdoptionPetAppointmentStatusDetailEntity() {
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public RequestAdoptionPetAppointmentEntity getRequestAdoptionPetAppointmentEntity() {
        return requestAdoptionPetAppointmentEntity;
    }

    public void setRequestAdoptionPetAppointmentEntity(RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity) {
        this.requestAdoptionPetAppointmentEntity = requestAdoptionPetAppointmentEntity;
    }

    public RequestAdoptionPetStatusAppointmentEntity getRequestAdoptionPetStatusAppointmentEntity() {
        return requestAdoptionPetStatusAppointmentEntity;
    }

    public void setRequestAdoptionPetStatusAppointmentEntity(RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity) {
        this.requestAdoptionPetStatusAppointmentEntity = requestAdoptionPetStatusAppointmentEntity;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
