package com.ck.dao.generic;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public interface GenericDAO<ID,T> {

    public void save(T entity);
    public void update(T entity);
    public void delete(List<T> entities);
    public T findById(ID id);
    public Boolean checkPropertyValueExist(String propertyName,String propertyValue);

}
