package com.ck.data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table( name = "news")
public class NewsEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column( name = "news_id")
    private Integer newsId;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column( name = "avatar")
    private String avatar;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "modified_date")
    private Timestamp modifiedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name  ="user_id")
    private UserEntity userEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name  ="news_category_id")
    private NewsCategoryEntity newsCategoryEntity;

    public NewsEntity() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public NewsCategoryEntity getNewsCategoryEntity() {
        return newsCategoryEntity;
    }

    public void setNewsCategoryEntity(NewsCategoryEntity newsCategoryEntity) {
        this.newsCategoryEntity = newsCategoryEntity;
    }
}
