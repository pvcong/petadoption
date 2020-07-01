package com.ck.controller;

import com.ck.controller.commander.NewsCommand;
import com.ck.controller.commander.UserCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.controller.utils.UploadUtils;
import com.ck.data.NewsEntity;
import com.ck.dto.NewsCategoryDTO;
import com.ck.dto.NewsDTO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.exceptionhandler.UploadFileException;
import com.ck.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class NewsController {
    @Autowired
    NewsService newsService;

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/news", method = RequestMethod.POST)
    public void saveNews(@RequestBody NewsDTO newsDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        newsDTO.setCreatedDate(createdDate);
        newsDTO.setModifiedDate(modifiedDate);
        newsService.save(newsDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/news", method = RequestMethod.PUT)
    public void updateNews(@RequestBody NewsDTO newsDTO){

        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        newsDTO.setModifiedDate(modifiedDate);
        newsService.update(newsDTO);
    }

    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/news", method = RequestMethod.DELETE)
    public void deleteNews(@RequestBody List<NewsDTO> newsDTOS){
        newsService.delete(newsDTOS);
    }
    @RequestMapping( value = "news/{id}")
    @ResponseStatus( code = HttpStatus.OK)
    public NewsDTO findNewsById(@PathVariable Integer id){
        NewsDTO newsDTO = newsService.findById(id);
        if( newsDTO.getNewsId() != null){
             return newsDTO;
        }
        throw new NotFoundObjectException();
    }
    @RequestMapping( value = "admin/news/{id}")
    @ResponseStatus( code = HttpStatus.OK)
    public NewsDTO findNewsByIdAdmin(@PathVariable Integer id){
        NewsDTO newsDTO = newsService.findById(id);
        if( newsDTO.getNewsId() != null){
            return newsDTO;
        }
        throw new NotFoundObjectException();
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/news", method = RequestMethod.GET)
    public Map<String,Object> getNewsHome(@ModelAttribute NewsCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = newsService.findByHome(cmd.getMaxItem(),cmd.getFirtItem());
        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);

        Map<String,Object> mapJson = new HashMap<>();
        mapJson.put("totalPage",cmd.getTotalPage());
        mapJson.put("objects",objects[1]);
        return mapJson;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/news", method = RequestMethod.GET)
    public Map<String,Object> getNewsAdmin(@ModelAttribute NewsCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        NewsDTO newsDTO = cmd.getPojo();
        NewsCategoryDTO newsCategoryDTO = null;
        if(newsDTO != null){
            newsCategoryDTO = newsDTO.getNewsCategoryDTO();
        }
        Object[] objects = newsService.findByAdmin(newsDTO,newsCategoryDTO,cmd.getSortProperty(),cmd.getSortValue(),cmd.getMaxItem(),cmd.getFirtItem());
        Integer totalItem = (Integer) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);

        Map<String,Object> mapJson = new HashMap<>();
        mapJson.put("totalPage",cmd.getTotalPage());
        mapJson.put("objects",objects[1]);
        return mapJson;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/news/avatar", method = RequestMethod.POST)
    public Map<String,String> uploadAvatar(@RequestParam() MultipartFile file){
        try {
            Object[] objects = UploadUtils.uploadFile(file,"news" + File.separator + "avatar");
            Map<String,String> responseMap = new HashMap<>();
            if((Boolean)objects[0] != true){
                responseMap.put("file",objects[2].toString());
                responseMap.put("size",objects[1].toString());
                responseMap.put("urlFile", ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("news/").path("avatar/").path(objects[2].toString()).toUriString());
                return responseMap;
            }
            else{
                throw new UploadFileException("");
            }
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "news/avatar/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImagePet(@PathVariable String fileName){
        File file = UploadUtils.findFile(fileName,"news/avatar");
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
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/news/image", method = RequestMethod.POST)
    public Map<String,String> uploadImage(@RequestParam() MultipartFile file){
        try {
            Object[] objects = UploadUtils.uploadFile(file,"news" + File.separator + "image");
            Map<String,String> responseMap = new HashMap<>();
            if((Boolean)objects[0] != true){
                responseMap.put("file",objects[2].toString());
                responseMap.put("size",objects[1].toString());
                responseMap.put("urlFile", ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("news/").path("image/").path(objects[2].toString()).toUriString());
                return responseMap;
            }
            else{
                throw new UploadFileException("");
            }
        } catch (IOException e) {
            throw new UploadFileException(e.getMessage());
        }
    }

    @ResponseStatus( value = HttpStatus.OK)
    @RequestMapping( value = "news/image/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImage(@PathVariable String fileName){
        File file = UploadUtils.findFile(fileName,"news/image");
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