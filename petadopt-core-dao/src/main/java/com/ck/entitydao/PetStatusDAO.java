package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.PetStatusEntity;

public interface PetStatusDAO extends GenericDAO<Integer, PetStatusEntity> {
    Object[] findPetStatusToStory(Integer limit , Integer offset);

}
