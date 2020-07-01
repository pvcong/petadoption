package com.ck.controller.commander;

import java.util.List;

public abstract class AbstractCommand<T> {
    private T pojo;
    private List<T> pojos;
    private Integer page = 1;
    private Integer firtItem = 0;
    private Integer maxItem = 4;
    private Integer totalItem = 0;
    private Integer totalPage = 0;
    private String sortProperty;
    private String sortValue;

    public AbstractCommand() {
    }

    public AbstractCommand(T pojo, List<T> pojos, Integer page, Integer firtItem, Integer maxItem, Integer totalItem, Integer totalPage, String sortProperty, String sortValue) {
        this.pojo = pojo;
        this.pojos = pojos;
        this.page = page;
        this.firtItem = firtItem;
        this.maxItem = maxItem;
        this.totalItem = totalItem;
        this.totalPage = totalPage;
        this.sortProperty = sortProperty;
        this.sortValue = sortValue;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public List<T> getPojos() {
        return pojos;
    }

    public void setPojos(List<T> pojos) {
        this.pojos = pojos;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getFirtItem() {
        return firtItem;
    }

    public void setFirtItem(Integer firtItem) {
        this.firtItem = firtItem;
    }

    public Integer getMaxItem() {
        return maxItem;
    }

    public void setMaxItem(Integer maxitem) {
        this.maxItem = maxitem;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
}
