package com.ck.utils;

import com.ck.data.NewsEntity;
import com.ck.dto.NewsDTO;

public class NewsUtils {
    public static NewsEntity dto2Entity(NewsDTO newsDTO){
        NewsEntity newsEntity = new NewsEntity();
        if(newsDTO != null){
            newsEntity.setContent(newsDTO.getContent());
            newsEntity.setTitle(newsDTO.getTitle());
            newsEntity.setNewsId(newsDTO.getNewsId());
            newsEntity.setModifiedDate(newsDTO.getModifiedDate());
            newsEntity.setCreatedDate(newsDTO.getCreatedDate());
            if(newsDTO.getStatus() != null)
            newsEntity.setStatus(newsDTO.getStatus().toLowerCase());
            newsEntity.setAvatar(newsDTO.getAvatar());

        }
        return newsEntity;
    }
    public static NewsDTO entity2DTO(NewsEntity newsEntity){
        NewsDTO newsDTO = new NewsDTO();
        if(newsEntity != null){
            newsDTO.setContent(newsEntity.getContent());
            newsDTO.setTitle(newsEntity.getTitle());
            newsDTO.setAvatar(newsEntity.getAvatar());
            if(newsEntity.getStatus() != null)
            newsDTO.setStatus(newsEntity.getStatus());
            newsDTO.setNewsId(newsEntity.getNewsId());
            newsDTO.setModifiedDate(newsEntity.getModifiedDate());
            newsDTO.setCreatedDate(newsEntity.getCreatedDate());


        }
        return newsDTO;
    }
}
