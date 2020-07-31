package com.ck.utils;

import com.ck.data.RescueOrderEntity;
import com.ck.dto.RescueOrderDTO;

import java.util.ArrayList;
import java.util.List;

public class RescueOrderUtils {
    public static RescueOrderEntity dto2Entity(RescueOrderDTO rescueOrderDTO){
        RescueOrderEntity rescueOrderEntity = new RescueOrderEntity();
        if(rescueOrderDTO != null){
            rescueOrderEntity.setRescueOrderId(rescueOrderDTO.getRescueOrderId());
            rescueOrderEntity.setContent(rescueOrderDTO.getContent());
            rescueOrderEntity.setCreatedDate(rescueOrderDTO.getCreatedDate());
            rescueOrderEntity.setDateRescue(rescueOrderDTO.getDateRescue());
            rescueOrderEntity.setModifiedDate(rescueOrderDTO.getModifiedDate());
            rescueOrderEntity.setRegion(rescueOrderDTO.getRegion());



        }
        return rescueOrderEntity;
    }

    public static RescueOrderDTO entity2DTO(RescueOrderEntity rescueOrderEntity){
        RescueOrderDTO rescueOrderDTO = new RescueOrderDTO();
        if(rescueOrderEntity != null){
            rescueOrderDTO.setRescueOrderId(rescueOrderEntity.getRescueOrderId());
            rescueOrderDTO.setContent(rescueOrderEntity.getContent());
            rescueOrderDTO.setCreatedDate(rescueOrderEntity.getCreatedDate());
            rescueOrderDTO.setDateRescue(rescueOrderEntity.getDateRescue());
            rescueOrderDTO.setModifiedDate(rescueOrderEntity.getModifiedDate());
            rescueOrderDTO.setRegion(rescueOrderEntity.getRegion());



        }
        return rescueOrderDTO;
    }

    public static List<RescueOrderEntity> dtos2Entities(List<RescueOrderDTO> rescueOrderDTOS){
        List<RescueOrderEntity> rescueOrderEntities = new ArrayList<>();
        for(RescueOrderDTO item : rescueOrderDTOS){
            RescueOrderEntity rescueOrderEntity = dto2Entity(item);
            rescueOrderEntities.add(rescueOrderEntity);
        }
        return rescueOrderEntities;
    }
    public static List<RescueOrderDTO> entities2DTOs(List<RescueOrderEntity> rescueOrderEntities){
        List<RescueOrderDTO> rescueOrderDTOS = new ArrayList<>();
        for(RescueOrderEntity item : rescueOrderEntities){
            RescueOrderDTO rescueOrderDTO = entity2DTO(item);
            rescueOrderDTOS.add(rescueOrderDTO);
        }
        return rescueOrderDTOS;
    }
}
