package com.ck.services;

import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetStatusTypeDTO;
import com.ck.entitydao.PetEntryStatusDAO;
import com.ck.entitydao.PetStatusTypeDAO;
import com.ck.utils.PetEntryStatusUtils;
import com.ck.utils.PetStatusTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetStatusTypeServiceImpl implements PetStatusTypeService{
    @Autowired
    PetStatusTypeDAO petStatusTypeDAO;
    @Override
    public List<PetStatusTypeDTO> findAll() {
        List<PetStatusTypeEntity> petStatusTypeEntities = petStatusTypeDAO.findAll();
        List<PetStatusTypeDTO> petStatusTypeDTOS = new ArrayList<>();
        for(PetStatusTypeEntity petStatusTypeEntity : petStatusTypeEntities){
            PetStatusTypeDTO petStatusTypeDTO = PetStatusTypeUtils.entity2DTO(petStatusTypeEntity);
            petStatusTypeDTOS.add(petStatusTypeDTO);
        }
        return petStatusTypeDTOS;
    }
}
