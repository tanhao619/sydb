package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AdvertisementMessage;
import com.cdyoue.oddJobs.dto.common.message.ArticlesMessage;
import com.cdyoue.oddJobs.dto.common.message.IncubatorMassage;
import com.cdyoue.oddJobs.dto.scfw.Incubator;
import com.cdyoue.oddJobs.dto.scfw.IncubatorInfo;
import com.cdyoue.oddJobs.dto.scfw.InvestorSummary;
import com.cdyoue.oddJobs.service.IncubatorService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T13:17:18.617Z")
@Controller
public class IncubatorController implements IncubatorApi {
   @Autowired
    IncubatorService incubatorService;

    @Override//根据ID查询一个孵化器信息
    public ResponseEntity<IncubatorInfo> getIncubatorById(@ApiParam(value = "孵化器ID", required = true) @PathVariable("id") Integer id) {
        try {
            if (incubatorService.getIncubatorById(id) == null){
                return new ResponseEntity(IncubatorMassage.NOTFOUND, HttpStatus.NOT_FOUND);
            }else {
                IncubatorInfo incubator = incubatorService.getIncubatorById(id);
                return new ResponseEntity<IncubatorInfo>(incubator, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(IncubatorMassage.NOTFOUND, HttpStatus.NOT_FOUND);
        }


    }

    @Override//根据ID编辑一个孵化器信息
    public ResponseEntity<HttpMessage> updateArticle(@ApiParam(value = "孵化器ID", required = true) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "孵化器对象", required = true) @RequestBody Incubator incubator) {
        UserMine role = SecurityUtils.getCurrentUserLogin();
        try {
            if (role.getRole() != 2){
                return new ResponseEntity<HttpMessage>(IncubatorMassage.UPDATEFORBIDDEN,HttpStatus.FORBIDDEN);
            }else if(incubatorService.getIncubatorById(id)==null){
                    return new ResponseEntity<HttpMessage>(IncubatorMassage.UPDATEFAIL, HttpStatus.INTERNAL_SERVER_ERROR);
            }else {
                incubatorService.updateArticle(id,incubator);
                return new ResponseEntity<HttpMessage>(IncubatorMassage.UPDATESUCCESSFUL, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<HttpMessage>(IncubatorMassage.UPDATEFAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
