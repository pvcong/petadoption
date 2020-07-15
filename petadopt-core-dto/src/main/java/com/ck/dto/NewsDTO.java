package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;

public class NewsDTO {

    private Integer newsId;

    private String title;

    private String content;
    private String status;
    private Timestamp createdDate;

    private Timestamp modifiedDate;
    private String avatar;
    private UserDTO userDTO;

    private NewsCategoryDTO newsCategoryDTO;

    public NewsDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public NewsCategoryDTO getNewsCategoryDTO() {
        return newsCategoryDTO;
    }

    public void setNewsCategoryDTO(NewsCategoryDTO newsCategoryDTO) {
        this.newsCategoryDTO = newsCategoryDTO;
    }
}
