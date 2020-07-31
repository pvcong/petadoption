package com.ck.services;

import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;

import java.util.List;

public interface NewsCategoryService {
    public void save(NewsCategoryDTO newsCategoryDTO);
    public void update(NewsCategoryDTO newsCategoryDTO);
    public void delete(List<NewsCategoryDTO> newsCategoryDTOS);
    public NewsCategoryDTO findById(Integer id);
    public List<NewsCategoryDTO> findAll();
    public Object[] findNewsOrNewsCategoryById(Integer newsCategoryId,Integer limit, Integer offset);
}
