package com.ck.services;

import com.ck.data.PetTypeEntity;
import com.ck.dto.PetTypeDTO;
import com.ck.entitydao.PetTypeDAO;
import com.ck.utils.PetTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetTypeServiceImpl implements PetTypeService {
    @Autowired
    PetTypeDAO petTypeDAO;
    @Override
    public void save(PetTypeDTO petTypeDTO) {
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);
        petTypeDAO.save(petTypeEntity);
    }

    @Override
    public void update(PetTypeDTO petTypeDTO) {
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);
        petTypeDAO.update(petTypeEntity);
    }

    @Override
    public void delete(List<PetTypeDTO> petTypeDTOs) {
        List<PetTypeEntity> petTypeEntities = new ArrayList<>();
        for(PetTypeDTO petTypeDTO : petTypeDTOs){
            PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);
            petTypeEntities.add(petTypeEntity);
        }
        petTypeDAO.delete(petTypeEntities);
    }

    @Override
    public PetTypeDTO findById(Integer id) {
        PetTypeEntity petTypeEntity = petTypeDAO.findById(id);
        PetTypeDTO petTypeDTO = PetTypeUtils.entity2DTO(petTypeEntity);
        return petTypeDTO;
    }
}
