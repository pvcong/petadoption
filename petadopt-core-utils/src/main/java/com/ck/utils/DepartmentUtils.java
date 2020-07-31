package com.ck.utils;

import com.ck.data.DepartmentEntity;
import com.ck.data.RoleEntity;
import com.ck.dto.DepartmentDTO;
import com.ck.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class DepartmentUtils {
    public static DepartmentEntity dto2Entity(DepartmentDTO departmentDTO){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        if(departmentDTO != null){
            departmentEntity.setDepartmentId(departmentDTO.getDepartmentId());
            departmentEntity.setName(departmentDTO.getName());


        }
        return departmentEntity;
    }

    public static DepartmentDTO entity2DTO(DepartmentEntity departmentEntity){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        if(departmentEntity != null){
            departmentDTO.setDepartmentId(departmentEntity.getDepartmentId());
            departmentDTO.setName(departmentEntity.getName());


        }
        return departmentDTO;
    }

    public static List<DepartmentEntity> dtos2Entities(List<DepartmentDTO> departmentDTOS){
        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        for(DepartmentDTO item : departmentDTOS){
            DepartmentEntity departmentEntity = dto2Entity(item);
            departmentEntities.add(departmentEntity);
        }
        return departmentEntities;
    }
    public static List<DepartmentDTO> entities2DTOs(List<DepartmentEntity> departmentEntities){
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for(DepartmentEntity item : departmentEntities){
            DepartmentDTO departmentDTO = entity2DTO(item);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }
}
