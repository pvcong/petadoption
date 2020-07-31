package com.ck.controller;

import com.ck.controller.commander.UserCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.controller.utils.UploadUtils;
import com.ck.dto.CustomUserDetails;
import com.ck.dto.RoleDTO;
import com.ck.dto.UserDTO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.exceptionhandler.UploadFileException;
import com.ck.sercurity.JwtTokenProvider;
import com.ck.services.UserService;

import com.ck.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class UserController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/user", method = RequestMethod.GET)
    public UserDTO getUserCurent(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDetails.getUser().setPassword(null);
        return userDetails.getUser();
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserDTO userDTO){
        UserDTO userDTO1 = userService.checkLogin(userDTO.getUserName(),userDTO.getPassword());


//                    Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        userDTO.getUserName(),
//                        userDTO.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(userDTO1 != null){
                String jwt = tokenProvider.generateToken(new CustomUserDetails(userDTO1));
                return jwt;

    }
    else{
            throw new NotFoundObjectException();
    }
        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context

        // Trả về jwt cho người dùng.
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody UserDTO userDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        userDTO.setCreatedDate(createdDate);
        userDTO.setModifiedDate(modifiedDate);
        userService.save(userDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/user", method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserDTO userDTO){

        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        userDTO.setModifiedDate(modifiedDate);
        userService.update(userDTO);
    }
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "admin/user/role", method = RequestMethod.PUT)
    public void updateRoleUser(@RequestBody UserDTO userDTO){
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        userDTO.setModifiedDate(modifiedDate);
        userService.updateRoleUser(userDTO);
    }

    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "admin/user", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody List<UserDTO> userDTOs){
        userService.delete(userDTOs);
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/user", method = RequestMethod.GET )
    public  Map<String,Object> findUserAdmin(@ModelAttribute UserCommand cmd){
        if(cmd == null){
            cmd = new UserCommand();
        }
        ParaginationUtils.caculationFirstItem(cmd);

        UserDTO userDTO = cmd.getPojo();
        RoleDTO roleDTO = null;
        if(userDTO != null){
            roleDTO = userDTO.getRoleDTO();
        }

        Object[] objects = userService.findUserAdmin(userDTO,roleDTO,cmd.getSortProperty(),cmd.getSortValue(),cmd.getMaxItem(),cmd.getFirtItem());

        List<UserDTO> userDTOS = (List<UserDTO>) objects[1];
        cmd.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(cmd);
        Integer totalPage = cmd.getTotalPage();

        Map<String,Object> map = new HashMap<>();
        map.put("objects",userDTOS);
        map.put("totalPage",totalPage);
        return map;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/user/{id}", method = RequestMethod.GET )
    public UserDTO findUser(@PathVariable Integer id){
        UserDTO userDTO = userService.findById(id);
        if(userDTO.getUserId() != null){
            return userDTO;
        }
        else{
            throw new NotFoundObjectException();
        }

    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/user/avatar", method = RequestMethod.POST)
    public Map<String,String> uploadAvatar(@RequestParam() MultipartFile file){
        try {
            Object[] objects = UploadUtils.uploadFile(file,"user" + File.separator + "avatar");
            Map<String,String> responseMap = new HashMap<>();
            if((Boolean)objects[0] != true){
                responseMap.put("file",objects[2].toString());
                responseMap.put("size",objects[1].toString());
                responseMap.put("urlFile", ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("user/").path("avatar/").path(objects[2].toString()).toUriString());
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
    @RequestMapping( value = "user/avatar/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImagePet(@PathVariable String fileName){
        File file = UploadUtils.findFile(fileName,"user/avatar");
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
