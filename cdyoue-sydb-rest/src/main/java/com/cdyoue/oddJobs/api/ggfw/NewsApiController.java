package com.cdyoue.oddJobs.api.ggfw;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.AuthenticationMessage;
import com.cdyoue.oddJobs.dto.common.message.NewsMessage;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.oddJobs.dto.ggfw.NewsDetail;
import com.cdyoue.oddJobs.dto.ggfw.NewsRequest;
import com.cdyoue.oddJobs.dto.ggfw.NewsSummary;
import com.cdyoue.oddJobs.exception.UnAuthenticationMessageException;
import com.cdyoue.oddJobs.service.PortalNewsService;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-09T11:27:32.880Z")

@Controller
public class NewsApiController implements NewsApi {

    @Autowired
    private PortalNewsService  portalNewsService;

    public ResponseEntity<HttpMessage> approveNews(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id,
                                                   @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {
        // do some magic!
        Boolean isExist = portalNewsService.isExistById(id);
        if (!isExist) {
            return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSFAILD404, HttpStatus.NOT_FOUND);
        }
        /*Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditNews);
        if (isReview) {
            return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSFAILD400, HttpStatus.BAD_REQUEST);
        }*/
        // 审核状态：0待审 1审核通过  2审核失败
        portalNewsService.reviewNews(id, 1, reason);
        return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSSUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> createNews(@ApiParam(value = "新闻实体信息" ,required=true ) @RequestBody NewsRequest news) {
        // do some magic!
        Integer usertype = SecurityUtils.getCurrentUserLogin().getUserType();
        // 只有企业才能发布新闻
        if (usertype == 1) {
            portalNewsService.save(news);
            return new ResponseEntity<HttpMessage>(NewsMessage.CREATENEWSSUCCESS, HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpMessage>(NewsMessage.CREATENEWSFAILD403, HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<PageInfo<CommunitySummary>> getNews(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                              @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                              @ApiParam(value = "审核状态：0待审 1审核通过 2审核失败")  @RequestParam(value = "reviewStatus", required = false) Integer reviewStatus) {
        // do some magic!
        Sort order = SortUtils.assembleSort("-publishTime");
        Pageable pageRequest = new PageRequest(pageNumber, pageSize, order);
        PageInfo<CommunitySummary> newsPage = portalNewsService.findNewsPage(q, reviewStatus, pageRequest);
        return new ResponseEntity<PageInfo<CommunitySummary>>(newsPage, HttpStatus.OK);
    }

    public ResponseEntity<NewsDetail> getNewsById(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id) {
        // do some magic!
        NewsDetail newsDetail = portalNewsService.findById(id);
        if (newsDetail == null) {
            return new ResponseEntity(NewsMessage.GETNEWSFAILD404, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<NewsDetail>(newsDetail, HttpStatus.OK);
    }

    public ResponseEntity<HttpMessage> rejectNews(@ApiParam(value = "新闻id",required=true ) @PathVariable("id") Integer id,
                                                  @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reason) {
        // do some magic!
        Boolean isExist = portalNewsService.isExistById(id);
        if (!isExist) {
            return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSFAILD404, HttpStatus.NOT_FOUND);
        }
        /*Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditNews);
        if (isReview) {
            return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSFAILD400, HttpStatus.BAD_REQUEST);
        }*/
        // 审核状态：0待审 1审核通过  2审核失败
        portalNewsService.reviewNews(id, 2, reason);
        return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSSUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> updateNews(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "需求对象" ,required=true ) @RequestBody NewsRequest newsRequest) {
        // do some magic!
        Boolean isExist = portalNewsService.isExistById(id);
        if (!isExist) {
            return new ResponseEntity<HttpMessage>(NewsMessage.UPDNEWSFAILD404, HttpStatus.NOT_FOUND);
        }
        Boolean isUpd = portalNewsService.updateById(id, newsRequest);
        if (!isUpd) {
            return new ResponseEntity<HttpMessage>(NewsMessage.UPDNEWSFAILD403, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<HttpMessage>(NewsMessage.UPDNEWSSUCCESS, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpMessage> deleteNews(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        Boolean isExist = portalNewsService.isExistById(id);
        if (!isExist){
            return new ResponseEntity<HttpMessage>(NewsMessage.DELNEWSFAILD404, HttpStatus.NOT_FOUND);
        }
        Boolean isDel = portalNewsService.deleteById(id);
        if (!isDel) {
            return new ResponseEntity<HttpMessage>(NewsMessage.DELNEWSFAILD403, HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<HttpMessage>(NewsMessage.DELNEWSSUCCESS, HttpStatus.CREATED);
    }

    public ResponseEntity<PageInfo<NewsSummary>> getMyNews(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页（从0开始）", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber) {
        // do some magic!
    	if (SecurityUtils.getCurrentUserLogin() == null) {
			throw new UnAuthenticationMessageException(AuthenticationMessage.USERUNLOGIN);
		}
        Sort order = SortUtils.assembleSort("-publishTime");
        Pageable pageRequest = new PageRequest(pageNumber, pageSize, order);
        Integer userid = SecurityUtils.getCurrentUserLogin().getId();
        PageInfo<NewsSummary> pageInfo = portalNewsService.findByUid(userid, pageRequest);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1) {
            return new ResponseEntity(NewsMessage.GETMYPUBLISHNEWSFAILD404, HttpStatus.OK);
        }
        return new ResponseEntity<PageInfo<NewsSummary>>(pageInfo, HttpStatus.OK);
    }
}
