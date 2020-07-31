package com.ck.utils;

import com.ck.data.PetTypeEntity;
import com.ck.data.ProductCategoryEntity;
import com.ck.dto.PetTypeDTO;
import com.ck.dto.ProductCategoryDTO;

public class ProductCategoryUtils {
    public static ProductCategoryEntity dto2Entity(ProductCategoryDTO productCategoryDTO){
        ProductCategoryEntity productCategoryEntity = new ProductCategoryEntity();
        if(productCategoryDTO != null){
            productCategoryEntity.setName(productCategoryDTO.getName());
            productCategoryEntity.setStatus(productCategoryDTO.getStatus());
            productCategoryEntity.setCreatedDate(productCategoryDTO.getModifiedDate());
            productCategoryEntity.setModifiedDate(productCategoryDTO.getModifiedDate());
            productCategoryEntity.setProductCategoryId(productCategoryDTO.getProductCategoryId());

        }
        return productCategoryEntity;
    }
    public static ProductCategoryDTO entity2DTO(ProductCategoryEntity productCategoryEntity){
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        if(productCategoryEntity != null){
            productCategoryDTO.setName(productCategoryEntity.getName());
            productCategoryDTO.setStatus(productCategoryEntity.getStatus());
            productCategoryDTO.setCreatedDate(productCategoryEntity.getModifiedDate());
            productCategoryDTO.setModifiedDate(productCategoryEntity.getModifiedDate());
            productCategoryDTO.setProductCategoryId(productCategoryEntity.getProductCategoryId());

        }
        return productCategoryDTO;
    }
}
