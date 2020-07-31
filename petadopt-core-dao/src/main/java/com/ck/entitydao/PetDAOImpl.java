package com.ck.entitydao;

import com.ck.constant.DAOConstant;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.*;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.core.codec.Hints;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class PetDAOImpl extends GenericDAOImpl<Integer, PetEntity> implements PetDAO {
    @Override
    public void update(PetEntity entity) {
        try{
            PetEntity petEntityPersist = entityManager.find(PetEntity.class,entity.getPetId());

            if(petEntityPersist == null){
                throw new NotFoundObjectException();
            }
            entity.setUserEntity(petEntityPersist.getUserEntity());
            entity.setCreatedDate(petEntityPersist.getCreatedDate());
           // petEntityPersist.setPetTypeEntity(entity.getPetTypeEntity());
//            petEntityPersist.setPetName(entity.getPetName());
//            petEntityPersist.setGender(entity.getGender());
//            petEntityPersist.setWeight(entity.getWeight());
//            petEntityPersist.setAge(entity.getAge());
//            petEntityPersist.setStatus(entity.getStatus());
//            petEntityPersist.setImage(entity.getImage());
//            petEntityPersist.setDescription(entity.getDescription());
//            petEntityPersist.setModifiedDate(entity.getModifiedDate());
//            petEntityPersist.setCreatedDate(entity.);
            entityManager.merge(entity);

        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void save(PetEntity entity) {
        try{
            PetAboutEntity petAboutEntity = entity.getPetAboutEntity();


            entity.setPetAboutEntity(null);
            entityManager.persist(entity);;
            petAboutEntity.setPetEntity(entity);
            entityManager.persist(petAboutEntity);
            PetStatusTypeEntity petStatusTypeEntity = new PetStatusTypeEntity();
            petStatusTypeEntity.setPetStatusTypeId(1);
            PetStatusEntity petStatusEntity = new PetStatusEntity();
            petStatusEntity.setPetEntity(entity);
            petStatusEntity.setDate(entity.getEntryDate());
            petStatusEntity.setContent("Hệ thống tự động tạo");
            petStatusEntity.setPetStatusTypeEntity(petStatusTypeEntity);

            petAboutEntity.setPetEntity(entity);

            entityManager.persist(petStatusEntity);
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }

    }

    @Override
    public void delete(List<PetEntity> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                PetEntity petEntityPersistence = entityManager.find(PetEntity.class,entities.get(i).getPetId());
                if(petEntityPersistence == null){
                    throw new NotFoundObjectException();
                }
                entityManager.remove(petEntityPersistence);
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public PetEntity findSinglePetHome(Integer id) {
        PetEntity petEntity = null;
        if(id != null){
            StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM PetEntity p JOIN FETCH p.petAboutEntity pa JOIN FETCH p.petTypeEntity pt ");
            stringQuery.append("WHERE p.petId = :id");
            Query query = entityManager.createQuery(stringQuery.toString());
            query.setParameter("id",id);
            try{
                List<PetEntity> petEntities = query.getResultList();
                for(PetEntity petEntity1 : petEntities){
                    petEntity = petEntity1;
                }
            }catch (HibernateException e){
                throw e;
            }finally {
                entityManager.close();
            }
        }
        return petEntity;
    }

    @Override
    public PetEntity findSinglePetAdmin(Integer id) {
        PetEntity petEntity = null;
        if(id != null){
            StringBuilder stringQuery = new StringBuilder("" +
                    "SELECT DISTINCT p FROM PetEntity p " +
                    "JOIN FETCH p.petTypeEntity pt " +
                    "JOIN FETCH p.userEntity u " +
                    "LEFT JOIN FETCH p.fosterPetEntity fp " +
                    "JOIN FETCH p.petEntryTypeEntity pet " +
                    "JOIN FETCH p.petEntryStatusEntity pet " +
                    "LEFT JOIN FETCH p.petStatusEntities ps " +
                    "JOIN FETCH p.petAboutEntity pa " +
                    " LEFT JOIN FETCH ps.petStatusTypeEntity pst " +
                    "WHERE 1=1 AND " +
                    " (ps.petStatusId IN (SELECT MAX(pss.petStatusId) FROM PetStatusEntity pss " +
                    " INNER JOIN pss.petEntity pp " +
                    "GROUP BY pss.petEntity ) OR ps.petStatusId IS NULL) AND p.petId = :petId");
            Query query = entityManager.createQuery(stringQuery.toString());
            query.setParameter("petId",id);
            try{
                List<PetEntity> petEntities = query.getResultList();
                for(PetEntity entity : petEntities){
                    petEntity = entity;
                }
            }catch (HibernateException e){
                throw e;
            }finally {
                entityManager.close();
            }

        }        return petEntity;
    }

    @Override
    public Object[] findPetAdmin(PetEntity petEntity, PetTypeEntity petTypeEntity,PetStatusTypeEntity petStatusTypeEntity, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<PetEntity> petEntities = null;
        Integer count = 0;

        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM PetEntity p" +
                " JOIN FETCH p.petTypeEntity pt" +
                " JOIN FETCH p.userEntity u ");
        stringQuery.append("   LEFT JOIN FETCH p.petStatusEntities ps  " +
                "   LEFT JOIN FETCH ps.petStatusTypeEntity pst" +
                "  WHERE (ps.petStatusId IN (SELECT MAX(pss.petStatusId) FROM PetStatusEntity pss " +
                "     INNER JOIN pss.petEntity pp " +
                " INNER JOIN pss.petStatusTypeEntity psts ");
//                "    GROUP BY pss.petEntity  ) " +
//                "OR ps.petStatusId IS NULL  ");
        stringQuery.append(" GROUP BY pss.petEntity)  OR ps.petStatusId IS NULL)  ");
        if(petStatusTypeEntity != null){
            if(petStatusTypeEntity.getPetStatusTypeId() != null){
                stringQuery.append("AND pst.petStatusTypeId = :petStatusTypeId ");
            }
        }
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                stringQuery.append(" AND pt.petTypeId = :petTypeId");
            }
        }

        if(petEntity != null){

            if(petEntity.getPetId() != null){
                stringQuery.append(" AND p.petId = :petId");
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQuery.append(" AND LOWER(p.petName) LIKE '%' || :petName || '%' ");
            }
            if(!StringUtils.isEmpty(petEntity.getStatus())){
                if(petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusShow) || petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusHide)){
                    stringQuery.append(" AND p.status = :petStatus");
                }
            }
        }
        if(!StringUtils.isEmpty(sortProperty) && !StringUtils.isEmpty(sortValue)){
            JpaUtils.buildOrderBy(stringQuery,"p." + sortProperty,sortValue);
        }else{
            JpaUtils.buildOrderBy(stringQuery,"p.modifiedDate","DESC");
        }
        Query query = entityManager.createQuery(stringQuery.toString());

        JpaUtils.buildLimit2Offset(query,limit,offset);

        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                query.setParameter("petTypeId",petTypeEntity.getPetTypeId());
            }
        }
        if(petStatusTypeEntity != null){
            if(petStatusTypeEntity.getPetStatusTypeId() != null){
                query.setParameter("petStatusTypeId",petStatusTypeEntity.getPetStatusTypeId());
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){
                query.setParameter("petId",petEntity.getPetId());
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                query.setParameter("petName",petEntity.getPetName());
            }
            if(!StringUtils.isEmpty(petEntity.getStatus())){
                if(petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusShow) || petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusHide )){
                    query.setParameter("petStatus",petEntity.getStatus());
                }

            }
        }

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT p) FROM PetEntity p" +
                " INNER JOIN p.petTypeEntity pt" +
                " INNER JOIN p.userEntity u  " );
        stringQueryCount.append("   LEFT JOIN  p.petStatusEntities ps  " +
                "   LEFT JOIN  ps.petStatusTypeEntity pst" +
                "  WHERE (ps.petStatusId IN (SELECT MAX(pss.petStatusId) FROM PetStatusEntity pss " +
                "     INNER JOIN pss.petEntity pp " +
                " INNER JOIN pss.petStatusTypeEntity psts ");
