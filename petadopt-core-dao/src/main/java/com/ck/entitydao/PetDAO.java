package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.PetAboutEntity;
import com.ck.data.PetEntity;
import com.ck.data.PetTypeEntity;

public interface PetDAO extends GenericDAO<Integer, PetEntity> {
    PetEntity findSinglePetHome(Integer id);
    PetEntity findSinglePetAdmin(Integer id);

    Object[] findPetAdmin(PetEntity petEntity, PetTypeEntity petTypeEntity,String sortProperty,String sortValue,Integer limit, Integer offset);
    Object[] findPetHome(PetEntity petEntity, PetTypeEntity petTypeEntity, PetAboutEntity petAboutEntity,Integer limit, Integer offset);
}
