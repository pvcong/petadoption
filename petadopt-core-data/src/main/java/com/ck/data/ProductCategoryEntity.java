package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table( name = "product_category")
public class ProductCategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "product_category_id")
    private Integer productCategoryId;
    @Column( name = "name")
    private String name;
    @Column( name = "status")
    private String status;
    @Column( name = "created_date")
    private Timestamp createdDate;
    @Column( name = "modified_date")
    private Timestamp modifiedDate;
    @OneToMany( mappedBy = "productCategoryEntity")
    private List<ProductEntity> productEntities;
    public ProductCategoryEntity() {
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
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
