package com.ck.services;

import com.ck.dto.PetStatusDTO;
import com.ck.entitydao.PetStatusDAO;

import java.util.List;

public interface PetStatusService {
    public void save(PetStatusDTO petStatusDTO);
    public void update(PetStatusDTO petStatusDTO);
    public PetStatusDTO findById(Integer id);
    public Object[] findPetStatusToStory(Integer limit, Integer offset);

}
