package com.ck.utils;

import com.ck.data.UserEntity;
import com.ck.dto.UserDTO;
import org.springframework.util.StringUtils;

public class UserUtils {
    public static UserEntity dto2Entity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        if(userDTO != null){
            userEntity.setUserId(userDTO.getUserId());
            if(!StringUtils.isEmpty(userDTO.getUserName()))
            userEntity.setUserName(userDTO.getUserName().toLowerCase());
            userEntity.setFullName(userDTO.getFullName());
            userEntity.setPhoneNumber(userDTO.getPhoneNumber());
            if(!StringUtils.isEmpty(userDTO.getEmail()))
            userEntity.setEmail(userDTO.getEmail().toLowerCase());
            userEntity.setGender(userDTO.getGender());
            userEntity.setAvatar(userDTO.getAvatar());
            userEntity.setBirthday(userDTO.getBirthday());
            userEntity.setModifiedDate(userDTO.getModifiedDate());
            userEntity.setCreatedDate(userDTO.getCreatedDate());

        }
        return userEntity;
    }
    public static UserDTO entity2DTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        if(userEntity != null){
            userDTO.setUserId(userEntity.getUserId());
            userDTO.setUserName(userEntity.getUserName());
            userDTO.setFullName(userEntity.getFullName());
            userDTO.setPhoneNumber(userEntity.getPhoneNumber());
            userDTO.setEmail(userEntity.getEmail());
            userDTO.setGender(userEntity.getGender());
            userDTO.setAvatar(userEntity.getAvatar());
            userDTO.setBirthday(userEntity.getBirthday());
            userDTO.setModifiedDate(userEntity.getModifiedDate());
            userDTO.setCreatedDate(userEntity.getCreatedDate());

        }
        return userDTO;
    }
}
