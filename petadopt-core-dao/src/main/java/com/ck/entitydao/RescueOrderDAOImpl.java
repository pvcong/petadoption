package com.ck.entitydao;

import com.ck.constant.DAOConstant;
import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.EmployeeEntity;
import com.ck.data.PetEntity;
import com.ck.data.RescueOrderEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class RescueOrderDAOImpl extends GenericDAOImpl<Integer, RescueOrderEntity> implements RescueOrderDAO {
    @Override
    public RescueOrderEntity findById(Integer integer) {
        RescueOrderEntity rescueOrderEntity = null;
        try {
            rescueOrderEntity = entityManager.find(persistenClass,integer);
            if(rescueOrderEntity == null){
                throw new NotFoundObjectException();
            }
            rescueOrderEntity.getPetEntities();
            rescueOrderEntity.getEmployeeEntity();
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return rescueOrderEntity;
    }

    @Override
    public void save(RescueOrderEntity entity) {
        try {
            entityManager.persist(entity);

        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void update(RescueOrderEntity entity) {
        RescueOrderEntity rescueOrderEntityPersis = entityManager.find(persistenClass,entity.getRescueOrderId());
        entity.setCreatedDate(rescueOrderEntityPersis.getModifiedDate());

        try{
            entityManager.merge(entity);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Object[] findRescueOrderByProperties(RescueOrderEntity rescueOrderEntity, EmployeeEntity employeeEntity, Integer limit, Integer offset) {
        List<RescueOrderEntity> rescueOrderEntities = new ArrayList<>();
        int count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT ro FROM RescueOrderEntity ro " +
                "JOIN FETCH ro.employeeEntity e " +
                "LEFT JOIN FETCH ro.petEntities p WHERE 1=1 ");
        if(rescueOrderEntity != null){
            if(rescueOrderEntity.getRescueOrderId() != null){
                stringQuery.append(" AND ro.rescueOrderId = :rescueOrderId");
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQuery.append(" AND e.employeeId = :employeeId");
            }
        }

        JpaUtils.buildOrderBy(stringQuery,"ro.createdDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);

        if(rescueOrderEntity != null){
            if(rescueOrderEntity.getRescueOrderId() != null){
                query.setParameter("rescueOrderId",rescueOrderEntity.getRescueOrderId());
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                query.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
        }
        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT ro) FROM RescueOrderEntity ro " +
                "INNER JOIN ro.employeeEntity e " +
                "LEFT JOIN ro.petEntities p WHERE 1=1 ");
        if(rescueOrderEntity != null){
            if(rescueOrderEntity.getRescueOrderId() != null){
                stringQueryCount.append(" AND ro.rescueOrderId = :rescueOrderId");
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQueryCount.append(" AND e.employeeId = :employeeId");
            }
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());

        if(rescueOrderEntity != null){
            if(rescueOrderEntity.getRescueOrderId() != null){
                queryCount.setParameter("rescueOrderId",rescueOrderEntity.getRescueOrderId());
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                queryCount.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
        }

        try {
            rescueOrderEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){

        }
        return new Object[]{count,rescueOrderEntities};
    }

    @Override
    public void updatePet(PetEntity petEntity) {
        try {

            PetEntity petEntityPersis = entityManager.find(PetEntity.class,petEntity.getPetId());
            if(petEntityPersis == null){
                throw new NotFoundObjectException();
            }
            petEntityPersis.setRescueOrderEntity(petEntity.getRescueOrderEntity());
            entityManager.merge(petEntityPersis);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void deletePet(PetEntity petEntity) {
        try {
            PetEntity petEntityPersis = entityManager.find(PetEntity.class,petEntity.getPetId());
            if(petEntityPersis == null){
                throw new NotFoundObjectException();
            }
            petEntityPersis.setRescueOrderEntity(null);
            entityManager.merge(petEntityPersis);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Object[] findPetDontOrder(PetEntity petEntity, Integer limit, Integer offset) {
        List<PetEntity> petEntities = null;
        Integer count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM PetEntity p" +
                " WHERE 1=1 AND p.rescueOrderEntity IS NULL");

        if(petEntity != null){

            if(petEntity.getPetId() != null){
                stringQuery.append(" AND p.petId = :petId");
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQuery.append(" AND LOWER(p.petName) LIKE '%' || :petName || '%' ");
            }

        }

            JpaUtils.buildOrderBy(stringQuery,"p.modifiedDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());

        JpaUtils.buildLimit2Offset(query,limit,offset);


        if(petEntity != null){
            if(petEntity.getPetId() != null){
                query.setParameter("petId",petEntity.getPetId());
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                query.setParameter("petName",petEntity.getPetName());
            }
        }

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT p) FROM PetEntity p" +

                "  WHERE 1=1 AND p.rescueOrderEntity IS NULL");

        if(petEntity != null){
            if(petEntity.getPetId() != null){
                stringQueryCount.append(" AND p.petId = :petId");
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQueryCount.append(" AND LOWER(p.petName) LIKE '%' || :petName || '%' ");
            }

        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());



        if(petEntity != null){
            if(petEntity.getPetId() != null){
                queryCount.setParameter("petId",petEntity.getPetId());
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                queryCount.setParameter("petName",petEntity.getPetName());
            }

        }

        try {
            petEntities = query.getResultList();
            count =  Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        } catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }


        return new Object[]{count,petEntities};
    }
    @Override
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset) {
        List<RescueOrderEntity> rescueOrderEntities = new ArrayList<>();
        int count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT ro FROM RescueOrderEntity ro");
        stringQuery.append(" INNER JOIN ro.employeeEntity e ");
        stringQuery.append(" JOIN FETCH ro.petEntities p WHERE e.employeeId = :employeeId ");

        JpaUtils.buildOrderBy(stringQuery,"ro.dateRescue","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);
        query.setParameter("employeeId",employeeId);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(ro) FROM RescueOrderEntity ro");
        stringQueryCount.append(" INNER JOIN ro.employeeEntity e ");
        stringQueryCount.append(" INNER JOIN ro.petEntities p WHERE e.employeeId = :employeeId ");


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        queryCount.setParameter("employeeId",employeeId);

        try {
            rescueOrderEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,rescueOrderEntities};
    }
}
