package com.cdyoue.oddJobs.api.common;


import com.alibaba.fastjson.JSONObject;
import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.dto.common.message.CategorytypeMessage;
import com.cdyoue.oddJobs.dto.requirement.RecommendeTalents;
import com.cdyoue.oddJobs.service.CategoryService;
import com.cdyoue.oddJobs.service.LgPortalRequirementService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T08:36:42.123Z")

@Controller
public class CategoriesApiController implements CategoriesApi {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LgPortalRequirementService portalRequirementService;
    public ResponseEntity<List<Category>> getCategoriesByType( @RequestParam(value = "type", required = false) Integer type,
                                                              @ApiParam(value = "id") @RequestParam(value = "id", required = false) Integer id) {
        if(type == null){
            return new ResponseEntity(CategorytypeMessage.CATEGORYTYPIRREGULAR,HttpStatus.BAD_REQUEST);
        }
        switch (type){
            case 2:
                List<Category> commonTypes = categoryService.findRequirementType();
                return new ResponseEntity<List<Category>>(commonTypes,HttpStatus.OK);
            case 1:
                List<Category> trades = categoryService.findTrades();
                return new ResponseEntity<List<Category>>(trades,HttpStatus.OK);
            case 3:
                 List<Category> pros = categoryService.findPros(id);
                return new ResponseEntity<List<Category>>(pros,HttpStatus.OK);
            default:
                return new ResponseEntity(CategorytypeMessage.CATEGORYTYPIRREGULAR,HttpStatus.BAD_REQUEST);
        }

    }

}
