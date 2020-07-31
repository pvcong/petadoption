package com.ck.services;

import com.ck.data.DepartmentEntity;
import com.ck.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    public void save(DepartmentDTO departmentDTO);
    public void update(DepartmentDTO departmentDTO);
    public void delete(List<DepartmentDTO> departmentDTOS);
    public DepartmentDTO findById(Integer id);
    public List<DepartmentDTO> findAll();

}
