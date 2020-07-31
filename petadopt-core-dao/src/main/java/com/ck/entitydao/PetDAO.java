package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.*;

import java.util.List;

public interface PetDAO extends GenericDAO<Integer, PetEntity> {
    PetEntity findSinglePetHome(Integer id);
    PetEntity findSinglePetAdmin(Integer id);

    Object[] findPetAdmin(PetEntity petEntity, PetTypeEntity petTypeEntity, PetStatusTypeEntity petStatusTypeEntity, String sortProperty, String sortValue, Integer limit, Integer offset);
    Object[] findPetHome(PetEntity petEntity, PetTypeEntity petTypeEntity, PetAboutEntity petAboutEntity,Integer limit, Integer offset);
    List<PetStatusEntity> findPetSStatusByPetId(Integer petId);

}
