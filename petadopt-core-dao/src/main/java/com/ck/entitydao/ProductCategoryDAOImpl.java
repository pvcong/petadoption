package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;
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
public class ProductCategoryDAOImpl extends GenericDAOImpl<Integer, ProductCategoryEntity> implements ProductCategoryDAO {
    @Override
    public void update(ProductCategoryEntity entity) {
        try {
            ProductCategoryEntity productCategoryEntity = entityManager.find(ProductCategoryEntity.class,entity.getProductCategoryId());
            if(productCategoryEntity == null){
                throw new NotFoundObjectException();
            }
            entity.setCreatedDate(entity.getCreatedDate());
            entityManager.merge(entity);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(List<ProductCategoryEntity> entities) {
            try{
                for(int i = 0; i < entities.size(); i++){
                    ProductCategoryEntity entity = entities.get(i);
                    ProductCategoryEntity productCategoryEntity =
                            entityManager.find(ProductCategoryEntity.class,entity.getProductCategoryId());
                    if(productCategoryEntity == null){
                        throw new NotFoundObjectException();
                    }
                    entityManager.remove(productCategoryEntity);
                }
            } catch (HibernateException e){
                throw e;
            }
            finally {
                entityManager.close();
            }
        }



    @Override
    public Object[] findAllProductOrProductCategoryById(Integer id, Integer limit, Integer offset) {
        ProductCategoryEntity productCategoryEntity = null;
        int count = 0;


        StringBuilder stringQueryProduct = new StringBuilder
                ("SELECT DISTINCT p FROM ProductEntity p " +
                "INNER JOIN p.productCategoryEntity  pc " +
                        "WHERE pc.productCategoryId = :productCategoryId");
        Query queryProduct = entityManager.createQuery(stringQueryProduct.toString());
        queryProduct.setParameter("productCategoryId",id);
        JpaUtils.buildLimit2Offset(queryProduct,limit,offset);

        StringBuilder stringQueryProductCount = new StringBuilder
                ("SELECT COUNT(DISTINCT p) FROM ProductEntity p " +
                        "INNER JOIN p.productCategoryEntity  pc " +
                        "WHERE pc.productCategoryId = :productCategoryId");
        Query queryProductCount = entityManager.createQuery(stringQueryProductCount.toString());
        queryProductCount.setParameter("productCategoryId",id);

        try {
            productCategoryEntity = entityManager.find(ProductCategoryEntity.class,id);
            List<ProductEntity> productEntities = queryProduct.getResultList();
            count = Integer.parseInt(String.format("%d",queryProductCount.getSingleResult()));
            if(productCategoryEntity != null)
            productCategoryEntity.setProductEntities(productEntities);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }
        return new Object[]{count,productCategoryEntity};
    }


}


