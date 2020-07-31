package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class RequestAdoptionPetAppointmentDTO {

    private Integer id;

    private String content;

    private Timestamp appointmentDate;

    private Timestamp createdDate;

    private Timestamp modifiedDate;

    private UserDTO userDTO;

    private RequestAdoptionPetDTO requestAdoptionPetDTO;
    private List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOs;
    public RequestAdoptionPetAppointmentDTO() {
    }

    public List<RequestAdoptionPetAppointmentStatusDetailDTO> getRequestAdoptionPetAppointmentStatusDetailDTOs() {
        return requestAdoptionPetAppointmentStatusDetailDTOs;
    }

    public void setRequestAdoptionPetAppointmentStatusDetailDTOs(List<RequestAdoptionPetAppointmentStatusDetailDTO> requestAdoptionPetAppointmentStatusDetailDTOs) {
        this.requestAdoptionPetAppointmentStatusDetailDTOs = requestAdoptionPetAppointmentStatusDetailDTOs;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RequestAdoptionPetDTO getRequestAdoptionPetDTO() {
        return requestAdoptionPetDTO;
    }

    public void setRequestAdoptionPetDTO(RequestAdoptionPetDTO requestAdoptionPetDTO) {
        this.requestAdoptionPetDTO = requestAdoptionPetDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    }

