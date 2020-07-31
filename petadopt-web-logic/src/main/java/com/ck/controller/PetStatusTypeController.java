package com.ck.controller;

import com.ck.data.PetStatusTypeEntity;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.dto.PetStatusTypeDTO;
import com.ck.services.PetEntryTypeService;
import com.ck.services.PetStatusTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PetStatusTypeController {
    @Autowired
    PetStatusTypeService petStatusTypeService;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/admin/petstatustype",method = RequestMethod.GET)
    private List<PetStatusTypeDTO> findAll(){
        return petStatusTypeService.findAll();
    }
}
