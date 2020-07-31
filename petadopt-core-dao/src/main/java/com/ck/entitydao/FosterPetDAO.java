package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.EmployeeEntity;
import com.ck.data.FosterPetEntity;
import com.ck.data.PetEntity;

public interface FosterPetDAO extends GenericDAO<Integer, FosterPetEntity> {
    public Object[] findRescueOrderByProperties(FosterPetEntity fosterPetEntity, EmployeeEntity employeeEntity, Integer limit, Integer offset);
    void updatePet(PetEntity petEntity);
    void deletePet(PetEntity petEntity);
    Object[] findPetDontFoster(PetEntity petEntity,Integer limit, Integer offset);
    Boolean checkPetIsFoster(Integer petId);
    Object[] findPetOfEmployye(Integer employeeId,Integer limit, Integer offset);
}

