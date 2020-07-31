package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetEntity;
import com.ck.data.RequestAdoptionPetEntity;
import com.ck.data.RequestAdoptionPetStatusDetailEntity;
import com.ck.data.RequestAdoptionPetStatusEntity;
import com.ck.dto.RequestAdoptionPetDTO;
import com.ck.dto.RequestAdoptionPetStatusDetailDTO;

import java.util.List;

public interface RequestAdoptionPetDAO extends GenericDAO<Integer, RequestAdoptionPetEntity> {
    public void updateAndAddStatus(RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity);
    RequestAdoptionPetStatusEntity findCurrentStatus(Integer id);
    Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetEntity requestAdoptionPetEntity,PetEntity petEntity, Integer requestPetStatusId, Integer limit, Integer offset);
    RequestAdoptionPetEntity findSingleRequestAdoptionPetById(Integer id);
    int totalRequestAdoptionPedding();
}
