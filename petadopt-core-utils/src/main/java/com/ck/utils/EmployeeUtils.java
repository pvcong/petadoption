package com.ck.utils;

import com.ck.data.EmployeeEntity;
import com.ck.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtils {
    public static EmployeeEntity dto2Entity(EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        if(employeeDTO != null){
            employeeEntity.setAddress(employeeDTO.getAddress());
            employeeEntity.setBirthday(employeeDTO.getBirthday());
            employeeEntity.setFullName(employeeDTO.getFullName());
            employeeEntity.setJoinDate(employeeDTO.getJoinDate());
            employeeEntity.setFirstName(employeeDTO.getFirstName());
            employeeEntity.setLastName(employeeDTO.getLastName());
            employeeEntity.setCreatedDate(employeeDTO.getCreatedDate());
            employeeEntity.setEmployeeId(employeeDTO.getEmployeeId());
            employeeEntity.setGender(employeeDTO.getGender());
            employeeEntity.setModifiedDate(employeeDTO.getModifiedDate());
            employeeEntity.setPhoneNumber(employeeDTO.getPhoneNumber());
            employeeEntity.setRegion(employeeDTO.getRegion());


        }
        return employeeEntity;
    }

    public static EmployeeDTO entity2DTO(EmployeeEntity employeeEntity){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if(employeeEntity != null){
            if(employeeDTO != null){
                employeeDTO.setJoinDate(employeeEntity.getJoinDate());
                employeeDTO.setAddress(employeeEntity.getAddress());
                employeeDTO.setBirthday(employeeEntity.getBirthday());
                employeeDTO.setFullName(employeeEntity.getFullName());
                employeeDTO.setFirstName(employeeEntity.getFirstName());
                employeeDTO.setLastName(employeeEntity.getLastName());
                employeeDTO.setCreatedDate(employeeEntity.getCreatedDate());
                employeeDTO.setEmployeeId(employeeEntity.getEmployeeId());
                employeeDTO.setGender(employeeEntity.getGender());
                employeeDTO.setModifiedDate(employeeEntity.getModifiedDate());
                employeeDTO.setPhoneNumber(employeeEntity.getPhoneNumber());
                employeeDTO.setRegion(employeeEntity.getRegion());


            }


        }
        return employeeDTO;
    }

    public static List<EmployeeEntity> dtos2Entities(List<EmployeeDTO> employeeDTOS){
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        for(EmployeeDTO item : employeeDTOS){
            EmployeeEntity employeeEntity = dto2Entity(item);
            employeeEntities.add(employeeEntity);
        }
        return employeeEntities;
    }
    public static List<EmployeeDTO> entities2DTOs(List<EmployeeEntity> employeeEntities){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity item : employeeEntities){
            EmployeeDTO employeeDTO = entity2DTO(item);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }
}
