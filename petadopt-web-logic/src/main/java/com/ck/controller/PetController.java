package com.ck.controller;

import com.ck.controller.commander.PetCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.controller.utils.UploadUtils;
import com.ck.data.PetEntity;
import com.ck.data.PetTypeEntity;
import com.ck.dto.PetAboutDTO;
import com.ck.dto.PetDTO;
import com.ck.dto.PetTypeDTO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.exceptionhandler.UploadFileException;
import com.ck.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class PetController {
    @Autowired
    PetService petService;
    @ResponseStatus( value = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/pet", method = RequestMethod.POST)
    public void savePet(@RequestBody PetDTO petDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        petDTO.setCreatedDate(createdDate);
        petDTO.setModifiedDate(modifiedDate);
        petService.save(petDTO);
    }
    @ResponseStatus( value = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/pet", method = RequestMethod.PUT)
    public void updatePet(@RequestBody PetDTO petDTO){
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        petDTO.setModifiedDate(modifiedDate);

        petService.update(petDTO);
    }
    @ResponseStatus( value = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/pet", method = RequestMethod.DELETE)
    public void deletePet(@RequestBody List<PetDTO> petDTOs){
        petService.delete(petDTOs);
    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "pet/{id}", method = RequestMethod.GET)
    public PetDTO findSinglePet(@PathVariable Integer id){
       PetDTO petDTO =  petService.findSinglePetHome(id);
       if(petDTO.getPetId() != null)
       return petDTO;
       else {
           throw new NotFoundObjectException();
       }
    }
    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "pet", method = RequestMethod.GET)
    public Map<String,Object> findPetHome(@ModelAttribute() PetCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        PetTypeDTO petTypeDTO = null;
        PetAboutDTO petAboutDTO = null;
        PetDTO petDTO = cmd.getPojo();
        if(petDTO != null){
            petAboutDTO = petDTO.getPetAboutDTO();
            petTypeDTO = petDTO.getPetTypeDTO();
        }
        Object[] objects =  petService.findPetHome(petDTO,petTypeDTO,petAboutDTO,cmd.getMaxItem(),cmd.getFirtItem());

        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);

        List<PetDTO> petDTOS = (List<PetDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();

        map.put("objects",petDTOS);
        map.put("totalPage",cmd.getTotalPage());
        return map;
    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "admin/pet/{id}", method = RequestMethod.GET)
    public PetDTO findSinglePetAdmin(@PathVariable Integer id){
        PetDTO petDTO =  petService.findSinglePetAdmin(id);
        if(petDTO .getPetId()!= null)
            return petDTO;
        else {
            throw new NotFoundObjectException();
        }
    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "admin/pet", method = RequestMethod.GET)
    public Map<String,Object> findPetAdmin(@ModelAttribute PetCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        PetTypeDTO petTypeDTO = null;
        PetDTO petDTO = cmd.getPojo();
        if(petDTO != null){
            petTypeDTO = petDTO.getPetTypeDTO();
        }
        Object[] objects =  petService.findPetAdmin(petDTO,petTypeDTO,cmd.getSortProperty(),cmd.getSortValue(),cmd.getMaxItem(),cmd.getFirtItem());

        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);

        List<PetDTO> petDTOS = (List<PetDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();

        map.put("objects",objects[1]);
        map.put("totalPage",cmd.getTotalPage());
        return map;
    }
    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "admin/pet/image", method = RequestMethod.POST)
    public Map<String,String> uploadImage(@RequestParam MultipartFile file, HttpServletRequest req){
        Map<String,String > pathFileUploadMap = new HashMap<>();
        try {
            req.getContextPath();
            ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            Object[] objects = UploadUtils.uploadFile(file,  "pet" + File.separator + "image");

            if((Boolean)objects[0] != true){

                pathFileUploadMap.put("file", (String) objects[2]);
                pathFileUploadMap.put("size", objects[1].toString());
                pathFileUploadMap.put("urlFile",ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("pet/").path("image/").path(objects[2].toString()).toUriString());
            }else {
                throw new UploadFileException("Lỗi không xác định");
            }

        } catch (IOException e){
            throw new UploadFileException(e.getMessage());
        }
        return pathFileUploadMap;

    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "pet/image/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImagePet(@PathVariable String fileName){
        File file = UploadUtils.findFile(fileName,"pet/image");
        Path path = Paths.get(file.getAbsolutePath());
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(file.length())
                .body(resource);
    }



}
