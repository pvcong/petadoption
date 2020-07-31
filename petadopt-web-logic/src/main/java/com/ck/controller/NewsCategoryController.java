package com.ck.controller;

import com.ck.controller.commander.NewsCategoryCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.dto.NewsCategoryDTO;
import com.ck.exceptionhandler.NotFoundObjectException;
import com.ck.services.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class NewsCategoryController {
    @Autowired
    NewsCategoryService newsCategoryService;
    @RequestMapping( value = "admin/newscategory", method = RequestMethod.POST)
    @ResponseStatus( code = HttpStatus.CREATED)
    public void save(@RequestBody NewsCategoryDTO newsCategoryDTO){
        newsCategoryService.save(newsCategoryDTO);

    }
    @RequestMapping( value = "admin/newscategory", method = RequestMethod.PUT)
    @ResponseStatus( code = HttpStatus.CREATED)
    public void update(@RequestBody NewsCategoryDTO newsCategoryDTO){
        newsCategoryService.update(newsCategoryDTO);

    }
    @RequestMapping( value = "admin/newscategory", method = RequestMethod.DELETE)
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    public void delete(@RequestBody List<NewsCategoryDTO> newsCategoryDTOs){
        newsCategoryService.delete(newsCategoryDTOs);

    }
    @RequestMapping( value = "admin/newscategory/{id}", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public NewsCategoryDTO findNewsCategoryById(@PathVariable Integer id){
        NewsCategoryDTO newsCategoryDTO = newsCategoryService.findById(id);
       return newsCategoryDTO;
    }
    @RequestMapping( value = "/newscategory/{id}", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public NewsCategoryDTO findNewsCategoryByIdHome(@PathVariable Integer id){
        NewsCategoryDTO newsCategoryDTO = newsCategoryService.findById(id);
        return newsCategoryDTO;
    }
    @RequestMapping( value = "admin/newscategory", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public List<NewsCategoryDTO> findAllAdmin(){
        List<NewsCategoryDTO> newsCategoryDTOs = newsCategoryService.findAll();
        return newsCategoryDTOs;
    }
    @RequestMapping( value = "newscategory", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public List<NewsCategoryDTO> findAllHome(){
        List<NewsCategoryDTO> newsCategoryDTOs = newsCategoryService.findAll();
        return newsCategoryDTOs;
    }
    @RequestMapping( value = "newscategory/{id}/news", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public Map<String,Object> findNewsOfNewsCategory(@PathVariable Integer id){
        NewsCategoryCommand newsCategoryCommand = new NewsCategoryCommand();
        ParaginationUtils.caculationFirstItem(newsCategoryCommand);
        Object objects[] = newsCategoryService.
                findNewsOrNewsCategoryById(
                                id,
                                newsCategoryCommand.getMaxItem(),
                                newsCategoryCommand.getFirtItem());
        newsCategoryCommand.setTotalItem((Integer) objects[0]);
        ParaginationUtils.caculationToltalPage(newsCategoryCommand);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalPage",newsCategoryCommand.getTotalPage());
        map.put("objects",objects[1]);
        return map;
    }
    @RequestMapping( value = "admin/newscategory/{id}/news", method = RequestMethod.GET)
    @ResponseStatus( code = HttpStatus.OK)
    public Map<String,Object> findNewsOfNewsCategoryAdmin(@PathVariable Integer id,@RequestBody  NewsCategoryCommand newsCategoryCommand){

        ParaginationUtils.caculationFirstItem(newsCategoryCommand);
        Object objects[] = newsCategoryService.
                findNewsOrNewsCategoryById(
                        id,
                        newsCategoryCommand.getMaxItem(),
                        newsCategoryCommand.getFirtItem());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalPage",objects[0]);
        map.put("objects",objects[1]);
        return map;
    }
}
