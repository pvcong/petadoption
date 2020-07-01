package com.ck.utils;

import com.ck.data.NewsCategoryEntity;
import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;

public class NewsCategoryUtils {
    public static NewsCategoryEntity dto2Entity(NewsCategoryDTO newsCategoryDTO){
        NewsCategoryEntity newsCategoryEntity = new NewsCategoryEntity();
        if(newsCategoryDTO != null){
            newsCategoryEntity.setName(newsCategoryDTO.getName());
            newsCategoryEntity.setNewsCategoryId(newsCategoryDTO.getNewsCategoryId());

        }
        return newsCategoryEntity;
    }
    public static NewsCategoryDTO entity2DTO(NewsCategoryEntity newsCategoryEntity){
        NewsCategoryDTO newsCategoryDTO = new NewsCategoryDTO();
        if(newsCategoryEntity != null){
            newsCategoryDTO.setName(newsCategoryEntity.getName());
            newsCategoryDTO.setNewsCategoryId(newsCategoryEntity.getNewsCategoryId());

        }
        return newsCategoryDTO;
    }
}
