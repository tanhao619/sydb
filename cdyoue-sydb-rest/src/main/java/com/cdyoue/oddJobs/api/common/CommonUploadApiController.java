package com.cdyoue.oddJobs.api.common;

import com.cdyoue.oddJobs.annotion.authentication.Role;
import com.cdyoue.oddJobs.config.*;
import com.cdyoue.oddJobs.constant.UEConfig;
import com.cdyoue.oddJobs.dto.AreaDTO;
import com.cdyoue.oddJobs.dto.RequestMessageInfo;
import com.cdyoue.oddJobs.dto.common.AttachmentInfoSumary;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.CommonMessage;
import com.cdyoue.oddJobs.dto.lgfc.OddJobNews;
import com.cdyoue.oddJobs.dto.message.PersonalCenterMessageDto;
import com.cdyoue.oddJobs.dto.message.RespseMessageOffDto;
import com.cdyoue.oddJobs.dto.scfw.InvestAndFinNews;
import com.cdyoue.oddJobs.dto.xqdt.ProjectNews;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalAttachmentEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.service.*;
import com.cdyoue.oddJobs.utils.ImgCompressUtils;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Controller
public class CommonUploadApiController implements CommonsApi {
    private Logger logger = LoggerFactory.getLogger(CommonUploadApiController.class);
    @Autowired
    private DynamicSetting setting;
    @Autowired
    private PortalAttachmentService portalAttachmentService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private LgPortalRequirementService lgPortalRequirementService;
    @Autowired
    private ImgCompressUtils imgCompressUtils;
    @Autowired
    private RollingNewsService rollingNewsService;

    @Autowired
    private MessageService messageService;

    @Value("${default.commonUpload.driver}")
     String driver;
    @Value("${default.commonUpload.server}")
     String server;
    @Value("${default.commonUpload.fileAddress}")
     String fileAddress;
    @Value("${ueditor.config}")
     String config;

    @Value("${default.customerService.QQ}")
    String qq;
    @Value("${default.customerService.QQ_WEB}")
    Boolean qq_web;

    @Value("${default.commonUpload.server}")
    String fileBasePath;

    @Override
    public ResponseEntity<AttachmentInfoSumary> commonUpload(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            logger.error("文件为空");
            return new ResponseEntity(CommonMessage.FILEISNOTNULL,HttpStatus.BAD_REQUEST);
        }

        String originalFilename = file.getOriginalFilename();
//        String folder = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
//        String folder = String.valueOf(new Date());
        Date date = new Date();
        String folder = (date.getMonth() + 1) + "-" + date.getDate();
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        StringBuffer uploadPath = setting.getUploadPath();
        StringBuffer fullPath = uploadPath.append("/").append(folder).append("/");
        String remotePath = "sy/"+folder+"/";

        String destFileName = new StringBuffer(UUID.randomUUID().toString())
                .append(fileType)
                .toString();


        File dest = new File(fullPath + destFileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            file.transferTo(dest);
            AttachmentInfoSumary sumary = new AttachmentInfoSumary();
            sumary.setName(originalFilename);
            sumary.setUrl(remotePath + destFileName);
            return new ResponseEntity<AttachmentInfoSumary>(sumary, HttpStatus.OK);
        } catch (IOException e) {
            dest.delete();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<AttachmentInfoSumary>(HttpStatus.BAD_REQUEST);
    }

