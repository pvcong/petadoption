package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.RoleEntity;
import com.ck.data.UserEntity;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import javax.persistence.Query;
import java.util.List;
@Transactional
@Repository
public class UserDAOImpl extends GenericDAOImpl<Integer, UserEntity> implements UserDAO {

    @Override
    public UserEntity findById(Integer integer) {
        UserEntity userEntity = null;
        try{
            if(integer != null){
                userEntity = entityManager.find(UserEntity.class,integer);
                userEntity.getRoleEntity();
            }
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }

        return userEntity;
    }

    @Override
    public void updateRoleUser(UserEntity userEntity) {
        try{
            UserEntity userEntityPersist = entityManager.find(UserEntity.class,userEntity.getUserId());
            userEntityPersist.setRoleEntity(userEntity.getRoleEntity());
            entityManager.merge(userEntityPersist);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }

    }

    @Override
    public UserEntity checkLoin(String userName, String password) {

        UserEntity userEntity = null;
        if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)){
            StringBuilder stringQuery = new StringBuilder("FROM UserEntity u JOIN FETCH u.roleEntity r WHERE LOWER(u.userName) = :userName AND u.password = :password ");
            Query query = entityManager.createQuery(stringQuery.toString());
            query.setParameter("userName",userName);
            query.setParameter("password",password);
           try{
               List<UserEntity> userEntities = query.getResultList();
               for(UserEntity userEntity1 : userEntities){
                   userEntity = userEntity1;

               }
           } catch (HibernateException e){
              throw e;
           } finally {
               entityManager.close();
           }
        }

        return userEntity;
    }

    @Override
    public Object[] findUserAdmin(UserEntity userEntity, RoleEntity roleEntity, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<UserEntity> userEntities= null;
        Integer count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT u FROM UserEntity u " +
                "JOIN FETCH u.roleEntity r WHERE 1=1 ");
        if(roleEntity != null){
            if(roleEntity.getRoleId() != null){
                stringQuery.append(" AND r.roleId = :roleId");
            }
        }
        if(userEntity != null){
            if(userEntity.getUserId() != null){
                stringQuery.append(" AND u.userId = :userId");
            }
            if(!StringUtils.isEmpty(userEntity.getUserName())){
                stringQuery.append(" AND LOWER(u.userName) LIKE '%' || :userName || '%' ");

            }
        }

        if(!StringUtils.isEmpty(sortProperty) && !StringUtils.isEmpty(sortValue)){
            JpaUtils.buildOrderBy(stringQuery,"u." + sortProperty,sortValue);
        }else{
            JpaUtils.buildOrderBy(stringQuery,"u.modifiedDate",sortValue);
        }
        Query query = entityManager.createQuery(stringQuery.toString());

        JpaUtils.buildLimit2Offset(query,limit,offset);

        if(roleEntity != null){
            if(roleEntity.getRoleId() != null){
                query.setParameter("roleId",roleEntity.getRoleId());
            }
        }
        if(userEntity != null){
            if(userEntity.getUserId() != null){
                query.setParameter("userId",userEntity.getUserId());

            }
            if(!StringUtils.isEmpty(userEntity.getUserName())){
                query.setParameter("userName",userEntity.getUserName());

            }
        }

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT u) FROM UserEntity u " +
                "INNER JOIN u.roleEntity r WHERE 1=1 ");
        if(roleEntity != null){
            if(roleEntity.getRoleId() != null){
                stringQueryCount.append(" AND r.roleId = :roleId");
            }
        }
        if(userEntity != null){
            if(userEntity.getUserId() != null){
                stringQueryCount.append(" AND u.userId = :userId");
            }
            if(!StringUtils.isEmpty(userEntity.getUserName())){
                stringQueryCount.append(" AND LOWER(u.userName) LIKE '%' || :userName || '%' ");

            }
        }

        Query queryCount = entityManager.createQuery(stringQueryCount.toString());


        if(roleEntity != null){
            if(roleEntity.getRoleId() != null){
                queryCount.setParameter("roleId",roleEntity.getRoleId());
            }
        }
        if(userEntity != null){
            if(userEntity.getUserId() != null){
                queryCount.setParameter("userId",userEntity.getUserId());

            }
            if(!StringUtils.isEmpty(userEntity.getUserName())){
                queryCount.setParameter("userName",userEntity.getUserName());

            }
        }

        try {
            userEntities = query.getResultList();
            count =  Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }

        return new Object[]{count,userEntities};
    }

    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = null;
        StringBuilder stringQuery = new StringBuilder("SELECT u FROM UserEntity u JOIN FETCH u.roleEntity r WHERE u.userName = :userName");
        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("userName",userName);
        try{
           List<UserEntity> userEntities = query.getResultList();
           for(UserEntity userEntity1 : userEntities){
               userEntity = userEntity1;
           }
        }catch (HibernateException e){
            throw e;
        }
        return userEntity;
    }

    @Override
    public void delete(List<UserEntity> entities) {
        try{
            for(UserEntity userEntity : entities){
                UserEntity userEntityPersis = entityManager.find(UserEntity.class,userEntity.getUserId());
                entityManager.remove(userEntityPersis);
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
