package com.ck.controller;

import com.ck.dto.DepartmentDTO;
import com.ck.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/admin/department",method = RequestMethod.GET)
    private List<DepartmentDTO> findAll(){
        return departmentService.findAll();
    }

}
