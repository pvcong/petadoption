package com.ck.services;

import com.ck.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    public void save(ProductCategoryDTO productCategoryDTO);
    public void update(ProductCategoryDTO productCategoryDTO);
    public void delete(List<ProductCategoryDTO> productCategoryDTOS);
    public ProductCategoryDTO findById(Integer id);
    public List<ProductCategoryDTO> findAll();
    public Object[] findProductOfProductCategoryById(Integer id, Integer limit, Integer offset);
}
