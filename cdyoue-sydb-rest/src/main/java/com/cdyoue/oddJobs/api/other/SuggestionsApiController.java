package com.cdyoue.oddJobs.api.other;

import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.other.SuggestionDetail;
import com.cdyoue.oddJobs.dto.other.SuggestionRequest;
import com.cdyoue.oddJobs.dto.other.SuggestionSummary;
import com.cdyoue.oddJobs.service.SuggestionService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-31T12:57:35.459Z")

@Controller
public class SuggestionsApiController implements SuggestionsApi {

    @Autowired
    private SuggestionService suggestionService;


    public ResponseEntity<HttpMessage> createSuggestion(@ApiParam(value = "意见反馈实体信息" ,required=true ) @RequestBody SuggestionRequest suggestionRequest) {
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Integer id = suggestionService.createSuggestion(userId,suggestionRequest);
        return id == null ? new ResponseEntity<HttpMessage>(HttpStatus.BAD_REQUEST) : new ResponseEntity<HttpMessage>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpMessage> deleteSuggestion(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id) {
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        if (userMine.getRole() != 2) return new ResponseEntity<HttpMessage>(CommonMessage.FORBIDDENEDITORiSNOTADMINORCREATOR, HttpStatus.UNAUTHORIZED);
        suggestionService.deleteSuggestion(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    public ResponseEntity getSuggestionById(@ApiParam(value = "意见反馈id",required=true ) @PathVariable("id") Integer id) {
        SuggestionDetail suggestionDetail = suggestionService.getSuggestionById(id);
        return new ResponseEntity(suggestionDetail, HttpStatus.OK);
    }

    public ResponseEntity getSuggestions( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC, "createTime");
        PageInfo<SuggestionSummary> pageInfo = suggestionService.getSuggestions(q, pageRequest);
        if (pageInfo.getList() == null || pageInfo.getList().size() < 1)
            return new ResponseEntity<HttpMessage>(CommonMessage.GETSUGGESTIONNOTFOUND, HttpStatus.NOT_FOUND);
        return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
    }
}
