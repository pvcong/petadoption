package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class NewsCategoryDAOImpl extends GenericDAOImpl<Integer, NewsCategoryEntity> implements NewsCategoryDAO {
    @Override
    public Object[] findNewsOfNewsCategoryById(Integer newsCategoryId, Integer limit, Integer offset) {
        NewsCategoryEntity newsCategoryEntity = null;
        int count = 0;

        StringBuilder stringQueryNews = new StringBuilder
                ("SELECT n FROM NewsEntity n " +
                        "INNER JOIN n.newsCategoryEntity nc WHERE 1=1 " +
                        "AND nc.newsCategoryId = :newsCategoryId");

        JpaUtils.buildOrderBy(stringQueryNews,"n.modifiedDate","DESC");
        Query queryNews  = entityManager.createQuery(stringQueryNews.toString());
        queryNews.setParameter("newsCategoryId",newsCategoryId);
        JpaUtils.buildLimit2Offset(queryNews,limit,offset);

        StringBuilder stringQueryNewsCount = new StringBuilder
                ("SELECT COUNT(n) FROM NewsEntity n " +
                        "INNER JOIN n.newsCategoryEntity nc WHERE 1=1 " +
                        "AND nc.newsCategoryId = :newsCategoryId");

        Query queryNewsCount  = entityManager.createQuery(stringQueryNewsCount.toString());

        queryNewsCount.setParameter("newsCategoryId",newsCategoryId);



        try{
            newsCategoryEntity = entityManager.find(NewsCategoryEntity.class,newsCategoryId);
            List<NewsEntity> newsEntities = queryNews.getResultList();
            count = Integer.parseInt(String.format("%d",queryNewsCount.getSingleResult()));
            if(newsCategoryEntity != null){
                newsCategoryEntity.setNewsEntities(newsEntities);
            }
        }catch (HibernateException e){
            logger.error(e.getMessage());
        }finally {
            entityManager.close();
        }

        return new Object[]{count,newsCategoryEntity};
    }

    @Override
    public void update(NewsCategoryEntity entity) {
        try{
            NewsCategoryEntity newsCategoryEntity = entityManager.find(NewsCategoryEntity.class,entity.getNewsCategoryId());
            if(newsCategoryEntity == null){
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
    public void delete(List<NewsCategoryEntity> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                NewsCategoryEntity newsCategoryEntity = entities.get(i);
                entityManager.find(persistenClass,newsCategoryEntity.getNewsCategoryId());
                entityManager.remove(entities.get(i));
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }
}
