package com.ck.services;

import com.ck.data.EmployeeEntity;
import com.ck.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public void save(EmployeeDTO employeeDTO);
    public void update(EmployeeDTO employeeDTO);
    public void delete(List<EmployeeDTO> employeeDTOS);
    public EmployeeDTO findById(Integer id);
    public Object[] findEmployeeByPropertiesAdmin(EmployeeDTO employeeDTO,Integer limit,Integer offset);
}
