package com.ck.services;

import com.ck.data.DepartmentEntity;
import com.ck.dto.DepartmentDTO;
import com.ck.entitydao.DepartmentDAO;
import com.ck.utils.DepartmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentDAO departmentDAO;
    @Override
    public void save(DepartmentDTO departmentDTO) {

    }

    @Override
    public void update(DepartmentDTO departmentDTO) {

    }

    @Override
    public void delete(List<DepartmentDTO> departmentDTOS) {

    }

    @Override
    public DepartmentDTO findById(Integer id) {
        return null;
    }

    @Override
    public List<DepartmentDTO> findAll() {
        List<DepartmentEntity> departmentEntities = departmentDAO.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for(DepartmentEntity departmentEntity : departmentEntities){
            DepartmentDTO departmentDTO = DepartmentUtils.entity2DTO(departmentEntity);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }
}
