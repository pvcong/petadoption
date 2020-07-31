package com.ck.utils;

import com.ck.data.RequestAdoptionPetEntity;
import com.ck.dto.RequestAdoptionPetDTO;
import org.springframework.web.bind.annotation.RequestBody;

public class RequestAdoptionPetUtils {
    public static RequestAdoptionPetEntity dto2Entity(RequestAdoptionPetDTO requestAdoptionPetDTO){
        RequestAdoptionPetEntity requestAdoptionPetEntity = new RequestAdoptionPetEntity();
        if(requestAdoptionPetDTO != null){
            requestAdoptionPetEntity.setAddress(requestAdoptionPetDTO.getAddress());
            requestAdoptionPetEntity.setFullName(requestAdoptionPetDTO.getFullName());
            requestAdoptionPetEntity.setContent(requestAdoptionPetDTO.getContent());
            requestAdoptionPetEntity.setPhoneNumber(requestAdoptionPetDTO.getPhoneNumber());
            requestAdoptionPetEntity.setRequesrAdoptionPerId(requestAdoptionPetDTO.getRequesrAdoptionPerId());
            requestAdoptionPetEntity.setModifiedDate(requestAdoptionPetDTO.getModifiedDate());
            requestAdoptionPetEntity.setCreatedDate(requestAdoptionPetDTO.getCreatedDate());


        }
        return requestAdoptionPetEntity;
    }
    public static RequestAdoptionPetDTO entity2DTO(RequestAdoptionPetEntity requestAdoptionPetEntity){
        RequestAdoptionPetDTO requestAdoptionPetDTO = new RequestAdoptionPetDTO();
        if(requestAdoptionPetEntity != null){
            requestAdoptionPetDTO.setAddress(requestAdoptionPetEntity.getAddress());
            requestAdoptionPetDTO.setFullName(requestAdoptionPetEntity.getFullName());
            requestAdoptionPetDTO.setContent(requestAdoptionPetEntity.getContent());
            requestAdoptionPetDTO.setPhoneNumber(requestAdoptionPetEntity.getPhoneNumber());
            requestAdoptionPetDTO.setRequesrAdoptionPerId(requestAdoptionPetEntity.getRequesrAdoptionPerId());
            requestAdoptionPetDTO.setModifiedDate(requestAdoptionPetEntity.getModifiedDate());
            requestAdoptionPetDTO.setCreatedDate(requestAdoptionPetEntity.getCreatedDate());

        }
        return requestAdoptionPetDTO;
    }
}
