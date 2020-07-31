package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.PetStatusEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class PetStatusDAOImpl extends GenericDAOImpl<Integer, PetStatusEntity> implements PetStatusDAO{
    @Override
    public void update(PetStatusEntity entity) {
        try{
            PetStatusEntity petStatusEntityPersis = entityManager.find(persistenClass,entity.getPetStatusId());
            entity.setPetEntity(petStatusEntityPersis.getPetEntity());
            if(petStatusEntityPersis == null){
                throw new NotFoundObjectException();
            }
            entityManager.merge(entity);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Object[] findPetStatusToStory(Integer limit, Integer offset) {
        List<PetStatusEntity> petStatusEntities = new ArrayList<>();
        int count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT ps FROM ");
        stringQuery.append("PetStatusEntity");
        stringQuery.append(" ps ");
        stringQuery.append(" JOIN FETCH ps.petStatusTypeEntity pst");
        stringQuery.append(" JOIN FETCH ps.petEntity p");

        JpaUtils.buildOrderBy(stringQuery,"date","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(ps) FROM ");
        stringQueryCount.append("PetStatusEntity");
        stringQueryCount.append(" ps ");
        stringQueryCount.append(" INNER JOIN  ps.petStatusTypeEntity pst");
        stringQueryCount.append(" INNER JOIN  ps.petEntity p");


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        try {
            petStatusEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,petStatusEntities};
    }
}
