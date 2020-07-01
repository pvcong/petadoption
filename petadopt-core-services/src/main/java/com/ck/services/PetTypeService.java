package com.ck.services;

import com.ck.dto.PetTypeDTO;

import java.util.List;

public interface PetTypeService {
    public void save(PetTypeDTO petTypeDTO);
    public void update(PetTypeDTO petTypeDTO);
    public void delete(List<PetTypeDTO> petTypeDTOs);
    public PetTypeDTO findById(Integer id);
}
