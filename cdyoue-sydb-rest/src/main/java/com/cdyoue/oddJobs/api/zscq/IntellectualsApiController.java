package com.cdyoue.oddJobs.api.zscq;


import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerDTO;
import com.cdyoue.oddJobs.dto.SyCooperativePartnerMiniDTO;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalIntellectualBuyEntity;
import com.cdyoue.oddJobs.service.*;
import com.cdyoue.oddJobs.service.sydb.CooperativePartnerService;
import com.cdyoue.oddJobs.service.sydb.SyAssessService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
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

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-11T00:49:03.408Z")

@Controller
public class IntellectualsApiController implements IntellectualsApi {

    @Autowired
    private IntellectualsApiService intellectualsApiServiceImpl ;
    @Autowired
    private PortalIntellectualSaleBrandService brandService;
    @Autowired
    private PortalIntellectualSalePatentService patentService;
    @Autowired
    private PortalIntellectualSaleWorkService workService;
    @Autowired
    private PortalIntellectualSaleSummerService summerService;
    @Autowired
    private PortalIntellectualSaleMineService mineService;
    @Autowired
    private PortalIntellectualSaleService portalIntellectualSaleService;
    @Autowired
    private SyAssessService assessService;
    @Autowired
    private CooperativePartnerService partnerService;

