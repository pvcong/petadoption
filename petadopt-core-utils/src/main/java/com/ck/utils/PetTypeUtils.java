package com.ck.utils;

import com.ck.data.PetStatusTypeEntity;
import com.ck.data.PetTypeEntity;
import com.ck.dto.PetTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class PetTypeUtils {
    public static PetTypeEntity dto2Entity(PetTypeDTO petTypeDTO){
        PetTypeEntity petTypeEntity = new PetTypeEntity();
        if(petTypeDTO != null){
            petTypeEntity.setPetTypeId(petTypeDTO.getPetTypeId());
            petTypeEntity.setPetTypeName(petTypeDTO.getPetTypeName());


        }
        return petTypeEntity;
    }
    public static PetTypeDTO entity2DTO(PetTypeEntity petTypeEntity){
        PetTypeDTO petTypeDTO = new PetTypeDTO();
        if(petTypeEntity != null){
            petTypeDTO.setPetTypeId(petTypeEntity.getPetTypeId());
            petTypeDTO.setPetTypeName(petTypeEntity.getPetTypeName());


        }
        return petTypeDTO;
    }

}
