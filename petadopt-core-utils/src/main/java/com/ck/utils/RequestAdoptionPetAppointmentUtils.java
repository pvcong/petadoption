package com.ck.utils;

import com.ck.data.RequestAdoptionPetAppointmentEntity;
import com.ck.data.RequestAdoptionPetEntity;
import com.ck.dto.RequestAdoptionPetAppointmentDTO;
import com.ck.dto.RequestAdoptionPetDTO;

public class RequestAdoptionPetAppointmentUtils {
    public static RequestAdoptionPetAppointmentEntity dto2Entity(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO){
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = new RequestAdoptionPetAppointmentEntity();
        if(requestAdoptionPetAppointmentDTO != null){
            requestAdoptionPetAppointmentEntity.setContent(requestAdoptionPetAppointmentDTO.getContent());
            requestAdoptionPetAppointmentEntity.setModifiedDate(requestAdoptionPetAppointmentDTO.getModifiedDate());
            requestAdoptionPetAppointmentEntity.setCreatedDate(requestAdoptionPetAppointmentDTO.getCreatedDate());
            requestAdoptionPetAppointmentEntity.setId(requestAdoptionPetAppointmentDTO.getId());
            requestAdoptionPetAppointmentEntity.setAppointmentDate(requestAdoptionPetAppointmentDTO.getAppointmentDate());

        }
        return requestAdoptionPetAppointmentEntity;
    }
    public static RequestAdoptionPetAppointmentDTO entity2DTO(RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity){
        RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO = new RequestAdoptionPetAppointmentDTO();
        if(requestAdoptionPetAppointmentEntity != null){
            requestAdoptionPetAppointmentDTO.setContent(requestAdoptionPetAppointmentEntity.getContent());
            requestAdoptionPetAppointmentDTO.setModifiedDate(requestAdoptionPetAppointmentEntity.getModifiedDate());
            requestAdoptionPetAppointmentDTO.setCreatedDate(requestAdoptionPetAppointmentEntity.getCreatedDate());
            requestAdoptionPetAppointmentDTO.setId(requestAdoptionPetAppointmentEntity.getId());
            requestAdoptionPetAppointmentDTO.setAppointmentDate(requestAdoptionPetAppointmentEntity.getAppointmentDate());


        }
        return requestAdoptionPetAppointmentDTO;
    }
}
