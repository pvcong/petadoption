package com.ck.services;

import com.ck.data.FosterPetEntity;
import com.ck.dto.FosterPetDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.RescueOrderDTO;

import java.util.List;

public interface FosterPetService {
    public void save(FosterPetDTO fosterPetDTO);
    public void update(FosterPetDTO fosterPetDTO);
    public void delete(List<FosterPetDTO> fosterPetDTOS);
    public void addPet(PetDTO petDTO);
    public void removePet(PetDTO petDTO);
    public FosterPetDTO findById(Integer id);
    Object[] findPetDontFoster(PetDTO petDTO,Integer limit, Integer offset);
    Boolean checkPetIsFoster(Integer petId);
    public Object[] findFosterPetByPropertiesAdmin(FosterPetDTO fosterPetDTO, Integer limit, Integer offset);
    public Object[] findPetOfEmployye(Integer employeeId, Integer limit, Integer offset);

}
