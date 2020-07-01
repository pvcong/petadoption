package com.ck.services;

import com.ck.data.PetAboutEntity;
import com.ck.data.PetEntity;
import com.ck.data.PetTypeEntity;
import com.ck.dto.PetAboutDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.PetTypeDTO;

import java.util.List;

public interface PetService {
    public void save(PetDTO petDTO);
    public void update(PetDTO petDTO);
    public void delete(List<PetDTO> petDTOs);
    public PetDTO findById(Integer id);
    public PetDTO findSinglePetHome(Integer id);
    public Object[] findPetHome(PetDTO petDTO, PetTypeDTO petTypeDTO, PetAboutDTO petAboutDTO, Integer limit, Integer offset);
    public PetDTO findSinglePetAdmin(Integer id);
    public Object[] findPetAdmin(PetDTO petDTO, PetTypeDTO petTypeDTO,String propertySort ,String propertyValue, Integer limit, Integer offset);
}
