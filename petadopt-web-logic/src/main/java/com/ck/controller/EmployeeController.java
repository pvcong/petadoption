package com.ck.controller;

import com.ck.controller.commander.EmployeeCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.EmployeeEntity;
import com.ck.dto.EmployeeDTO;
import com.ck.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
    public class EmployeeController {


    @Autowired
    EmployeeService employeeService;


    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/employee", method = RequestMethod.POST)
    public void saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        employeeDTO.setCreatedDate(createdDate);
        employeeDTO.setModifiedDate(createdDate);
        employeeService.save(employeeDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/employee", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody EmployeeDTO employeeDTO){

        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        employeeDTO.setModifiedDate(modifiedDate);
        employeeService.update(employeeDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/employee/{id}", method = RequestMethod.GET)
    public EmployeeDTO findEmployeeById(@PathVariable Integer id){


        return employeeService.findById(id);
    }


    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/employee", method = RequestMethod.GET )
    public Map<String,Object> findEmployeeAdmin(@ModelAttribute EmployeeCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        EmployeeDTO employeeDTO = cmd.getPojo();
        Object[] objects = employeeService.findEmployeeByPropertiesAdmin(employeeDTO,cmd.getMaxItem(),cmd.getFirtItem());

        List<EmployeeDTO> employeeDTOs = (List<EmployeeDTO>) objects[1];
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);
        Integer totalPage = cmd.getTotalPage();

        Map<String,Object> map = new HashMap<>();
        map.put("objects",employeeDTOs);
        map.put("totalPage",totalPage);
        return map;
    }



}
