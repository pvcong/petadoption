package com.ck.dao.generic;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;
@Transactional
public abstract class GenericDAOImpl<ID,T> implements GenericDAO<ID,T> {
    @PersistenceContext
    protected EntityManager entityManager;
    protected Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);
    protected Class<T> persistenClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    @Autowired
    protected MessageSource messageSource;
    @Override
    public void save(T entity) {
        try{
            entityManager.persist(entity);
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void update(T entity) {
        try{
            entityManager.merge(entity);
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(List<T> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                entityManager.remove(entities.get(i));
            }
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public T findById(ID id) {
        T entity = null;
        try{
           entity =  entityManager.find(persistenClass,id);
        } catch (HibernateException e){
            throw e;
        }
        finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Boolean checkPropertyValueExist(String propertyName, String propertyValue) {
        Boolean isExist = false;
        if(!StringUtils.isEmpty(propertyName) && !StringUtils.isEmpty(propertyValue) ){
            StringBuilder stringQuery = new StringBuilder("FROM " + persistenClass.getName() +  " u WHERE LOWER(u." + propertyName + ") = :propertyValue");
            Query query = entityManager.createQuery(stringQuery.toString());
            query.setParameter("propertyValue",propertyValue);
            try{
                List<T> entities = query.getResultList();
                for(T entity : entities){
                    isExist = true;
                }
            }catch (HibernateException e){
                throw e;
            }finally {
                entityManager.close();
            }
        }
        return isExist;
    }
}
