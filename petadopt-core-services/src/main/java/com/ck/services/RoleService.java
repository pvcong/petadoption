package com.ck.services;

import com.ck.dto.RoleDTO;
import com.ck.entitydao.RoleDAO;

import java.util.List;

public interface RoleService {
    public void save(RoleDTO roleDTO);
    public void update(RoleDTO roleDTO);
    public void delete(List<RoleDTO> roleDTOS);
    public RoleDTO findById(Integer id);
}
