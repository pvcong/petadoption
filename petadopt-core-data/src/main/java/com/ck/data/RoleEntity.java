package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "role")
public class RoleEntity {
    @Id
    @Column( name = "role_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column( name = "role_name")
    private String role_name;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "roleEntity")
    private List<UserEntity> userEntities;

    public RoleEntity() {
    }

    public RoleEntity(String role_name, List<UserEntity> userEntities) {
        this.role_name = role_name;
        this.userEntities = userEntities;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
