package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.EmployeeEntity;
import com.ck.data.PetEntity;
import com.ck.data.RescueOrderEntity;

public interface RescueOrderDAO extends GenericDAO<Integer, RescueOrderEntity> {
    Object[] findRescueOrderByProperties(RescueOrderEntity rescueOrderEntity, EmployeeEntity employeeEntity,Integer limit,Integer offset);
    void updatePet(PetEntity petEntity);
    void deletePet(PetEntity petEntity);
    Object[] findPetDontOrder(PetEntity petEntity,Integer limit, Integer offset);
    Object[] findPetOfEmployye(Integer employeeId,Integer limit, Integer offset);

}
