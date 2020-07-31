package com.ck.services;

import com.ck.data.EmployeeEntity;
import com.ck.data.FosterPetEntity;
import com.ck.data.PetEntity;
import com.ck.data.RescueOrderEntity;
import com.ck.dto.EmployeeDTO;
import com.ck.dto.FosterPetDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.RescueOrderDTO;
import com.ck.entitydao.FosterPetDAO;
import com.ck.entitydao.RescueOrderDAO;
import com.ck.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FosterPetServiceImpl implements FosterPetService {
    @Autowired
    FosterPetDAO fosterPetDAO;
    @Override
    public void save(FosterPetDTO fosterPetDTO) {
        FosterPetEntity fosterPetEntity = FosterPetUtils.dto2Entity(fosterPetDTO);
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(fosterPetDTO.getEmployeeDTO());


        fosterPetEntity.setEmployeeEntity(employeeEntity);
        fosterPetDAO.save(fosterPetEntity);
    }

    @Override
    public void update(FosterPetDTO fosterPetDTO) {
        FosterPetEntity fosterPetEntity = FosterPetUtils.dto2Entity(fosterPetDTO);
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(fosterPetDTO.getEmployeeDTO());
        fosterPetEntity.setEmployeeEntity(employeeEntity);
        fosterPetDAO.update(fosterPetEntity);

    }

    @Override
    public void delete(List<FosterPetDTO> fosterPetDTOS) {

    }

//    @Override
//    public void delete(List<RescueOrderDTO> rescueOrderDTOS) {
//
//    }

//    @Override
//    public void delete(List<RescueOrderDTO> rescueOrderDTOS) {
//
//    }

    @Override
    public FosterPetDTO findById(Integer id) {
        FosterPetEntity fosterPetEntity = fosterPetDAO.findById(id);

            FosterPetDTO fosterPetDTOItem = FosterPetUtils.entity2DTO(fosterPetEntity);
            List<PetDTO> petDTOS = new ArrayList<>();
            EmployeeDTO employeeDTO = EmployeeUtils.entity2DTO(fosterPetEntity.getEmployeeEntity());
            for(PetEntity petEntityItem : fosterPetEntity.getPetEntities()){
                PetDTO petDTO = PetUtils.entity2DTO(petEntityItem);
                petDTOS.add(petDTO);
            }
            fosterPetDTOItem.setEmployeeDTO(employeeDTO);
            fosterPetDTOItem.setPetDTOS(petDTOS);

        return fosterPetDTOItem;
    }

    @Override
    public Object[] findFosterPetByPropertiesAdmin(FosterPetDTO fosterPetDTO, Integer limit, Integer offset) {
        PetEntity petEntity = null;
        EmployeeEntity employeeEntity = null;
        if(fosterPetDTO != null){
            if(fosterPetDTO.getEmployeeDTO() != null){
                employeeEntity = EmployeeUtils.dto2Entity(fosterPetDTO.getEmployeeDTO());
            }
        }
        FosterPetEntity fosterPetEntity = FosterPetUtils.dto2Entity(fosterPetDTO);
        Object[] objects = fosterPetDAO.findRescueOrderByProperties(fosterPetEntity,employeeEntity,limit,offset);
        List<FosterPetEntity> fosterPetEntities = (List<FosterPetEntity>) objects[1];
        List<FosterPetDTO> fosterPetDTOS = new ArrayList<>();
        for(FosterPetEntity item : fosterPetEntities){
            FosterPetDTO fosterPetDTOItem = FosterPetUtils.entity2DTO(item);
            List<PetDTO> petDTOS = new ArrayList<>();
            EmployeeDTO employeeDTO = EmployeeUtils.entity2DTO(item.getEmployeeEntity());
            for(PetEntity petEntityItem : item.getPetEntities()){
                PetDTO petDTO = PetUtils.entity2DTO(petEntityItem);
                petDTOS.add(petDTO);
            }
            fosterPetDTOItem.setEmployeeDTO(employeeDTO);
            fosterPetDTOItem.setPetDTOS(petDTOS);
            fosterPetDTOS.add(fosterPetDTOItem);
        }

        return new Object[]{objects[0],fosterPetDTOS};
    }

    @Override
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset) {
        Object[] objects = fosterPetDAO.findPetOfEmployye(employeeId,limit,offset);
        List<FosterPetEntity> fosterPetEntities = (List<FosterPetEntity>) objects[1];
        List<FosterPetDTO> fosterpetDTOS = new ArrayList<>();
        for(FosterPetEntity fosterPetEntity : fosterPetEntities){
            FosterPetDTO fosterPetDTO = FosterPetUtils.entity2DTO(fosterPetEntity);
            List<PetEntity> petEntities = fosterPetEntity.getPetEntities();
            List<PetDTO> petDTOS = new ArrayList<>();
            for(PetEntity petEntity : petEntities){
                PetDTO petDTO = PetUtils.entity2DTO(petEntity);
                petDTOS.add(petDTO);
            }
            fosterPetDTO.setPetDTOS(petDTOS);
            fosterpetDTOS.add(fosterPetDTO);
        }


        return new Object[]{objects[0],fosterpetDTOS};
    }

    @Override
    public void addPet(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        FosterPetEntity fosterPetEntity = FosterPetUtils.dto2Entity(petDTO.getFosterPetDTO());
        petEntity.setFosterPetEntity(fosterPetEntity);
        fosterPetDAO.updatePet(petEntity);
    }

    @Override
    public void removePet(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        FosterPetEntity fosterPetEntity = FosterPetUtils.dto2Entity(petDTO.getFosterPetDTO());
        petEntity.setFosterPetEntity(fosterPetEntity);
        fosterPetDAO.deletePet(petEntity);
    }
    @Override
    public Object[] findPetDontFoster(PetDTO petDTO, Integer limit, Integer offset) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        Object[] objects = fosterPetDAO.findPetDontFoster(petEntity, limit, offset);
        List<PetEntity> petEntities = (List<PetEntity>) objects[1];
        List<PetDTO> petDTOS = new ArrayList<>();

        for (PetEntity item : petEntities) {
            PetDTO petDTOItem = PetUtils.entity2DTO(item);
            petDTOS.add(petDTOItem);
        }
        return new Object[]{objects[0], petDTOS};
    }

    @Override
    public Boolean checkPetIsFoster(Integer petId) {
        return fosterPetDAO.checkPetIsFoster(petId);
    }
}
