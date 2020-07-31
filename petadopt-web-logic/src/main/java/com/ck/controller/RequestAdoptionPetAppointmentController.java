package com.ck.controller;

import com.ck.controller.commander.RequestAdoptionPetAppointmentCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.RequestAdoptionPetAppointmentEntity;
import com.ck.dto.CustomUserDetails;
import com.ck.dto.RequestAdoptionPetAppointmentDTO;
import com.ck.dto.RequestAdoptionPetAppointmentStatusDetailDTO;
import com.ck.dto.UserDTO;
import com.ck.services.RequestAdoptionPetAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RequestAdoptionPetAppointmentController {
    @Autowired
    RequestAdoptionPetAppointmentService requestAdoptionPetAppointmentService;
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/requestpet/appointment", method = RequestMethod.POST)
    private void save(@RequestBody RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetAppointmentDTO.setCreatedDate(timestamp);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = customUserDetails.getUser();
        requestAdoptionPetAppointmentDTO.setUserDTO(userDTO);
        requestAdoptionPetAppointmentDTO.setModifiedDate(timestamp);
        requestAdoptionPetAppointmentService.save(requestAdoptionPetAppointmentDTO);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = "/admin/requestpet/today", method = RequestMethod.GET)
    public Map<String,Integer> totalAppointmentAvailabilityToday(){

        Map<String,Integer> map = new HashMap<>();
        map.put("totalItem",requestAdoptionPetAppointmentService.totalAppointmentAvailabilityToday());
        return  map;

    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/requestpet/appointment/date", method = RequestMethod.PUT)
    private void updateAppointmentDate(@RequestBody RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetAppointmentDTO.setModifiedDate(timestamp);
        requestAdoptionPetAppointmentService.update(requestAdoptionPetAppointmentDTO);
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/requestpet/appointment", method = RequestMethod.PUT)
    private void updateStatus(@RequestBody RequestAdoptionPetAppointmentStatusDetailDTO requestAdoptionPetAppointmentStatusDetailDTO){
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(customUserDetails.getUser().getUserId());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        requestAdoptionPetAppointmentStatusDetailDTO.setCreatedDate(timestamp);
        requestAdoptionPetAppointmentStatusDetailDTO.setUserDTO(userDTO);
        requestAdoptionPetAppointmentService.updateStatus(requestAdoptionPetAppointmentStatusDetailDTO);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = {"/admin/requestpet/appointment","/admin/requestpetstatus/{id}/requestpet/appointment"}, method = RequestMethod.GET)
    public Map<String,Object> findRequestPet(@ModelAttribute RequestAdoptionPetAppointmentCommand cmd, @PathVariable(required = false, name = "id") Integer requestStatusId){
        ParaginationUtils.caculationFirstItem(cmd);
        Object objects[] = requestAdoptionPetAppointmentService.
                findRequestAdoptionPetByStatusId(cmd.getPojo(),
                        requestStatusId,cmd.getMaxItem(),cmd.getFirtItem());
        int totalItem = (int) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<RequestAdoptionPetAppointmentDTO> requestAdoptionPetDTOs = (List<RequestAdoptionPetAppointmentDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",cmd.getTotalPage());
        map.put("objects",requestAdoptionPetDTOs);
        return map;

    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping(value = "/admin/requestpet/appointment/{id}", method = RequestMethod.GET)
    public RequestAdoptionPetAppointmentDTO findSingledRequestPetById(@PathVariable(name = "id") Integer id){
        RequestAdoptionPetAppointmentDTO requestAdoptionPetAppointmentDTO = requestAdoptionPetAppointmentService.findSingleRequestAdoptionPetById(id);

        return requestAdoptionPetAppointmentDTO;

    }
//    @ResponseStatus( code = HttpStatus.OK)
//    @RequestMapping(value = "/admin/requestpet/pendding", method = RequestMethod.GET)
//    public Map<String,Integer> totalRequestAdoptionPedding(){
//
//        Map<String,Integer> map = new HashMap<>();
//        map.put("totalItem",requestAdoptionPetService.totalRequestAdoptionPedding());
//        return  map;
//
//    }
}