    @Override//获取所有地区列表
    public ResponseEntity<List<AreaDTO>> getArea() {
        List<AreaDTO> areaDTOs =  areaService.getArea();
        return new ResponseEntity<List<AreaDTO>>(areaDTOs,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProjectNews>> getProjectNews() {
        List<ProjectNews> projectNewses = lgPortalRequirementService.getProjectNews();
        if (projectNewses == null || projectNewses.size() < 1) throw new NotFoundEntityException("数据没找到");
        return new ResponseEntity<>(projectNewses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OddJobNews>> getOddJobNews() {
        List<OddJobNews> oddJobNewses = rollingNewsService.getOddJobNews();
        if (oddJobNewses == null || oddJobNewses.size() < 1) throw new NotFoundEntityException("数据没找到");
        return new ResponseEntity<>(oddJobNewses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<InvestAndFinNews>> getInvestAndFinNews() {
        List<InvestAndFinNews> investAndFinNewses = rollingNewsService.getInvestAndFinNews();
        if (investAndFinNewses == null || investAndFinNewses.size() < 1) throw new NotFoundEntityException("数据没找到");
        return new ResponseEntity<>(investAndFinNewses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo> getSysMessages(@RequestParam Integer pageNum,@RequestParam Integer pageSize) {
        Sort sort = SortUtils.assembleSort("-createTime");
        Pageable pageable = new PageRequest(pageNum,pageSize,sort);
        PageInfo<PersonalCenterMessageDto> allPersonalMessageInfo = MessageUtils.getAllPersonalMessageInfo(pageable);
        return new ResponseEntity<PageInfo>(allPersonalMessageInfo,HttpStatus.OK);
//        return null;
    }

    @Override
    public ResponseEntity LookSysMessage(@PathVariable("msgId") Integer msgId,@RequestBody PersonalCenterMessageDto personalCenterMessageDto) {
        if (msgId == null) return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        if(personalCenterMessageDto == null) return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        MessageUtils.changeLookStatus(msgId,personalCenterMessageDto.getEventId(),personalCenterMessageDto.getQuesReplyId(),personalCenterMessageDto.getType());
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<QQInfo> getQQData() {
        QQInfo qqInfo = new QQInfo();
        qqInfo.setQq(qq);
        qqInfo.setQq_web(qq_web);
        return new  ResponseEntity<QQInfo>(qqInfo,HttpStatus.OK);
    }
    @Override
    public Map<String, String> getFileBasePath() {
        Map<String, String> basePath = new HashMap<>();
        basePath.put("basePath",fileBasePath);
        return basePath;
    }

    @Override
    public ResponseEntity<Integer> UnLookSysMessage() {
        Integer integer = MessageUtils.countUnreadMsgNum();
        return new ResponseEntity(integer, HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> putOfficialSysMessage(@RequestBody RequestMessageInfo requestMessageInfo) {
        try {
            MessageUtils.createOfficialMessage(MessageEventTypeEnum.OFFICIAL, MessageMsgTypeEnum.OfficialNotification,requestMessageInfo.getInfo(),requestMessageInfo.getLink());
            return new ResponseEntity<HttpMessage>(CommonMessage.CREATESUCCESS,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<HttpMessage>(CommonMessage.CREATEFAIL,HttpStatus.OK);
        }
    }

    @Override
    @Role(2)
    public ResponseEntity<PageInfo> getOfficials( @RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(value = "q", required = false) String q) {
        if (q == null){
            q = "";
        }
        Sort order = SortUtils.assembleSort("-createTime");
        Pageable pr = new PageRequest(pageNum,pageSize,order);
        PageInfo<RespseMessageOffDto> officials = messageService.getOfficialsMessage(q, pr);


        return  new ResponseEntity<PageInfo>(officials,HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<HttpMessage> delOfficials(@PathVariable(value = "id")Integer id) {
        RespseMessageOffDto messageOffDto= messageService.getMessageById(id);
        if(null == messageOffDto) return new ResponseEntity<HttpMessage>(CommonMessage.BADREQUEST,HttpStatus.OK);
        messageService.deleteOne(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }

    @Override
    @Role(2)
    public ResponseEntity<RespseMessageOffDto> getMessageDetail(@PathVariable(value = "id") Integer id) {
       RespseMessageOffDto messageOffDto= messageService.getMessageById(id);
        return new ResponseEntity<RespseMessageOffDto>(messageOffDto,HttpStatus.OK);
    }


    @RequestMapping(value = "/getUeditorConfig", method = RequestMethod.GET)
    public void getUeditorConfig(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Type", "text/html");
        String roolPath = "";
        String configStr = UEConfig.UECONFIG_C;
        System.out.println(roolPath);
        try {
            response.getWriter().write(configStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public UeditorResult upload(MultipartFile upfile) throws IOException {
        String c="";
        UeditorResult re = new UeditorResult();
        if (upfile != null && upfile.getSize() > 0) {

            String fileName = upfile.getOriginalFilename();
            try {
                fileName = new String(fileName.getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String filedisk = getDatePath();

            int index = fileName.lastIndexOf(".");
            int length = fileName.length();
            String type = fileName.substring(index, length);
            String pathDisk = getTypeDiskPath(type);
            String uuId = ""+ (int) (1 + Math.random() * (9999 - 0 + 1)) + new Date().getTime();
            String filePath = driver+"ueditor/" + pathDisk + "/" + filedisk + "/" + uuId;
//            String newFileName = "ma_" + (int) (1 + Math.random() * (9999 - 0 + 1)) + new Date().getTime() + "" + type;
            String newFileName = fileName;
            File newFilePath = new File(filePath);
            if (!newFilePath.exists()) {
                newFilePath.mkdirs();
            }

            filePath = filePath + "/" + newFileName;
            File newFile = new File(filePath);
            upfile.transferTo(newFile);
            String iPath = server+"ueditor/" + pathDisk +
                    "/" + filedisk + "/" + uuId+"/" + newFileName;;
            c = "{\"original\": \""+ fileName
                    +"\",\"state\": \""+ "SUCCESS" +"\", \"title\": \""
                    + fileName +"\", \"url\": \""+ iPath +"\"}";
//            Rec re = new Rec();
            re.setOriginal(fileName);
            re.setState("SUCCESS");
            re.setTitle(fileName);
            re.setUrl(iPath);

        }

        return re;
    }
    /**
     * 获取时间格式目录
     *
     * @return
     */
    public static String getDatePath() {

        String dick = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            dick = sdf.format(new Date());
        } catch (Exception e) {
            dick = "nomuchDich";
        }
        return dick;
    }

    /**
     * 根据文件类型 生成 目录
     *
     * @param type
     * @return
     */
    public static String getTypeDiskPath(String type) {
        String pathDisk = null;
        if (type.indexOf("png") > 0 || type.indexOf("jpg") > 0 || type.indexOf("BMP") > 0 || type.indexOf("GIF") > 0 || type.indexOf("JPEG") > 0) {
            pathDisk = "image";
        } else if (type.indexOf("pdf") > 0 || type.indexOf("PDF") > 0) {
            pathDisk = "pdf";
        } else if (type.indexOf("zip") > 0 || type.indexOf("ZIP") > 0) {
            pathDisk = "zip";
        } else if (type.indexOf("mp4") > 0 || type.indexOf("rmvb") > 0 || type.indexOf("MPEG4") > 0 || type.indexOf("MP4") > 0) {
            pathDisk = "video";
        } else {
            pathDisk = "file";
        }
        return pathDisk;
}
}
