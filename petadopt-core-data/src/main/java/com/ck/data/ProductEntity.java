package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "product_id")
    private Integer productId;

    @Column( name = "name")
    private String name;
    @Column( name = "size")
    private String size;
    @Column( name = "weight")
    private String weight;
    @Column( name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "status")
    private String status;
    @Column(name = "brand")
    private String brand;
    @Column(name = "price")
    private Integer price;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @Column(name = "shopee_link")
    private String shopeeLink;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private UserEntity userEntity;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "product_category_id")
    private ProductCategoryEntity productCategoryEntity;

    public ProductEntity() {
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductCategoryEntity getProductCategoryEntity() {
        return productCategoryEntity;
    }

    public void setProductCategoryEntity(ProductCategoryEntity productCategoryEntity) {
        this.productCategoryEntity = productCategoryEntity;
    }
}
