package com.ck.services;

import com.ck.data.RequestAdoptionPetEntity;
import com.ck.data.RequestAdoptionPetStatusDetailEntity;
import com.ck.data.UserEntity;
import com.ck.dto.ProductDTO;
import com.ck.dto.RequestAdoptionPetDTO;
import com.ck.dto.RequestAdoptionPetStatusDetailDTO;

import java.util.List;

public interface RequestAdoptionPetService {
    public void save(RequestAdoptionPetDTO requestAdoptionPetDTO);
    public void updateStatus(RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO);
    public void delete(List<RequestAdoptionPetDTO> requestAdoptionPetDTOS);
    public RequestAdoptionPetEntity findById(Integer id);
    public void findCurrentStatus(Integer id);
    Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetDTO requestAdoptionPetDTO,Integer requestAdoptionPetStatusId,Integer limit, Integer offset);
    RequestAdoptionPetDTO findSingleRequestAdoptionPetById(Integer id);
    int totalRequestAdoptionPedding();
}
