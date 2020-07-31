package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class ProductCategoryDTO {

    private Integer productCategoryId;

    private String name;

    private String status;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private List<ProductDTO> productDTOS;
    public ProductCategoryDTO() {
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
