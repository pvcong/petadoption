package com.ck.controller;

import com.ck.controller.commander.FosterPetCommand;
import com.ck.controller.commander.PetCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.FosterPetEntity;
import com.ck.dto.FosterPetDTO;
import com.ck.dto.PetDTO;
import com.ck.services.FosterPetService;
import com.ck.utils.JpaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class FosterPetController {
    @Autowired
    FosterPetService fosterPetService;


    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/fosterpet", method = RequestMethod.POST)
    public void saveRescueOrder(@RequestBody FosterPetDTO fosterPetDTO){

        fosterPetService.save(fosterPetDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/fosterpet", method = RequestMethod.PUT)
    public void updateRescueOrder(@RequestBody FosterPetDTO fosterPetDTO){

        fosterPetService.update(fosterPetDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/fosterpet/{id}", method = RequestMethod.GET)
    public FosterPetDTO findEmployeeById(@PathVariable Integer id){


        return fosterPetService.findById(id);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/employee/{id}/fosterpet", method = RequestMethod.GET)
    public Map<String,Object> findPetOfEmployye(@ModelAttribute FosterPetCommand cmd, @PathVariable(name="id") Integer id){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = fosterPetService.findPetOfEmployye(id,cmd.getMaxItem(),cmd.getFirtItem());
         cmd.setTotalItem((Integer) objects[0]);
         ParaginationUtils.caculationToltalPage(cmd);
         List<FosterPetEntity> fosterPetEntities = (List<FosterPetEntity>) objects[1];
         Map<String,Object> map = new HashMap<>();
         map.put("totalPage",objects[0]);
         map.put("objects",fosterPetEntities);
         return map;
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/fosterpet", method = RequestMethod.GET )
    public Map<String,Object> findRescueOrder(@ModelAttribute FosterPetCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        FosterPetDTO fosterPetDTO  = cmd.getPojo();
        Object[] objects = fosterPetService.findFosterPetByPropertiesAdmin(fosterPetDTO,cmd.getMaxItem(),cmd.getFirtItem());

        List<FosterPetDTO> fosterPetDTOS = (List<FosterPetDTO>) objects[1];
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);
        Integer totalPage = cmd.getTotalPage();

        Map<String,Object> map = new HashMap<>();
        map.put("objects",fosterPetDTOS);
        map.put("totalPage",totalPage);
        return map;
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/fosterpet/{id}/pet", method = RequestMethod.POST)
    public void savePetofFosterPet(@RequestBody PetDTO petDTO, @PathVariable(name = "id") Integer fosterPetId){
        fosterPetService.findById(fosterPetId);
        FosterPetDTO fosterPetDTO = new FosterPetDTO();
        fosterPetDTO.setFosterPetId(fosterPetId);
        petDTO.setFosterPetDTO(fosterPetDTO);
        fosterPetService.addPet(petDTO);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "admin/fosterpet/pet/{petId}", method = RequestMethod.GET)
    public Map<String,Object> checkPetIsFosterPet( @PathVariable(name = "petId") Integer petId){

       Boolean isFoster =  fosterPetService.checkPetIsFoster(petId);
       Map<String,Object> map = new HashMap<>();
       map.put("isFoster",isFoster);
       return map;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "admin/fosterpet/pet", method = RequestMethod.GET)
    public Map<String,Object> findPetDontOrder(@ModelAttribute PetCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);

        PetDTO petDTO = cmd.getPojo();

        Object[] objects =  fosterPetService.findPetDontFoster(petDTO,cmd.getMaxItem(),cmd.getFirtItem());

        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<PetDTO> petDTOS = (List<PetDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();

        map.put("objects",petDTOS);
        map.put("totalPage",cmd.getTotalPage());
        return map;
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "admin/fosterpet/{id}/pet", method = RequestMethod.DELETE)
    public void removePetofRescueOrder(@RequestBody PetDTO petDTO,@PathVariable(name = "id") Integer fosterPetId){
        fosterPetService.findById(fosterPetId);
        FosterPetDTO fosterPetDTO = new FosterPetDTO();
        fosterPetDTO.setFosterPetId(fosterPetId);
        petDTO.setFosterPetDTO(fosterPetDTO);
        fosterPetService.removePet(petDTO);
    }
}
