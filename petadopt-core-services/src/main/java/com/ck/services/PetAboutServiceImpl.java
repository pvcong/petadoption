package com.ck.services;

import com.ck.data.PetAboutEntity;
import com.ck.dto.PetAboutDTO;
import com.ck.entitydao.PetAboutDAO;
import com.ck.utils.PetAboutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PetAboutServiceImpl implements PetAboutService {
    @Autowired
    PetAboutDAO petAboutDAO;
    @Override
    public void save(PetAboutDTO petAboutDTO) {
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petAboutDTO);
        petAboutDAO.save(petAboutEntity);
    }

    @Override
    public void update(PetAboutDTO petAboutDTO) {
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petAboutDTO);
        petAboutDAO.update(petAboutEntity);
    }

    @Override
    public void delete(List<PetAboutDTO> petAboutDTOS) {
        List<PetAboutEntity> petAboutEntities = new ArrayList<>();
        for(PetAboutDTO petAboutDTO : petAboutDTOS){
            PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petAboutDTO);
            petAboutEntities.add(petAboutEntity);
        }
        petAboutDAO.delete(petAboutEntities);
    }

    @Override
    public PetAboutDTO findById(Integer id) {
        PetAboutEntity petAboutEntity = petAboutDAO.findById(id);
        PetAboutDTO petAboutDTO = PetAboutUtils.entity2DTO(petAboutEntity);
        return petAboutDTO;
    }
}
