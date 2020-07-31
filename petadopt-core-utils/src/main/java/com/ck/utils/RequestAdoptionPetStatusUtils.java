package com.ck.utils;

import com.ck.data.RequestAdoptionPetStatusEntity;
import com.ck.dto.RequestAdoptionPetStatusDTO;

public class RequestAdoptionPetStatusUtils {
    public static RequestAdoptionPetStatusEntity dto2Entity(RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO){
        RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity = new RequestAdoptionPetStatusEntity();
        if(requestAdoptionPetStatusDTO != null){
            requestAdoptionPetStatusEntity.setId(requestAdoptionPetStatusDTO.getId());
            requestAdoptionPetStatusEntity.setName(requestAdoptionPetStatusDTO.getName());
        }
        return requestAdoptionPetStatusEntity;
    }
    public static RequestAdoptionPetStatusDTO entity2DTO(RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity){
        RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO = new RequestAdoptionPetStatusDTO();
        if(requestAdoptionPetStatusEntity != null){
            requestAdoptionPetStatusDTO.setId(requestAdoptionPetStatusEntity.getId());
            requestAdoptionPetStatusDTO.setName(requestAdoptionPetStatusEntity.getName());
        }
        return requestAdoptionPetStatusDTO;
    }
}
