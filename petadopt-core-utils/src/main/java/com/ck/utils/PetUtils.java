package com.ck.utils;

import com.ck.data.PetEntity;
import com.ck.dto.PetDTO;

public class PetUtils {
    public static PetEntity dto2Entity(PetDTO petDTO){
        PetEntity petEntity = new PetEntity();
        if(petDTO != null){
            petEntity.setPetId(petDTO.getPetId());
            petEntity.setPetName(petDTO.getPetName());
            petEntity.setAddress(petDTO.getAddress());
            petEntity.setAge(petDTO.getAge());
            petEntity.setBreed(petDTO.getBreed());
            petEntity.setColor(petDTO.getColor());
            petEntity.setGender(petDTO.getGender());
            petEntity.setCreatedDate(petDTO.getCreatedDate());
            petEntity.setModifiedDate(petDTO.getModifiedDate());
            petEntity.setDescription(petDTO.getDescription());
            petEntity.setImage(petDTO.getImage());
            petEntity.setStatus(petDTO.getStatus());
            petEntity.setWeight(petDTO.getWeight());

        }
        return petEntity;
    }
    public static PetDTO entity2DTO(PetEntity petEntity){
        PetDTO petDTO = new PetDTO();
        if(petEntity != null){
            petDTO.setPetId(petEntity.getPetId());
            petDTO.setPetName(petEntity.getPetName());
            petDTO.setAddress(petEntity.getAddress());
            petDTO.setAge(petEntity.getAge());
            petDTO.setGender(petEntity.getGender());
            petDTO.setBreed(petEntity.getBreed());
            petDTO.setColor(petEntity.getColor());
            petDTO.setCreatedDate(petEntity.getCreatedDate());
            petDTO.setModifiedDate(petEntity.getModifiedDate());
            petDTO.setDescription(petEntity.getDescription());
            petDTO.setImage(petEntity.getImage());
            petDTO.setStatus(petEntity.getStatus());
            petDTO.setWeight(petEntity.getWeight());


        }
        return petDTO;
    }
}
