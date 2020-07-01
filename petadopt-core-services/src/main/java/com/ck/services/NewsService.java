package com.ck.services;

import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    public void save(NewsDTO newsDTO);
    public void update(NewsDTO newsDTO);
    public void delete(List<NewsDTO> newsDTOS);
    public NewsDTO findById(Integer id);
    public Object[] findByHome(Integer limit, Integer offset);
    public Object[] findByAdmin(NewsDTO newsDTO, NewsCategoryDTO newsCategoryDTO, String sortProperty, String sortValue, Integer limit, Integer offset);

}
