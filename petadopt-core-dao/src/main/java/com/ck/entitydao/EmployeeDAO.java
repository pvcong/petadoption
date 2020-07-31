package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.DepartmentEntity;
import com.ck.data.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO extends GenericDAO<Integer, EmployeeEntity> {
    Object[] findEmployeeByProperties(EmployeeEntity employeeEntity, DepartmentEntity departmentEntity, String sortProperty, String sortValue, Integer limit, Integer offset);
}
