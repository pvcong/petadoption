package com.ck.services;

import com.ck.dto.PetAboutDTO;
import com.ck.dto.PetDTO;
import com.ck.entitydao.PetAboutDAO;

import java.util.List;

public interface PetAboutService {
    public void save(PetAboutDTO petAboutDTO);
    public void update(PetAboutDTO petAboutDTO);
    public void delete(List<PetAboutDTO> petAboutDTOS);
    public PetAboutDTO findById(Integer id);
}
