package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.dao.lqsq.PortalNewsResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.ggfw.CommunitySummary;
import com.cdyoue.oddJobs.dto.ggfw.NewsDetail;
import com.cdyoue.oddJobs.dto.ggfw.NewsRequest;
import com.cdyoue.oddJobs.dto.ggfw.NewsSummary;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalNewsEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.PortalNewsMapper;
import com.cdyoue.oddJobs.service.PortalNewsService;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by John on 2017/5/9.
 */
@Service
public class PortalNewsServiceImpl implements PortalNewsService {

    @Autowired
    private PortalNewsResponsitory portalNewsResponsitory;

    @Autowired
    private PortalNewsMapper  mapper;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override
    public PageInfo<CommunitySummary> findNewsPage(String q, Integer reviewStatus, Pageable requestPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();

        QueryRequest qe1 = new QueryRequest();
        qe1.setF("title");
        qe1.setO(Operator.LIKE);
        qe1.setV(q);
        queryRequest.add(qe1);

        QueryRequest qeEr = new QueryRequest();
        qeEr.setF("reviewStatus");
        qeEr.setO(Operator.EQ);
        qeEr.setV(reviewStatus + "");
        qeEr.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qeEr);

        Specification specifica = specificationHelper.getSpecifica(PortalNewsEntity.class, queryRequest);
        Page<PortalNewsEntity> portalNewsEntityPage = portalNewsResponsitory.findAll(specifica, requestPage);
        if (portalNewsEntityPage.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        List<CommunitySummary>  css =  portalNewsEntityPage.getContent().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(css, requestPage, portalNewsEntityPage.getTotalElements()));
    }

    @Override
    @Transactional
    public void save(NewsRequest news) {
        PortalNewsEntity portalNewsEntity = new PortalNewsEntity();
        portalNewsEntity.setTitle(news.getTitle());
        portalNewsEntity.setIntroduction(news.getContent());
        // 选填、如不填、系统将默认抓取正文前140个字
        if (news.getAbstract() != null && !news.getAbstract().trim().equals("")) {
            portalNewsEntity.setSummary(news.getAbstract());
        } else {
            if(news.getContent().length() > 125){
                portalNewsEntity.setSummary(news.getContent().substring(0, 125) + "...");
            }else {
                portalNewsEntity.setSummary(news.getContent());
            }

        }
        // 选填、如不填、系统自动抓取详情中的第一张图片
        if (news.getCoverImgUrl() != null && !news.getCoverImgUrl().trim().equals("")) {
            portalNewsEntity.setCoverImg(news.getCoverImgUrl());
        } else {
            portalNewsEntity.setCoverImg("");//??什么是详情的的第一张图片
        }
        // 发布时间、创建时间目前需求一致
        portalNewsEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        portalNewsEntity.setPublishTime(new Timestamp(System.currentTimeMillis()));
        // 创建人id
        portalNewsEntity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        // 审核状态：0 待审 1 审核失败  2审核通过
        portalNewsEntity.setReviewStatus(new Integer(0).byteValue());
        // 浏览次数
        portalNewsEntity.setViewsCount(0);
        portalNewsResponsitory.save(portalNewsEntity);
    }

    @Override
    public NewsDetail findById(Integer id) {
        PortalNewsEntity portalNewsEntity = portalNewsResponsitory.findOne(id);
        if (portalNewsEntity == null){
            return null;
        }
        //portalNewsEntity.setViewsCount(portalNewsEntity.getViewsCount() + 1);
        //portalNewsResponsitory.save(portalNewsEntity);
        NewsDetail newsDetail = mapper.newsEntityToNewsDetail(portalNewsEntity);
        return newsDetail;
    }

    @Override
    public PageInfo<NewsSummary> findByUid(Integer userid, Pageable pageRequest) {
        Page<PortalNewsEntity> portalNewsEntityPage = portalNewsResponsitory.findByUid(userid, pageRequest);
        List<NewsSummary> newsSummaries = portalNewsEntityPage.getContent().stream().map(p -> mapper.newsEntityToNewsSummary(p)).collect(Collectors.toList());
        return new PageInfo<NewsSummary>(new PageImpl(newsSummaries, pageRequest, portalNewsEntityPage.getTotalElements()));
    }

    @Override
    public Boolean isExistById(Integer id) {
        if(portalNewsResponsitory.findOne(id)==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    @Transactional
    public Boolean updateById(Integer id, NewsRequest newsRequest) {
        PortalNewsEntity portalNewsEntity = portalNewsResponsitory.findOne(id);
        // 先判断是不是自己的，或者是管理员
        //if (SecurityUtils.getCurrentUserLogin().getId().equals(portalNewsEntity.getCreateBy()) || SecurityUtils.getCurrentUserLogin().getUserType() == 2){
        if (SecurityUtils.getCurrentUserLogin().getId().equals(portalNewsEntity.getCreateBy())){
            portalNewsEntity.setTitle(newsRequest.getTitle());
            portalNewsEntity.setIntroduction(newsRequest.getContent());
            portalNewsEntity.setSummary(newsRequest.getAbstract());
            portalNewsEntity.setCoverImg(newsRequest.getCoverImgUrl());
            portalNewsEntity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
            portalNewsEntity.setReviewStatus(new Byte("0"));//修改新闻时需要回滚审核状态（0）
            portalNewsEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            portalNewsEntity.setReviewStatus(new Integer(0).byteValue());
            portalNewsResponsitory.save(portalNewsEntity);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public Boolean deleteById(Integer id) {
        PortalNewsEntity portalNewsEntity = portalNewsResponsitory.findOne(id);
        // 先判断是不是自己的，或者是管理员
        //if (SecurityUtils.getCurrentUserLogin().getId() == portalNewsEntity.getCreateBy() || SecurityUtils.getCurrentUserLogin().getRole() == 2) {
        if (SecurityUtils.getCurrentUserLogin().getId().equals(portalNewsEntity.getCreateBy()) || SecurityUtils.getCurrentUserLogin().getRole() == 2) {
            portalNewsResponsitory.delete(portalNewsEntity);
            //MessageUtils.cancelEventAction(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditNews);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public void reviewNews(Integer id, Integer status, Reason reason) {
        PortalNewsEntity portalNewsEntity = portalNewsResponsitory.findOne(id);
        portalNewsEntity.setReviewStatus(status.byteValue());
        portalNewsEntity.setReviewReason(reason.getReason());
        portalNewsEntity.setReviewTime(new Timestamp(System.currentTimeMillis()));
        // 审核人员（后台登录的管理员id），怎么获取
        portalNewsEntity.setReviewUserId(SecurityUtils.getCurrentUserLogin().getId());
        portalNewsResponsitory.save(portalNewsEntity);
//        Boolean isReview = MessageUtils.isMessageExist(id, MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditNews);
//        if (!isReview) {
            //return new ResponseEntity<HttpMessage>(NewsMessage.REVIEWNEWSFAILD400, HttpStatus.BAD_REQUEST);
            MessageUtils.createAuditMessage(portalNewsEntity.getCreateBy(), portalNewsEntity.getId(), MessageEventTypeEnum.AUDIT, MessageMsgTypeEnum.AuditNews,status+"");
//        }
    }

}
