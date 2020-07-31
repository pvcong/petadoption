package com.ck.utils;

import com.ck.data.RequestAdoptionPetStatusDetailEntity;
import com.ck.dto.RequestAdoptionPetStatusDetailDTO;

public class RequestAdoptionPetStatusDetailUtils {
    public static RequestAdoptionPetStatusDetailEntity dto2Entity(RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO){
        RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity = new RequestAdoptionPetStatusDetailEntity();
        if(requestAdoptionPetStatusDetailDTO != null){
            requestAdoptionPetStatusDetailEntity.setId(requestAdoptionPetStatusDetailDTO.getId());
            requestAdoptionPetStatusDetailEntity.setCreatedDate(requestAdoptionPetStatusDetailDTO.getCreatedDate());
            requestAdoptionPetStatusDetailEntity.setContent(requestAdoptionPetStatusDetailDTO.getContent());
        }
        return requestAdoptionPetStatusDetailEntity;
    }
    public static RequestAdoptionPetStatusDetailDTO entity2Dto(RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity){
        RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO = new RequestAdoptionPetStatusDetailDTO();
        if(requestAdoptionPetStatusDetailEntity != null){
            requestAdoptionPetStatusDetailDTO.setId(requestAdoptionPetStatusDetailEntity.getId());
            requestAdoptionPetStatusDetailDTO.setCreatedDate(requestAdoptionPetStatusDetailEntity.getCreatedDate());
            requestAdoptionPetStatusDetailDTO.setContent(requestAdoptionPetStatusDetailEntity.getContent());
        }
        return requestAdoptionPetStatusDetailDTO;
    }
}
