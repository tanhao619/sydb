package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.FullRecruitmentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.EnterpriserecruitmentEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.RecruitmentsMapper;
import com.cdyoue.oddJobs.service.RecruitmentsService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
@Transactional
public class RecruitmentsServiceImpl extends ServiceSupport<EnterpriserecruitmentEntity> implements RecruitmentsService {

    @Autowired
    private FullRecruitmentResponsitory fullRecruitmentResponsitory;

    @Autowired
    private RecruitmentsMapper recruitmentsMapper;

    @Autowired
    private MessageResponsitory messageResponsitory;


    @Override
    public void createFullRecruitment(RecruitmentsFullInfo recruitments) {
        EnterpriserecruitmentEntity enterpriserecruitmentEntity = new EnterpriserecruitmentEntity();
        BeanUtils.copyProperties(recruitments,enterpriserecruitmentEntity);
        BeanUtils.copyProperties(recruitments.getRecruitmentsInfo(),enterpriserecruitmentEntity);
        enterpriserecruitmentEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //enterpriserecruitmentEntity.setWelfare(recruitments.getWelfare());
        //enterpriserecruitmentEntity.setExpectedSalary(recruitments.getExpectedSalary());
        enterpriserecruitmentEntity.setRefreshTime(new Timestamp(System.currentTimeMillis()));
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        Integer eid = fullRecruitmentResponsitory.getEid(uid);
        enterpriserecruitmentEntity.setEntId(eid);
        fullRecruitmentResponsitory.save(enterpriserecruitmentEntity);
    }


    @Override
    public PageInfo<RecruitmentsFullMini> getFullRecruitments(String q, PageRequest pr) {
        Page<EnterpriserecruitmentEntity> tePage = super.findByStrLike("jobName",q, pr);
        List<RecruitmentsFullMini> topics = tePage.getContent().stream().map(p ->
                recruitmentsMapper.enterpriserecruitmentEntityTOrecruitmentsFullMini(p))
                .collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(topics, pr, tePage.getTotalElements()));
    }

    // 获取全职详细信息
    @Override
    public RecruitmentsDetail getFullRecruitmentById(Integer id) {
        EnterpriserecruitmentEntity pre = fullRecruitmentResponsitory.findOne(id);
        if(pre==null){
             throw new NotFoundEntityException();
        }
        RecruitmentsDetail detail = recruitmentsMapper.enterpriserecruitmentEntityTOrecruitmentsDetail(pre);
        Boolean messageExist = MessageUtils.isMessageExist(id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForFullTimeJob);
        detail.getRecruitmentsFullMini().setApplyFor(messageExist);
        return detail;
    }
//获得推荐全职
    @Override
    public PageInfo<RecommandsJob> getRecommendJobs(List<RecommendData> data, Pageable requestPage) {
        List<Integer> recommendJodIds=new ArrayList<>();
        for(int i=0;i<data.size();i++){
            recommendJodIds.add(data.get(i).getJobid());
        }
        Page<EnterpriserecruitmentEntity> fulljobEntityPage=fullRecruitmentResponsitory.getFullJobPage(recommendJodIds,requestPage);
        List<RecommandsJob> recommandsJobList=fulljobEntityPage.getContent().stream().map(p -> recruitmentsMapper.enterpriserecruitmentEntityToRecommandsJob(p)).collect(Collectors.toList());
        for(int i=0;i<recommandsJobList.size();i++){
            recommandsJobList.get(i).setRecommendeddegree(data.get(i).getRecommendeddegree());//推荐度
        }
        return new PageInfo<>(new PageImpl(recommandsJobList,requestPage, fulljobEntityPage.getTotalElements()));
    }
     //获得个人投递的全职
    @Override
    public PageInfo<RecruitmentsFullJobs> getMyFullJobs(Integer userId,Pageable pageable) {
        //得到投递的全职的ID
        List<Integer> fullJobIds=messageResponsitory.getMessageEventIds(userId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        if(fullJobIds!=null && fullJobIds.size() > 0){
            //根据全职ID查询对应的全职信息
            Page<EnterpriserecruitmentEntity> fullJobPage=fullRecruitmentResponsitory.getFullJobPage(fullJobIds,pageable);
            List<RecruitmentsFullJobs> fullJobList=fullJobPage.getContent().stream().map(p -> recruitmentsMapper.enterpriserecruitmentEntityToPersonRecruitmentsFullJobs(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(fullJobList,pageable, fullJobPage.getTotalElements()));
        }else {
            return null;
        }
    }
//获得企业发布的全职
    @Override
    public PageInfo<RecruitmentsFullJobs> getUserEnterpriseFullJobs(Integer id,String q,Pageable pageable) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<EnterpriserecruitmentEntity> fullJobPage=fullRecruitmentResponsitory.getUserEnterpriseFullJobPage(id,q,pageable);
        List<RecruitmentsFullJobs> fullJobList=fullJobPage.getContent().stream().map(p -> recruitmentsMapper.enterpriserecruitmentEntityToRecruitmentsFullJobs(p)).collect(Collectors.toList());
        if(fullJobList!=null){
            return new PageInfo<>(new PageImpl(fullJobList,pageable, fullJobPage.getTotalElements()));
        }
        return null;
    }

    //
    @Override
    public Integer getFullRecruitmentEntId(Integer id) {
       Integer entId = fullRecruitmentResponsitory.getFullRecruitmentEntId(id);
        if(entId==null){
            throw new NotFoundEntityException();
        }
        return entId;
    }

    @Override
    public void deleteById(Integer id) {
        fullRecruitmentResponsitory.delete(id);
    }
//获取我接收邀请的全职
    @Override
    public PageInfo<RecruitmentsFullJobs> getMyInviteFullJobs(Integer userId, Pageable requestPage) {
        List<Integer> fullJobIds=messageResponsitory.getMyMessageEventIds(userId,MessageEventTypeEnum.INVITATION.getValue(),MessageMsgTypeEnum.ApplyForFullTimeJob.getMsgType());
        if(fullJobIds==null||fullJobIds.size()==0){
            return null;
        }else {
            Page<EnterpriserecruitmentEntity> fullJobsPage=fullRecruitmentResponsitory.getFullJobPage(fullJobIds,requestPage);
            List<RecruitmentsFullJobs> fullJobsList=fullJobsPage.getContent().stream().map(p -> recruitmentsMapper.enterpriserecruitmentEntityToPersonRecruitmentsFullJobs(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(fullJobsList,requestPage, fullJobsPage.getTotalElements()));
        }
    }


    @Override
    public Class getJpaRepositoryClazz() {
        return FullRecruitmentResponsitory.class;
    }
}
