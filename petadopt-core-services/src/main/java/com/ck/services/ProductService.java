package com.ck.services;

import com.ck.data.ProductEntity;
import com.ck.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public void save(ProductDTO productDTO);
    public void update(ProductDTO productDTO);
    public void delete(List<ProductDTO> productDTOS);
    public ProductDTO findById(Integer id);
    public Object[] findProductHome(ProductDTO productDTO,Integer limit, Integer offset);
    public Object[] findProductAdmin(ProductDTO productDTO,String sortProperty,String sortValue,Integer limit, Integer offset);

}
