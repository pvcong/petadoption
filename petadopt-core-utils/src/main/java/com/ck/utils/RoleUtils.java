package com.ck.utils;

import com.ck.data.RoleEntity;
import com.ck.dto.RoleDTO;

public class RoleUtils {
    public static RoleEntity dto2Entity(RoleDTO roleDTO){
        RoleEntity roleEntity = new RoleEntity();
        if(roleDTO != null){
           roleEntity.setRoleId(roleDTO.getRoleId());
           roleEntity.setRole_name(roleDTO.getRole_name());


        }
        return roleEntity;
    }
    public static RoleDTO entity2DTO(RoleEntity roleEntity){
        RoleDTO roleDTO = new RoleDTO();
        if(roleEntity != null){
            roleDTO.setRoleId(roleEntity.getRoleId());
            roleDTO.setRole_name(roleEntity.getRole_name());


        }
        return roleDTO;
    }
}
