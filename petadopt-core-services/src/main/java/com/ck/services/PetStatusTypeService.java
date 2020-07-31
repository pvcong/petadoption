package com.ck.services;

import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.dto.PetStatusTypeDTO;

import java.util.List;

public interface PetStatusTypeService {
    public List<PetStatusTypeDTO> findAll();

}
