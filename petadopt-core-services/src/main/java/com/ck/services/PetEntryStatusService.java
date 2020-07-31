package com.ck.services;

import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetEntryTypeDTO;

import java.util.List;

public interface PetEntryStatusService {
    public List<PetEntryStatusDTO> findAll();
}
