package com.ck.services;

import com.ck.data.*;
import com.ck.dto.*;
import com.ck.entitydao.RequestAdoptionPetAppointmentDAO;
import com.ck.entitydao.RequestAdoptionPetDAO;
import com.ck.exceptionhandler.StatusNotMatchException;
import com.ck.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestAdoptionPetAppointmentServiceImpl implements RequestAdoptionPetAppointmentService {
    @Autowired
    RequestAdoptionPetAppointmentDAO requestAdoptionPetAppointmentDAO;
    @Override
    public void save(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO) {
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = RequestAdoptionPetAppointmentUtils.dto2Entity(requestAdoptionPetAppointmentDTO);
        RequestAdoptionPetEntity requestAdoptionPetEntity = RequestAdoptionPetUtils.dto2Entity(requestAdoptionPetAppointmentDTO.getRequestAdoptionPetDTO());
        UserEntity userEntity = UserUtils.dto2Entity(requestAdoptionPetAppointmentDTO.getUserDTO());
        requestAdoptionPetAppointmentEntity.setUserEntity(userEntity);
        requestAdoptionPetAppointmentEntity.setRequestAdoptionPetEntity(requestAdoptionPetEntity);
//        requestAdoptionPetStatusDetailEntity.set
        requestAdoptionPetAppointmentDAO.save(requestAdoptionPetAppointmentEntity);

    }

    @Override
    public void update(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO) {
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = RequestAdoptionPetAppointmentUtils.dto2Entity(requestAdoptionPetAppointmentDTO);
        requestAdoptionPetAppointmentDAO.update(requestAdoptionPetAppointmentEntity);
    }

    @Override
    public void updateStatus(RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO) {
       RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity =
               requestAdoptionPetAppointmentDAO.findCurrentStatus(requestAdoptionPetAppointmentStatusDetailDTO.getRequestAdoptionPetAppointmentDTO().getId());

        if(ValidateUpdateRequestPetAppointmentUtils.validate(
                requestAdoptionPetAppointmentStatusDetailDTO.getRequestAdoptionPetStatusAppointmentDTO().getId(),
                requestAdoptionPetStatusAppointmentEntity.getId()))
            {
                RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity =
                        RequestAdoptionPetAppointmentStatusDetailUtils.dto2Entity(requestAdoptionPetAppointmentStatusDetailDTO);
                RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = RequestAdoptionPetAppointmentUtils.dto2Entity(requestAdoptionPetAppointmentStatusDetailDTO.getRequestAdoptionPetAppointmentDTO());

                RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity1 = RequestAdoptionPetAppointmentStatusUtils
                        .dto2Entity(requestAdoptionPetAppointmentStatusDetailDTO.getRequestAdoptionPetStatusAppointmentDTO());
                UserEntity userEntity = UserUtils.dto2Entity(requestAdoptionPetAppointmentStatusDetailDTO.getUserDTO());
                requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetAppointmentEntity(requestAdoptionPetAppointmentEntity);
                requestAdoptionPetAppointmentStatusDetailEntity.setRequestAdoptionPetStatusAppointmentEntity(requestAdoptionPetStatusAppointmentEntity1);
                requestAdoptionPetAppointmentEntity.setUserEntity(userEntity);
                requestAdoptionPetAppointmentStatusDetailEntity.setUserEntity(userEntity);
                requestAdoptionPetAppointmentDAO.updateAndAddStatus(requestAdoptionPetAppointmentStatusDetailEntity);
        }else{
                throw new StatusNotMatchException(requestAdoptionPetAppointmentStatusDetailDTO.getRequestAdoptionPetStatusAppointmentDTO().getId().toString(),requestAdoptionPetStatusAppointmentEntity.getId().toString());
        }
    }



    @Override
    public RequestAdoptionPetEntity findById(Integer id) {
        return null;
    }



    @Override
    public Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO, Integer requestAdoptionPetStatusId,Integer limit, Integer offset) {
        RequestAdoptionPetStatusAppointmentEntity requestAdoptionPetStatusAppointmentEntity = null;
        RequestAdoptionPetEntity requestAdoptionPetEntity = null;
        if(requestAdoptionPetAppointmentDTO != null){
            if(requestAdoptionPetAppointmentDTO.getRequestAdoptionPetDTO() != null){
                requestAdoptionPetEntity = RequestAdoptionPetUtils.dto2Entity(requestAdoptionPetAppointmentDTO.getRequestAdoptionPetDTO());
            }
        }
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = RequestAdoptionPetAppointmentUtils.dto2Entity(requestAdoptionPetAppointmentDTO);
        Object[] objects = requestAdoptionPetAppointmentDAO.findRequestAdoptionPetByStatusId(requestAdoptionPetAppointmentEntity,requestAdoptionPetEntity,requestAdoptionPetStatusId,limit,offset);
        List<RequestAdoptionPetAppointmentEntity> requestAdoptionPetAppointmentEntities = (List<RequestAdoptionPetAppointmentEntity>) objects[1];
        List<RequestAdoptionPetAppointmentDTO> requestAdoptionPetAppointmentDTOS = new ArrayList<>();
        for(RequestAdoptionPetAppointmentEntity item : requestAdoptionPetAppointmentEntities){
            RequestAdoptionPetAppointmentDTO itemRequestAdoptionPetDTO = RequestAdoptionPetAppointmentUtils.entity2DTO(item);
            List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOS = new ArrayList<>();

            List<RequestAdoptionPetAppointmentStatusDetailEntity> requestAdoptionPetAppointmentStatusDetailEntities = item.getRequestAdoptionPetAppointmentStatusDetailEntities();
            RequestAdoptionPetAppointmentStatusDetailEntity requestAdoptionPetAppointmentStatusDetailEntity = requestAdoptionPetAppointmentStatusDetailEntities.get(0);
            RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO = RequestAdoptionPetAppointmentStatusDetailUtils.entity2Dto(requestAdoptionPetAppointmentStatusDetailEntity);
            requestAdoptionPetAppointmentStatusDetailDTOS.add(requestAdoptionPetAppointmentStatusDetailDTO);

            RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO = RequestAdoptionPetAppointmentStatusUtils.entity2DTO(requestAdoptionPetAppointmentStatusDetailEntity.getRequestAdoptionPetStatusAppointmentEntity());
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());

            requestAdoptionPetAppointmentStatusDetailDTO.setRequestAdoptionPetStatusAppointmentDTO(requestAdoptionPetStatusAppointmentDTO);
            itemRequestAdoptionPetDTO.setUserDTO(userDTO);

            RequestAdoptionPetDTO requestAdoptionPetDTO = RequestAdoptionPetUtils.entity2DTO(item.getRequestAdoptionPetEntity());
            itemRequestAdoptionPetDTO.setRequestAdoptionPetDTO(requestAdoptionPetDTO);
            itemRequestAdoptionPetDTO.setRequestAdoptionPetAppointmentStatusDetailDTOs(requestAdoptionPetAppointmentStatusDetailDTOS);

            requestAdoptionPetAppointmentDTOS.add(itemRequestAdoptionPetDTO);

        }
        return  new Object[]{objects[0],requestAdoptionPetAppointmentDTOS};

    }

    @Override
    public RequestAdoptionPetAppointmentDTO findSingleRequestAdoptionPetById(Integer id) {
        RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = requestAdoptionPetAppointmentDAO.findSingleRequestAdoptionPetById(id);

        List<RequestAdoptionPetAppointmentStatusDetailEntity> requestAdoptionPetAppointmentStatusDetailEntities = requestAdoptionPetAppointmentEntity.getRequestAdoptionPetAppointmentStatusDetailEntities();
        List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOS = new ArrayList<>();
        for (RequestAdoptionPetAppointmentStatusDetailEntity item : requestAdoptionPetAppointmentStatusDetailEntities){
            RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO = RequestAdoptionPetAppointmentStatusDetailUtils.entity2Dto(item);
            RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO = RequestAdoptionPetAppointmentStatusUtils.entity2DTO(item.getRequestAdoptionPetStatusAppointmentEntity());
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());

            requestAdoptionPetAppointmentStatusDetailDTO.setUserDTO(userDTO);
            requestAdoptionPetAppointmentStatusDetailDTO.setRequestAdoptionPetStatusAppointmentDTO(requestAdoptionPetStatusAppointmentDTO);

            requestAdoptionPetAppointmentStatusDetailDTOS.add(requestAdoptionPetAppointmentStatusDetailDTO);

        }
        RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO = RequestAdoptionPetAppointmentUtils.entity2DTO(requestAdoptionPetAppointmentEntity);
        RequestAdoptionPetDTO requestAdoptionPetDTO = RequestAdoptionPetUtils.entity2DTO(requestAdoptionPetAppointmentEntity.getRequestAdoptionPetEntity());
        UserDTO userDTO = UserUtils.entity2DTO(requestAdoptionPetAppointmentEntity.getUserEntity());
        requestAdoptionPetAppointmentDTO.setRequestAdoptionPetDTO(requestAdoptionPetDTO);
        requestAdoptionPetAppointmentDTO.setRequestAdoptionPetAppointmentStatusDetailDTOs(requestAdoptionPetAppointmentStatusDetailDTOS);
        requestAdoptionPetAppointmentDTO.setUserDTO(userDTO);
        return requestAdoptionPetAppointmentDTO;
    }

    @Override
    public int totalAppointmentAvailabilityToday() {
        int count = requestAdoptionPetAppointmentDAO.totalAppointmentAvailabilityToday();
        return count;
    }
}
