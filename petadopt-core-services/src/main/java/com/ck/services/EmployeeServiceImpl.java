package com.ck.services;

import com.ck.data.DepartmentEntity;
import com.ck.data.EmployeeEntity;
import com.ck.dto.DepartmentDTO;
import com.ck.dto.EmployeeDTO;
import com.ck.entitydao.EmployeeDAO;
import com.ck.utils.DepartmentUtils;
import com.ck.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService{
    @Autowired
    EmployeeDAO employeeDAO;
    @Override
    public void save(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(employeeDTO);
        DepartmentEntity departmentEntity = DepartmentUtils.dto2Entity(employeeDTO.getDepartmentDTO());
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeDAO.save(employeeEntity);
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(employeeDTO);
        DepartmentEntity departmentEntity = DepartmentUtils.dto2Entity(employeeDTO.getDepartmentDTO());
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeDAO.update(employeeEntity);
    }

    @Override
    public void delete(List<EmployeeDTO> employeeDTOS) {

    }

    @Override
    public EmployeeDTO findById(Integer id) {
        EmployeeEntity employeeEntity = employeeDAO.findById(id);
        DepartmentEntity departmentEntity = employeeEntity.getDepartmentEntity();
        EmployeeDTO employeeDTO = EmployeeUtils.entity2DTO(employeeEntity);
        DepartmentDTO departmentDTO = DepartmentUtils.entity2DTO(departmentEntity);
        employeeDTO.setDepartmentDTO(departmentDTO);
        return  employeeDTO;

    }

    @Override
    public Object[] findEmployeeByPropertiesAdmin(EmployeeDTO employeeDTO, Integer limit, Integer offset) {
        DepartmentEntity departmentEntity = null;
        if(employeeDTO != null){
            if(employeeDTO.getDepartmentDTO() != null){
                departmentEntity = DepartmentUtils.dto2Entity(employeeDTO.getDepartmentDTO());
            }
        }
        EmployeeEntity employeeEntity = EmployeeUtils.dto2Entity(employeeDTO);
        employeeEntity.setDepartmentEntity(departmentEntity);
        Object[] objects = employeeDAO.findEmployeeByProperties(employeeEntity,departmentEntity,null,null,limit,offset);
        List<EmployeeEntity> employeeEntities = (List<EmployeeEntity>) objects[1];
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity item : employeeEntities){
            EmployeeDTO employeeDTO1 = EmployeeUtils.entity2DTO(item);
            DepartmentDTO departmentDTO = DepartmentUtils.entity2DTO(item.getDepartmentEntity());
            employeeDTO1.setDepartmentDTO(departmentDTO);
            employeeDTOS.add(employeeDTO1);
        }
        return new Object[]{objects[0],employeeDTOS};
    }


}
