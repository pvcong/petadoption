package com.ck.controller;

import com.ck.controller.commander.ProductCategoryCommand;
import com.ck.controller.utils.ParaginationUtils;
import com.ck.dto.ProductCategoryDTO;
import com.ck.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/productcategory", method = RequestMethod.POST)
    private void save(@RequestBody ProductCategoryDTO productCategoryDTO){
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        productCategoryDTO.setModifiedDate(modifiedDate);
        productCategoryDTO.setCreatedDate(createdDate);
        productCategoryService.save(productCategoryDTO);
    }

    @ResponseStatus( code = HttpStatus.CREATED)
    @RequestMapping( value = "/admin/productcategory", method = RequestMethod.PUT)
    private void update(@RequestBody ProductCategoryDTO productCategoryDTO){
        Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
        productCategoryDTO.setModifiedDate(modifiedDate);
        productCategoryService.update(productCategoryDTO);
    }
    @ResponseStatus( code = HttpStatus.NO_CONTENT)
    @RequestMapping( value = "/admin/productcategory", method = RequestMethod.DELETE)
    private void delete(@RequestBody List<ProductCategoryDTO> productCategoryDTO){
        productCategoryService.delete(productCategoryDTO);
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = {"/admin/productcategory/{id}","/productcategory/{id}"}, method = RequestMethod.GET)
    private ProductCategoryDTO find(@PathVariable Integer id){
        return productCategoryService.findById(id);
    }

    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/productcategory", method = RequestMethod.GET)
    private List<ProductCategoryDTO> findAllAdmin(){
        return productCategoryService.findAll();
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/productcategory", method = RequestMethod.GET)
    private List<ProductCategoryDTO> findAllHome(){
        return productCategoryService.findAll();
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/admin/productcategory/{id}/product", method = RequestMethod.GET)
    private Map<String,Object> findProductAmin(@PathVariable Integer id, @ModelAttribute ProductCategoryCommand productCategoryCommand){
        ParaginationUtils.caculationFirstItem(productCategoryCommand);
        Object[] objects = productCategoryService
                .findProductOfProductCategoryById(
                        id,productCategoryCommand.getMaxItem(),productCategoryCommand.getFirtItem());
        int totalItem = (int) objects[0];
        ProductCategoryDTO productCategoryDTO = (ProductCategoryDTO) objects[1];
        productCategoryCommand.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(productCategoryCommand);

        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",productCategoryCommand.getTotalPage());
        map.put("objects",productCategoryDTO);
        return map;
    }
    @ResponseStatus( code = HttpStatus.OK)
    @RequestMapping( value = "/productcategory/{id}/product", method = RequestMethod.GET)
    private Map<String,Object> findProductHome(@PathVariable Integer id, @ModelAttribute ProductCategoryCommand productCategoryCommand){
        ParaginationUtils.caculationFirstItem(productCategoryCommand);
        Object[] objects = productCategoryService
                .findProductOfProductCategoryById(
                        id,productCategoryCommand.getMaxItem(),productCategoryCommand.getFirtItem());
        int totalItem = (int) objects[0];
        ProductCategoryDTO productCategoryDTO = (ProductCategoryDTO) objects[1];
        productCategoryCommand.setTotalItem(totalItem);
        ParaginationUtils.caculationToltalPage(productCategoryCommand);

        Map<String,Object> map = new HashMap<>();
        map.put("totalPage",productCategoryCommand.getTotalPage());
        map.put("objects",productCategoryDTO);
        return map;
    }
}
