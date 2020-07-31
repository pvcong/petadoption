package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;


public class ProductDTO {

    private Integer productId;


    private String name;
    private String size;
    private String weight;
    private String description;
    private String image;
    private String status;
    private String brand;
    private Integer price;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private String shopeeLink;

    private UserDTO userDTO;

    private ProductCategoryDTO productCategoryDTO;

    public ProductDTO() {
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getShopeeLink() {
        return shopeeLink;
    }

    public void setShopeeLink(String shopeeLink) {
        this.shopeeLink = shopeeLink;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ProductCategoryDTO getProductCategoryDTO() {
        return productCategoryDTO;
    }

    public void setProductCategoryDTO(ProductCategoryDTO productCategoryDTO) {
        this.productCategoryDTO = productCategoryDTO;
    }
}
