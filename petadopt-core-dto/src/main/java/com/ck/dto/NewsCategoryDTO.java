package com.ck.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

public class NewsCategoryDTO {

    private Integer newsCategoryId;
    private String name;
    private List<NewsDTO> newsDTOS;
    public NewsCategoryDTO() {
    }

    public List<NewsDTO> getNewsDTOS() {
        return newsDTOS;
    }

    public void setNewsDTOS(List<NewsDTO> newsDTOS) {
        this.newsDTOS = newsDTOS;
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
