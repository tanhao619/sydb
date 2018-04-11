package com.cdyoue.oddJobs.api.scfw;

import com.cdyoue.oddJobs.dto.AreaBanner;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.zscq.IntellectualTop;
import com.cdyoue.oddJobs.entity.lgsq.PortalAreaServiceEntity;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.AreaService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.scfw.SpaceDetail;
import com.cdyoue.oddJobs.dto.scfw.SpaceSummary;

import io.swagger.annotations.ApiParam;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-18T06:53:23.085Z")

@Controller
public class SpacesApiController implements SpacesApi {

    @Autowired
    private AreaService areaService;

    @Override
    public ResponseEntity<HttpMessage> siteTop(@ApiParam(value = "", required = true) @RequestBody IntellectualTop top) {
        areaService.siteTop(top);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> removeSiteTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        areaService.removeSiteTop(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AreaBanner>> getAreaBanners() {
        List<AreaBanner> banners = areaService.getAreaBanners();
        return new ResponseEntity<List<AreaBanner>>(banners,HttpStatus.OK);
    }

    /**
     * 发布空间，只有企业才能发布
     * @param space
     * @return
     */
    public ResponseEntity<HttpMessage> createSpace(@ApiParam(value = "空间实体信息" ,required=true ) @RequestBody SpaceDetail space) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
           return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        Integer userType=userMine.getUserType();
        if(userType==1){
          //企业用户
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }

        //发布场地
        areaService.save(space);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 删除空间
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> deleteSpace(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine!=null){
            Integer userId = userMine.getId();
            Integer userType = userMine.getUserType();
            Integer userRole = userMine.getRole();
            if(userRole == 2) {
                //管理员可以删除所有的场地
                areaService.deleteSpace(id);
                return new ResponseEntity<HttpMessage>(HttpStatus.OK);
            }else if (userRole == 1){
                //企业用户只能删除自己发布的
                if (areaService.deleteAuthority(userId, id)){
                    areaService.deleteSpace(id);
                    return new ResponseEntity<HttpMessage>(HttpStatus.OK);
                }else{
                    return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
                }
            }else{
                return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
            }
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteAllSpaces(@ApiParam(value = "", required = true) @RequestParam("ids") Integer[] ids) {
        areaService.deleteAllSpaces(ids);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取空间详情
     * @param id
     * @return
     */
    public ResponseEntity<SpaceDetail> getSpaceById(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        SpaceDetail spaceDetail=areaService.getSpaceDetail(id);
        return new ResponseEntity<SpaceDetail>(spaceDetail,HttpStatus.OK);
    }

    /**
     * 获取空间列表
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo> getSpaces(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                              @ApiParam(value = "审核状态：0待审 1审核通过  2审核失败", allowableValues = "0,1,2") @RequestParam(value = "reviewStatus",required = false) Integer reviewStatus,
                                              @ApiParam(value = "空间类型：1:场地，2:工位", allowableValues = "1,2") @RequestParam(value = "type",required = false) Integer type,
                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                              @ApiParam(value = "一级地理位置【省，传对应位置code值】") @RequestParam(value = "areaIdLvPre", required = false) Integer areaIdLvPre,
                                              @ApiParam(value = "二级地理位置【市，传对应位置code值】") @RequestParam(value = "areaIdLvNext", required = false) Integer areaIdLvNext) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        Sort order = SortUtils.assembleSort("-publishTime");
        Pageable requestPage=new PageRequest(pageNumber,pageSize,order);
        PageInfo<SpaceSummary> pageInfo=null;
        if(userMine!=null&&userMine.getRole()==2){
            //超级管理员可以查看所有
            pageInfo = reviewStatus == null ? areaService.getSpaceSummaryPage(type,q,areaIdLvPre,areaIdLvNext,null,null,requestPage):areaService.getSpaceSummaryPage(type,q,areaIdLvPre,areaIdLvNext,reviewStatus,null,requestPage);
            }else {
                pageInfo= areaService.getSpaceSummaryPage(type,q,areaIdLvPre,areaIdLvNext,1,null,requestPage);
            }
          return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }

    /**
     * 获取我发布的空间列表
     * @param pageSize
     * @param pageNumber
     * @param type
     * @param q
     * @param areaIdLvPre
     * @param areaIdLvNext
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getMySpaces(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                @ApiParam(value = "空间类型：1:场地，2:工位", allowableValues = "1,2") @RequestParam(value = "type",required = false) Integer type,
                                                @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                @ApiParam(value = "一级地理位置【省，传对应位置code值】") @RequestParam(value = "areaIdLvPre", required = false) Integer areaIdLvPre,
                                                @ApiParam(value = "二级地理位置【市，传对应位置code值】") @RequestParam(value = "areaIdLvNext", required = false) Integer areaIdLvNext) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);//用户没登录
        }else {
            Integer userType=userMine.getUserType();
            if(userType==0){
                return new ResponseEntity<PageInfo>(HttpStatus.FORBIDDEN);//用户没权限，普通用户没有发布的空间
            }
            Integer userId=userMine.getId();
            Sort order = SortUtils.assembleSort("-createTime");
            Pageable requestPage=new PageRequest(pageNumber,pageSize,order);
            PageInfo<SpaceSummary> pageInfo= areaService.getSpaceSummaryPage(type,q,areaIdLvPre,areaIdLvNext,null,userId,requestPage);
            return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
        }
    }

    /**
     * 编辑空间
     * @param id
     * @param space
     * @return
     */
    public ResponseEntity<HttpMessage> updateSpace(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "空间对象" ,required=true ) @RequestBody SpaceDetail space) {
        // do some magic!
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        Integer userType=userMine.getUserType();
        if(userType==1){
            //企业用户
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        //先查询有无该空间
        PortalAreaServiceEntity portalAreaServiceEntity=areaService.findOne(id);
        if(portalAreaServiceEntity!=null){
            areaService.update(id,space);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
    }

    /**
     * 通过空间发布
     * @param id
     * @param reason
     * @return
     */
    public ResponseEntity<HttpMessage> approveNews(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {
        //审核状态：0未审核，1审核通过，2审核不通过
        areaService.reviewArea(id, 1, reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    /**
     * 拒绝空间发布
     * @param id
     * @param reason
     * @return
     */
    public ResponseEntity<HttpMessage> rejectNews(@ApiParam(value = "空间id",required=true ) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {
        //审核状态：0未审核，1审核通过，2审核不通过
        areaService.reviewArea(id, 2, reason);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

}
