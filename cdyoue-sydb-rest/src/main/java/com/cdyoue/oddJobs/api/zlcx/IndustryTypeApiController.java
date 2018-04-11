package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.zlcx.IndustryType;
import com.cdyoue.oddJobs.service.sydb.IndustryTypeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/20.
 */
@Controller
public class IndustryTypeApiController implements IndustryTypeApi {

    @Autowired
    private IndustryTypeService industryTypeService;

    @Override
    public ResponseEntity<List<IndustryType>> getIndustrys(@ApiParam(value = "行业类型所属分类（0：专家分类，1：设备分类。。自行扩展）", required = true) @RequestParam(value = "type", required = true) Integer type) {
        List<IndustryType> list = industryTypeService.findByType(type);
        return new ResponseEntity<List<IndustryType>>(list, HttpStatus.OK);
    }

}
