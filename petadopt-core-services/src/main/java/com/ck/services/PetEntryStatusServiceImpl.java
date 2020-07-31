package com.ck.services;

import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetEntryTypeEntity;
import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.entitydao.PetEntryStatusDAO;
import com.ck.entitydao.PetEntryTypeDAO;
import com.ck.utils.PetEntryStatusUtils;
import com.ck.utils.PetEntryTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetEntryStatusServiceImpl implements PetEntryStatusService{
    @Autowired
    PetEntryStatusDAO petEntryStatusDAO;
    @Override
    public List<PetEntryStatusDTO> findAll() {
        List<PetEntryStatusEntity> petEntryStatusEntities = petEntryStatusDAO.findAll();
        List<PetEntryStatusDTO> petEntryStatusDTOS = new ArrayList<>();
        for(PetEntryStatusEntity petEntryStatusEntity : petEntryStatusEntities){
            PetEntryStatusDTO petEntryTypeDTO = PetEntryStatusUtils.entity2DTO(petEntryStatusEntity);
            petEntryStatusDTOS.add(petEntryTypeDTO);
        }
        return petEntryStatusDTOS;
    }
}
