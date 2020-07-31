package com.ck.dto;

import javax.persistence.*;
import java.util.List;


public class RequestAdoptionPetStatusAppointmentDTO {

    private Integer id;

    private String name;
    private List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOs;
    public RequestAdoptionPetStatusAppointmentDTO() {
    }

    public List<RequestAdoptionPetAppointmentStatusDetailDTO> getRequestAdoptionPetAppointmentStatusDetailDTOs() {
        return requestAdoptionPetAppointmentStatusDetailDTOs;
    }

    public void setRequestAdoptionPetAppointmentStatusDetailDTOs(List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOs) {
        this.requestAdoptionPetAppointmentStatusDetailDTOs = requestAdoptionPetAppointmentStatusDetailDTOs;
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
