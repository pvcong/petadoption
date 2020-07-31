package com.ck.controller;

import com.ck.dto.PetEntryTypeDTO;
import com.ck.services.PetEntryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class PetEntryTypeController {
    @Autowired
    PetEntryTypeService petEntryTypeService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/admin/petentrytype",method = RequestMethod.GET)
    private List<PetEntryTypeDTO> findAll(){
        return petEntryTypeService.findAll();
    }
}
