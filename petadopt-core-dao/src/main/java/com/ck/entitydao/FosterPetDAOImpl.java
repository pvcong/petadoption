package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.EmployeeEntity;
import com.ck.data.FosterPetEntity;
import com.ck.data.PetEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FosterPetDAOImpl extends GenericDAOImpl<Integer, FosterPetEntity> implements FosterPetDAO {
    @Override
    public FosterPetEntity findById(Integer integer) {
        FosterPetEntity fosterPetEntity = null;
        try {
            fosterPetEntity = entityManager.find(persistenClass,integer);
            if(fosterPetEntity == null){
                throw new NotFoundObjectException();
            }
            fosterPetEntity.getPetEntities();
            fosterPetEntity.getEmployeeEntity();
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return fosterPetEntity;
    }

    @Override
    public void save(FosterPetEntity entity) {
        try {
            entityManager.persist(entity);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void update(FosterPetEntity entity) {
        FosterPetEntity rescueOrderEntityPersis = entityManager.find(persistenClass,entity.getFosterPetId());
        try{
            entityManager.merge(entity);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }



    @Override
    public Object[] findRescueOrderByProperties(FosterPetEntity fosterPetEntity, EmployeeEntity employeeEntity, Integer limit, Integer offset) {
        List<FosterPetEntity> fosterPetEntities = new ArrayList<>();
        int count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT fp FROM FosterPetEntity fp " +
                "JOIN FETCH fp.employeeEntity e " +
                "LEFT JOIN FETCH fp.petEntities p WHERE 1=1 ");
        if(fosterPetEntity != null){
            if(fosterPetEntity.getFosterPetId() != null){
                stringQuery.append(" AND fp.fosterPetId = :fosterPetId");
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQuery.append(" AND e.employeeId = :employeeId");
            }
        }

        JpaUtils.buildOrderBy(stringQuery,"fp.fosterDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);

        if(fosterPetEntity != null){
            if(fosterPetEntity.getFosterPetId() != null){
                query.setParameter("fosterPetId",fosterPetEntity.getFosterPetId());
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                query.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
        }
        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT fp) FROM FosterPetEntity fp " +
                "INNER JOIN fp.employeeEntity e " +
                "LEFT JOIN fp.petEntities p WHERE 1=1 ");
        if(fosterPetEntity != null){
            if(fosterPetEntity.getFosterPetId() != null){
                stringQueryCount.append(" AND fp.fosterPetId = :fosterPetId");
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                stringQueryCount.append(" AND e.employeeId = :employeeId");
            }
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());

        if(fosterPetEntity != null){
            if(fosterPetEntity.getFosterPetId() != null){
                queryCount.setParameter("fosterPetId",fosterPetEntity.getFosterPetId());
            }
        }

        if(employeeEntity != null){
            if(employeeEntity.getEmployeeId() != null){
                queryCount.setParameter("employeeId",employeeEntity.getEmployeeId());
            }
        }

        try {
            fosterPetEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){

        }
        return new Object[]{count,fosterPetEntities};
    }
    @Override
    public void updatePet(PetEntity petEntity) {
        try {

            PetEntity petEntityPersis = entityManager.find(PetEntity.class,petEntity.getPetId());
            if(petEntityPersis == null){
                throw new NotFoundObjectException();
            }
            petEntityPersis.setFosterPetEntity(petEntity.getFosterPetEntity());
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
            petEntityPersis.setFosterPetEntity(null);
            entityManager.merge(petEntityPersis);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }
    @Override
    public Object[] findPetDontFoster(PetEntity petEntity, Integer limit, Integer offset) {
        List<PetEntity> petEntities = null;
        Integer count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM PetEntity p" +
                " WHERE 1=1 AND p.rescueOrderEntity IS NULL AND p.fosterPetEntity IS NULL");

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

                "  WHERE 1=1 AND p.rescueOrderEntity IS NULL  AND p.fosterPetEntity IS NULL");

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
    public Boolean checkPetIsFoster(Integer petId){
        Boolean isFoster = false;
        try {
            PetEntity petEntity = entityManager.find(PetEntity.class,petId);
            if(petEntity != null){
                if(petEntity.getFosterPetEntity() != null){
                    isFoster = true;
                }
            }
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return isFoster;
    }

    @Override
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset) {
        List<FosterPetEntity> fosterPetEntities = new ArrayList<>();
        int count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT fp FROM FosterPetEntity fp");
        stringQuery.append(" INNER JOIN fp.employeeEntity e ");
        stringQuery.append(" JOIN FETCH fp.petEntities p WHERE e.employeeId = :employeeId ");

        JpaUtils.buildOrderBy(stringQuery,"fp.fosterDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);
        query.setParameter("employeeId",employeeId);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(fp) FROM FosterPetEntity fp");
        stringQueryCount.append(" INNER JOIN fp.employeeEntity e ");
        stringQueryCount.append(" INNER JOIN fp.petEntities p WHERE e.employeeId = :employeeId ");


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        queryCount.setParameter("employeeId",employeeId);

        try {
            fosterPetEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,fosterPetEntities};
    }
}
