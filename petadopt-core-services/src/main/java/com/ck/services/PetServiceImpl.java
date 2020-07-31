package com.ck.services;

import com.ck.data.*;
import com.ck.dto.*;
import com.ck.entitydao.PetDAO;
import com.ck.entitydao.PetTypeDAO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.*;
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
        PetEntryTypeEntity petEntryTypeEntity = PetEntryTypeUtils.dto2Entity(petDTO.getPetEntryTypeDTO());
        PetEntryStatusEntity petEntryStatusEntity = PetEntryStatusUtils.dto2Entity(petDTO.getPetEntryStatusDTO());

        petEntity.setPetEntryStatusEntity(petEntryStatusEntity);
        petEntity.setPetEntryTypeEntity(petEntryTypeEntity);
        petEntity.setUserEntity(userEntity);
        petEntity.setPetTypeEntity(petTypeEntity);
        petEntity.setPetAboutEntity(petAboutEntity);
        petDAO.save(petEntity);
    }

    @Override
    public void update(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petDTO.getPetTypeDTO());
        PetEntryTypeEntity petEntryTypeEntity = PetEntryTypeUtils.dto2Entity(petDTO.getPetEntryTypeDTO());
        PetEntryStatusEntity petEntryStatusEntity = PetEntryStatusUtils.dto2Entity(petDTO.getPetEntryStatusDTO());
        PetAboutEntity petAboutEntity = PetAboutUtils.dto2Entity(petDTO.getPetAboutDTO());



        petEntity.setPetEntryStatusEntity(petEntryStatusEntity);
        petEntity.setPetEntryTypeEntity(petEntryTypeEntity);
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


        PetEntryStatusDTO petEntryStatusDTO = PetEntryStatusUtils.entity2DTO(petEntity.getPetEntryStatusEntity());
        PetEntryTypeDTO petEntryTypeDTO = PetEntryTypeUtils.entity2DTO(petEntity.getPetEntryTypeEntity());

        List<PetStatusEntity> petStatusEntities = petEntity.getPetStatusEntities();
        List<PetStatusDTO> petStatusDTOS = new ArrayList<>();
        if(petStatusEntities.size() >0){
            for(PetStatusEntity item : petStatusEntities){
                PetStatusDTO petStatusDTO = PetStatusUtils.entity2DTO(item);
                PetStatusTypeDTO petStatusTypeDTO = PetStatusTypeUtils.entity2DTO(item.getPetStatusTypeEntity());
                petStatusDTO.setPetStatusTypeDTO(petStatusTypeDTO);
                petStatusDTOS.add(petStatusDTO);
            }
        }
        petDTO.setPetStatusDTOS(petStatusDTOS);
        PetTypeDTO petTypeDTO = PetTypeUtils.entity2DTO(petTypeEntity);
        PetAboutDTO petAboutDTO = PetAboutUtils.entity2DTO(petAboutEntity);
        FosterPetDTO fosterPetDTO = FosterPetUtils.entity2DTO(petEntity.getFosterPetEntity());
        petDTO.setFosterPetDTO(fosterPetDTO.getFosterPetId() != null ? fosterPetDTO : null );
        petDTO.setPetEntryStatusDTO(petEntryStatusDTO);
        petDTO.setPetEntryTypeDTO(petEntryTypeDTO);
        petDTO.setPetAboutDTO(petAboutDTO);
        petDTO.setPetTypeDTO(petTypeDTO);
        return petDTO;
    }

    @Override
    public Object[] findPetAdmin(PetDTO petDTO, PetTypeDTO petTypeDTO,PetStatusTypeDTO petStatusTypeDTO, String propertySort, String propertyValue, Integer limit, Integer offset) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        PetTypeEntity petTypeEntity = PetTypeUtils.dto2Entity(petTypeDTO);
        PetStatusTypeEntity petStatusTypeEntity = PetStatusTypeUtils.dto2Entity(petStatusTypeDTO);
        Object[] objects = petDAO.findPetAdmin(petEntity,petTypeEntity,petStatusTypeEntity,propertySort,propertyValue,limit,offset);

        List<PetEntity> petEntities = (List<PetEntity>) objects[1];
        List<PetDTO> petDTOS = new ArrayList<>();
        for(PetEntity petEntity1 : petEntities){
            PetDTO petDTO1 = PetUtils.entity2DTO(petEntity1);
            UserDTO userDTO = UserUtils.entity2DTO(petEntity1.getUserEntity());
            petDTO1.setUserDTO(userDTO);
            List<PetStatusDTO> petStatusDTOs = new ArrayList<>();
            if(petEntity1.getPetStatusEntities() != null){
                for(PetStatusEntity petStatusEntityItem : petEntity1.getPetStatusEntities()){
                   PetStatusDTO  petStatusDTO = PetStatusUtils.entity2DTO(petStatusEntityItem);
                   PetStatusTypeDTO petStatusTypeDTO1 = PetStatusTypeUtils.entity2DTO(petStatusEntityItem.getPetStatusTypeEntity());
                   petStatusDTO.setPetStatusTypeDTO(petStatusTypeDTO1);
                   petStatusDTOs.add(petStatusDTO);
                }
            }
            petDTO1.setPetStatusDTOS(petStatusDTOs);
            petDTOS.add(petDTO1);
        }

        return new Object[]{objects[0],petDTOS};
    }
    @Override
    public List<PetStatusDTO> findByPetStatusById(Integer petId) {
        List<PetStatusEntity> petStatusEntities= petDAO.findPetSStatusByPetId(petId);

        List<PetStatusDTO> petStatusDTOS = new ArrayList<>();

        for(PetStatusEntity item : petStatusEntities){
            PetStatusDTO petStatusDTO = PetStatusUtils.entity2DTO(item);
            PetStatusTypeDTO petStatusTypeDTO = PetStatusTypeUtils.entity2DTO(item.getPetStatusTypeEntity());
            petStatusDTO.setPetStatusTypeDTO(petStatusTypeDTO);
            petStatusDTOS.add(petStatusDTO);
        }
        return petStatusDTOS;
    }
}
