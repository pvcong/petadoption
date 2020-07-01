package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.RoleEntity;
import com.ck.data.UserEntity;

import java.util.List;

public interface UserDAO extends GenericDAO<Integer, UserEntity> {
    void updateRoleUser(UserEntity userEntity);
    UserEntity checkLoin(String userName, String password);
    Object[] findUserAdmin(UserEntity userEntity, RoleEntity roleEntity, String sortProperty, String sortValue, Integer limit, Integer offset);
    UserEntity findByUserName(String userName);
}
