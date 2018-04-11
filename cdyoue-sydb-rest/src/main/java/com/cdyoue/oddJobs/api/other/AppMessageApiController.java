package com.cdyoue.oddJobs.api.other;

import com.cdyoue.oddJobs.dto.common.AppMsgResponse;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.MessageBody;
import com.cdyoue.oddJobs.dto.common.ResponseDetail;
import com.cdyoue.oddJobs.dto.common.message.AppReadMessage;
import com.cdyoue.oddJobs.service.MessageService;
import com.cdyoue.oddJobs.utils.MessageUtils;
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
 * Created by tr on 2017/6/8.
 */

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-06-08T12:57:35.459Z")

@Controller
public class AppMessageApiController implements AppMessageApi {

    @Autowired
    private MessageService messageService;

    public ResponseEntity getUnreadAppMessage() {
        if (messageService.getUnreadAppMessage() == null || messageService.getUnreadAppMessage().size() < 1) return new ResponseEntity<HttpMessage>(AppReadMessage.APPUNREADMSGNOTFOUND, HttpStatus.OK);
        List<AppMsgResponse> list =  messageService.getUnreadAppMessage();
        return new ResponseEntity<List<AppMsgResponse>>(list, HttpStatus.OK);
    }


    public ResponseEntity getUnreadMsgSummary(@ApiParam(value = "消息类别：1审核，2收到邀请，3关注通知，4需求通知，5官方通知", required = true) @PathVariable("msgType") Integer msgType,
                                              @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                              @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber, pageSize, Sort.Direction.DESC,"createTime");
        try {
            PageInfo<ResponseDetail> pageInfo = messageService.getUnreadMsgSummary(msgType, pageRequest);
            if (pageInfo.getList() == null || pageInfo.getList().size() < 1) return new ResponseEntity<HttpMessage>(AppReadMessage.APPUNREADMSGNOTFOUND, HttpStatus.OK);
            return new ResponseEntity<PageInfo>(pageInfo, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<HttpMessage>(AppReadMessage.MSGTYPENOTFOUND,HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<HttpMessage> msgStatusRead(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                     @RequestParam(value = "eventId", required = false) Integer eventId,
                                                     @RequestParam(value = "quesReplyId", required = false) Integer quesReplyId,
                                                     @RequestParam(value = "type", required = false) Integer type) {
        try {
            if (id == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            //if (messageBody == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
            //MessageUtils.changeLookStatus(id, messageBody.getEventId(), messageBody.getType());
            MessageUtils.changeLookStatus(id, eventId, quesReplyId,type);
        } catch (NullPointerException npe){
            return new ResponseEntity<HttpMessage>(AppReadMessage.MSGNOTFOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<HttpMessage>(AppReadMessage.SYSTEMERROR, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HttpMessage>(AppReadMessage.MSGREADSUCCESS, HttpStatus.OK);
    }


    public ResponseEntity<HttpMessage> allMsgStatusReadByType(@ApiParam(value = "消息类别：1审核，2收到邀请，3关注通知，4需求通知，5官方通知", required = true) @PathVariable("msgType") Integer msgType) {
        try {
             MessageUtils.changeLookStatusByType(msgType);
        } catch (Exception e){
            return new ResponseEntity<HttpMessage>(AppReadMessage.SYSTEMERROR, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
}
