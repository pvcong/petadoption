package com.ck.utils;

import com.ck.data.PetEntryTypeEntity;
import com.ck.data.PetTypeEntity;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.dto.PetTypeDTO;

public class PetEntryTypeUtils {
    public static PetEntryTypeEntity dto2Entity(PetEntryTypeDTO petEntryTypeDTO){
        PetEntryTypeEntity petEntryTypeEntity = new PetEntryTypeEntity();
        if(petEntryTypeDTO != null){
            petEntryTypeEntity.setPetEntryTypeId(petEntryTypeDTO.getPetEntryTypeId());
            petEntryTypeEntity.setName(petEntryTypeDTO.getName());


        }
        return petEntryTypeEntity;
    }
    public static PetEntryTypeDTO entity2DTO(PetEntryTypeEntity petEntryTypeEntity){
        PetEntryTypeDTO petEntryTypeDTO = new PetEntryTypeDTO();
        if(petEntryTypeEntity != null){
            petEntryTypeDTO.setName(petEntryTypeEntity.getName());
            petEntryTypeDTO.setPetEntryTypeId(petEntryTypeEntity.getPetEntryTypeId());


        }
        return petEntryTypeDTO;
    }
}
