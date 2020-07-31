package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.*;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import com.ck.utils.RequestAdoptionPetStatusDetailUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Repository
public class RequestAdoptionPetDAOImpl extends GenericDAOImpl<Integer, RequestAdoptionPetEntity> implements RequestAdoptionPetDAO {

    @Override
    public void save(RequestAdoptionPetEntity entity) {

        RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity = new RequestAdoptionPetStatusEntity();
        requestAdoptionPetStatusEntity.setId(1);
        RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity = new RequestAdoptionPetStatusDetailEntity();
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetStatusDetailEntity.setCreatedDate(createdDate);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1);

        requestAdoptionPetStatusDetailEntity.setUserEntity(userEntity);
        requestAdoptionPetStatusDetailEntity.setRequestAdoptionPetEntity(entity);
        requestAdoptionPetStatusDetailEntity.setRequestAdoptionPetStatusEntity(requestAdoptionPetStatusEntity);

        try {
            entityManager.persist(entity);
            entityManager.persist(requestAdoptionPetStatusDetailEntity);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(List<RequestAdoptionPetEntity> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                RequestAdoptionPetEntity entity = entities.get(i);
                RequestAdoptionPetEntity requestAdoptionPetEntity =
                        entityManager.find(RequestAdoptionPetEntity.class,entity.getRequesrAdoptionPerId());
                entityManager.remove(requestAdoptionPetEntity);
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }



    @Override
    public void updateAndAddStatus(RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity) {
        try {

            entityManager.persist(requestAdoptionPetStatusDetailEntity);
            if(requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetStatusEntity().getId() == 5){
                RequestAdoptionPetAppointmentEntity entity = requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetEntity().getRequestAdoptionPetAppointmentEntities().get(0);
                RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity = new RequestAdoptionPetStatusAppointmentEntity();
                requestAdoptionPetStatusAppointmentEntity.setId(1);
                RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity = new RequestAdoptionPetAppointmentStatusDetailEntity();
                Timestamp createdDate = new Timestamp(System.currentTimeMillis());
                requestAdoptionPetAppointmentStatusDetailEntity.setCreatedDate(createdDate);
                requestAdoptionPetAppointmentStatusDetailEntity.setUserEntity(entity.getUserEntity());
                requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetAppointmentEntity(entity);
                entity.setCreatedDate(createdDate);
                entity.setModifiedDate(createdDate);
                entity.setRequestAdoptionPetEntity(requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetEntity());
                requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetStatusAppointmentEntity(requestAdoptionPetStatusAppointmentEntity);
                entityManager.persist(entity);
                entityManager.persist(requestAdoptionPetAppointmentStatusDetailEntity);
            }

        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public RequestAdoptionPetStatusEntity findCurrentStatus(Integer id) {
        List<RequestAdoptionPetStatusEntity> requestAdoptionPetStatusEntities = null;
        RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity = null;

        StringBuilder stringQuery = new StringBuilder("SELECT raps FROM RequestAdoptionPetStatusEntity raps " +
                "INNER JOIN raps.requestAdoptionPetStatusDetailEntities rapsd " +
                "INNER JOIN rapsd.requestAdoptionPetEntity rap " +
                "WHERE rapsd.id IN " +
                "(SELECT MAX(rapsds.id) FROM RequestAdoptionPetStatusDetailEntity rapsds " +
                "INNER JOIN rapsds.requestAdoptionPetEntity raps " +
                "GROUP BY raps.requesrAdoptionPerId) AND rap.requesrAdoptionPerId = :id");
        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("id",id);
        try{
             requestAdoptionPetStatusEntities = query.getResultList();

            if(requestAdoptionPetStatusEntities.size() <=0){
                throw new NotFoundObjectException();
            }
            for(RequestAdoptionPetStatusEntity item : requestAdoptionPetStatusEntities){
                requestAdoptionPetStatusEntity = item;
            }


        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
        return requestAdoptionPetStatusEntity;
    }

    @Override
    public Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetEntity requestAdoptionPetEntity,PetEntity petEntity, Integer requestPetStatusId, Integer limit, Integer offset) {
        List<RequestAdoptionPetEntity> requestAdoptionPetEntities = new ArrayList<>();
        Integer count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT rap FROM RequestAdoptionPetEntity rap " +
                "JOIN FETCH rap.requestAdoptionPetStatusDetailEntities rapsd " +
                "JOIN FETCH rapsd.requestAdoptionPetStatusEntity raps " +
                "JOIN FETCH rap.petEntity p  " +
                "WHERE 1=1 AND rapsd.id IN( SELECT MAX(rapsds.id) FROM RequestAdoptionPetStatusEntity rapss " +
                "   INNER JOIN rapss.requestAdoptionPetStatusDetailEntities rapsds INNER JOIN rapsds.requestAdoptionPetEntity raps" +
                "        GROUP BY raps.requesrAdoptionPerId)");
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                stringQuery.append(" AND rap.requesrAdoptionPerId = :id");
            }
            if(!StringUtils.isEmpty(requestAdoptionPetEntity.getPhoneNumber())){
                stringQuery.append(" AND rap.phoneNumber = :phoneNumber");
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){

                stringQuery.append(" AND p.petId = :petId ");
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                stringQuery.append(" AND raps.id IN(:rapsIds)");
            }
        else{
                stringQuery.append(" AND raps.id = :rapsId");
            }
        }
        JpaUtils.buildOrderBy(stringQuery, "rapsd.createdDate","DESC");
        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                query.setParameter("id",requestAdoptionPetEntity.getRequesrAdoptionPerId());
            }
            if(!StringUtils.isEmpty(requestAdoptionPetEntity.getPhoneNumber())){
                query.setParameter("phoneNumber",requestAdoptionPetEntity.getPhoneNumber());
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){
                query.setParameter("petId",petEntity.getPetId());
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                List<Integer> ids = new ArrayList<>();
                ids.add(2);
                ids.add(3);
                query.setParameter("rapsIds",ids);



            }
            else{
                query.setParameter("rapsId",requestPetStatusId);
            }
        }

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT rap) FROM RequestAdoptionPetEntity rap " +
                "INNER JOIN rap.requestAdoptionPetStatusDetailEntities rapsd " +
                "INNER JOIN rapsd.requestAdoptionPetStatusEntity raps " +
                "INNER JOIN rap.petEntity p "  +
                "WHERE 1=1 AND rapsd.id IN( SELECT MAX(rapsds.id) FROM RequestAdoptionPetStatusEntity rapss" +
                "  INNER JOIN rapss.requestAdoptionPetStatusDetailEntities rapsds INNER JOIN rapsds.requestAdoptionPetEntity raps" +
                "         GROUP BY raps.requesrAdoptionPerId)");
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                stringQueryCount.append(" AND rap.requesrAdoptionPerId = :id");
            }
            if(!StringUtils.isEmpty(requestAdoptionPetEntity.getPhoneNumber())){
                stringQueryCount.append(" AND rap.phoneNumber = :phoneNumber");
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){
                stringQueryCount.append(" AND p.petId = :petId");
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                stringQuery.append(" AND raps.id IN(:rapsIds)");
            }
            else{
                stringQueryCount.append(" AND raps.id = :rapsId");

            }
        }
        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                queryCount.setParameter("id",requestAdoptionPetEntity.getRequesrAdoptionPerId());
            }
            if(!StringUtils.isEmpty(requestAdoptionPetEntity.getPhoneNumber())){
                queryCount.setParameter("phoneNumber",requestAdoptionPetEntity.getPhoneNumber());
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){
                queryCount.setParameter("petId",petEntity.getPetId());
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                List<Integer> ids = new ArrayList<>();
                ids.add(3);
                ids.add(2);
                query.setParameter("rapsIds",ids);

            }
         else{
                queryCount.setParameter("rapsId",requestPetStatusId);

            }
        }

        requestAdoptionPetEntities = query.getResultList();
        count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        entityManager.close();
        return new Object[]{count,requestAdoptionPetEntities};
    }

    @Override
    public RequestAdoptionPetEntity findSingleRequestAdoptionPetById(Integer id) {
        RequestAdoptionPetEntity requestAdoptionPetEntity = null;
        StringBuilder stringBuilder = new StringBuilder("SELECT rap FROM RequestAdoptionPetEntity rap " +
                "JOIN FETCH rap.requestAdoptionPetStatusDetailEntities rapsd " +
                "JOIN FETCH rap.petEntity p " +
                "JOIN FETCH rapsd.requestAdoptionPetStatusEntity raps " +
                "JOIN FETCH rapsd.userEntity " +
                "WHERE rap.requesrAdoptionPerId = :id");
        Query query = entityManager.createQuery(stringBuilder.toString());
        query.setParameter("id",id);
        try{

            List<RequestAdoptionPetEntity> requestAdoptionPetEntities = query.getResultList();
            for(RequestAdoptionPetEntity item : requestAdoptionPetEntities){
                requestAdoptionPetEntity = item;
            }
            if(requestAdoptionPetEntity == null){
                throw new NotFoundObjectException();
            }
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;

        }finally {
            entityManager.close();
        }
        return requestAdoptionPetEntity;
    }

    @Override
    public int totalRequestAdoptionPedding() {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(rap) FROM RequestAdoptionPetEntity rap " +
                " INNER JOIN rap.requestAdoptionPetStatusDetailEntities rapsd " +
                " INNER JOIN rapsd.requestAdoptionPetStatusEntity raps " +
                "WHERE raps.id = :id " +
                "AND rapsd.id IN " +
                "(SELECT MAX(rapsds.id) FROM RequestAdoptionPetStatusDetailEntity rapsds " +
                " INNER JOIN rapsds.requestAdoptionPetStatusEntity rapss " +
                " INNER JOIN rapsds.requestAdoptionPetEntity raps " +
                "GROUP BY raps.requesrAdoptionPerId)" );
        Query query = entityManager.createQuery(stringBuilder.toString());
        query.setParameter("id",1);
        try{
            count = Integer.parseInt(String.format("%d",query.getSingleResult()));
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
        return count;
    }


}
