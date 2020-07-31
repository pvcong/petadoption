package com.ck.controller;

import com.ck.controller.commander.RequestAdoptionPetCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.RequestAdoptionPetEntity;
import com.ck.data.RequestAdoptionPetStatusDetailEntity;
import com.ck.dto.CustomUserDetails;
import com.ck.dto.RequestAdoptionPetDTO;
import com.ck.dto.RequestAdoptionPetStatusDetailDTO;
import com.ck.dto.UserDTO;
import com.ck.services.RequestAdoptionPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RequestAdoptionPetController {
    @Autowired
    RequestAdoptionPetService requestAdoptionPetService;
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/requestpet", method = RequestMethod.POST)
    private void save(@RequestBody RequestAdoptionPetDTO requestAdoptionPetDTO){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetDTO.setCreatedDate(timestamp);
        requestAdoptionPetDTO.setModifiedDate(timestamp);
        requestAdoptionPetService.save(requestAdoptionPetDTO);
    }

    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/requestpet", method = RequestMethod.PUT)
    private void updateStatus(@RequestBody RequestAdoptionPetStatusDetailDTO requestAdoptionPetStatusDetailDTO){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(customUserDetails.getUser().getUserId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetStatusDetailDTO.setCreatedDate(timestamp);
        requestAdoptionPetStatusDetailDTO.setUserDTO(userDTO);
        requestAdoptionPetService.updateStatus(requestAdoptionPetStatusDetailDTO);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = {"/admin/requestpet","/admin/requestpetstatus/{id}/requestpet"}, method = RequestMethod.GET)
    public Map<String,Object> findRequestPet(@ModelAttribute RequestAdoptionPetCommand cmd,@PathVariable(required = false, name = "id") Integer requestStatusId){
        ParaginationUtils.caculationFirstItem(cmd);
        Object objects[] = requestAdoptionPetService.
                findRequestAdoptionPetByStatusId(cmd.getPojo(),
                        requestStatusId,cmd.getMaxItem(),cmd.getFirtItem());
        int totalItem = (int) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<RequestAdoptionPetDTO> requestAdoptionPetDTOs = (List<RequestAdoptionPetDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",cmd.getTotalPage());
        map.put("objects",requestAdoptionPetDTOs);
        return map;

    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = "/admin/requestpet/{id}", method = RequestMethod.GET)
    public RequestAdoptionPetDTO findSingledRequestPetById(@PathVariable(name = "id") Integer id){
        RequestAdoptionPetDTO requestAdoptionPetDTO = requestAdoptionPetService.findSingleRequestAdoptionPetById(id);

        return requestAdoptionPetDTO;

    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = "/admin/requestpet/pendding", method = RequestMethod.GET)
    public Map<String,Integer> totalRequestAdoptionPedding(){

        Map<String,Integer> map = new HashMap<>();
        map.put("totalItem",requestAdoptionPetService.totalRequestAdoptionPedding());
        return  map;

    }
}
