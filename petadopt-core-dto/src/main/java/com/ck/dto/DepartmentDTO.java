package com.ck.dto;

import javax.persistence.*;
import java.util.List;


public class DepartmentDTO {

    private Integer departmentId;

    private String name;

    private List<EmployeeDTO> employeeDTOS;

    public DepartmentDTO() {
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDTO> getEmployeeDTOS() {
        return employeeDTOS;
    }

    public void setEmployeeDTOS(List<EmployeeDTO> employeeDTOS) {
        this.employeeDTOS = employeeDTOS;
    }
}
