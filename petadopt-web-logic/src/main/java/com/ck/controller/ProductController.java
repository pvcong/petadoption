package com.ck.controller;

import com.ck.controller.commander.ProductCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.controller.utils.UploadUtils;
import com.ck.data.UserEntity;
import com.ck.dto.CustomUserDetails;
import com.ck.dto.ProductDTO;
import com.ck.dto.UserDTO;
import com.ck.exceptionhandler.UploadFileException;
import com.ck.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    AuthenticationManager authenticationManager;
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/product", method = RequestMethod.POST)
    private void save(@RequestBody ProductDTO productDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        CustomUserDetails customUserDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(customUserDetails.getUser().getUserId());
        productDTO.setUserDTO(userDTO);
        productDTO.setModifiedDate(modifiedDate);
        productDTO.setCreatedDate(createdDate);
        productService.save(productDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/product", method = RequestMethod.PUT)
    private void update(@RequestBody ProductDTO productDTO){
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        productDTO.setModifiedDate(modifiedDate);
        productService.update(productDTO);
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/product", method = RequestMethod.DELETE)
    private void delete(@RequestBody List<ProductDTO> productDTOS){
        productService.delete(productDTOS);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/product/{id}", method = RequestMethod.GET)
    private ProductDTO find(@PathVariable Integer id){
        return productService.findById(id);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/product", method = RequestMethod.GET)
    private Map<String,Object> findHome(@ModelAttribute ProductCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = productService.findProductHome(cmd.getPojo(),cmd.getMaxItem(),cmd.getFirtItem());
        int totalItem = (int) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<ProductDTO> productDTOS = (List<ProductDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",cmd.getTotalPage());
        map.put("objects",productDTOS);
        return map;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/product", method = RequestMethod.GET)
    private Map<String,Object> findAdmin(@ModelAttribute ProductCommand cmd){
        ParaginationUtils.caculationFirstItem(cmd);
        Object[] objects = productService.findProductAdmin(cmd.getPojo(),cmd.getSortProperty(),cmd.getSortValue(),cmd.getMaxItem(),cmd.getFirtItem());
        int totalItem = (int) objects[0];
        cmd.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(cmd);
        List<ProductDTO> productDTOS = (List<ProductDTO>) objects[1];
        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",cmd.getTotalPage());
        map.put("objects",productDTOS);
        return map;
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/product/image", method = RequestMethod.POST)
    public Map<String,String> uploadImage(@RequestParam() MultipartFile file){
        try {
            Object[] objects = UploadUtils.uploadFile(file,"product" + File.separator + "image");
            Map<String,String> responseMap = new HashMap<>();
            if((Boolean)objects[0] != true){
                responseMap.put("file",objects[2].toString());
                responseMap.put("size",objects[1].toString());
                responseMap.put("urlFile", ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("product/").path("image/").path(objects[2].toString()).toUriString());
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
    @RequestMapping( value = "product/image/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImagePet(@PathVariable String fileName){
        File file = UploadUtils.findFile(fileName,"product/image");
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
