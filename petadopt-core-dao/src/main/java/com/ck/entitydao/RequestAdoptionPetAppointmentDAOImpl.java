package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.*;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class RequestAdoptionPetAppointmentDAOImpl extends GenericDAOImpl<Integer, RequestAdoptionPetAppointmentEntity> implements RequestAdoptionPetAppointmentDAO {

    @Override
    public void save(RequestAdoptionPetAppointmentEntity entity) {

        RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity = new RequestAdoptionPetStatusAppointmentEntity();
        requestAdoptionPetStatusAppointmentEntity.setId(1);
        RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity = new RequestAdoptionPetAppointmentStatusDetailEntity();
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetAppointmentStatusDetailEntity.setCreatedDate(createdDate);
        requestAdoptionPetAppointmentStatusDetailEntity.setUserEntity(entity.getUserEntity());
        requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetAppointmentEntity(entity);
        requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetStatusAppointmentEntity(requestAdoptionPetStatusAppointmentEntity);

        try {
            entityManager.persist(entity);
            entityManager.persist(requestAdoptionPetAppointmentStatusDetailEntity);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }


    @Override
    public void update(RequestAdoptionPetAppointmentEntity entity) {
        try{
            RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntityPersis = entityManager.find(persistenClass,entity.getId());
            requestAdoptionPetAppointmentEntityPersis.setAppointmentDate(entity.getAppointmentDate());
            requestAdoptionPetAppointmentEntityPersis.setModifiedDate(entity.getModifiedDate());
            entityManager.merge(requestAdoptionPetAppointmentEntityPersis);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }

    }
    @Override
    public void updateAndAddStatus(RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetStatusDetailEntity) {
        try {

            entityManager.persist(requestAdoptionPetStatusDetailEntity);
            if(requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetStatusAppointmentEntity().getId() == 4){
                RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntityPersis =
                        entityManager.find(RequestAdoptionPetAppointmentEntity.class
                                ,requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetAppointmentEntity().getId());
                PetEntity petEntity = requestAdoptionPetAppointmentEntityPersis.getRequestAdoptionPetEntity().getPetEntity();
                petEntity.setStatus("Đã được nhận nuôi");
                entityManager.merge(petEntity);
            }

        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public RequestAdoptionPetStatusAppointmentEntity findCurrentStatus(Integer id) {
        List<RequestAdoptionPetStatusAppointmentEntity> requestAdoptionPetStatusAppointmentEntities = null;
        RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusEntity = null;

        StringBuilder stringQuery = new StringBuilder("SELECT rapsa FROM RequestAdoptionPetStatusAppointmentEntity rapsa " +
                "INNER JOIN rapsa.requestAdoptionPetAppointmentStatusDetailEntities rapasd " +
                "INNER JOIN rapasd.requestAdoptionPetAppointmentEntity rapa " +
                "WHERE rapasd.id IN " +
                "(SELECT MAX(rapasds.id) FROM RequestAdoptionPetAppointmentStatusDetailEntity rapasds " +
                "INNER JOIN rapasds.requestAdoptionPetAppointmentEntity rapas " +
                "GROUP BY rapas.id) AND rapa.id = :id");
        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("id",id);
        try{
             requestAdoptionPetStatusAppointmentEntities = query.getResultList();

            if(requestAdoptionPetStatusAppointmentEntities.size() <=0){
                throw new NotFoundObjectException();
            }
            for(RequestAdoptionPetStatusAppointmentEntity item : requestAdoptionPetStatusAppointmentEntities){
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
    public Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity,RequestAdoptionPetEntity requestAdoptionPetEntity, Integer requestPetStatusId, Integer limit, Integer offset) {
        List<RequestAdoptionPetAppointmentEntity> requestAdoptionPetAppointmentEntities = new ArrayList<>();
        Integer count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT rapa FROM RequestAdoptionPetAppointmentEntity rapa " +
                "JOIN FETCH rapa.requestAdoptionPetAppointmentStatusDetailEntities rapasd " +
                "JOIN FETCH rapasd.requestAdoptionPetStatusAppointmentEntity rapsa " +
                "JOIN FETCH rapa.requestAdoptionPetEntity rap  " +
                "JOIN FETCH rapa.userEntity u "+
                "WHERE 1=1 AND rapasd.id IN( SELECT MAX(rapasds.id) FROM RequestAdoptionPetStatusAppointmentEntity rapass " +
                "   INNER JOIN rapass.requestAdoptionPetAppointmentStatusDetailEntities rapasds INNER JOIN rapasds.requestAdoptionPetAppointmentEntity rapas" +
                "        GROUP BY rapas.id)");
        if(requestAdoptionPetAppointmentEntity != null){
            if(requestAdoptionPetAppointmentEntity.getId() != null){
                stringQuery.append(" AND rapa.id = :id");
            }
        }
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){

                stringQuery.append(" AND rap.requesrAdoptionPerId = :requesrAdoptionPerId ");
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                stringQuery.append(" AND rapsa.id IN(:rapsaIds)");
            } else{
                stringQuery.append(" AND rapsa.id = :rapsaId");
            }
        }
        JpaUtils.buildOrderBy(stringQuery, "rapasd.createdDate","DESC");
        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);
        if(requestAdoptionPetAppointmentEntity != null){
            if(requestAdoptionPetAppointmentEntity.getId() != null){
                query.setParameter("id",requestAdoptionPetAppointmentEntity.getId());
            }
        }
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                query.setParameter("requesrAdoptionPerId",requestAdoptionPetEntity.getRequesrAdoptionPerId());
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                List<Integer> ids = new ArrayList<>();
                ids.add(2);
                ids.add(3);
                query.setParameter("rapsaIds",ids);

            }
        else{
                query.setParameter("rapsaId",requestPetStatusId);
            }
        }

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT rapa) FROM RequestAdoptionPetAppointmentEntity rapa " +
                "INNER JOIN rapa.requestAdoptionPetAppointmentStatusDetailEntities rapasd " +
                "INNER JOIN rapasd.requestAdoptionPetStatusAppointmentEntity rapsa " +
                "INNER JOIN rapa.requestAdoptionPetEntity rap "  +
                "INNER JOIN rapa.userEntity u " +
                "WHERE 1=1 AND rapasd.id IN( SELECT MAX(rapasds.id) FROM RequestAdoptionPetStatusAppointmentEntity rapsas" +
                "  INNER JOIN rapsas.requestAdoptionPetAppointmentStatusDetailEntities rapasds INNER JOIN rapasds.requestAdoptionPetAppointmentEntity rapas" +
                "         GROUP BY rapas.id)");
        if(requestAdoptionPetAppointmentEntity != null){
            if(requestAdoptionPetAppointmentEntity.getId() != null){
                stringQueryCount.append(" AND rapa.id = :id");
            }

        }
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                stringQueryCount.append(" AND rap.requesrAdoptionPerId = :requesrAdoptionPerId");
            }
        }
        if(requestPetStatusId != null) {
            if (requestPetStatusId == 2) {
                stringQuery.append(" AND rapsa.id IN(:rapsaIds)");
            } else {
                stringQueryCount.append(" AND rapsa.id = :rapsaId");

            }
        }

        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        if(requestAdoptionPetAppointmentEntity != null){
            if(requestAdoptionPetAppointmentEntity.getId() != null){
                queryCount.setParameter("id",requestAdoptionPetAppointmentEntity.getId());
            }

        }
        if(requestAdoptionPetEntity != null){
            if(requestAdoptionPetEntity.getRequesrAdoptionPerId() != null){
                queryCount.setParameter("requesrAdoptionPerId",requestAdoptionPetEntity.getRequesrAdoptionPerId());
            }
        }
        if(requestPetStatusId != null){
            if(requestPetStatusId == 2){
                List<Integer> ids = new ArrayList<>();
                ids.add(3);
                ids.add(2);
                query.setParameter("rapsaIds",ids);

            }
        else{
                queryCount.setParameter("rapsaId",requestPetStatusId);

            }
        }

        requestAdoptionPetAppointmentEntities = query.getResultList();
        count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        entityManager.close();
        return new Object[]{count,requestAdoptionPetAppointmentEntities};
    }

    @Override
    public RequestAdoptionPetAppointmentEntity findSingleRequestAdoptionPetById(Integer id) {
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = null;
        StringBuilder stringQueryCount = new StringBuilder("SELECT DISTINCT rapa FROM RequestAdoptionPetAppointmentEntity rapa " +
                "INNER JOIN rapa.requestAdoptionPetAppointmentStatusDetailEntities rapasd " +
                "INNER JOIN rapasd.requestAdoptionPetStatusAppointmentEntity rapsa " +
                "INNER JOIN rapa.requestAdoptionPetEntity rap "  +
                "INNER JOIN rapa.userEntity u " +
                "WHERE 1=1 AND rapasd.id IN( SELECT MAX(rapasds.id) FROM RequestAdoptionPetStatusAppointmentEntity rapsas" +
                "  INNER JOIN rapsas.requestAdoptionPetAppointmentStatusDetailEntities rapasds INNER JOIN rapasds.requestAdoptionPetAppointmentEntity rapas" +
                "         GROUP BY rapas.id) AND rapa.id = :id");
        Query query = entityManager.createQuery(stringQueryCount.toString());
        query.setParameter("id",id);
        try{

            List<RequestAdoptionPetAppointmentEntity> requestAdoptionPetAppointmentEntities = query.getResultList();
            for(RequestAdoptionPetAppointmentEntity item : requestAdoptionPetAppointmentEntities){
                requestAdoptionPetAppointmentEntity = item;
            }
            if(requestAdoptionPetAppointmentEntity == null){
                throw new NotFoundObjectException();
            }
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;

        }finally {
            entityManager.close();
        }
        return requestAdoptionPetAppointmentEntity;
    }

    @Override
    public int totalAppointmentAvailabilityToday() {
        int count = 0;
        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT rapa) FROM RequestAdoptionPetAppointmentEntity rapa " +
                " WHERE rapa.appointmentDate BETWEEN :startDate AND :endDate");

        Query query = entityManager.createQuery(stringQueryCount.toString());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        Timestamp today = null;

        Timestamp tomorrow = null;

            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.add(Calendar.DAY_OF_YEAR,-1);
            today = new Timestamp(calendar.getTime().getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            tomorrow =  new Timestamp(calendar.getTime().getTime());


        query.setParameter("startDate",today);
        query.setParameter("endDate",tomorrow);

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
