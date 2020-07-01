package com.ck.services;

import com.ck.data.RoleEntity;
import com.ck.dto.RoleDTO;
import com.ck.entitydao.RoleDAO;
import com.ck.utils.RoleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
    @Override
    public void save(RoleDTO roleDTO) {
        RoleEntity roleEntity = RoleUtils.dto2Entity(roleDTO);
        roleDAO.save(roleEntity);

    }

    @Override
    public void update(RoleDTO roleDTO) {
        RoleEntity roleEntity = RoleUtils.dto2Entity(roleDTO);
        roleDAO.update(roleEntity);
    }

    @Override
    public void delete(List<RoleDTO> roleDTOS) {
        List<RoleEntity> roleEntities = new ArrayList<>();
        for(RoleDTO roleDTO : roleDTOS){
            RoleEntity roleEntity = RoleUtils.dto2Entity(roleDTO);
            roleEntities.add(roleEntity);
        }
        roleDAO.delete(roleEntities);
    }

    @Override
    public RoleDTO findById(Integer id) {
        RoleEntity roleEntity = roleDAO.findById(id);
        RoleDTO roleDTO = RoleUtils.entity2DTO(roleEntity);
        return roleDTO;
    }
}
