package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.FullRecruitmentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.PartRecruitmentResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.ParttimejobEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.RecruitmentsMapper;
import com.cdyoue.oddJobs.service.RecruitsPartService;
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
 * Created by Administrator on 2017/5/9.
 */
@Service
@Transactional
public class RecruitsPartServiceImpl extends ServiceSupport<ParttimejobEntity> implements RecruitsPartService {
    @Autowired
    private PartRecruitmentResponsitory partRecruitmentResponsitory;
    @Autowired
    private FullRecruitmentResponsitory fullRecruitmentResponsitory;
    @Autowired
    private RecruitmentsMapper recruitmentsMapper;
    @Autowired
    private MessageResponsitory messageResponsitory;
    @Override
    public void createPartRecruitment(RecruitmentsPartInfo recruitments) {
        ParttimejobEntity parttimejobEntity = new ParttimejobEntity();
        BeanUtils.copyProperties(recruitments,parttimejobEntity);
        BeanUtils.copyProperties(recruitments.getRecruitmentsInfo(),parttimejobEntity);
//        parttimejobEntity.setExpectedSalary(recruitments.getExpectedSalary());
        parttimejobEntity.setRefreshTime(new Timestamp(System.currentTimeMillis()));
        parttimejobEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        Integer eid = fullRecruitmentResponsitory.getEid(uid);
        parttimejobEntity.setEntId(eid);
//        parttimejobEntity.setWorkTime(recruitments.getWorkTime());
        partRecruitmentResponsitory.save(parttimejobEntity);
    }

    @Override
    public PageInfo<RecruitmentsPartMini> getPartRecruitments(String q, PageRequest pr) {
        Page<ParttimejobEntity> tePage = super.findByStrLike("jobName",q,pr);
        List<RecruitmentsPartMini> topics = tePage.getContent().stream().map(p -> recruitmentsMapper.parttimejobEntityTOrecruitmentsPartMini(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(topics, pr, tePage.getTotalElements()));
    }
//推荐兼职
    @Override
    public PageInfo<RecommandsJob> getRecommendJobs(List<RecommendData> data, Pageable requestPage) {
       List<Integer> recommendJodIds=new ArrayList<>();
       for(int i=0;i<data.size();i++){
           recommendJodIds.add(data.get(i).getJobid());
       }
        Page<ParttimejobEntity> parttimejobEntityPage=partRecruitmentResponsitory.getPartJobPage(recommendJodIds,requestPage);
        List<RecommandsJob> recommandsJobList=parttimejobEntityPage.getContent().stream().map(p -> recruitmentsMapper.parttimejobEntityToRecommandsJob(p)).collect(Collectors.toList());
        for(int i=0;i<recommandsJobList.size();i++){
            recommandsJobList.get(i).setRecommendeddegree(data.get(i).getRecommendeddegree());//推荐度
        }
        return new PageInfo<>(new PageImpl(recommandsJobList,requestPage, parttimejobEntityPage.getTotalElements()));
    }
//获得个人投递兼职
    @Override
    public PageInfo<RecruitmentsPartJobs> getMyPartJobs(Integer userId, Pageable pageable) {
        //得到投递的兼职的ID
        List<Integer> partJobIds=messageResponsitory.getMessageEventIds(userId, MessageEventTypeEnum.APPLYFOR.getValue(), MessageMsgTypeEnum.ApplyForPartTimeJob.getMsgType());
        if(partJobIds!=null && partJobIds.size() > 0){
            //根据全职ID查询对应的兼职信息
            Page<ParttimejobEntity> partJobPage=partRecruitmentResponsitory.getPartJobPage(partJobIds,pageable);
            List<RecruitmentsPartJobs> partJobList=partJobPage.getContent().stream().map(p -> recruitmentsMapper.parttimejobEntityToPersonRecruitmentsPartJobs(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(partJobList,pageable, partJobPage.getTotalElements()));
        }else {
            return null;
        }
    }
//获得企业发布的兼职
    @Override
    public PageInfo<RecruitmentsPartJobs> getUserEnterprisePartJobs(Integer userId,String q, Pageable pageable) {
        if(q==null||q.trim().equals("")){
            q="%%";
        }else{
            q="%"+q+"%";
        }
        Page<ParttimejobEntity> partJobPage=partRecruitmentResponsitory.getUserEnterprisePartJobPage(userId,q,pageable);
        List<RecruitmentsPartJobs> partJobList=partJobPage.getContent().stream().map(p -> recruitmentsMapper.parttimejobEntityToRecruitmentsPartJobs(p)).collect(Collectors.toList());
        if(partJobList!=null){
            return new PageInfo<>(new PageImpl(partJobList,pageable, partJobPage.getTotalElements()));
        }
        return null;
    }

    @Override
    public Integer getFullRecruitmentEntId(Integer id) {
        Integer entId = partRecruitmentResponsitory.getFullRecruitmentEntId(id);
        if(entId==null){
            throw new NotFoundEntityException();
        }
        return entId;
    }

    @Override
    public void deleteById(Integer id) {
        partRecruitmentResponsitory.delete(id);
    }
//获得收到的兼职邀请
    @Override
    public PageInfo<RecruitmentsPartJobs> getMyInvitePartJobs(Integer userId, Pageable requestPage) {
        List<Integer> partJobIds=messageResponsitory.getMyMessageEventIds(userId,MessageEventTypeEnum.INVITATION.getValue(),MessageMsgTypeEnum.InvitationPartTimeJob.getMsgType());
        if(partJobIds==null||partJobIds.size()==0){
            return null;
        }else {
            Page<ParttimejobEntity> partJobsPage=partRecruitmentResponsitory.getPartJobPage(partJobIds,requestPage);
            List<RecruitmentsPartJobs> partJobsList=partJobsPage.getContent().stream().map(p -> recruitmentsMapper.parttimejobEntityToPersonRecruitmentsPartJobs(p)).collect(Collectors.toList());
            return new PageInfo<>(new PageImpl(partJobsList,requestPage, partJobsPage.getTotalElements()));
        }
    }

    @Override
    public RecruitmentsDetail getPartRecruitmentById(Integer id) {
        ParttimejobEntity pre = partRecruitmentResponsitory.findOne(id);
        if(pre==null){
            throw new NotFoundEntityException();
        }
        RecruitmentsDetail detail = recruitmentsMapper.parttimejobEntityTOrecruitmentsDetail(pre);
        Boolean messageExist = MessageUtils.isMessageExist(id, MessageEventTypeEnum.APPLYFOR, MessageMsgTypeEnum.ApplyForPartTimeJob);
        detail.getRecruitmentsPartMini().setApplyFor(messageExist);
        return detail;
    }


    @Override
    public Class getJpaRepositoryClazz() {
        return PartRecruitmentResponsitory.class;
    }
}
