package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.*;

public interface RequestAdoptionPetAppointmentDAO extends GenericDAO<Integer, RequestAdoptionPetAppointmentEntity> {
    public void updateAndAddStatus(RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity);
    RequestAdoptionPetStatusAppointmentEntity findCurrentStatus(Integer id);
    Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetAppointmentEntity RequestAdoptionPetAppointmentEntity,RequestAdoptionPetEntity requestAdoptionPetEntity ,Integer requestPetStatusId, Integer limit, Integer offset);
    RequestAdoptionPetAppointmentEntity findSingleRequestAdoptionPetById(Integer id);
    int totalAppointmentAvailabilityToday();
}
