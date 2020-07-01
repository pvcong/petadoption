package com.ck.services;

import com.ck.data.UserEntity;
import com.ck.dto.CustomUserDetails;
import com.ck.dto.RoleDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.UserDAO;
import com.ck.utils.RoleUtils;
import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userDAO.findByUserName(userName);
        if(userEntity == null){
            throw new UsernameNotFoundException(userName);
        }
        UserDTO userDTO = UserUtils.entity2DTO(userEntity);
        RoleDTO roleDTO = RoleUtils.entity2DTO(userEntity.getRoleEntity());
        userDTO.setRoleDTO(roleDTO);
        return new CustomUserDetails(userDTO);
    }

}
