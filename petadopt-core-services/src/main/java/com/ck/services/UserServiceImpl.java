package com.ck.services;

import com.ck.data.RoleEntity;
import com.ck.data.UserEntity;
import com.ck.dto.RoleDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.UserDAO;
import com.ck.utils.RoleUtils;
import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;
    @Override
    public void save(UserDTO userDTO) {
        UserEntity userEntity = UserUtils.dto2Entity(userDTO);
        RoleDTO roleDTO = userDTO.getRoleDTO();
        RoleEntity roleEntity = RoleUtils.dto2Entity(roleDTO);
        userEntity.setRoleEntity(roleEntity);
        userDAO.save(userEntity);
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity userEntity = UserUtils.dto2Entity(userDTO);
        userDAO.update(userEntity);
    }

    @Override
    public void updateRoleUser(UserDTO userDTO) {
        UserEntity userEntity = UserUtils.dto2Entity(userDTO);
        RoleEntity roleEntity = RoleUtils.dto2Entity(userDTO.getRoleDTO());
        userEntity.setRoleEntity(roleEntity);
        userDAO.updateRoleUser(userEntity);
    }

    @Override
    public void delete(List<UserDTO> userDTOs) {
        List<UserEntity> userEntities = new ArrayList<>();
        for(UserDTO userDTO : userDTOs){
            UserEntity userEntity = UserUtils.dto2Entity(userDTO);
            userEntities.add(userEntity);
        }
        userDAO.delete(userEntities);

    }

    @Override
    public UserDTO findById(Integer id) {
        UserEntity userEntity = userDAO.findById(id);
        UserDTO userDTO = UserUtils.entity2DTO(userEntity);
        RoleEntity roleEntity = userEntity.getRoleEntity();
        RoleDTO roleDTO = RoleUtils.entity2DTO(roleEntity);
        userDTO.setRoleDTO(roleDTO);
        return userDTO;
    }

    @Override
    public UserDTO checkLogin(String userName, String password) {
        UserEntity userEntity = userDAO.checkLoin(userName,password);

        UserDTO userDTO = null;
        if(userEntity != null){
            userDTO = new UserDTO();
            userDTO.setUserId(userEntity.getUserId());
            userDTO.setUserName(userEntity.getUserName());
            userDTO.setAvatar(userEntity.getAvatar());
            userDTO.setFullName(userEntity.getFullName());
            RoleEntity roleEntity = userEntity.getRoleEntity();
            RoleDTO roleDTO = RoleUtils.entity2DTO(roleEntity);
            userDTO.setRoleDTO(roleDTO);
        }
        return userDTO;
    }

    @Override
    public Boolean checkPropertyValueExist(String propertyName, String propertyValue) {
        Boolean isExits = userDAO.checkPropertyValueExist(propertyName,propertyValue);
        return isExits;
    }

    @Override
    public UserDTO findUserByUserName(String userName) {
        UserEntity userEntity = userDAO.findByUserName(userName);
        if(userEntity == null){
            throw new UsernameNotFoundException(userName);
        }

        return UserUtils.entity2DTO(userEntity);
    }

    @Override
    public Object[] findUserAdmin(UserDTO userDTO, RoleDTO roleDTO, String sortProperty, String sortValue, Integer limit, Integer offset) {
        UserEntity userEntity = UserUtils.dto2Entity(userDTO);
        RoleEntity roleEntity = RoleUtils.dto2Entity(roleDTO);

        Object[] objects = userDAO.findUserAdmin(userEntity,roleEntity,sortProperty,sortValue,limit,offset);

        List<UserEntity> userEntities = (List<UserEntity>) objects[1];
        List<UserDTO> userDTOS = new ArrayList<>();

        for(UserEntity userEntity1 : userEntities){
            UserDTO userDTO1 = UserUtils.entity2DTO(userEntity1);
            RoleDTO roleDTO1 = RoleUtils.entity2DTO(userEntity1.getRoleEntity());
            userDTO1.setRoleDTO(roleDTO1);

            userDTOS.add(userDTO1);
        }
        return new Object[]{objects[0],userDTOS};
    }
}