    @Override
    public ResponseEntity<HttpMessage> buyTop(@ApiParam(value = "", required = true) @RequestBody IntellectualTop top) {
        portalIntellectualSaleService.buyTop(top.getId(),top.getTopImg());
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> saleTop(@ApiParam(value = "", required = true) @RequestBody IntellectualTop top) {
        Integer type = top.getType();
        Integer id = top.getId();
        String topImg = top.getTopImg();
        //1商标，2专利，3著作权
        if(type != null && type == 1){
            brandService.topSaleBrand(id,topImg);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type != null && type == 2){
            patentService.topSalePatent(id,topImg);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type != null && type == 3) {
            workService.topSaleWork(id,topImg);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> removeBuyTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        portalIntellectualSaleService.removeBuyTop(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> removeSaleTop(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "", required = true) @PathVariable("type") Integer type) {
        //1商标，2专利，3著作权
        if(type != null && type == 1){
            brandService.removeSaleBrand(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type != null && type == 2){
            patentService.removeSalePatent(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type != null && type == 3) {
            workService.removeSaleWork(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<PageInfo> getAssessList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                  @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                  @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新", defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort) {
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<AssessDetailDTO> pageInfo = assessService.getAssessList(q,requestPage);
        return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> insertAssess(@ApiParam(value = "", required = true) @RequestBody AssessDetailSummer detailSummer) {
        assessService.insertAssess(detailSummer);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AssessDetailDTO> getAssessById(@ApiParam(value = "知产评估id", required = true) @PathVariable("id") Integer id) {
        if(id!=null){
            AssessDetailDTO assessDetail = assessService.getAssessById(id);
            if (assessDetail!=null){
                return new ResponseEntity<AssessDetailDTO>(assessDetail, HttpStatus.OK);
            }
        }
        return new ResponseEntity<AssessDetailDTO>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteAssess(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        assessService.deleteAssess(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteAllAssess(@ApiParam(value = "", required = true) @RequestParam("ids") Integer[] ids) {
        assessService.deleteAllAssess(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SyCooperativePartnerDTO>> getPartnerList() {
        List<SyCooperativePartnerDTO> par = partnerService.getPartner();
        return new ResponseEntity<List<SyCooperativePartnerDTO>>(par,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo> getPartnerPageList(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q, @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，" +
                                                       "默认按照发布时间排序。publishTime = 发布时间最新", defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort) {
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<SyCooperativePartnerDTO> pageInfo = partnerService.getPagePartners(q,requestPage);
        return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SyCooperativePartnerDTO> getPartnerDetail(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        SyCooperativePartnerDTO detail = partnerService.getPartnerDetail(id);
        return new ResponseEntity<SyCooperativePartnerDTO>(detail,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> insertPartner(@ApiParam(value = "合作伙伴实体信息", required = true) @RequestBody SyCooperativePartnerMiniDTO miniDTO) {
        partnerService.insertPartner(miniDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deletePartner(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        partnerService.deletePartner(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteAllPartner(@ApiParam(value = "", required = true) @RequestParam("ids") Integer[] ids) {
        partnerService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> updatePartner(@ApiParam(value = "", required = true) @PathVariable("id") Integer id, @ApiParam(value = "合作伙伴信息对象", required = true) @RequestBody SyCooperativePartnerMiniDTO miniDTO) {
        partnerService.updatePartner(id,miniDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<IntellectualSaleBanner>> getIntellectualSalesBanner() {
        List<IntellectualSaleBanner> banners = portalIntellectualSaleService.getSaleBanners();
        return new ResponseEntity<List<IntellectualSaleBanner>>(banners,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<IntellectualBuyBanner>> getIntellectualBuysBanner() {
        List<IntellectualBuyBanner> banners = portalIntellectualSaleService.getBuyBanners();
        return new ResponseEntity<List<IntellectualBuyBanner>>(banners,HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> approveIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                           @ApiParam(value = "理由", required = true) @RequestBody Reason reason) {
        // do some magic!
        PortalIntellectualBuyEntity pib = intellectualsApiServiceImpl.findIntellectualBuy(id);
        if (pib == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        intellectualsApiServiceImpl.approveIntellectualBuy(pib,reason.getReason());
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> approveIntellectualSale(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                               @ApiParam(value = "类型：1商标，2专利，3著作权",required=true ) @PathVariable("type") Integer type,
                                                                 @ApiParam(value = "审核原因" ,required=true ) @RequestBody IntellectualMIni reviewReason) {
        // do some magic!
        //1商标，2专利，3著作权
        if(type!=null&&type==1){
            brandService.approveIntellectualSaleBrand(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type!=null&&type==2){
             patentService.approveIntellectualSalePatent(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type!=null&&type==3) {
            workService.approveIntellectualSaleWork(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> createIntellectual(@ApiParam(value = "知产求购实体信息" ,required=true ) @RequestBody Intellectual intellectual) {
        Integer id = intellectualsApiServiceImpl.createIntellectualBuy(intellectual);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createIntellectualSaleBrand(@ApiParam(value = "知产出售实体信息", required = true) @RequestBody Trademark trademark) {
        Integer id = brandService.createBrand(trademark);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createIntellectualSalePatent(@ApiParam(value = "知产出售实体信息", required = true) @RequestBody Patent patent) {
        Integer  id  = patentService.createPatent(patent);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpMessage> createIntellectualSaleWork(@ApiParam(value = "知产出售实体信息", required = true) @RequestBody Copyright copyright) {
        Integer id = workService.createWork(copyright);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteIntelCopyright(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        workService.deleteIntelCopyright(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteIntelPatent(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        patentService.deleteIntelPatentById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteIntelTrademark(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        brandService.deleteIntelTrademarkById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        intellectualsApiServiceImpl.deleteByPrimary(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<CopyrightDetail> getIntelCopyrightById(@ApiParam(value = "著作权id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        CopyrightDetail detail = workService.getWorkById(id);
        return new ResponseEntity<CopyrightDetail>(detail,HttpStatus.OK);
    }

    public ResponseEntity<PatentDetail> getIntelPatentById(@ApiParam(value = "知产专利id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        PatentDetail detail = patentService.getIntelPatentById(id);
        return new ResponseEntity<PatentDetail>(detail,HttpStatus.OK);
    }

    public ResponseEntity<TrademarkDetail> getIntelTrademarkById(@ApiParam(value = "商标id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        TrademarkDetail detail = brandService.getIntelTrademarkById(id);
        return new ResponseEntity<TrademarkDetail>(detail,HttpStatus.OK);
    }

    public ResponseEntity<IntellectualDetail> getIntellectualById(@ApiParam(value = "知产求购id",required=true ) @PathVariable("id") Integer id) {
        IntellectualDetail intellectualDetail = intellectualsApiServiceImpl.getIntellectualBuyById(id);
        return new ResponseEntity<IntellectualDetail>(intellectualDetail, HttpStatus.OK);
    }

    //获取知产出售列表
    public ResponseEntity<PageInfo<IntellectualSaleSummary>> getIntellectualSales( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
          @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort,
         @ApiParam(value = "知识产权类别：1商标，2专利，3著作权") @RequestParam(value = "type", required = false) Integer type,
         @ApiParam(value = "交易类型：1:转让，2:许可") @RequestParam(value = "transactionType", required = false) Integer transactionType) {
        // do some magic!
        Sort orders = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber,pageSize,orders);
        //1商标，2专利，3著作权
        PageInfo<IntellectualSaleSummary> pageInfo = null;
        if(type!=null&&type==1){
             pageInfo =  brandService.getIntellectualSalesBrand(q,transactionType,pr);
        }else if (type!=null&&type==2){
             pageInfo =  patentService.getIntellectualSalesPatent(q,transactionType,pr);
        }else if (type!=null&&type==3) {
            pageInfo = workService.getIntellectualSalesWork(q, transactionType, pr);
        } else {
            pageInfo = portalIntellectualSaleService.getIntellectualSales(q, transactionType, pr);
        }
        return new ResponseEntity<PageInfo<IntellectualSaleSummary>>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getIntellectuals(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                     @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                     @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                     @ApiParam(value = "排序字段和方式 例如：/sales?sort=publishTime，默认按照发布时间排序。publishTime = 发布时间最新",defaultValue = "-publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                     @ApiParam(value = "知识产权类别：1商标，2专利，3著作权") @RequestParam(value = "type", required = false) Integer type,
                                                     @ApiParam(value = "交易类型：1:转让，2:许可") @RequestParam(value = "transactionType", required = false) Integer transactionType) {
        // do some magic!
        Sort orders = SortUtils.assembleSort(sort);
        Pageable requestPage = new PageRequest(pageNumber, pageSize, orders);
        PageInfo<IntellectualSummary> pageInfo = intellectualsApiServiceImpl.getIntellectuals(q, type,transactionType, requestPage);
        return new ResponseEntity<PageInfo>(pageInfo,HttpStatus.OK);
    }

    public ResponseEntity getMyIntellectualSales( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
        if (SecurityUtils.getCurrentUserLogin() == null) return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        Pageable requestPage = new PageRequest(pageNumber, pageSize);
        PageInfo<IntellectualSaleMine> pageInfo = mineService.getMyIntellectualSales(requestPage);
        if (pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(CommonMessage.GETMYINTELLSALENOTFOUND, HttpStatus.NOT_FOUND);
            return new ResponseEntity<PageInfo<IntellectualSaleMine>>(pageInfo, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<IntellectualSaleSummary>> getIntellectualSalesHots() {
        List<IntellectualSaleSummary> saleSummaries =  summerService.getIntellectualSalesSummer();
        return new ResponseEntity<List<IntellectualSaleSummary>>(saleSummaries,HttpStatus.OK);
    }

    public ResponseEntity getNyIntellectuals( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                        @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
        @ApiParam(value = "知识产权类别：1商标，2专利，3著作权", allowableValues = "1,2,3") @RequestParam(value = "type", required = false) Integer type,
        @ApiParam(value = "交易类型：1:转让，2:许可",allowableValues = "1,2") @RequestParam(value = "transactionType", required = false) Integer transactionType) {
        if (SecurityUtils.getCurrentUserLogin() == null) return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        Pageable requestPage = new PageRequest(pageNumber, pageSize,Sort.Direction.DESC,"publishTime");
        PageInfo<IntellectualMine> pageInfo = intellectualsApiServiceImpl.findMyIntellectualBuy(requestPage, type, transactionType);
        if (pageInfo==null||pageInfo.getList()==null||pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(CommonMessage.GETMYINTELLBUYNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> rejectIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                          @ApiParam(value = "理由", required = true) @RequestBody Reason reason) {
        PortalIntellectualBuyEntity pib = intellectualsApiServiceImpl.findIntellectualBuy(id);
        if (pib == null) return new ResponseEntity<HttpMessage>(HttpStatus.NOT_FOUND);
        intellectualsApiServiceImpl.rejectIntellectualBuy(pib,reason.getReason());
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> rejectIntellectualSale(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                                              @ApiParam(value = "类型：1商标，2专利，3著作权",required=true ) @PathVariable("type") Integer type,
                                                              @ApiParam(value = "审核原因" ,required=true ) @RequestBody IntellectualMIni reviewReason) {
        // do some magic!
        //1商标，2专利，3著作权
        if(type!=null&&type==1){
            brandService.rejectIntellectualSaleBrand(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type!=null&&type==2){
            patentService.rejectIntellectualSalePatent(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else if (type!=null&&type==3) {
            workService.rejectIntellectualSaleWork(id,reviewReason);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }else {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<HttpMessage> updateIntelCopyright(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "著作权对象" ,required=true ) @RequestBody Copyright intellectual) {
        // do some magic!
        Integer tid = Integer.valueOf(id);
        workService.updateIntelCopyright(tid,intellectual);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateIntelPatent(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "知产专利对象" ,required=true ) @RequestBody Patent intellectual) {
        // do some magic!
        Integer tid = Integer.valueOf(id);
        patentService.updateIntelPatent(tid,intellectual);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateIntelTrademark(@ApiParam(value = "",required=true ) @PathVariable("id") String id,
        @ApiParam(value = "商标对象" ,required=true ) @RequestBody Trademark intellectual) {
        // do some magic!
        Integer tid = Integer.valueOf(id);
        brandService.updateIntelTrademark(tid,intellectual);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> updateIntellectual(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "知产求购对象" ,required=true ) @RequestBody Intellectual intellectual) {
        // do some magic!
        intellectualsApiServiceImpl.updateIntellectual(id,intellectual);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

}
