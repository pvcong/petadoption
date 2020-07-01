package com.ck.data;

import javax.persistence.*;

@Entity
@Table( name = "pet_about")
public class PetAboutEntity {
    @Id
    @Column(name = "pet_id")
    private Integer petId;
    @Column( name = "sterilization")
    private String sterilization;
    @Column( name = "inject_rabies")
    private String inject_rabies;
    @Column( name = "vaccination")
    private String vaccination;
    @Column( name = "friendly_people")
    private String friendly_people;
    @Column( name = "friendly_cat")
    private String friendly_cat;
    @Column( name = "friendly_dog")
    private String friendly_dog;
    @Column( name = "toilet_place")
    private String toilet_place;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn( name = "pet_id")
    private PetEntity petEntity;



    public PetAboutEntity() {
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getSterilization() {
        return sterilization;
    }

    public void setSterilization(String sterilization) {
        this.sterilization = sterilization;
    }

    public String getInject_rabies() {
        return inject_rabies;
    }

    public void setInject_rabies(String inject_rabies) {
        this.inject_rabies = inject_rabies;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public String getFriendly_people() {
        return friendly_people;
    }

    public void setFriendly_people(String friendly_people) {
        this.friendly_people = friendly_people;
    }

    public String getFriendly_cat() {
        return friendly_cat;
    }

    public void setFriendly_cat(String friendly_cat) {
        this.friendly_cat = friendly_cat;
    }

    public String getFriendly_dog() {
        return friendly_dog;
    }

    public void setFriendly_dog(String friendly_dog) {
        this.friendly_dog = friendly_dog;
    }

    public String getToilet_place() {
        return toilet_place;
    }

    public void setToilet_place(String toilet_place) {
        this.toilet_place = toilet_place;
    }

    public PetEntity getPetEntity() {
        return petEntity;
    }

    public void setPetEntity(PetEntity petEntity) {
        this.petEntity = petEntity;
    }
}
