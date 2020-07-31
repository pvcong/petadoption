package com.ck.services;

import com.ck.data.*;
import com.ck.dto.*;
import com.ck.entitydao.RequestAdoptionPetDAO;
import com.ck.exceptionhandler.StatusNotMatchException;
import com.ck.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public class RequestAdoptionPetServiceImpl implements RequestAdoptionPetService {
    @Autowired
    RequestAdoptionPetDAO requestAdoptionPetDAO;
    @Override
    public void save(RequestAdoptionPetDTO requestAdoptionPetDTO) {
        RequestAdoptionPetEntity requestAdoptionPetEntity = RequestAdoptionPetUtils.dto2Entity(requestAdoptionPetDTO);
        PetEntity petEntity = PetUtils.dto2Entity(requestAdoptionPetDTO.getPetDTO());

        requestAdoptionPetEntity.setPetEntity(petEntity);
//        requestAdoptionPetStatusDetailEntity.set
        requestAdoptionPetDAO.save(requestAdoptionPetEntity);

    }

    @Override
    public void updateStatus(RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO) {
       RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity =
               requestAdoptionPetDAO.findCurrentStatus(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetDTO().getRequesrAdoptionPerId());

        if(ValidateUpdateRequestPetStatus.validate(
                requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetStatusDTO().getId(),
                requestAdoptionPetStatusEntity.getId()))
            {
                RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity =
                        RequestAdoptionPetStatusDetailUtils.dto2Entity(requestAdoptionPetStatusDetailDTO);
                RequestAdoptionPetEntity requestAdoptionPetEntity = RequestAdoptionPetUtils.dto2Entity(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetDTO());

                RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity1 = RequestAdoptionPetStatusUtils
                        .dto2Entity(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetStatusDTO());
                UserEntity userEntity = UserUtils.dto2Entity(requestAdoptionPetStatusDetailDTO.getUserDTO());
                requestAdoptionPetStatusDetailEntity.setRequestAdoptionPetEntity(requestAdoptionPetEntity);
                requestAdoptionPetStatusDetailEntity.setRequestAdoptionPetStatusEntity(requestAdoptionPetStatusEntity1);
                requestAdoptionPetStatusDetailEntity.setUserEntity(userEntity);
                if(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetStatusDTO().getId() == 5){
                    RequestAdoptionPetAppointmentEntity requestAdoptionPetAppointmentEntity = RequestAdoptionPetAppointmentUtils.dto2Entity(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetDTO().getRequestAdoptionPetAppointmentDTOS().get(0));
                    List<RequestAdoptionPetAppointmentEntity> requestAdoptionPetAppointmentEntities = new ArrayList<>();
                    requestAdoptionPetAppointmentEntity.setUserEntity(userEntity);
                    requestAdoptionPetAppointmentEntities.add(requestAdoptionPetAppointmentEntity);
                    requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetEntity().setRequestAdoptionPetAppointmentEntities(requestAdoptionPetAppointmentEntities);

                }
                requestAdoptionPetDAO.updateAndAddStatus(requestAdoptionPetStatusDetailEntity);
        }else{
                throw new StatusNotMatchException(requestAdoptionPetStatusDetailDTO.getRequestAdoptionPetStatusDTO().getId().toString(),requestAdoptionPetStatusEntity.getId().toString());
        }
    }

    @Override
    public void delete(List<RequestAdoptionPetDTO> requestAdoptionPetDTOS) {

    }

    @Override
    public RequestAdoptionPetEntity findById(Integer id) {
        return null;
    }

    @Override
    public void findCurrentStatus(Integer id) {
        requestAdoptionPetDAO.findCurrentStatus(id);
    }

    @Override
    public Object[] findRequestAdoptionPetByStatusId(RequestAdoptionPetDTO requestAdoptionPetDTO, Integer requestAdoptionPetStatusId,Integer limit, Integer offset) {
        RequestAdoptionPetStatusEntity requestAdoptionPetStatusEntity = null;
        PetEntity petEntity = null;
        if(requestAdoptionPetDTO != null){
            if(requestAdoptionPetDTO.getPetDTO() != null){
                petEntity = PetUtils.dto2Entity(requestAdoptionPetDTO.getPetDTO());
            }
        }
        RequestAdoptionPetEntity requestAdoptionPetEntity = RequestAdoptionPetUtils.dto2Entity(requestAdoptionPetDTO);
        Object[] objects = requestAdoptionPetDAO.findRequestAdoptionPetByStatusId(requestAdoptionPetEntity,petEntity,requestAdoptionPetStatusId,limit,offset);
        List<RequestAdoptionPetEntity> requestAdoptionPetEntities = (List<RequestAdoptionPetEntity>) objects[1];
        List<RequestAdoptionPetDTO> requestAdoptionPetDTOS = new ArrayList<>();
        for(RequestAdoptionPetEntity item : requestAdoptionPetEntities){
            RequestAdoptionPetDTO itemRequestAdoptionPetDTO = RequestAdoptionPetUtils.entity2DTO(item);
            List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS = new ArrayList<>();

            List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities = item.getRequestAdoptionPetStatusDetailEntities();
            RequestAdoptionPetStatusDetailEntity requestAdoptionPetStatusDetailEntity = requestAdoptionPetStatusDetailEntities.get(0);
            RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO = RequestAdoptionPetStatusDetailUtils.entity2Dto(requestAdoptionPetStatusDetailEntity);
            requestAdoptionPetStatusDetailDTOS.add(requestAdoptionPetStatusDetailDTO);

            RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO = RequestAdoptionPetStatusUtils.entity2DTO(requestAdoptionPetStatusDetailEntity.getRequestAdoptionPetStatusEntity());
            UserDTO userDTO = UserUtils.entity2DTO(requestAdoptionPetStatusDetailEntity.getUserEntity());

            requestAdoptionPetStatusDetailDTO.setRequestAdoptionPetStatusDTO(requestAdoptionPetStatusDTO);
            requestAdoptionPetStatusDetailDTO.setUserDTO(userDTO);

            PetDTO petDTO = PetUtils.entity2DTO(item.getPetEntity());
            itemRequestAdoptionPetDTO.setPetDTO(petDTO);
            itemRequestAdoptionPetDTO.setRequestAdoptionPetStatusDetailDTOS(requestAdoptionPetStatusDetailDTOS);

            requestAdoptionPetDTOS.add(itemRequestAdoptionPetDTO);

        }
        return  new Object[]{objects[0],requestAdoptionPetDTOS};

    }

    @Override
    public RequestAdoptionPetDTO findSingleRequestAdoptionPetById(Integer id) {
        RequestAdoptionPetEntity requestAdoptionPetEntity = requestAdoptionPetDAO.findSingleRequestAdoptionPetById(id);

        List<RequestAdoptionPetStatusDetailEntity> requestAdoptionPetStatusDetailEntities = requestAdoptionPetEntity.getRequestAdoptionPetStatusDetailEntities();
        List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS = new ArrayList<>();
        for (RequestAdoptionPetStatusDetailEntity item : requestAdoptionPetStatusDetailEntities){
            RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO = RequestAdoptionPetStatusDetailUtils.entity2Dto(item);
            RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO = RequestAdoptionPetStatusUtils.entity2DTO(item.getRequestAdoptionPetStatusEntity());
            UserDTO userDTO = UserUtils.entity2DTO(item.getUserEntity());

            requestAdoptionPetStatusDetailDTO.setUserDTO(userDTO);
            requestAdoptionPetStatusDetailDTO.setRequestAdoptionPetStatusDTO(requestAdoptionPetStatusDTO);

            requestAdoptionPetStatusDetailDTOS.add(requestAdoptionPetStatusDetailDTO);

        }
        RequestAdoptionPetDTO requestAdoptionPetDTO = RequestAdoptionPetUtils.entity2DTO(requestAdoptionPetEntity);
        PetDTO petDTO = PetUtils.entity2DTO(requestAdoptionPetEntity.getPetEntity());
        requestAdoptionPetDTO.setPetDTO(petDTO);
        requestAdoptionPetDTO.setRequestAdoptionPetStatusDetailDTOS(requestAdoptionPetStatusDetailDTOS);

        return requestAdoptionPetDTO;
    }

    @Override
    public int totalRequestAdoptionPedding() {
        int count = requestAdoptionPetDAO.totalRequestAdoptionPedding();
        return count;
    }
}
