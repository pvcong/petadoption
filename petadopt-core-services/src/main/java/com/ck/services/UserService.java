package com.ck.services;

import com.ck.data.RoleEntity;
import com.ck.data.UserEntity;
import com.ck.dto.RoleDTO;
import com.ck.dto.UserDTO;

import java.util.List;

public interface UserService {
    public void save(UserDTO userDTO);
    public void update(UserDTO userDTO);
    public void updateRoleUser(UserDTO userDTO);
    public void delete(List<UserDTO> userDTOs);
    public UserDTO findById(Integer id);
    public UserDTO checkLogin(String userName, String password);
    public Boolean checkPropertyValueExist(String propertyName, String propertyValue);
    public UserDTO findUserByUserName(String userName);
    public Object[] findUserAdmin(UserDTO userDTO, RoleDTO roleDTO,String sortProperty, String sortValue,Integer limit, Integer offset);
}
