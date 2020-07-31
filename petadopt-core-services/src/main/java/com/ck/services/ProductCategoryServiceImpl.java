package com.ck.services;

import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;
import com.ck.dto.ProductCategoryDTO;
import com.ck.dto.ProductDTO;
import com.ck.entitydao.ProductCategoryDAO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.ProductCategoryUtils;
import com.ck.utils.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDAO productCategoryDAO;
    @Override
    public void save(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = ProductCategoryUtils.dto2Entity(productCategoryDTO);
        productCategoryDAO.save(productCategoryEntity);
    }

    @Override
    public void update(ProductCategoryDTO productCategoryDTO) {
        ProductCategoryEntity productCategoryEntity = ProductCategoryUtils.dto2Entity(productCategoryDTO);
        productCategoryDAO.update(productCategoryEntity);
    }

    @Override
    public void delete(List<ProductCategoryDTO> productCategoryDTOS) {
        List<ProductCategoryEntity> productCategoryEntities = new ArrayList<>();
        for(ProductCategoryDTO item : productCategoryDTOS){
            ProductCategoryEntity productCategoryEntity = ProductCategoryUtils.dto2Entity(item);
            productCategoryEntities.add(productCategoryEntity);

        }
        productCategoryDAO.delete(productCategoryEntities);
    }

    @Override
    public ProductCategoryDTO findById(Integer id) {
        ProductCategoryEntity productCategoryEntity = productCategoryDAO.findById(id);
        ProductCategoryDTO productCategoryDTO = ProductCategoryUtils.entity2DTO(productCategoryEntity);
        if(productCategoryDTO == null){
            throw  new NotFoundObjectException();
        }
        return productCategoryDTO;
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        List<ProductCategoryEntity> productCategoryEntities = productCategoryDAO.findAll();
        List<ProductCategoryDTO> productCategoryDTOS = new ArrayList<>();
        for(ProductCategoryEntity item : productCategoryEntities){
            ProductCategoryDTO productCategoryDTO = ProductCategoryUtils.entity2DTO(item);
            productCategoryDTOS.add(productCategoryDTO);

        }
        return productCategoryDTOS;
    }

    @Override
    public Object[] findProductOfProductCategoryById(Integer id, Integer limit, Integer offset) {
        Object[] objects = productCategoryDAO.findAllProductOrProductCategoryById(id,limit,offset);
        ProductCategoryEntity productCategoryEntity = (ProductCategoryEntity) objects[1];
        if(productCategoryEntity == null){
            throw new NotFoundObjectException();
        }
        List<ProductEntity> productEntities = productCategoryEntity.getProductEntities();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(ProductEntity item : productEntities){
            ProductDTO productDTO = ProductUtils.entity2DTO(item);
            productDTOS.add(productDTO);

        }
        ProductCategoryDTO productCategoryDTO = ProductCategoryUtils.entity2DTO(productCategoryEntity);
        productCategoryDTO.setProductDTOS(productDTOS);
        return  new Object[]{objects[0],productCategoryDTO};
    }
}
