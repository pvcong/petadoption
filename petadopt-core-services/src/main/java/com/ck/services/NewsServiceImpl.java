package com.ck.services;

import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.data.UserEntity;
import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.NewsDAO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.utils.NewsCategoryUtils;
import com.ck.utils.NewsUtils;
import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    NewsDAO newsDAO;
    @Override
    public void save(NewsDTO newsDTO) {
        NewsEntity newsEntity = NewsUtils.dto2Entity(newsDTO);
        UserEntity userEntity = UserUtils.dto2Entity(newsDTO.getUserDTO());
        NewsCategoryEntity newsCategoryEntity = NewsCategoryUtils.dto2Entity(newsDTO.getNewsCategoryDTO());
        newsEntity.setUserEntity(userEntity);
        newsEntity.setNewsCategoryEntity(newsCategoryEntity);
        newsDAO.save(newsEntity);
    }

    @Override
    public void update(NewsDTO newsDTO) {
        NewsEntity newsEntity = NewsUtils.dto2Entity(newsDTO);
        NewsCategoryEntity newsCategoryEntity = NewsCategoryUtils.dto2Entity(newsDTO.getNewsCategoryDTO());
        newsEntity.setNewsCategoryEntity(newsCategoryEntity);
        newsDAO.update(newsEntity);
    }

    @Override
    public void delete(List<NewsDTO> newsDTOS) {
        List<NewsEntity> newsEntities = new ArrayList<>();
        for(NewsDTO newsDTO : newsDTOS){
            NewsEntity newsEntity = NewsUtils.dto2Entity(newsDTO);
            newsEntities.add(newsEntity);
        }
        newsDAO.delete(newsEntities);
    }

    @Override
    public NewsDTO findById(Integer id) {
        NewsEntity newsEntity = newsDAO.findById(id);
        if(newsEntity == null){
            throw new NotFoundObjectException();

        }
        NewsDTO newsDTO = NewsUtils.entity2DTO(newsEntity);
        UserEntity userEntity = newsEntity.getUserEntity();
        UserDTO userDTO = UserUtils.entity2DTO(userEntity);
        NewsCategoryEntity newsCategoryEntity = newsEntity.getNewsCategoryEntity();
        NewsCategoryDTO categoryDTO = NewsCategoryUtils.entity2DTO(newsCategoryEntity);
        newsDTO.setNewsCategoryDTO(categoryDTO);
        newsDTO.setUserDTO(userDTO);
        return newsDTO;
    }

    @Override
    public Object[] findByHome(Integer limit, Integer offset) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        Object[] objects = newsDAO.findNewsHome(limit,offset);
        List<NewsEntity> newsEntities = (List<NewsEntity>) objects[1];
        for(NewsEntity newsEntity : newsEntities){
            NewsDTO newsDTO = NewsUtils.entity2DTO(newsEntity);
            newsDTOS.add(newsDTO);
        }
        return new Object[]{objects[0],newsDTOS};
    }

    @Override
    public Object[] findByAdmin(NewsDTO newsDTO, NewsCategoryDTO newsCategoryDTO, String sortProperty, String sortValue, Integer limit, Integer offset) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        NewsEntity newsEntity = NewsUtils.dto2Entity(newsDTO);
        NewsCategoryEntity newsCategoryEntity = NewsCategoryUtils.dto2Entity(newsCategoryDTO);
        Object[] objects = newsDAO.findNewsAdmin(newsEntity,newsCategoryEntity,sortProperty,sortValue,limit,offset);
        List<NewsEntity> newsEntities = (List<NewsEntity>) objects[1];
        for(NewsEntity item : newsEntities){
            NewsDTO newsDTO1 = NewsUtils.entity2DTO(item);
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());
            newsDTO1.setUserDTO(userDTO);
            NewsCategoryEntity newsCategoryEntity1 = item.getNewsCategoryEntity();
            NewsCategoryDTO newsCategoryDTO1 = NewsCategoryUtils.entity2DTO(newsCategoryEntity1);
            newsDTO1.setNewsCategoryDTO(newsCategoryDTO1);
            newsDTOS.add(newsDTO1);
        }
        return new Object[]{objects[0],newsDTOS};
    }
}
