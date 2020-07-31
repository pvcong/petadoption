package com.ck.dto;

import javax.persistence.*;
import java.util.List;


public class RequestAdoptionPetStatusDTO {

    private Integer id;

    private String name;

    private List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS;
    public RequestAdoptionPetStatusDTO() {
    }

    public List<RequestAdoptionPetStatusDetailDTO> getRequestAdoptionPetStatusDetailDTOS() {
        return requestAdoptionPetStatusDetailDTOS;
    }

    public void setRequestAdoptionPetStatusDetailDTOS(List<RequestAdoptionPetStatusDetailDTO> requestAdoptionPetStatusDetailDTOS) {
        this.requestAdoptionPetStatusDetailDTOS = requestAdoptionPetStatusDetailDTOS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
