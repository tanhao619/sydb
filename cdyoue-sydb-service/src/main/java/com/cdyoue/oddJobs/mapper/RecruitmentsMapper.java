package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.account.EnterAccountDetail;
import com.cdyoue.oddJobs.dto.account.EnterAccountForRecruitment;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.*;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.service.AccountsService;
import com.cdyoue.oddJobs.service.UserEnterpriseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class RecruitmentsMapper {

    @Autowired
    private AccountsService accountsService;
    @Autowired
    private UserEnterpriseService userEnterpriseService;

    @Autowired
    private MessageResponsitory messageResponsitory;

    @Autowired
    private FullRecruitmentResponsitory responsitory;

    @Autowired
    private FunctioncategoryResponsitory functioncategoryResponsitory;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;

    @Autowired
    private PortalWord4RecruitmentResponsitory wordResponsitory;


    @Autowired
    private TalentResponsitory talentResponsitory;

    public RecruitmentsDetail enterpriserecruitmentEntityTOrecruitmentsDetail(EnterpriserecruitmentEntity pre) {
        RecruitmentsDetail detail = new RecruitmentsDetail();
        RecruitmentsBase base = new RecruitmentsBase();
        RecruitmentsFullMini fullMini = new RecruitmentsFullMini();
        //根据企业id获取企业详细信息
        Integer uid = responsitory.getUserId(pre.getEntId());
        EnterAccountDetail enterAccountDetail = accountsService.getMyEnterpriseInfo(uid);
        EnterAccountForRecruitment forRecruitment = new EnterAccountForRecruitment();
        BeanUtils.copyProperties(enterAccountDetail,forRecruitment);
        BeanUtils.copyProperties(pre,base);

        String categoryName = responsitory.findCategoryById(pre.getExpectFunctionCategory());
        base.setCategoryName(categoryName);
        //得到全职ID
        int eId = pre.getId();
        //查询应聘的人数
        //int userCount = messageResponsitory.findUserCountByEventId(eId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(eId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        int userCount = accepter.size();
        base.setJoinNum(userCount);

        fullMini.setRecruitmentsBase(base);
        fullMini.setEnterAccountForRecruitment(forRecruitment);
        fullMini.setRefreshTime(String.valueOf(pre.getRefreshTime()));
        fullMini.setCreateTime(pre.getCreateTime());
        fullMini.setWelfare(pre.getWelfare());
        fullMini.setExpectedSalary(pre.getExpectedSalary());
        detail.setRecruitmentsFullMini(fullMini);


        List<Talent4Work> talent4Works = new ArrayList<Talent4Work>();
        for (Integer userid:accepter) {
            PortalWord4RecruitmentEntity wordEntity = wordResponsitory.getFullWordById(eId,userid);
            Talent4Work talent4Work = new Talent4Work();
            UserEntity userEntity = userResponsitory.findOne(userid);
            talent4Work.setId(userid);
            talent4Work.setTel(wordEntity.getTel());
            talent4Work.setSignature(wordEntity.getInfo());
            Integer functionCategoryId = userEntity.getExpectFunctionCategory();
            if (functionCategoryId != null) {
                FunctioncategoryEntity functioncategoryEntity = functioncategoryResponsitory.findOne(functionCategoryId);
                if (functioncategoryEntity != null) {
                    String functionCategoryName = functioncategoryEntity.getName();
                    talent4Work.setFunctionCategoryName(functionCategoryName);
                }
            }
            if (userEntity.getUserpersonalEntity() != null) {
                talent4Work.setHeadimgurl(userEntity.getUserpersonalEntity().getHeadImg());
                talent4Work.setJob(userEntity.getUserpersonalEntity().getJob());
                PageRequest pr = new PageRequest(0,1, Sort.Direction.DESC,"startTime");

                Page<PortalTechnologyEntity> pteAger = talentResponsitory.findByMajor(userEntity.getId(), 3, pr);

                if(pteAger.getContent().size() != 0){
                    talent4Work.setMajor(pteAger.getContent().get(0).getMajor());
                }
//                talent4Work.setSignature(wordEntity.getInfo());
                talent4Work.setName(userEntity.getUserpersonalEntity().getName());
                List<PortalRealNameInfoEntity> byUseridAndReviewstatus = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(userid, new Integer(1).byteValue());
                List<Integer> certs = new ArrayList<>();
                byUseridAndReviewstatus.forEach(p ->{
                    certs.add(p.getApplyType());
                });
                talent4Work.setCerts(certs);
                //List<Integer> certs = portalRealNameInfoResponsitory.findCertsByUseridAndReviewstatus(userid, new Integer(2).byteValue());
                //talent4Work.setCerts(certs);
            }
            talent4Works.add(talent4Work);
        }
        detail.setAcceptPeoples(talent4Works);
        return detail;

    }

    public RecruitmentsDetail parttimejobEntityTOrecruitmentsDetail(ParttimejobEntity pre) {
        RecruitmentsDetail detail = new RecruitmentsDetail();
        RecruitmentsBase base = new RecruitmentsBase();
        RecruitmentsPartMini partMini = new RecruitmentsPartMini();
        //根据企业id获取企业详细信息
        Integer uid = responsitory.getUserId(pre.getEntId());
        EnterAccountDetail enterAccountDetail = accountsService.getMyEnterpriseInfo(uid);
        EnterAccountForRecruitment forRecruitment = new EnterAccountForRecruitment();
        BeanUtils.copyProperties(enterAccountDetail,forRecruitment);
        BeanUtils.copyProperties(pre,base);

        String categoryName = responsitory.findCategoryById(pre.getExpectFunctionCategory());
        base.setCategoryName(categoryName);
        //得到兼职ID
        int eId = pre.getId();
        //查询应聘的人数
        //int userCount = messageResponsitory.findUserCountByEventId(eId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(eId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        int userCount = accepter.size();
        base.setJoinNum(userCount);

        partMini.setRecruitmentsBase(base);
        partMini.setEnterAccountForRecruitment(forRecruitment);
        partMini.setRefreshTime(String.valueOf(pre.getRefreshTime()));
        partMini.setSalary(String.valueOf(pre.getExpectedSalary()));
        partMini.setWelfare(pre.getWelfare());
        partMini.setStarTime(String.valueOf(pre.getStartTime()));
        partMini.setEndTime(String.valueOf(pre.getEndTime()));
        partMini.setWorkTime(pre.getWorkTime());
        partMini.setCreateTime(pre.getCreateTime());
        // 报名人列表
        List<Talent4Work> talent4Works = new ArrayList<Talent4Work>();
        for (Integer userid:accepter) {
            PortalWord4RecruitmentEntity wordEntity = wordResponsitory.getPartWordById(eId,userid);
            Talent4Work talent4Work = new Talent4Work();
            UserEntity userEntity = userResponsitory.findOne(userid);
            talent4Work.setId(userid);
            talent4Work.setTel(wordEntity.getTel());
            talent4Work.setSignature(wordEntity.getInfo());
            Integer functionCategoryId = userEntity.getExpectFunctionCategory();
            if (functionCategoryId != null) {
                FunctioncategoryEntity functioncategoryEntity = functioncategoryResponsitory.findOne(functionCategoryId);
                if (functioncategoryEntity != null) {
                    String functionCategoryName = functioncategoryEntity.getName();
                    talent4Work.setFunctionCategoryName(functionCategoryName);
                }
            }
            if (userEntity.getUserpersonalEntity() != null) {
                talent4Work.setJob(userEntity.getUserpersonalEntity().getJob());

                PageRequest pr = new PageRequest(0,1, Sort.Direction.DESC,"startTime");

                Page<PortalTechnologyEntity> pteAger = talentResponsitory.findByMajor(userEntity.getId(), 3, pr);

                if(pteAger.getContent().size() != 0){
                    talent4Work.setMajor(pteAger.getContent().get(0).getMajor());
                }


                talent4Work.setHeadimgurl(userEntity.getUserpersonalEntity().getHeadImg());
//                talent4Work.setSignature(wordEntity.getInfo());
                talent4Work.setName(userEntity.getUserpersonalEntity().getName());
                List<PortalRealNameInfoEntity> byUseridAndReviewstatus = portalRealNameInfoResponsitory.findByUseridAndReviewstatus(userid, new Integer(1).byteValue());
                List<Integer> certs = new ArrayList<>();
                byUseridAndReviewstatus.forEach(p ->{
                    certs.add(p.getApplyType());
                });
                talent4Work.setCerts(certs);

                //List<Integer> certs = portalRealNameInfoResponsitory.findCertsByUseridAndReviewstatus(userid, new Integer(2).byteValue());
                //talent4Work.setCerts(certs);
            }
            talent4Works.add(talent4Work);
        }
        detail.setAcceptPeoples(talent4Works);


        detail.setRecruitmentsPartMini(partMini);
        return detail;
    }

    public RecruitmentsFullMini enterpriserecruitmentEntityTOrecruitmentsFullMini(EnterpriserecruitmentEntity ee) {
        RecruitmentsFullMini rm = new RecruitmentsFullMini();
        RecruitmentsBase rb = new RecruitmentsBase();
        //根据企业id获取企业详细信息
        Integer uid = responsitory.getUserId(ee.getEntId());
        EnterAccountDetail detail = accountsService.getMyEnterpriseInfo(uid);
        EnterAccountForRecruitment forRecruitment = new EnterAccountForRecruitment();
        BeanUtils.copyProperties(detail,forRecruitment);
        BeanUtils.copyProperties(ee,rb);

        String categoryName = responsitory.findCategoryById(ee.getExpectFunctionCategory());
        rb.setCategoryName(categoryName);
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(ee.getId(), MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        int userCount = accepter.size();
        rb.setJoinNum(userCount);

        rm.setRecruitmentsBase(rb);
        rm.setExpectedSalary(ee.getExpectedSalary());
        rm.setWelfare(ee.getWelfare());
        rm.setRefreshTime(String.valueOf(ee.getRefreshTime()));
        rm.setCreateTime(ee.getCreateTime());
        rm.setEnterAccountForRecruitment(forRecruitment);
        return rm;
    }

    public RecruitmentsPartMini parttimejobEntityTOrecruitmentsPartMini(ParttimejobEntity pe) {
        RecruitmentsPartMini partMini = new RecruitmentsPartMini();
        RecruitmentsBase base = new RecruitmentsBase();
        //根据企业id获取企业详细信息
        Integer uid = responsitory.getUserId(pe.getEntId());
        EnterAccountDetail detail = accountsService.getMyEnterpriseInfo(uid);
        EnterAccountForRecruitment forRecruitment = new EnterAccountForRecruitment();
        BeanUtils.copyProperties(detail,forRecruitment);
        BeanUtils.copyProperties(pe,base);

        String categoryName = responsitory.findCategoryById(pe.getExpectFunctionCategory());
        base.setCategoryName(categoryName);
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(pe.getId(), MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        int userCount = accepter.size();
        base.setJoinNum(userCount);

        partMini.setRecruitmentsBase(base);
        partMini.setRefreshTime(String.valueOf(pe.getRefreshTime()));
        partMini.setWelfare(pe.getWelfare());
        partMini.setStarTime(String.valueOf(pe.getStartTime()));
        partMini.setEndTime(String.valueOf(pe.getEndTime()));
        partMini.setSalary(String.valueOf(pe.getExpectedSalary()));
        partMini.setEnterAccountForRecruitment(forRecruitment);
        partMini.setCreateTime(pe.getCreateTime());
        partMini.setWorkTime(pe.getWorkTime());
        return partMini;
    }

    public RecommandsJob parttimejobEntityToRecommandsJob(ParttimejobEntity parttimejobEntity){
        RecommandsJob recommandsJob=new RecommandsJob();
        recommandsJob.setId(parttimejobEntity.getId());
        recommandsJob.setName(parttimejobEntity.getJobName());
        return recommandsJob;
    }

    public RecommandsJob enterpriserecruitmentEntityToRecommandsJob(EnterpriserecruitmentEntity enterpriserecruitmentEntity){
        RecommandsJob recommandsJob=new RecommandsJob();
        recommandsJob.setId(enterpriserecruitmentEntity.getId());
        recommandsJob.setName(enterpriserecruitmentEntity.getJobName());
        return recommandsJob;
    }

    /**
     * enterpriserecruitmentEntityToRecruitmentsFullMini(获得个人投递全职)
     * @param enterpriserecruitmentEntity
     * @return
     */
    public RecruitmentsFullJobs enterpriserecruitmentEntityToPersonRecruitmentsFullJobs(EnterpriserecruitmentEntity enterpriserecruitmentEntity){
        RecruitmentsFullJobs recruitmentsFullJobs = new RecruitmentsFullJobs();
        recruitmentsFullJobs.setId(enterpriserecruitmentEntity.getId());
        recruitmentsFullJobs.setJobName(enterpriserecruitmentEntity.getJobName());//职位名称
        recruitmentsFullJobs.setWorkPlace(enterpriserecruitmentEntity.getWorkPlace());//工作地点
        recruitmentsFullJobs.setWorkingLife(enterpriserecruitmentEntity.getWorkingLife());//工作经验
        //获得企业ID
        int entId=enterpriserecruitmentEntity.getEntId();
        UserenterpriseEntity userenterpriseEntity=userEnterpriseService.findOne(entId);
        recruitmentsFullJobs.setEnterpriseName(userenterpriseEntity.getName());//企业名称
        recruitmentsFullJobs.setEnterpriseLogo(userenterpriseEntity.getHeadImg());//企业LOGO
        recruitmentsFullJobs.setRefreshTime(new SimpleDateFormat("yyyy/MM/dd").format(enterpriserecruitmentEntity.getRefreshTime()));//刷新时间
        recruitmentsFullJobs.setSalary(enterpriserecruitmentEntity.getExpectedSalary());//薪水
        recruitmentsFullJobs.setWelfare(enterpriserecruitmentEntity.getWelfare());//公司福利
        return recruitmentsFullJobs;
    }
    //获得个人投递兼职
    public RecruitmentsPartJobs parttimejobEntityToPersonRecruitmentsPartJobs(ParttimejobEntity parttimejobEntity){
        RecruitmentsPartJobs recruitmentsPartJobs=new RecruitmentsPartJobs();
        recruitmentsPartJobs.setId(parttimejobEntity.getId());
        recruitmentsPartJobs.setJobName(parttimejobEntity.getJobName());//职位名称
        recruitmentsPartJobs.setWorkPlace(parttimejobEntity.getWorkPlace());//工作地点
        recruitmentsPartJobs.setJobRequire(parttimejobEntity.getJobRequire());//工作要求
        recruitmentsPartJobs.setJobDesc(parttimejobEntity.getJobDesc());//兼职工作描述
        //获得企业ID
        int entId=parttimejobEntity.getEntId();
        UserenterpriseEntity userenterpriseEntity=userEnterpriseService.findOne(entId);
        recruitmentsPartJobs.setEnterpriseName(userenterpriseEntity.getName());//企业名称
        recruitmentsPartJobs.setRefreshTime(new SimpleDateFormat("yyyy/MM/dd").format(parttimejobEntity.getRefreshTime()));//刷新时间
        recruitmentsPartJobs.setSalary(parttimejobEntity.getExpectedSalary());//薪水，按小时算
        //recruitmentsPartJobs.setStartTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(parttimejobEntity.getStartTime()));//开始时间
        //recruitmentsPartJobs.setEndTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(parttimejobEntity.getEndTime()));//结束时间
        recruitmentsPartJobs.setWorkTime(parttimejobEntity.getWorkTime());
        return recruitmentsPartJobs;
    }
//获得企业发布的全职
    public RecruitmentsFullJobs enterpriserecruitmentEntityToRecruitmentsFullJobs(EnterpriserecruitmentEntity enterpriserecruitmentEntity) {
        RecruitmentsFullJobs recruitmentsFullJobs = new RecruitmentsFullJobs();
        recruitmentsFullJobs.setId(enterpriserecruitmentEntity.getId());//全职id
        recruitmentsFullJobs.setJobName(enterpriserecruitmentEntity.getJobName());//职位名称
        recruitmentsFullJobs.setWorkPlace(enterpriserecruitmentEntity.getWorkPlace());//工作地点
        recruitmentsFullJobs.setWorkingLife(enterpriserecruitmentEntity.getWorkingLife());//工作经验
        recruitmentsFullJobs.setRefreshTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(enterpriserecruitmentEntity.getRefreshTime()));//刷新时间
        recruitmentsFullJobs.setSalary(enterpriserecruitmentEntity.getExpectedSalary());//薪水
        recruitmentsFullJobs.setWelfare(enterpriserecruitmentEntity.getWelfare());//公司福利
        //得到全职ID
        int enterpriseId = enterpriserecruitmentEntity.getId();
        //查询应聘的人数
        //int userCount = messageResponsitory.findUserCountByEventId(enterpriseId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(enterpriseId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        int userCount = accepter.size();
        recruitmentsFullJobs.setJoinNum(userCount);//应聘人数
        return recruitmentsFullJobs;
    }
//获得企业发布的兼职
    public RecruitmentsPartJobs parttimejobEntityToRecruitmentsPartJobs(ParttimejobEntity parttimejobEntity) {
        RecruitmentsPartJobs recruitmentsPartJobs=new RecruitmentsPartJobs();
        recruitmentsPartJobs.setId(parttimejobEntity.getId());
        recruitmentsPartJobs.setJobName(parttimejobEntity.getJobName());//职位名称
        recruitmentsPartJobs.setWorkPlace(parttimejobEntity.getWorkPlace());//工作地点
        recruitmentsPartJobs.setRefreshTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(parttimejobEntity.getRefreshTime()));//刷新时间
        //recruitmentsPartJobs.setStartTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(parttimejobEntity.getStartTime()));//开始时间
        //recruitmentsPartJobs.setEndTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(parttimejobEntity.getEndTime()));//结束时间
        recruitmentsPartJobs.setSalary(parttimejobEntity.getExpectedSalary());//薪水
        recruitmentsPartJobs.setWorkTime(parttimejobEntity.getWorkTime());
        //得到全职ID
        int enterpriseId = parttimejobEntity.getId();
        //查询应聘的人数
        //int userCount = messageResponsitory.findUserCountByEventId(enterpriseId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        List<Integer> accepter = messageResponsitory.findUserCountByEventId(enterpriseId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        int userCount = accepter.size();
        recruitmentsPartJobs.setJoinNum(userCount);//应聘人数
        return recruitmentsPartJobs;
    }
}