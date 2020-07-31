package com.ck.services;

import com.ck.data.EmployeeEntity;
import com.ck.data.PetEntity;
import com.ck.data.RescueOrderEntity;
import com.ck.dto.EmployeeDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.RescueOrderDTO;
import com.ck.entitydao.RescueOrderDAO;
import com.ck.utils.EmployeeUtils;
import com.ck.utils.PetUtils;
import com.ck.utils.RescueOrderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RescueOrderServiceImpl implements RescueOrderService {
    @Autowired
    RescueOrderDAO rescueOrderDAO;

    @Override
    public void save(RescueOrderDTO rescueOrderDTO) {
        RescueOrderEntity rescueOrderEntity = RescueOrderUtils.dto2Entity(rescueOrderDTO);
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(rescueOrderDTO.getEmployeeDTO());

        rescueOrderEntity.setEmployeeEntity(employeeEntity);
        rescueOrderDAO.save(rescueOrderEntity);
    }

    @Override
    public void update(RescueOrderDTO rescueOrderDTO) {
        RescueOrderEntity rescueOrderEntity = RescueOrderUtils.dto2Entity(rescueOrderDTO);
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(rescueOrderDTO.getEmployeeDTO());
        rescueOrderEntity.setEmployeeEntity(employeeEntity);
        rescueOrderDAO.update(rescueOrderEntity);

    }

    @Override
    public void addPet(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        RescueOrderEntity rescueOrderEntity = RescueOrderUtils.dto2Entity(petDTO.getRescueOrderDTO());
        petEntity.setRescueOrderEntity(rescueOrderEntity);
        rescueOrderDAO.updatePet(petEntity);
    }

    @Override
    public void removePet(PetDTO petDTO) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        RescueOrderEntity rescueOrderEntity = RescueOrderUtils.dto2Entity(petDTO.getRescueOrderDTO());
        petEntity.setRescueOrderEntity(rescueOrderEntity);
        rescueOrderDAO.deletePet(petEntity);
    }

    @Override
    public void delete(List<RescueOrderDTO> rescueOrderDTOS) {

    }

//    @Override
//    public void delete(List<RescueOrderDTO> rescueOrderDTOS) {
//
//    }

    @Override
    public RescueOrderDTO findById(Integer id) {
        RescueOrderEntity rescueOrderEntity = rescueOrderDAO.findById(id);

        RescueOrderDTO rescueOrderDTOItem = RescueOrderUtils.entity2DTO(rescueOrderEntity);
        List<PetDTO> petDTOS = new ArrayList<>();
        EmployeeDTO employeeDTO = EmployeeUtils.entity2DTO(rescueOrderEntity.getEmployeeEntity());
        for (PetEntity petEntityItem : rescueOrderEntity.getPetEntities()) {
            PetDTO petDTO = PetUtils.entity2DTO(petEntityItem);
            petDTOS.add(petDTO);
        }
        rescueOrderDTOItem.setEmployeeDTO(employeeDTO);
        rescueOrderDTOItem.setPetDTOS(petDTOS);

        return rescueOrderDTOItem;
    }

    @Override
    public Object[] findRescueOrderByPropertiesAdmin(RescueOrderDTO rescueOrderDTO, Integer limit, Integer offset) {
        PetEntity petEntity = null;
        EmployeeEntity employeeEntity = null;
        if (rescueOrderDTO != null) {
            if (rescueOrderDTO.getEmployeeDTO() != null) {
                employeeEntity = EmployeeUtils.dto2Entity(rescueOrderDTO.getEmployeeDTO());
            }
        }
        RescueOrderEntity rescueOrderEntity = RescueOrderUtils.dto2Entity(rescueOrderDTO);
        Object[] objects = rescueOrderDAO.findRescueOrderByProperties(rescueOrderEntity, employeeEntity, limit, offset);
        List<RescueOrderEntity> rescueOrderEntities = (List<RescueOrderEntity>) objects[1];
        List<RescueOrderDTO> rescueOrderDTOS = new ArrayList<>();
        for (RescueOrderEntity item : rescueOrderEntities) {
            RescueOrderDTO rescueOrderDTOItem = RescueOrderUtils.entity2DTO(item);
            List<PetDTO> petDTOS = new ArrayList<>();
            EmployeeDTO employeeDTO = EmployeeUtils.entity2DTO(item.getEmployeeEntity());
            for (PetEntity petEntityItem : item.getPetEntities()) {
                PetDTO petDTO = PetUtils.entity2DTO(petEntityItem);
                petDTOS.add(petDTO);
            }
            rescueOrderDTOItem.setEmployeeDTO(employeeDTO);
            rescueOrderDTOItem.setPetDTOS(petDTOS);
            rescueOrderDTOS.add(rescueOrderDTOItem);
        }

        return new Object[]{objects[0], rescueOrderDTOS};
    }

    public Object[] findPetDontOrder(PetDTO petDTO, Integer limit, Integer offset) {
        PetEntity petEntity = PetUtils.dto2Entity(petDTO);
        Object[] objects = rescueOrderDAO.findPetDontOrder(petEntity, limit, offset);
        List<PetEntity> petEntities = (List<PetEntity>) objects[1];
        List<PetDTO> petDTOS = new ArrayList<>();

        for (PetEntity item : petEntities) {
            PetDTO petDTOItem = PetUtils.entity2DTO(item);
            petDTOS.add(petDTOItem);
        }
        return new Object[]{objects[0], petDTOS};
    }
    @Override
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset) {
        Object[] objects = rescueOrderDAO.findPetOfEmployye(employeeId,limit,offset);
        List<RescueOrderEntity> rescueOrderEntities = (List<RescueOrderEntity>) objects[1];
        List<RescueOrderDTO> rescueOrderDTOS = new ArrayList<>();
        for(RescueOrderEntity rescueOrderEntity : rescueOrderEntities){
            RescueOrderDTO rescueOrderDTO = RescueOrderUtils.entity2DTO(rescueOrderEntity);
            List<PetEntity> petEntities = rescueOrderEntity.getPetEntities();
            List<PetDTO> petDTOS = new ArrayList<>();
            for(PetEntity petEntity : petEntities){
                PetDTO petDTO = PetUtils.entity2DTO(petEntity);
                petDTOS.add(petDTO);
            }
            rescueOrderDTO.setPetDTOS(petDTOS);
            rescueOrderDTOS.add(rescueOrderDTO);
        }


        return new Object[]{objects[0],rescueOrderDTOS};
    }

}
