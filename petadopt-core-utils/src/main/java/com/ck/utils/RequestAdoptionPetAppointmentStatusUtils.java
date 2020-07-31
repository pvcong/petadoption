package com.ck.utils;

import com.ck.data.RequestAdoptionPetStatusAppointmentEntity;
import com.ck.data.RequestAdoptionPetStatusEntity;
import com.ck.dto.RequestAdoptionPetStatusAppointmentDTO;
import com.ck.dto.RequestAdoptionPetStatusDTO;

public class RequestAdoptionPetAppointmentStatusUtils {
    public static RequestAdoptionPetStatusAppointmentEntity dto2Entity(RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO){
        RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity = new RequestAdoptionPetStatusAppointmentEntity();
        if(requestAdoptionPetStatusAppointmentDTO != null){
            requestAdoptionPetStatusAppointmentEntity.setId(requestAdoptionPetStatusAppointmentDTO.getId());
            requestAdoptionPetStatusAppointmentEntity.setName(requestAdoptionPetStatusAppointmentDTO.getName());
        }
        return requestAdoptionPetStatusAppointmentEntity;
    }
    public static RequestAdoptionPetStatusAppointmentDTO entity2DTO(RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity){
        RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO = new RequestAdoptionPetStatusAppointmentDTO();
        if(requestAdoptionPetStatusAppointmentEntity != null){
            requestAdoptionPetStatusAppointmentDTO.setId(requestAdoptionPetStatusAppointmentEntity.getId());
            requestAdoptionPetStatusAppointmentDTO.setName(requestAdoptionPetStatusAppointmentEntity.getName());
        }
        return requestAdoptionPetStatusAppointmentDTO;
    }
}