//                "    GROUP BY pss.petEntity  ) " +
//                "OR ps.petStatusId IS NULL  ");
        stringQueryCount.append(" GROUP BY pss.petEntity)  OR ps.petStatusId IS NULL) ");
        if(petStatusTypeEntity != null){
            if(petStatusTypeEntity.getPetStatusTypeId() != null) {
                stringQueryCount.append("AND pst.petStatusTypeId = :petStatusTypeId ");
            }
        }
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                stringQueryCount.append(" AND pt.petTypeId = :petTypeId");
            }
        }

        if(petEntity != null){
            if(petEntity.getPetId() != null){
                stringQueryCount.append(" AND p.petId = :petId");
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQueryCount.append(" AND LOWER(p.petName) LIKE '%' || :petName || '%' ");
            }
            if(!StringUtils.isEmpty(petEntity.getStatus())){
                if(petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusShow) || petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusHide)){
                    stringQueryCount.append(" AND p.status = :petStatus");
                }
            }
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());


        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                queryCount.setParameter("petTypeId",petTypeEntity.getPetTypeId());
            }
        }
        if(petStatusTypeEntity != null){
            if(petStatusTypeEntity.getPetStatusTypeId() != null){
                queryCount.setParameter("petStatusTypeId",petStatusTypeEntity.getPetStatusTypeId());
            }
        }
        if(petEntity != null){
            if(petEntity.getPetId() != null){
                queryCount.setParameter("petId",petEntity.getPetId());
            }
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                queryCount.setParameter("petName",petEntity.getPetName());
            }
            if(!StringUtils.isEmpty(petEntity.getStatus())){
                if(petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusShow) || petEntity.getStatus().toLowerCase().equals(DAOConstant.petStatusHide )){
                    queryCount.setParameter("petStatus",petEntity.getStatus());
                }

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
    public Object[] findPetHome(PetEntity petEntity, PetTypeEntity petTypeEntity, PetAboutEntity petAboutEntity, Integer limit, Integer offset) {
        List<PetEntity> petEntities = new ArrayList<>();
        Integer count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p " +
                "FROM PetEntity p JOIN FETCH p.petAboutEntity pa INNER JOIN p.petTypeEntity pt   " +
                " LEFT JOIN p.petStatusEntities ps  " +
                " INNER JOIN ps.petStatusTypeEntity pst " +
                        "WHERE (ps.petStatusId IN (SELECT MAX(pss.petStatusId) FROM PetStatusEntity pss " +
                        "   INNER JOIN pss.petEntity pp " +
                        "  GROUP BY pss.petEntity  ) OR ps.petStatusId IS NULL) AND pst.petStatusTypeId = :petStatusTypeId");
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                stringQuery.append(" AND pt.petTypeId = :petTypeId ");
            }
        }
        if(petAboutEntity != null){
            if(!StringUtils.isEmpty(petAboutEntity.getSterilization())){
                stringQuery.append(" AND LOWER(pa.sterilization) = :sterilization ");
            }
        }
        if(petEntity != null){
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQuery.append(" AND  LOWER(p.petName) LIKE  '%' || :petName || '%'  ");
            }
            if(!StringUtils.isEmpty(petEntity.getColor())){
                stringQuery.append(" AND  LOWER(p.color) = :color ");

            }
            if(!StringUtils.isEmpty(petEntity.getGender())){
                stringQuery.append(" AND  LOWER(p.gender) = :gender ");

            }
            if(petEntity.getAge()!= null){
                if(petEntity.getAge() <= 6){
                    stringQuery.append(" AND p.age <= :age");
                }
                else if(petEntity.getAge() > 6 && petEntity.getAge() < 24 ){
                    stringQuery.append(" AND p.age > :age1 AND p.age < :age2");
                }
                else if(petEntity.getAge() >= 24){
                    stringQuery.append(" AND p.age >= :age");
                }

            }
        }


        stringQuery.append(" ORDER BY  p.modifiedDate DESC ");
        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("petStatusTypeId",2);
        JpaUtils.buildLimit2Offset(query, limit, offset);
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                query.setParameter("petTypeId",petTypeEntity.getPetTypeId());
            }
        }
        if(petAboutEntity != null){
            if(!StringUtils.isEmpty(petAboutEntity.getSterilization())){
                query.setParameter("sterilization",petAboutEntity.getSterilization());
            }
        }
        if(petEntity != null){
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                query.setParameter("petName",petEntity.getPetName());

            }
            if(!StringUtils.isEmpty(petEntity.getColor())){
                query.setParameter("color",petEntity.getColor());

            }
            if(!StringUtils.isEmpty(petEntity.getGender())){
                query.setParameter("gender",petEntity.getGender());

            }
            if(petEntity.getAge()!= null){
                if(petEntity.getAge() <= 6){
                    query.setParameter("age",6);
                }
                else if(petEntity.getAge() > 6 && petEntity.getAge() < 24 ){
                    query.setParameter("age1",6);
                    query.setParameter("age2",24);

                }
                else if(petEntity.getAge() >= 24){
                    query.setParameter("age",24);

                }

            }
        }




        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT p) " +
                "FROM PetEntity p INNER JOIN  p.petAboutEntity pa INNER JOIN p.petTypeEntity pt" +
                " LEFT JOIN p.petStatusEntities ps  " +
                        " INNER JOIN ps.petStatusTypeEntity pst " +
                        "WHERE (ps.petStatusId IN (SELECT MAX(pss.petStatusId) FROM PetStatusEntity pss " +
                        "   INNER JOIN pss.petEntity pp " +
                        "  GROUP BY pss.petEntity  ) OR ps.petStatusId IS NULL) AND pst.petStatusTypeId = :petStatusTypeId");
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                stringQueryCount.append(" AND pt.petTypeId = :petTypeId ");
            }
        }
        if(petAboutEntity != null){
            if(!StringUtils.isEmpty(petAboutEntity.getSterilization())){
                stringQueryCount.append(" AND LOWER(pa.sterilization) = :sterilization ");
            }
        }
        if(petEntity != null){
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                stringQueryCount.append(" AND  LOWER(p.petName) LIKE  '%' || :petName || '%'  ");
            }
            if(!StringUtils.isEmpty(petEntity.getColor())){
                stringQueryCount.append(" AND  LOWER(p.color) = :color ");

            }
            if(!StringUtils.isEmpty(petEntity.getGender())){
                stringQueryCount.append(" AND  LOWER(p.gender) = :gender ");

            }

            if(petEntity != null){
                if(petEntity.getAge()!= null){
                    if(petEntity.getAge() <= 6){
                        stringQueryCount.append(" AND p.age <= :age");
                    }
                    else if(petEntity.getAge() > 6 && petEntity.getAge() < 24 ){
                        stringQueryCount.append(" AND p.age > :age1 AND p.age < :age2");
                    }
                    else if(petEntity.getAge() >= 24){
                        stringQueryCount.append(" AND p.age >= :age");
                    }

                }
            }
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        queryCount.setParameter("petStatusTypeId",2);
        if(petTypeEntity != null){
            if(petTypeEntity.getPetTypeId() != null){
                queryCount.setParameter("petTypeId",petTypeEntity.getPetTypeId());
            }
        }
        if(petAboutEntity != null){
            if(!StringUtils.isEmpty(petAboutEntity.getSterilization())){
                queryCount.setParameter("sterilization",petAboutEntity.getSterilization());
            }
        }
        if(petEntity != null){
            if(!StringUtils.isEmpty(petEntity.getPetName())){
                queryCount.setParameter("petName",petEntity.getPetName());

            }
            if(!StringUtils.isEmpty(petEntity.getColor())){
                queryCount.setParameter("color",petEntity.getColor());

            }
            if(!StringUtils.isEmpty(petEntity.getGender())){
                queryCount.setParameter("gender",petEntity.getGender());

            }

            if(petEntity.getAge()!= null){
                if(petEntity.getAge() <= 6){
                    queryCount.setParameter("age",6);
                }
                else if(petEntity.getAge() > 6 && petEntity.getAge() < 24 ){
                    queryCount.setParameter("age1",6);
                    queryCount.setParameter("age2",24);

                }
                else if(petEntity.getAge() >= 24){
                    queryCount.setParameter("age",24);

                }

            }
        }

        try{
            petEntities = query.getResultList();
            count =  Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }



        return new Object[]{count,petEntities};
    }

    @Override
    public List<PetStatusEntity> findPetSStatusByPetId(Integer petId) {
        List<PetStatusEntity>  petStatusEntities = new ArrayList<>();
        StringBuilder stringQuery = new StringBuilder("SELECT ps FROM PetStatusEntity ps " +
                "INNER JOIN ps.petEntity p " +
                "JOIN FETCH ps.petStatusTypeEntity " +
                "WHERE p.petId = :petId");

        JpaUtils.buildOrderBy(stringQuery,"date","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("petId",petId);



        try {
            petStatusEntities = query.getResultList();

        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return petStatusEntities;
    }
}
