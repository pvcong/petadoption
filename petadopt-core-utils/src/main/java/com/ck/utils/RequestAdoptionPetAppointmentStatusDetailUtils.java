package com.ck.utils;

import com.ck.data.RequestAdoptionPetAppointmentStatusDetailEntity;
import com.ck.dto.RequestAdoptionPetAppointmentStatusDetailDTO;

public class RequestAdoptionPetAppointmentStatusDetailUtils {
    public static RequestAdoptionPetAppointmentStatusDetailEntity dto2Entity(RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO){
        RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity = new RequestAdoptionPetAppointmentStatusDetailEntity();
        if(requestAdoptionPetAppointmentStatusDetailDTO != null){
            requestAdoptionPetAppointmentStatusDetailEntity.setId(requestAdoptionPetAppointmentStatusDetailDTO.getId());
            requestAdoptionPetAppointmentStatusDetailEntity.setCreatedDate(requestAdoptionPetAppointmentStatusDetailDTO.getCreatedDate());
            requestAdoptionPetAppointmentStatusDetailEntity.setContent(requestAdoptionPetAppointmentStatusDetailDTO.getContent());
        }
        return requestAdoptionPetAppointmentStatusDetailEntity;
    }
    public static RequestAdoptionPetAppointmentStatusDetailDTO entity2Dto(RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity){
        RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO = new RequestAdoptionPetAppointmentStatusDetailDTO();
        if(requestAdoptionPetAppointmentStatusDetailEntity != null){
            requestAdoptionPetAppointmentStatusDetailDTO.setId(requestAdoptionPetAppointmentStatusDetailEntity.getId());
            requestAdoptionPetAppointmentStatusDetailDTO.setCreatedDate(requestAdoptionPetAppointmentStatusDetailEntity.getCreatedDate());
            requestAdoptionPetAppointmentStatusDetailDTO.setContent(requestAdoptionPetAppointmentStatusDetailEntity.getContent());
        }
        return requestAdoptionPetAppointmentStatusDetailDTO;
    }
}
