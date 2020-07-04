package com.ck.services;

import com.ck.data.PetAboutEntity;
import com.ck.data.PetEntity;
import com.ck.data.PetTypeEntity;
import com.ck.data.UserEntity;
import com.ck.dto.PetAboutDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.PetTypeDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.PetDAO;
import com.ck.entitydao.PetTypeDAO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.PetAboutUtils;
import com.ck.utils.PetTypeUtils;
import com.ck.utils.PetUtils;
import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetDAO petDAO;
    @Override
    public void save(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);

        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petDTO.getPetTypeDTO());
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petDTO.getPetAboutDTO());
        UserEntity userEntity = UserUtils.dto2Entity(petDTO.getUserDTO());

        petEntity.setPetTypeEntity(petTypeEntity);
        petEntity.setPetAboutEntity(petAboutEntity);

        petEntity.setUserEntity(userEntity);
        petDAO.save(petEntity);
    }

    @Override
    public void update(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petDTO.getPetTypeDTO());
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petDTO.getPetAboutDTO());
        petEntity.setPetTypeEntity(petTypeEntity);
        petEntity.setPetAboutEntity(petAboutEntity);
        petDAO.update(petEntity);
    }

    @Override
    public void delete(List<PetDTO> petDTOS) {
        List<PetEntity> petEntities = new ArrayList<>();
        for(PetDTO petDTO :   petDTOS){
            PetEntity petEntity = PetUtils.dto2Entity(petDTO);
            petEntities.add(petEntity);
        }
        petDAO.delete(petEntities);
    }

    @Override
    public PetDTO findById(Integer id) {
        PetEntity petEntity = petDAO.findById(id);
        PetDTO petDTO = PetUtils.entity2DTO(petEntity);
        return petDTO;
    }

    @Override
    public PetDTO findSinglePetHome(Integer id) {
        PetEntity petEntity = petDAO.findSinglePetHome(id);
        if(petEntity == null){
            throw new NotFoundObjectException();
        }
        PetDTO petDTO = PetUtils.entity2DTO(petEntity);
        PetAboutEntity petAboutEntity = petEntity.getPetAboutEntity();
        PetTypeEntity petTypeEntity = petEntity.getPetTypeEntity();

        PetTypeDTO petTypeDTO = PetTypeUtils.entity2DTO(petTypeEntity);
        PetAboutDTO petAboutDTO = PetAboutUtils.entity2DTO(petAboutEntity);

        petDTO.setPetAboutDTO(petAboutDTO);
        petDTO.setPetTypeDTO(petTypeDTO);
        return petDTO;
    }

    @Override
    public Object[] findPetHome(PetDTO petDTO, PetTypeDTO petTypeDTO, PetAboutDTO petAboutDTO, Integer limit, Integer offset) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petAboutDTO);

        Object[] objects = petDAO.findPetHome(petEntity,petTypeEntity,petAboutEntity,limit,offset);

        List<PetEntity> petEntities = (List<PetEntity>) objects[1];
        List<PetDTO> petDTOS = new ArrayList<>();

        for(PetEntity petEntity1 : petEntities){
            PetDTO petDTO1 = PetUtils.entity2DTO(petEntity1);
            PetAboutDTO petAboutDTO1 = PetAboutUtils.entity2DTO(petEntity1.getPetAboutEntity());
            petDTO1.setPetAboutDTO(petAboutDTO1);
            petDTOS.add(petDTO1);
        }

        return new Object[]{objects[0],petDTOS};
    }

    @Override
    public PetDTO findSinglePetAdmin(Integer id) {
        PetEntity petEntity = petDAO.findSinglePetAdmin(id);
        if(petEntity == null){
            throw new NotFoundObjectException();

        }
        PetDTO petDTO = PetUtils.entity2DTO(petEntity);
        PetAboutEntity petAboutEntity = petEntity.getPetAboutEntity();
        PetTypeEntity petTypeEntity = petEntity.getPetTypeEntity();

        PetTypeDTO petTypeDTO = PetTypeUtils.entity2DTO(petTypeEntity);
        PetAboutDTO petAboutDTO = PetAboutUtils.entity2DTO(petAboutEntity);

        petDTO.setPetAboutDTO(petAboutDTO);
        petDTO.setPetTypeDTO(petTypeDTO);
        return petDTO;
    }

    @Override
    public Object[] findPetAdmin(PetDTO petDTO, PetTypeDTO petTypeDTO, String propertySort, String propertyValue, Integer limit, Integer offset) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);

        Object[] objects = petDAO.findPetAdmin(petEntity,petTypeEntity,propertySort,propertyValue,limit,offset);

        List<PetEntity> petEntities = (List<PetEntity>) objects[1];
        List<PetDTO> petDTOS = new ArrayList<>();

        for(PetEntity petEntity1 : petEntities){
            PetDTO petDTO1 = PetUtils.entity2DTO(petEntity1);
            UserDTO userDTO = UserUtils.entity2DTO(petEntity1.getUserEntity());
            petDTO1.setUserDTO(userDTO);
            petDTOS.add(petDTO1);
        }

        return new Object[]{objects[0],petDTOS};
    }
}
