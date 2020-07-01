package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


public class RoleDTO {

    private Integer roleId;

    private String role_name;

    private List<UserDTO> userDTOs;

    public RoleDTO() {
    }

    public RoleDTO(Integer roleId, String role_name, List<UserDTO> userDTOs) {
        this.roleId = roleId;
        this.role_name = role_name;
        this.userDTOs = userDTOs;
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

    public List<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }
}
