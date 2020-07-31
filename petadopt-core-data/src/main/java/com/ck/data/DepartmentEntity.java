package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer departmentId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "departmentEntity")
    private List<EmployeeEntity> employeeEntities;

    public DepartmentEntity() {
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

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }
}
