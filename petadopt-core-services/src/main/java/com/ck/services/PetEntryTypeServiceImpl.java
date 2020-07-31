package com.ck.services;

import com.ck.data.PetEntryStatusEntity;
import com.ck.data.PetEntryTypeEntity;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.dto.PetStatusTypeDTO;
import com.ck.entitydao.PetEntryTypeDAO;
import com.ck.utils.PetEntryTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetEntryTypeServiceImpl implements PetEntryTypeService{
    @Autowired
    PetEntryTypeDAO petEntryTypeDAO;
    @Override
    public List<PetEntryTypeDTO> findAll() {
        List<PetEntryTypeEntity> petEntryStatusEntities = petEntryTypeDAO.findAll();
        List<PetEntryTypeDTO> petEntryTypeDTOS = new ArrayList<>();
        for(PetEntryTypeEntity petEntryTypeEntityItem : petEntryStatusEntities){
            PetEntryTypeDTO petEntryTypeDTO = PetEntryTypeUtils.entity2DTO(petEntryTypeEntityItem);
            petEntryTypeDTOS.add(petEntryTypeDTO);
        }
        return petEntryTypeDTOS;
    }
}
