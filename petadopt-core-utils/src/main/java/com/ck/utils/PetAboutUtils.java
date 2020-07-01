package com.ck.utils;

import com.ck.data.PetAboutEntity;
import com.ck.dto.PetAboutDTO;

public class PetAboutUtils {
    public static PetAboutEntity dto2Entity(PetAboutDTO petAboutDTO){
        PetAboutEntity petAboutEntity = new PetAboutEntity();
        if(petAboutDTO != null){
            petAboutEntity.setFriendly_cat(petAboutDTO.getFriendly_cat());
            petAboutEntity.setFriendly_dog(petAboutDTO.getFriendly_dog());
            petAboutEntity.setFriendly_people(petAboutDTO.getFriendly_people());
            petAboutEntity.setInject_rabies(petAboutDTO.getInject_rabies());
            petAboutEntity.setPetId(petAboutDTO.getPetId());
            petAboutEntity.setSterilization(petAboutDTO.getSterilization());
            petAboutEntity.setToilet_place(petAboutDTO.getToilet_place());
            petAboutEntity.setVaccination(petAboutDTO.getVaccination());


        }
        return petAboutEntity;
    }
    public static PetAboutDTO entity2DTO(PetAboutEntity petAboutEntity){
        PetAboutDTO petAboutDTO = new PetAboutDTO();
        if(petAboutEntity != null){
            petAboutDTO.setFriendly_cat(petAboutEntity.getFriendly_cat());
            petAboutDTO.setFriendly_dog(petAboutEntity.getFriendly_dog());
            petAboutDTO.setFriendly_people(petAboutEntity.getFriendly_people());
            petAboutDTO.setInject_rabies(petAboutEntity.getInject_rabies());
            petAboutDTO.setPetId(petAboutEntity.getPetId());
            petAboutDTO.setSterilization(petAboutEntity.getSterilization());
            petAboutDTO.setToilet_place(petAboutEntity.getToilet_place());
            petAboutDTO.setVaccination(petAboutEntity.getVaccination());


        }
        return petAboutDTO;
    }
}
