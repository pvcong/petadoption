package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;


public class RequestAdoptionPetAppointmentStatusDetailDTO {

    private Integer id;

    private String content;

    private UserDTO userDTO;

    private RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO;

    private RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO;

    private Timestamp createdDate;

    public RequestAdoptionPetAppointmentStatusDetailDTO() {
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

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public RequestAdoptionPetAppointmentDTO getRequestAdoptionPetAppointmentDTO() {
        return requestAdoptionPetAppointmentDTO;
    }

    public void setRequestAdoptionPetAppointmentDTO(RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO) {
        this.requestAdoptionPetAppointmentDTO = requestAdoptionPetAppointmentDTO;
    }

    public RequestAdoptionPetStatusAppointmentDTO getRequestAdoptionPetStatusAppointmentDTO() {
        return requestAdoptionPetStatusAppointmentDTO;
    }

    public void setRequestAdoptionPetStatusAppointmentDTO(RequestAdoptionPetStatusAppointmentDTO requestAdoptionPetStatusAppointmentDTO) {
        this.requestAdoptionPetStatusAppointmentDTO = requestAdoptionPetStatusAppointmentDTO;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
