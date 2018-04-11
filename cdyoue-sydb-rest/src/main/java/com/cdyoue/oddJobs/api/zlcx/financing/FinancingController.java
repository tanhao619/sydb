package com.cdyoue.oddJobs.api.zlcx.financing;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.FinancingDTO.FinancingTop;
import com.cdyoue.oddJobs.entity.syData.FinancingEntity;
import com.cdyoue.oddJobs.service.sydb.FinancingService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
@Controller
public class FinancingController implements FinancingApi{
    @Autowired
    FinancingService financingService;

    /**
     * 获取首页top
     * @return
     */
    @Override
    public ResponseEntity<FinancingTop> getFinancingTop() {
        return new ResponseEntity<FinancingTop>(financingService.getFinancingTop(), HttpStatus.OK);
    }

    /**
     * 获取融资项目分页列表
     * @param pageSize
     * @param pageNumber
     * @param sort
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getFinancingPageList(
            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @ApiParam(value = "排序字段和方式", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort,
            @ApiParam(value = "状态分类", defaultValue = "2") @RequestParam(value = "0",defaultValue = "2", required = false) Integer type,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<FinancingTop> pageInfo = financingService.getFinancingPageList(pr,type,q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    /**
     * 获取后台分页列表
     * @param pageSize
     * @param pageNumber
     * @param sort
     * @param q
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getFinancingBackPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                             @ApiParam(value = "排序字段和方式", defaultValue = "-createTime") @RequestParam(value = "sort", required = false) String sort,
                                                             @ApiParam(value = "审核状态筛选", defaultValue = "") @RequestParam(value = "status", required = false) String status,
                                                             @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<FinancingTop> pageInfo = financingService.getFinancingBackPageList(pr,q,status);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    /**
     * 获取一个融资项目详情
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<FinancingEntity> getFinancingById(@ApiParam(value = "1", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<FinancingEntity>(financingService.getFinancingById(id), HttpStatus.OK);
    }

    /**
     * 审核项目
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> uncollectFinancing(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                          @ApiParam(value = "",required=true ) @PathVariable("status") Integer status) {

        financingService.uncollectFinancing(id,status);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }




    /**
     * 顶置
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> madeTop(@ApiParam(value = "", required = true) @RequestBody FinancingEntity financingEntity) {
        financingService.madeTop(financingEntity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 取消顶置
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> cancelTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        financingService.cancelTop(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 删除融资信息
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> deleteFinancing(@ApiParam(value = "", required = true) @PathVariable("id") List<Integer> id) {
        financingService.deleteFinancing(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 创建新融资需求
     * @param financingEntity
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> createFinancing(@ApiParam(value = "创建的设备实体", required = true) @RequestBody FinancingEntity financingEntity) {
        financingService.createFinancing(financingEntity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取我发布的融资列表
     * @param pageSize
     * @param pageNumber
     * @param sort
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getMyFinancingPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                           @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @ApiParam(value = "排序字段和方式", defaultValue = "createTime") @RequestParam(value = "sort", required = false) String sort){
        Integer id = SecurityUtils.getCurrentUserLogin().getId();
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<FinancingTop> pageInfo = financingService.getMyFinancingPageList(pr,id);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    /**
     * 融资审核
     * @param financingEntity
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> toExamine(@ApiParam(value = "", required = true) @RequestBody FinancingEntity financingEntity) {
        financingService.toExamine(financingEntity);
        return null;
    }
}
