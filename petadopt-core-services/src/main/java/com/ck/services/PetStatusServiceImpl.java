package com.ck.services;

import com.ck.data.PetEntity;
import com.ck.data.PetStatusEntity;
import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetDTO;
import com.ck.dto.PetStatusDTO;
import com.ck.dto.PetStatusTypeDTO;
import com.ck.entitydao.PetStatusDAO;
import com.ck.utils.PetStatusTypeUtils;
import com.ck.utils.PetStatusUtils;
import com.ck.utils.PetTypeUtils;
import com.ck.utils.PetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetStatusServiceImpl implements PetStatusService {
    @Autowired
    PetStatusDAO petStatusDAO;
    @Override
    public void save(PetStatusDTO petStatusDTO) {
        PetStatusEntity petStatusEntity = PetStatusUtils.dto2Entity(petStatusDTO);
        PetEntity petEntity = PetUtils.dto2Entity(petStatusDTO.getPetDTO());
        PetStatusTypeEntity petStatusTypeEntity = PetStatusTypeUtils.dto2Entity(petStatusDTO.getPetStatusTypeDTO());

        petStatusEntity.setPetStatusTypeEntity(petStatusTypeEntity);
        petStatusEntity.setPetEntity(petEntity);

        petStatusDAO.save(petStatusEntity);
    }

    @Override
    public void update(PetStatusDTO petStatusDTO) {
        PetStatusEntity petStatusEntity = PetStatusUtils.dto2Entity(petStatusDTO);
        PetStatusTypeEntity petStatusTypeEntity = PetStatusTypeUtils.dto2Entity(petStatusDTO.getPetStatusTypeDTO());

        petStatusEntity.setPetStatusTypeEntity(petStatusTypeEntity);

        petStatusDAO.update(petStatusEntity);
    }

    @Override
    public PetStatusDTO findById(Integer id) {
        return null;
    }

    @Override
    public Object[] findPetStatusToStory(Integer limit, Integer offset) {
        Object[] objects = petStatusDAO.findPetStatusToStory(limit,offset);
        List<PetStatusEntity> petStatusEntities = (List<PetStatusEntity>) objects[1];
        List<PetStatusDTO> petStatusDTOS = new ArrayList<>();
        for(PetStatusEntity petStatusEntity : petStatusEntities){
            PetStatusDTO petStatusDTO = PetStatusUtils.entity2DTO(petStatusEntity);
            PetStatusTypeDTO petStatusTypeDTO = PetStatusTypeUtils.entity2DTO(petStatusEntity.getPetStatusTypeEntity());
            PetDTO petDTO = PetUtils.entity2DTO(petStatusEntity.getPetEntity());
            petStatusDTO.setPetDTO(petDTO);
            petStatusDTO.setPetStatusTypeDTO(petStatusTypeDTO);
            petStatusDTOS.add(petStatusDTO);
        }
        return new Object[]{objects[0],petStatusDTOS};
    }


}
