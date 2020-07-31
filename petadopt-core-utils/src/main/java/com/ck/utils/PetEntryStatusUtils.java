package com.ck.utils;

import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetEntryTypeEntity;
import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetEntryTypeDTO;

public class PetEntryStatusUtils {
    public static PetEntryStatusEntity dto2Entity(PetEntryStatusDTO petEntryStatusDTO){
        PetEntryStatusEntity petEntryStatusEntity = new PetEntryStatusEntity();
        if(petEntryStatusDTO != null){
            petEntryStatusEntity.setPetEntryStatusId(petEntryStatusDTO.getPetEntryStatusId());
            petEntryStatusEntity.setName(petEntryStatusDTO.getName());


        }
        return petEntryStatusEntity;
    }
    public static PetEntryStatusDTO entity2DTO(PetEntryStatusEntity petEntryStatusEntity){
        PetEntryStatusDTO petEntryStatusDTO = new PetEntryStatusDTO();
        if(petEntryStatusEntity != null){
            petEntryStatusDTO.setName(petEntryStatusEntity.getName());
            petEntryStatusDTO.setPetEntryStatusId(petEntryStatusEntity.getPetEntryStatusId());


        }
        return petEntryStatusDTO;
    }
}
