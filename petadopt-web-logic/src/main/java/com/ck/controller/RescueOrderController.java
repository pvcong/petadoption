package com.ck.controller;

import com.ck.controller.commander.PetCommand;
import com.ck.controller.commander.RescueOrderCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.PetEntity;
import com.ck.data.RescueOrderEntity;
import com.ck.dto.EmployeeDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.RescueOrderDTO;
import com.ck.services.RescueOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class RescueOrderController {

    @Autowired
    RescueOrderService rescueOrderService;


    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/rescueorder", method = RequestMethod.POST)
    public void saveRescueOrder(@RequestBody RescueOrderDTO rescueOrderDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        rescueOrderDTO.setCreatedDate(createdDate);
        rescueOrderDTO.setModifiedDate(createdDate);
        rescueOrderService.save(rescueOrderDTO);
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/rescueorder/{id}/pet", method = RequestMethod.POST)
    public void savePetofRescueOrder(@RequestBody PetDTO petDTO,@PathVariable(name = "id") Integer rescueOrderId){
        rescueOrderService.findById(rescueOrderId);
        RescueOrderDTO rescueOrderDTO = new RescueOrderDTO();
        rescueOrderDTO.setRescueOrderId(rescueOrderId);
        petDTO.setRescueOrderDTO(rescueOrderDTO);
        rescueOrderService.addPet(petDTO);
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "admin/rescueorder/{id}/pet", method = RequestMethod.DELETE)
    public void removePetofRescueOrder(@RequestBody PetDTO petDTO,@PathVariable(name = "id") Integer rescueOrderId){
        rescueOrderService.findById(rescueOrderId);
        RescueOrderDTO rescueOrderDTO = new RescueOrderDTO();
        rescueOrderDTO.setRescueOrderId(rescueOrderId);
        petDTO.setRescueOrderDTO(rescueOrderDTO);
        rescueOrderService.removePet(petDTO);
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/rescueorder", method = RequestMethod.PUT)
    public void updateRescueOrder(@RequestBody RescueOrderDTO rescueOrderDTO){

        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        rescueOrderDTO.setModifiedDate(modifiedDate);
        rescueOrderService.update(rescueOrderDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/rescueorder/{id}", method = RequestMethod.GET)
    public RescueOrderDTO findEmployeeById(@PathVariable Integer id){


        return rescueOrderService.findById(id);
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/rescueorder/pet", method = RequestMethod.GET)
    public Map<String,Object> findPetDontOrder(@ModelAttribute PetCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);

        PetDTO petDTO = cmd.getPojo();

        Object[] objects =  rescueOrderService.findPetDontOrder(petDTO,cmd.getMaxItem(),cmd.getFirtItem());

        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<PetDTO> petDTOS = (List<PetDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();

        map.put("objects",petDTOS);
        map.put("totalPage",cmd.getTotalPage());
        return map;
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/rescueorder", method = RequestMethod.GET )
    public Map<String,Object> findRescueOrder(@ModelAttribute RescueOrderCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        RescueOrderDTO rescueOrderDTO  = cmd.getPojo();
        Object[] objects = rescueOrderService.findRescueOrderByPropertiesAdmin(rescueOrderDTO,cmd.getMaxItem(),cmd.getFirtItem());

        List<RescueOrderDTO> rescueOrderDTOS = (List<RescueOrderDTO>) objects[1];
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);
        Integer totalPage = cmd.getTotalPage();

        Map<String,Object> map = new HashMap<>();
        map.put("objects",rescueOrderDTOS);
        map.put("totalPage",totalPage);
        return map;
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/employee/{id}/rescueorder", method = RequestMethod.GET)
    public Map<String,Object> findPetOfEmployye(@ModelAttribute RescueOrderCommand cmd, @PathVariable(name="id") Integer id){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = rescueOrderService.findPetOfEmployye(id,cmd.getMaxItem(),cmd.getFirtItem());
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);
        List<RescueOrderEntity> rescueOrderEntities = (List<RescueOrderEntity>) objects[1];
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",objects[0]);
        map.put("objects",rescueOrderEntities);
        return map;
    }


}
