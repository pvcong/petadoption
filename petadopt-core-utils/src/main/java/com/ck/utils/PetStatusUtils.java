package com.ck.utils;

import com.ck.data.PetStatusEntity;
import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetStatusDTO;
import com.ck.dto.PetStatusTypeDTO;

public class PetStatusUtils {
    public static PetStatusEntity dto2Entity(PetStatusDTO petStatusDTO){
        PetStatusEntity petStatusEntity = new PetStatusEntity();
        if(petStatusDTO != null){
            petStatusEntity.setContent(petStatusDTO.getContent());
            petStatusEntity.setPetStatusId(petStatusDTO.getPetStatusId());
            petStatusEntity.setDate(petStatusDTO.getDate());



        }
        return petStatusEntity;
    }
    public static PetStatusDTO entity2DTO(PetStatusEntity petStatusEntity){
        PetStatusDTO petStatusDTO = new PetStatusDTO();
        if(petStatusEntity != null){
            petStatusDTO.setContent(petStatusEntity.getContent());
            petStatusDTO.setPetStatusId(petStatusEntity.getPetStatusId());
            petStatusDTO.setDate(petStatusEntity.getDate());


        }
        return petStatusDTO;
    }
}
