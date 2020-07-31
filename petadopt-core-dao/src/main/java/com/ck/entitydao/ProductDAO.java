package com.ck.entitydao;

import com.ck.dao.generic.GenericDAO;
import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;

public interface ProductDAO extends GenericDAO<Integer, ProductEntity> {
    public Object[] findProductHome(ProductEntity productEntity,
                                     Integer limit, Integer offset);
    public Object[] findProductAdmin(ProductEntity productEntity,ProductCategoryEntity productCategoryEntity,
                                    String sortProperty,String sortValue, Integer limit, Integer offset);
}
