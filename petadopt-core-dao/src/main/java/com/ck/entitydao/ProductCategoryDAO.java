package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;

import java.util.List;

public interface ProductCategoryDAO extends GenericDAO<Integer, ProductCategoryEntity> {
 public List<ProductCategoryEntity> findAll();

 public Object[] findAllProductOrProductCategoryById(Integer id, Integer limit, Integer offset);
}
