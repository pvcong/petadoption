package com.ck.controller;

import com.ck.controller.commander.PetStatusCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.data.PetStatusEntity;
import com.ck.dto.PetStatusDTO;
import com.ck.services.PetStatusService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PetStatusController {
    @Autowired
    PetStatusService petStatusService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(value = "/admin/petstatus", method = RequestMethod.POST)
    private void save(@RequestBody PetStatusDTO petStatusDTO){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        petStatusDTO.setDate(timestamp);
        petStatusService.save(petStatusDTO);
    }
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/admin/petstatus", method = RequestMethod.GET)
    private  Map<String,Object> findPetStatus(@ModelAttribute PetStatusCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = petStatusService.findPetStatusToStory(cmd.getMaxItem(),cmd.getFirtItem());
        List<PetStatusEntity> petStatusEntities = (List<PetStatusEntity>) objects[1];
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);

        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",objects[0]);
        map.put("objects",petStatusEntities);
        return map;
    }


}
