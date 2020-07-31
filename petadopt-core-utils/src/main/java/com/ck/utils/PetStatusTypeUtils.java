package com.ck.utils;

import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetStatusEntity;
import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetStatusDTO;
import com.ck.dto.PetStatusTypeDTO;
import com.ck.dto.PetTypeDTO;

import java.util.ArrayList;
import java.util.List;

public class PetStatusTypeUtils {
    public static PetStatusTypeEntity dto2Entity(PetStatusTypeDTO petStatusTypeDTO){
        PetStatusTypeEntity petStatusTypeEntity = new PetStatusTypeEntity();
        if(petStatusTypeDTO != null){
            petStatusTypeEntity.setName(petStatusTypeDTO.getName());
            petStatusTypeEntity.setPetStatusTypeId(petStatusTypeDTO.getPetStatusTypeId());


        }
        return petStatusTypeEntity;
    }
    public static PetStatusTypeDTO entity2DTO(PetStatusTypeEntity petStatusTypeEntity){
        PetStatusTypeDTO petStatusTypeDTO = new PetStatusTypeDTO();
        if(petStatusTypeEntity != null){
            petStatusTypeDTO.setName(petStatusTypeEntity.getName());
            petStatusTypeDTO.setPetStatusTypeId(petStatusTypeEntity.getPetStatusTypeId());


        }
        return petStatusTypeDTO;
    }
    public static List<PetStatusTypeDTO> entities2DTOS(List<PetStatusTypeEntity> petStatusTypeEntities){
        List<PetStatusTypeDTO> petStatusTypeDTOS = new ArrayList<>();
        if(petStatusTypeEntities != null){
            for(PetStatusTypeEntity item : petStatusTypeEntities){
                PetStatusTypeDTO petStatusTypeDTO = PetStatusTypeUtils.entity2DTO(item);
                petStatusTypeDTOS.add(petStatusTypeDTO);
            }


        }
        return petStatusTypeDTOS;
    }
    public static List<PetStatusTypeEntity> DTOS2Entities(List<PetStatusTypeDTO> petStatusTypeDTOS){
        List<PetStatusTypeEntity> petStatusTypeEntities = new ArrayList<>();
        if(petStatusTypeDTOS != null){
            for(PetStatusTypeDTO item : petStatusTypeDTOS){
                PetStatusTypeEntity petStatusTypeEntity = PetStatusTypeUtils.dto2Entity(item);
                petStatusTypeEntities.add(petStatusTypeEntity);
            }


        }
        return petStatusTypeEntities;
    }
}
