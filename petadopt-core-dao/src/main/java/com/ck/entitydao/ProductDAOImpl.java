package com.ck.entitydao;

import com.ck.dao.generic.GenericDAOImpl;
import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.JpaUtils;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl extends GenericDAOImpl<Integer, ProductEntity> implements ProductDAO {
    @Override
    public ProductEntity findById(Integer integer) {
        ProductEntity productEntity = entityManager.find(persistenClass,integer);
        if(productEntity == null){
            throw new NotFoundObjectException();
        }
        productEntity.getProductCategoryEntity();
        return productEntity;
    }

    @Override
    public void update(ProductEntity entity) {
        try {
            ProductEntity productEntity = entityManager.find(ProductEntity.class,entity.getProductId());
            if(productEntity == null){
                throw new NotFoundObjectException();
            }
            entity.setUserEntity(productEntity.getUserEntity());
            entity.setCreatedDate(entity.getCreatedDate());
            entityManager.merge(entity);
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }    }

    @Override
    public void delete(List<ProductEntity> entities) {
        try{
            for(int i = 0; i < entities.size(); i++){
                ProductEntity entity = entities.get(i);
                ProductEntity productCategoryEntity =
                        entityManager.find(ProductEntity.class,entity.getProductId());
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
    public Object[] findProductHome(ProductEntity productEntity, Integer limit, Integer offset) {
        List<ProductEntity> productEntities = new ArrayList<>();
        int count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM ProductEntity p" +
                " WHERE 1=1");
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                stringQuery.append(" AND LOWER(p.name) LIKE '%' || :name || '%' ");
            }
        }

            JpaUtils.buildOrderBy(stringQuery," p.modifiedDate", " DESC");

        Query query = entityManager.createQuery(stringQuery.toString());
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                query.setParameter("name",productEntity.getName());
            }
        }
        JpaUtils.buildLimit2Offset(query,limit,offset);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT p) FROM ProductEntity p" +
                " WHERE 1=1");
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                stringQueryCount.append(" AND LOWER(p.name) LIKE '%' || :name || '%'");
            }
        }
        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                queryCount.setParameter("name",productEntity.getName());
            }
        }

        try {
            productEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,productEntities};
    }

    @Override
    public Object[] findProductAdmin(ProductEntity productEntity, ProductCategoryEntity productCategoryEntity, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<ProductEntity> productEntities = new ArrayList<>();
        int count = 0;
        StringBuilder stringQuery = new StringBuilder("SELECT DISTINCT p FROM ProductEntity p" +
                " JOIN FETCH p.userEntity u INNER JOIN p.productCategoryEntity pc WHERE 1=1");
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                stringQuery.append(" AND LOWER(p.name) LIKE '%' || :name || '%' " );
            }
            if(!StringUtils.isEmpty(productEntity.getStatus())){
                if(productEntity.getStatus().equals("show") || productEntity.getStatus().equals("hide")){
                    stringQuery.append(" AND p.status = :status");
                }
            }
            if(!StringUtils.isEmpty(productEntity.getProductId())){
                stringQuery.append(" AND p.productId = :productId " );

            }
        }
        if(productCategoryEntity != null){
            if(productCategoryEntity.getProductCategoryId() != null){
                stringQuery.append(" pc.productCategoryId :productCategoryId");
            }
        }
        if(StringUtils.isEmpty(sortProperty) && StringUtils.isEmpty(sortValue)){
            JpaUtils.buildOrderBy(stringQuery," p.modifiedDate", "DESC");
        }else{
            JpaUtils.buildOrderBy(stringQuery,sortProperty,sortValue);
        }
        Query query = entityManager.createQuery(stringQuery.toString());
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                query.setParameter("name",productEntity.getName());
            }
            if(!StringUtils.isEmpty(productEntity.getStatus())){
                if(productEntity.getStatus().equals("show") || productEntity.getStatus().equals("hide")){
                    query.setParameter("status",productEntity.getStatus());
                }
            }
            if(!StringUtils.isEmpty(productEntity.getProductId())){
                query.setParameter("productId",productEntity.getProductId() );

            }
        }
        if(productCategoryEntity != null){
            if(productCategoryEntity.getProductCategoryId() != null){
                query.setParameter("productCategoryId", productCategoryEntity.getProductCategoryId());
            }
        }
        JpaUtils.buildLimit2Offset(query,limit,offset);

        StringBuilder stringQueryCount = new StringBuilder("SELECT COUNT(DISTINCT p) FROM ProductEntity p " +
                "INNER JOIN  p.productCategoryEntity pc " +
                "INNER JOIN p.userEntity u" +
                " WHERE 1=1");
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                stringQueryCount.append(" AND LOWER(p.name) LIKE '%' || :name || '%' ");
            }
            if(!StringUtils.isEmpty(productEntity.getStatus())){
                if(productEntity.getStatus().equals("show") || productEntity.getStatus().equals("hide")){
                    stringQueryCount.append(" AND p.status = :status");
                }
            }
            if(!StringUtils.isEmpty(productEntity.getProductId())){
                stringQueryCount.append(" AND p.productId = :productId " );

            }
        }
        if(productCategoryEntity != null){
            if(productCategoryEntity.getProductCategoryId() != null){
                stringQueryCount.append(" pc.productCategoryId :productCategoryId");
            }
        }
        Query queryCount = entityManager.createQuery(stringQueryCount.toString());
        if(productEntity != null){
            if(!StringUtils.isEmpty(productEntity.getName())){
                queryCount.setParameter("name",productEntity.getName());
            }
            if(!StringUtils.isEmpty(productEntity.getStatus())){
                if(productEntity.getStatus().equals("show") || productEntity.getStatus().equals("hide")){
                    queryCount.setParameter("status",productEntity.getStatus());
                }
            }
            if(!StringUtils.isEmpty(productEntity.getProductId())){
                queryCount.setParameter("productId",productEntity.getProductId() );

            }
        }
        if(productCategoryEntity != null){
            if(productCategoryEntity.getProductCategoryId() != null){
                queryCount.setParameter("productCategoryId", productCategoryEntity.getProductCategoryId());
            }
        }
        try {
            productEntities = query.getResultList();
            count = Integer.parseInt(String.format("%d",queryCount.getSingleResult()));
        }catch (HibernateException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            entityManager.close();
        }
        return new Object[]{count,productEntities};
    }
}
