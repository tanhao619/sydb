package com.cdyoue.oddJobs.api.advertisement;

import com.cdyoue.oddJobs.dto.advertisement.Advertisement;
import com.cdyoue.oddJobs.dto.advertisement.advertisementInfo;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AccountMessage;
import com.cdyoue.oddJobs.dto.common.message.AdvertisementMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.AdvertisementService;
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
 * Created by Administrator on 2017/5/10.
 */

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-07T12:12:19.725Z")
@Controller
public class AdvertisementController implements AdvertisementApi{

    @Autowired
    AdvertisementService advertisementService;

    @Override//获取一个页面所有广告位信息
    public ResponseEntity<List<Advertisement>> getAdvertisements(@ApiParam(value = "页面名称编号，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk", required = true) @PathVariable("view") String view) {
        if (advertisementService.findPageAll(view).size() != 0){
           List<Advertisement> advertisements = advertisementService.findPageAll(view);
           return new ResponseEntity<List<Advertisement>>(advertisements,HttpStatus.OK);
       }else {
           return new ResponseEntity(AdvertisementMessage.NOTFOUND,HttpStatus.NOT_FOUND);
       }

    }


    @Override//编辑一条广告信息
    public ResponseEntity<HttpMessage> updateAdvertisement(@ApiParam(value = "页面名称，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk", required = true) @PathVariable("view") String view,
                                                      @ApiParam(value = "广告排序ID：左中右：1,2,3", required = true) @PathVariable("id") Integer id,
                                                      @ApiParam(value = "广告对象", required = true) @RequestBody advertisementInfo advertisement) {
    	if (SecurityUtils.getCurrentUserLogin() == null) {
			throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
		}
    	int role = SecurityUtils.getCurrentUserLogin().getRole();
        try {
            if (role != 2){
                return new ResponseEntity<HttpMessage>(AdvertisementMessage.UPDATEFORBIDDEN,HttpStatus.FORBIDDEN);
            }else if(advertisementService.getAdvertisement(view,id) == null){
                return new ResponseEntity<HttpMessage>(AdvertisementMessage.NOTFOUND,HttpStatus.NOT_FOUND);
            }else{
                advertisementService.updateAdvertisement(view,id,advertisement);
                return new ResponseEntity<HttpMessage>(AdvertisementMessage.UPDATESUCCESSFUL,HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<HttpMessage>(AdvertisementMessage.UPDATEFAIL,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @Override//查询一个广告信息
    public ResponseEntity<Advertisement> getAdvertisement(@ApiParam(value = "页面名称，如：首页：sy；产业洞察：cydc；数据服务：sjfw；助力创新：zlcx；助力创业：zlcy；助力创新—设备库：zlcxsbk", required = true) @PathVariable("view") String view,
                                                   @ApiParam(value = "广告排序ID：左中右：1,2,3", required = true) @PathVariable("id") Integer id) {
        try {
            Advertisement advertisement =  advertisementService.getAdvertisement(view,id);
            return new ResponseEntity<Advertisement>(advertisement,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(AdvertisementMessage.NOTFOUND,HttpStatus.NOT_FOUND);
        }

    }
}
