package com.ck.services;

import com.ck.data.NewsCategoryEntity;
import com.ck.data.NewsEntity;
import com.ck.data.UserEntity;
import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;
import com.ck.dto.UserDTO;
import com.ck.entitydao.NewsCategoryDAO;
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
public class NewsCategoryServiceImpl implements NewsCategoryService{
    @Autowired
    NewsCategoryDAO newsCategoryDAO;

    @Override
    public void save(NewsCategoryDTO newsCategoryDTO) {
        newsCategoryDAO.save(NewsCategoryUtils.dto2Entity(newsCategoryDTO));
    }

    @Override
    public void update(NewsCategoryDTO newsCategoryDTO) {
        newsCategoryDAO.update(NewsCategoryUtils.dto2Entity(newsCategoryDTO));

    }

    @Override
    public void delete(List<NewsCategoryDTO> newsCategoryDTOS) {
        List<NewsCategoryEntity> newsCategoryEntities  = new ArrayList<>();
        for(NewsCategoryDTO item : newsCategoryDTOS){
            NewsCategoryEntity newsCategoryEntity = NewsCategoryUtils.dto2Entity(item);
            newsCategoryEntities.add(newsCategoryEntity);
        }
        newsCategoryDAO.delete(newsCategoryEntities);
    }
    @Override
    public Object[] findNewsOrNewsCategoryById(Integer newsCategoryId,Integer limit, Integer offset) {
        Object[] objects = newsCategoryDAO.findNewsOfNewsCategoryById(newsCategoryId,limit,offset);
        NewsCategoryEntity newsCategoryEntity = (NewsCategoryEntity) objects[1];
        if(newsCategoryEntity == null){
            throw new NotFoundObjectException();
        }
            NewsCategoryDTO newsCategoryDTO = NewsCategoryUtils.entity2DTO(newsCategoryEntity);
            List<NewsEntity> newsEntities = newsCategoryEntity.getNewsEntities();
            List<NewsDTO> newsDTOS = new ArrayList<>();
            for(NewsEntity item: newsEntities){
                NewsDTO newsDTO = NewsUtils.entity2DTO(item);
                newsDTOS.add(newsDTO);
            }
        newsCategoryDTO.setNewsDTOS(newsDTOS);
        return new Object[]{objects[0],newsCategoryDTO};
    }
    @Override
    public NewsCategoryDTO findById(Integer id) {
        NewsCategoryEntity newsCategoryEntity = newsCategoryDAO.findById(id);
        NewsCategoryDTO newsCategoryDTO = NewsCategoryUtils.entity2DTO(newsCategoryEntity);
        return newsCategoryDTO;
    }

    @Override
    public List<NewsCategoryDTO> findAll() {
        List<NewsCategoryEntity> newsCategoryEntities = newsCategoryDAO.findAll();
        List<NewsCategoryDTO> newsCategoryDTOS = new ArrayList<>();
        for(NewsCategoryEntity item : newsCategoryEntities){
            NewsCategoryDTO newsCategoryDTO = NewsCategoryUtils.entity2DTO(item);
            newsCategoryDTOS.add(newsCategoryDTO);
        }
        return newsCategoryDTOS;
    }
}
