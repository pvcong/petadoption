package com.ck.utils;

import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;
import com.ck.dto.ProductCategoryDTO;
import com.ck.dto.ProductDTO;

public class ProductUtils {
    public static ProductEntity dto2Entity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        if(productDTO != null){
            productEntity.setName(productDTO.getName());
            productEntity.setStatus(productDTO.getStatus());
            productEntity.setCreatedDate(productDTO.getModifiedDate());
            productEntity.setModifiedDate(productDTO.getModifiedDate());
            productEntity.setProductId(productDTO.getProductId());
            productEntity.setBrand(productDTO.getBrand());
            productEntity.setPrice(productDTO.getPrice());
            productEntity.setWeight(productDTO.getWeight());
            productEntity.setShopeeLink(productDTO.getShopeeLink());
            productEntity.setSize(productDTO.getSize());
            productEntity.setImage(productDTO.getImage());
            productEntity.setDescription(productDTO.getDescription());


        }
        return productEntity;
    }
    public static ProductDTO entity2DTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        if(productEntity != null){
            productDTO.setName(productEntity.getName());
            productDTO.setPrice(productEntity.getPrice());
            productDTO.setStatus(productEntity.getStatus());
            productDTO.setCreatedDate(productEntity.getModifiedDate());
            productDTO.setModifiedDate(productEntity.getModifiedDate());
            productDTO.setProductId(productEntity.getProductId());
            productDTO.setBrand(productEntity.getBrand());
            productDTO.setWeight(productEntity.getWeight());
            productDTO.setShopeeLink(productEntity.getShopeeLink());
            productDTO.setSize(productEntity.getSize());
            productDTO.setImage(productEntity.getImage());
            productDTO.setDescription(productEntity.getDescription());


        }
        return productDTO;
    }
}
