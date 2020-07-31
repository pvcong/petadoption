package com.ck.services;

import com.ck.data.ProductCategoryEntity;
import com.ck.data.ProductEntity;
import com.ck.data.UserEntity;
import com.ck.dto.ProductDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.ProductDAO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.ProductCategoryUtils;
import com.ck.utils.ProductUtils;
import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;
    @Override
    public void save(ProductDTO productDTO) {
        ProductEntity productEntity = ProductUtils.dto2Entity(productDTO);
        ProductCategoryEntity productCategoryEntity = ProductCategoryUtils.dto2Entity(productDTO.getProductCategoryDTO());
        productEntity.setProductCategoryEntity(productCategoryEntity);
        UserEntity userEntity = UserUtils.dto2Entity(productDTO.getUserDTO());
        productEntity.setUserEntity(userEntity);
        productDAO.save(productEntity);
    }

    @Override
    public void update(ProductDTO productDTO) {
        ProductEntity productEntity = ProductUtils.dto2Entity(productDTO);
        ProductCategoryEntity productCategoryEntity = ProductCategoryUtils.dto2Entity(productDTO.getProductCategoryDTO());
        productEntity.setProductCategoryEntity(productCategoryEntity);
        productDAO.update(productEntity);
    }

    @Override
    public void delete(List<ProductDTO> productDTOS) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for(ProductDTO productDTO : productDTOS){
            ProductEntity productEntity = ProductUtils.dto2Entity(productDTO);
            productEntities.add(productEntity);
        }
        productDAO.delete(productEntities);
    }

    @Override
    public ProductDTO findById(Integer id) {
        ProductEntity productEntity = productDAO.findById(id);
        if(productEntity == null){
            throw new NotFoundObjectException();
        }
        return ProductUtils.entity2DTO(productEntity);
    }

    @Override
    public Object[] findProductHome(ProductDTO productDTO, Integer limit, Integer offset) {
        ProductEntity productEntity = ProductUtils.dto2Entity(productDTO);
        Object[] objects = productDAO.findProductHome(productEntity,limit,offset);
        List<ProductEntity> productEntities = (List<ProductEntity>) objects[1];
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(ProductEntity item : productEntities){
            ProductDTO itemDTO = ProductUtils.entity2DTO(item);
            productDTOS.add(itemDTO);
        }
        return new Object[]{objects[0],productDTOS};
    }

    @Override
    public Object[] findProductAdmin(ProductDTO productDTO, String sortProperty, String sortValue, Integer limit, Integer offset) {
        ProductEntity productEntity = ProductUtils.dto2Entity(productDTO);
        ProductCategoryEntity productCategoryEntity = null;
        if(productDTO != null){
            if(productDTO.getProductCategoryDTO() != null){
                productCategoryEntity = productEntity.getProductCategoryEntity();
            }
        }
        Object[] objects = productDAO.findProductAdmin(productEntity,productCategoryEntity,sortProperty,sortValue,limit,offset);
        List<ProductEntity> productEntities = (List<ProductEntity>) objects[1];
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(ProductEntity item : productEntities){
            ProductDTO itemDTO = ProductUtils.entity2DTO(item);
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());
            itemDTO.setUserDTO(userDTO);
            productDTOS.add(itemDTO);
        }
        return new Object[]{objects[0],productDTOS};
    }
}
