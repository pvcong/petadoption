package com.ck.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


public class RequestAdoptionPetStatusDetailDTO {

    private Integer id;

    private String content;



    private Timestamp createdDate;

    private RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO;

    private RequestAdoptionPetDTO requestAdoptionPetDTO;

    private UserDTO userDTO;
    public RequestAdoptionPetStatusDetailDTO() {
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



    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public RequestAdoptionPetStatusDTO getRequestAdoptionPetStatusDTO() {
        return requestAdoptionPetStatusDTO;
    }

    public void setRequestAdoptionPetStatusDTO(RequestAdoptionPetStatusDTO requestAdoptionPetStatusDTO) {
        this.requestAdoptionPetStatusDTO = requestAdoptionPetStatusDTO;
    }

    public RequestAdoptionPetDTO getRequestAdoptionPetDTO() {
        return requestAdoptionPetDTO;
    }

    public void setRequestAdoptionPetDTO(RequestAdoptionPetDTO requestAdoptionPetDTO) {
        this.requestAdoptionPetDTO = requestAdoptionPetDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
