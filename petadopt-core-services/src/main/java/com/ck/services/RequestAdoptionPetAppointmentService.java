package com.ck.services;

import com.ck.data.RequestAdoptionPetEntity;
import com.ck.dto.RequestAdoptionPetAppointmentDTO;
import com.ck.dto.RequestAdoptionPetAppointmentStatusDetailDTO;
import com.ck.dto.RequestAdoptionPetDTO;
import com.ck.dto.RequestAdoptionPetStatusDetailDTO;

import java.util.List;

public interface RequestAdoptionPetAppointmentService {
    public void save(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO);
    public void update(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO);
    public void updateStatus(RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO);
    public RequestAdoptionPetEntity findById(Integer id);
    Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO, Integer requestAdoptionPetStatusId, Integer limit, Integer offset);
    RequestAdoptionPetAppointmentDTO findSingleRequestAdoptionPetById(Integer id);
    int totalAppointmentAvailabilityToday();
}
