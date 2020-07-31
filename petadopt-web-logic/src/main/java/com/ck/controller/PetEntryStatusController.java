package com.ck.controller;

import com.ck.dto.PetEntryStatusDTO;
import com.ck.dto.PetEntryTypeDTO;
import com.ck.services.PetEntryStatusService;
import com.ck.services.PetEntryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class PetEntryStatusController {
    @Autowired
    PetEntryStatusService petEntryStatuservice;
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/admin/petentrystatus",method = RequestMethod.GET)
    private List<PetEntryStatusDTO> findAll(){
        return petEntryStatuservice.findAll();
    }
}
