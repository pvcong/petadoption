package com.ck.utils;

import com.ck.data.FosterPetEntity;
import com.ck.dto.FosterPetDTO;

import java.util.ArrayList;
import java.util.List;

public class FosterPetUtils {
    public static FosterPetEntity dto2Entity(FosterPetDTO fosterPetDTO){
        FosterPetEntity fosterPetEntity = new FosterPetEntity();
        if(fosterPetDTO != null){
            fosterPetEntity.setFosterDate(fosterPetDTO.getFosterDate());
            fosterPetEntity.setContent(fosterPetDTO.getContent());
            fosterPetEntity.setFosterPetId(fosterPetDTO.getFosterPetId());

        }
        return fosterPetEntity;
    }

    public static FosterPetDTO entity2DTO(FosterPetEntity fosterPetEntity){
        FosterPetDTO fosterPetDTO = new FosterPetDTO();
        if(fosterPetEntity != null){
            fosterPetDTO.setFosterDate(fosterPetEntity.getFosterDate());
            fosterPetDTO.setContent(fosterPetEntity.getContent());
            fosterPetDTO.setFosterPetId(fosterPetEntity.getFosterPetId());

        }
        return fosterPetDTO;
    }


}
