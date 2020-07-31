package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.NewsCategoryEntity;

public interface NewsCategoryDAO extends GenericDAO<Integer,NewsCategoryEntity> {
    public Object[] findNewsOfNewsCategoryById(Integer newsCategoryId,Integer limit, Integer offset);
}
