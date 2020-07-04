package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class NewsDAOImpl extends GenericDAOImpl<Integer, NewsEntity> implements NewsDAO {
    @Override
    public void update(NewsEntity entity) {
        try{
            NewsEntity newsEntityPersis = entityManager.find(NewsEntity.class,entity.getNewsId());
            entity.setUserEntity(newsEntityPersis.getUserEntity());
            entity.setCreatedDate(newsEntityPersis.getCreatedDate());
            entityManager.merge(entity);
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(List<NewsEntity> entities) {
        try{
            for(NewsEntity newsEntity : entities){
                NewsEntity newsEntityPersis = entityManager.find(NewsEntity.class,newsEntity.getNewsId());
                entityManager.remove(newsEntityPersis);
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public NewsEntity findById(Integer integer) {
        NewsEntity entity = null;
        try{
            entity =  entityManager.find(persistenClass,integer);
            entity.getNewsCategoryEntity();
            entity.getUserEntity();
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Object[] findNewsHome(Integer limit, Integer offset) {
        List<NewsEntity> newsEntities = null;
        Integer count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT n FROM NewsEntity n WHERE 1=1");
        JpaUtils.buildOrderBy(stringQuery,"n.modifiedDate","DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(n) FROM NewsEntity n WHERE 1=1");
        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        try {
            newsEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,newsEntities};
    }

    @Override
    public Object[] findNewsAdmin(NewsEntity newsEntity, NewsCategoryEntity newsCategoryEntity, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<NewsEntity> newsEntities = null;
        Integer count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT n FROM NewsEntity n " +
                "JOIN FETCH  n.userEntity u " +
                "INNER JOIN n.newsCategoryEntity nc WHERE 1=1");
        if(newsEntity != null){
            if(newsEntity.getNewsId() != null){
                stringQuery.append(" AND n.newsId = :newsId");
            }
            if(!StringUtils.isEmpty(newsEntity.getTitle())){
                stringQuery.append(" AND n.title LIKE '%' || :title || '%'" );
            }
        }
        if(newsCategoryEntity != null){
            if(newsCategoryEntity.getNewsCategoryId() != null){
                stringQuery.append(" AND nc.newsCategoryId = :newsCategoryId");
            }
        }
        if(!StringUtils.isEmpty(sortProperty) && !StringUtils.isEmpty(sortValue)){
            JpaUtils.buildOrderBy(stringQuery,sortProperty,sortValue);
        }
        else{
            JpaUtils.buildOrderBy(stringQuery,"n.modifiedDate","DESC");
        }

        Query query = entityManager.createQuery(stringQuery.toString());
        JpaUtils.buildLimit2Offset(query,limit,offset);

        if(newsEntity != null){
            if(newsEntity.getNewsId() != null){
                query.setParameter("newsId",newsEntity.getNewsId());
            }
            if(!StringUtils.isEmpty(newsEntity.getTitle())){
                query.setParameter("title",newsEntity.getTitle());
            }
        }
        if(newsCategoryEntity != null){
            if(newsCategoryEntity.getNewsCategoryId() != null){
                query.setParameter("newsCategoryId",newsCategoryEntity.getNewsCategoryId());
            }
        }


        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT n) FROM NewsEntity n " +
                "INNER JOIN n.userEntity u " +
                "INNER JOIN n.newsCategoryEntity nc WHERE 1=1");
        if(newsEntity != null){
            if(newsEntity.getNewsId() != null){
                stringQueryCount.append(" AND n.newsId = :newsId");
            }
            if(!StringUtils.isEmpty(newsEntity.getTitle())){
                stringQueryCount.append(" AND n.title LIKE '%' || :title || '%'" );
            }
        }
        if(newsCategoryEntity != null){
            if(newsCategoryEntity.getNewsCategoryId() != null){
                stringQueryCount.append(" AND nc.newsCategoryId = :newsCategoryId");
            }
        }


        Query queryCount = entityManager.createQuery(stringQueryCount.toString());

        if(newsEntity != null){
            if(newsEntity.getNewsId() != null){
                queryCount.setParameter("newsId",newsEntity.getNewsId());
            }
            if(!StringUtils.isEmpty(newsEntity.getTitle())){
                queryCount.setParameter("title",newsEntity.getTitle());
            }
        }
        if(newsCategoryEntity != null){
            if(newsCategoryEntity.getNewsCategoryId() != null){
                queryCount.setParameter("newsCategoryId",newsCategoryEntity.getNewsCategoryId());
            }
        }
        try {
            newsEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,newsEntities};
    }

}
