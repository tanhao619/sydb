package com.cdyoue.oddJobs.api.zlcx.equipment;

import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentApplyDTO;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentDetail;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentInfo;
import com.cdyoue.oddJobs.dto.zlcx.EquipmentDTO.EquipmentTop;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.EquipmentEntity;
import com.cdyoue.oddJobs.entity.syData.SyEquipmentApplyView;
import com.cdyoue.oddJobs.service.sydb.EquipmentService;
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
 * Created by Administrator on 2017/9/18.
 */
@Controller
public class EquipmentController implements  EquipmentApi{
    @Autowired
    EquipmentService equipmentService;


    /**
     * 获取首页设备top8
     * @return
     */
    @Override
    public ResponseEntity<List<EquipmentTop>> getEquipmentTop() {
        List<EquipmentTop> equipmentTops =  equipmentService.findTop();
        return new ResponseEntity<>(equipmentTops, HttpStatus.OK);
    }

    /**
     * 获取分页列表
     * @param pageSize 分页大小
     * @param pageNumber 页码
     * @param sort 排序字段
     * @param type 行业类型
     * @param q 搜过关键字
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getEquipmentPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                         @ApiParam(value = "排序字段和方式", allowableValues = "createTime,viewCount,all") @RequestParam(value = "sort", required = false) String sort,
                                                         @ApiParam(value = "行业分类") @RequestParam(value = "type", required = false) Integer type,
                                                         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<EquipmentTop> pageInfo = equipmentService.getEquipmentPageList(pr, type, q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    /**
     * 获取一个设备详情
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<EquipmentDetail> getEquipmentById(@ApiParam(value = "1", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<EquipmentDetail>(equipmentService.getEquipmentById(id), HttpStatus.OK);
    }


    /**
     * 创建设备
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> createEquipment(@ApiParam(value = "创建的设备实体", required = true) @RequestBody EquipmentEntity equipmentEntity) {
        equipmentService.createEquipment(equipmentEntity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 删除设备
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> deleteEquipment(@ApiParam(value = "", required = true) @PathVariable("id") List<Integer> id) {
        equipmentService.deleteEquipment(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }


    /**
     * 编辑设备信息
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> updateEquipment(@ApiParam(value = "设备对象", required = true) @RequestBody EquipmentInfo equipmentInfo) {
        equipmentService.updateEquipment(equipmentInfo);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 申请设备
     * @param equipmentApplyEntity
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> applyEquipment(@ApiParam(value = "提交申请信息", required = true) @RequestBody EquipmentApplyEntity equipmentApplyEntity) {
            equipmentService.applyEquipment(equipmentApplyEntity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取一个设备申请详情
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<EquipmentApplyDTO> getEquipmentApplyById(@ApiParam(value = "1", required = true) @PathVariable("id") Integer id) {

        return new ResponseEntity<EquipmentApplyDTO>(equipmentService.getEquipmentApplyById(id), HttpStatus.OK);
    }

    /**
     * 获取设备申请分页列表
     * @param pageSize
     * @param pageNumber
     * @param sort
     * @param q
     * @return
     */
    @Override
    public ResponseEntity<PageInfo> getEquipmentApplyPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                              @ApiParam(value = "排序字段和方式", defaultValue = "createTime", allowableValues = "createTime,viewCount") @RequestParam(value = "sort", required = false) String sort,
                                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<SyEquipmentApplyView> pageInfo = equipmentService.getEquipmentApplyPageList(pr, q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    /**
     * 删除申请
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> deleteEquipmentApplyById(@ApiParam(value = "1", required = true) @PathVariable("id") List<Integer> id) {
        equipmentService.deleteEquipmentApplyById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 设备顶置
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> madeTop(@ApiParam(value = "", required = true) @RequestBody EquipmentEntity equipmentEntity) {
        equipmentService.madeTop(equipmentEntity);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 取消顶置
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> cancelTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        equipmentService.cancelTop(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 收藏设备
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> collectionEquipment(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        equipmentService.collectionEquipment(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<HttpMessage> cancelCollectionEquipment(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        equipmentService.cancelCollectionEquipment(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
