package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pet_status")
public class PetStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer petStatusId;
    @Column(name = "date")
    private Timestamp date;
    @Column(name = "content")
    private String content;
    @ManyToOne()
    @JoinColumn(name = "pet_id")
    private PetEntity petEntity;
    @ManyToOne()
    @JoinColumn(name = "pet_status_type_id")
    private PetStatusTypeEntity petStatusTypeEntity;

    public PetStatusEntity() {
    }

    public Integer getPetStatusId() {
        return petStatusId;
    }

    public void setPetStatusId(Integer petStatusId) {
        this.petStatusId = petStatusId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(PetEntity petEntity) {
        this.petEntity = petEntity;
    }

    public PetStatusTypeEntity getPetStatusTypeEntity() {
        return petStatusTypeEntity;
    }

    public void setPetStatusTypeEntity(PetStatusTypeEntity petStatusTypeEntity) {
        this.petStatusTypeEntity = petStatusTypeEntity;
    }
}
