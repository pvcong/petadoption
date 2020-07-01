package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.dto.NewsCategoryDTO;

import java.util.List;

public interface NewsDAO extends GenericDAO<Integer,NewsEntity> {
    public Object[] findNewsHome(Integer limit, Integer offset);
    public Object[] findNewsAdmin(NewsEntity newsEntity, NewsCategoryEntity newsCategoryEntity, String sortProperty, String sortValue, Integer limit, Integer offset);

}
