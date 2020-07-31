package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "request_adopt_pet_status_detail")
public class RequestAdoptionPetStatusDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column( name = "content")
    private String content;

    @Column(name = "created_date")
    private Timestamp createdDate;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "status_id")
    private RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "request_adopt_pet_id")
    private RequestAdoptionPetEntity requestAdoptionPetEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private UserEntity userEntity;
    public RequestAdoptionPetStatusDetailEntity() {
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



    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public RequestAdoptionPetStatusEntity getRequestAdoptionPetStatusEntity() {
        return requestAdoptionPetStatusEntity;
    }

    public void setRequestAdoptionPetStatusEntity(RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity) {
        this.requestAdoptionPetStatusEntity = requestAdoptionPetStatusEntity;
    }

    public RequestAdoptionPetEntity getRequestAdoptionPetEntity() {
        return requestAdoptionPetEntity;
    }

    public void setRequestAdoptionPetEntity(RequestAdoptionPetEntity requestAdoptionPetEntity) {
        this.requestAdoptionPetEntity = requestAdoptionPetEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
