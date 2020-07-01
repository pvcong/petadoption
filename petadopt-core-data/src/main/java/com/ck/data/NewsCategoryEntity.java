package com.ck.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "news_category")
public class NewsCategoryEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "news_category_id")
    private Integer newsCategoryId;
    @Column( name = "name")
    private String name;
    @OneToMany( mappedBy = "newsCategoryEntity")
    private List<NewsEntity> newsEntities;
    public NewsCategoryEntity() {
    }

    public List<NewsEntity> getNewsEntities() {
        return newsEntities;
    }

    public void setNewsEntities(List<NewsEntity> newsEntities) {
        this.newsEntities = newsEntities;
    }

    public Integer getNewsCategoryId() {
        return newsCategoryId;
    }

    public void setNewsCategoryId(Integer newsCategoryId) {
        this.newsCategoryId = newsCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
