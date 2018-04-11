package com.cdyoue.oddJobs.api.zscq;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.zscq.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.service.IndustryReportService;
import com.cdyoue.oddJobs.service.PatentService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-19T00:40:47.264Z")

@Controller
public class PatentsApiController implements PatentsApi {

    @Autowired
    private PatentService patentService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IndustryReportService industryReportService;

    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;

    public ResponseEntity<HttpMessage> createReport(@ApiParam(value = "产业链报告实体信息" ,required=true ) @RequestBody IndustryReportDetail industryReportDetail) {
        Integer id = industryReportService.createReport(industryReportDetail);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> deleteReport(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        industryReportService.deleteReport(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取专利大数据比价信息
     * @param name
     * @return
     */
    public ResponseEntity<List<PatentBigdataPriceComp>> getPatentBdPriceById(@ApiParam(value = "专利大数据名称",required=true ) @PathVariable("name") String name) {
        // do some magic!
        List<PatentBigdataPriceComp> patentBigdataPriceComps = patentService.findBdpriByPatentname(name);
        return new ResponseEntity<List<PatentBigdataPriceComp>>(patentBigdataPriceComps, HttpStatus.OK);
    }

    /**
     * 删除专利大数据比价信息
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> deletePatentBdpById(@ApiParam(value = "专利大数据比价id",required=true ) @PathVariable("id") Integer id) {
        patentService.deletePatentBdpById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取专利大数据列表
     * @param pageSize
     * @param pageNumber
     * @param q
     * @param sort
     * @return
     */
    public ResponseEntity<PageInfo<PatentBigdataSummary>> getPatentBds( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
            @ApiParam(value = "页码。默认第一页（从0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
            @ApiParam(value = "排序字段和方式 例如：/patents/bigdata?sort=index", allowableValues = "industryCode, priorityCount, refTimes, typeCodeCount, patentAge") @RequestParam(value = "sort", required = false) String sort) {
        // do some magic!
        Pageable pageRequest = null;
        if (sort != null && !sort.trim().equals("")) {
            Sort order = SortUtils.assembleSort(sort);
            pageRequest = new PageRequest(pageNumber, pageSize, order);
        } else {
            pageRequest = new PageRequest(pageNumber, pageSize);
        }
        PageInfo<PatentBigdataSummary> patentBigdataSummaryPageInfo = patentService.findBdsByPatentname(q, pageRequest);
        return new ResponseEntity<PageInfo<PatentBigdataSummary>>(patentBigdataSummaryPageInfo, HttpStatus.OK);
    }

    /**
     * 删除专利大数据信息
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> deletePatentBdById(@ApiParam(value = "专利大数据id",required=true ) @PathVariable("id") Integer id) {
        patentService.deletePatentBdById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取国家专利详情
     * @param id
     * @return
     */
    public ResponseEntity<PatentNationDetail> getPatentById(@ApiParam(value = "国家专利id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        PatentNationDetail patentNationDetail = patentService.findById(id);
        return new ResponseEntity<PatentNationDetail>(patentNationDetail, HttpStatus.OK);
    }

    /**
     * 删除国家专利
     * @param id
     * @return
     */
    public ResponseEntity<HttpMessage> deletePatentById(@ApiParam(value = "国家专利id",required=true ) @PathVariable("id") Integer id) {
        patentService.deletePatentById(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    /**
     * 获取国家专利列表
     * @param pageSize
     * @param pageNumber
     * @param q
     * @return
     */
    public ResponseEntity<PageInfo<PatentNationSummary>> getPatents(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                                    @ApiParam(value = "页码。默认第一页（从0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                                    @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                                    @ApiParam(value = "专利类型：1 发明专利，2 实用新型专利，3 外观设计专利") @RequestParam(value = "type", required = false) String type) {
        // do some magic!

        Sort order = SortUtils.assembleSort("-requestTime");
        Pageable pageRequest = new PageRequest(pageNumber, pageSize, order);
        PageInfo<PatentNationSummary> pageInfo = patentService.findByPatentname(q, type, pageRequest);
        /*if (pageInfo.getList() == null || pageInfo.getList().size() < 1) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity<PageInfo<PatentNationSummary>>(pageInfo, HttpStatus.OK);
    }

    /**
     * 获取推荐国家专利列表
     * @return
     */
    public ResponseEntity<List<PatentNationSummary>> getRecommandPatents() {
        // do some magic!
        int userId=SecurityUtils.getCurrentUserLogin() == null ? -1: SecurityUtils.getCurrentUserLogin().getId();
        Map parm = new HashMap();
        parm.put("id", userId);
        RecommendPatent recommendPatent = null;
        try {
            recommendPatent = (RecommendPatent) restTemplate.getForEntity(recommendRemoteUrl.concat("/recommendPatent?id={id}"), RecommendPatent.class, parm).getBody();
        } catch (RestClientException e) {
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
        /*if(recommendPatent.getResult() == -1 || recommendPatent.getData().size()==0){
            return new ResponseEntity(QuestionMessage.RECOMMENDRESPONSENOTFOUND,HttpStatus.NOT_FOUND);
        }*/
        List<Integer> patentids = new ArrayList<Integer>();
        for (RecommendPatentData recommendPatentData : recommendPatent.getData()) {
            patentids.add(recommendPatentData.getPatentid());
        }
        if (patentids.size() == 0) {
            throw new NotFoundEntityException("没有数据");
        }
        List<PatentNationSummary> patentNationSummaries = patentService.findByIds(patentids);
        return new ResponseEntity<List<PatentNationSummary>>(patentNationSummaries, HttpStatus.OK);
    }

    public ResponseEntity<IndustryReportDetail> getReportById(@ApiParam(value = "产业链报告id",required=true ) @PathVariable("id") Integer id) {
        IndustryReportDetail industryReportDetail = industryReportService.getReportById(id);
        return new ResponseEntity<IndustryReportDetail>(industryReportDetail, HttpStatus.OK);
    }

    public ResponseEntity<PageInfo> getReports( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        // do some magic!规范化
        Pageable requestPage = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"publishTime");
        PageInfo<IndustryReportSummary> pageInfo = industryReportService.findByKeyWord(requestPage, q);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);

    }

    public ResponseEntity<HttpMessage> updateReport(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "产业链报告对象" ,required=true ) @RequestBody IndustryReportDetail industryReportDetail) {
        industryReportService.updateReport(id, industryReportDetail);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

	// 收藏专利
	public ResponseEntity<HttpMessage> collectPatent(@ApiParam(value = "国家专利id", required = true) @PathVariable("id") Integer id) {
		// TODO Auto-generated method stub
        // 判断用户是否收藏（400）
        Boolean isCollect = MessageUtils.isMessageExist(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionPatent);
        if (isCollect) {
            return new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST);
        }
        // 收藏后关联到用户，防止重复收藏
        MessageUtils.createMessage(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionPatent);
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
	}
}
