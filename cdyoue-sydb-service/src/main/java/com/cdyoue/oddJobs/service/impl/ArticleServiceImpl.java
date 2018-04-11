package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.ArticleResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.ArticleTopResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.MessageResponsitory;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.lgfc.ArticleMini;
import com.cdyoue.oddJobs.dto.lgfc.ArticleTop;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalArticleTopEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalCommonPageDetailEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.MessageUtils;
import org.springframework.data.domain.Page;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.lgfc.ArticleRequest;
import com.cdyoue.oddJobs.mapper.ArticleMapper;
import com.cdyoue.oddJobs.service.ArticleService;
import com.cdyoue.oddJobs.service.support.ServiceSupport;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/5/2.
 */
@Service
public class ArticleServiceImpl extends ServiceSupport<PortalCommonPageDetailEntity> implements ArticleService {
    @Autowired
    private ArticleMapper mapper;
    @Autowired
    private ArticleResponsitory articleResponsitory;
    @Autowired
    private ArticleTopResponsitory articleTopResponsitory;
    @Autowired
    MessageResponsitory messageResponsitory;

    @Autowired
    private SpecificationHelper specificationHelper;

    @Override//创建文章
    public void createArticle(ArticleRequest article1DTO) {
        PortalCommonPageDetailEntity entity = new PortalCommonPageDetailEntity();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Integer  type = 3;
        entity = mapper.requestToEntity(article1DTO);
        entity.setCreateTime(time);
        entity.setUpdateTime(time);
        entity.setViewsCount(0);
        entity.setFavorCount(0);
        entity.setCreateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setPageType(type);
        entity.setStatus(0);
        entity.setTop(0);
        entity.setLink("/H5/articleDetails.html?id=" + entity.getId());
        articleResponsitory.save(entity);
    }

    @Override//通过ID查询一个文章
    public Article getArticleById(Integer id) {
        Article articleDTO = new Article();
        PortalCommonPageDetailEntity entity = articleResponsitory.getOne(id);
        articleDTO = mapper.entityToDto(entity);
        return articleDTO;
    }

    @Override//查询文章列表
    public PageInfo<Article> findList(PageRequest pr, String type, String q, Integer userid) {
        String pageType = "3";
        String status = "1";
        UserMine user=SecurityUtils.getCurrentUserLogin();
        //如果是超级管理员，则可以查询包括未审核通过的文章
        if(user!=null&&user.getRole()==2){
           status="";
        }
        Page<PortalCommonPageDetailEntity> rpPage = super.findByStrAndTopLike("pageType", pageType,"top",type,"title",q,"status",status,"createBy",userid+"",pr);
        List<Article> articles = rpPage.getContent().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(articles, pr, rpPage.getTotalElements()));
    }

    @Override//查询我写的文章列表
    public PageInfo<Article> findMyList(PageRequest pr,String q) {
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        String pageType = "3";
        Page<PortalCommonPageDetailEntity> rpPage = super.findByCreateQE("pageType", pageType,"createBy",uId,"title",q,"status",null,pr);
        List<Article> articles = rpPage.getContent().stream().map(p -> mapper.entityToDto(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(articles, pr, rpPage.getTotalElements()));
    }
    //查询某人发的文章
    @Override
    public PageInfo<ArticleMini> findSomeOneArticle(PageRequest pr, String q,Integer id,Integer status) {
        String pageType = "3";
        Page<PortalCommonPageDetailEntity> rpPage = super.findByCreateQE("pageType", pageType,"createBy",id,"title",q,"status",status,pr);
        List<ArticleMini> articles = rpPage.getContent().stream().map(p -> mapper.PortalCommonPageDetailEntityToArticleMini(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(articles, pr, rpPage.getTotalElements()));
    }
    @Override//查询我的收藏列表
    public PageInfo<Article> findMyCollectionsList(PageRequest pr, String type) {
        Page<PortalMessageEntity> rpPage = MessageUtils.getPageMessage(MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle,pr);
        List<Article> articles = rpPage.getContent().stream().map(p -> mapper.messageToArticle(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(articles, pr, rpPage.getTotalElements()));
    }

    @Override//收藏文章
    public void collectArticle(Integer id) {
           MessageUtils.createMessage(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle);
        PortalCommonPageDetailEntity entity = articleResponsitory.findOne(id);
        entity.setFavorCount(entity.getFavorCount()+1);
        articleResponsitory.save(entity);
    }

    @Override
    @Transactional//取消收藏文章
    public void uncollectArticle(Integer id) {
        MessageUtils.cancelEventAction(id,MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle);
        PortalCommonPageDetailEntity entity = articleResponsitory.findOne(id);
        entity.setFavorCount(entity.getFavorCount()-1);
        articleResponsitory.save(entity);
    }

    @Override//修改文章
    public void updateArticle(Integer id, ArticleRequest article) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        PortalCommonPageDetailEntity entity = articleResponsitory.findOne(id);
        entity.setTitle(article.getTitle());
        entity.setIntroduction(article.getContent());
        entity.setUpdateTime(time);
        entity.setUpdateBy(SecurityUtils.getCurrentUserLogin().getId());
        entity.setStatus(0);
        entity.setReviewReason(null);
        articleResponsitory.save(entity);
    }


    @Override//删除文章
    @Transactional
    public void deleteArticle(Integer id) {
        Integer  type = 3;
        MessageUtils.cancelEventAction(id,MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle);
        articleResponsitory.deleteArticle(id,type);
    }

    @Override//审核文章
    public void examineArticle(Integer id, Reason reviewReason, boolean b) {
        Integer type = 3;
        if (b){
            PortalCommonPageDetailEntity entity = articleResponsitory.findByIdAndType(id,type);
            entity.setStatus(1);
            entity.setPublishTime(new Timestamp(System.currentTimeMillis()));
            entity.setReviewReason(reviewReason.getReason());
            articleResponsitory.save(entity);
        }else {
            PortalCommonPageDetailEntity entity = articleResponsitory.findByIdAndType(id,type);
            entity.setStatus(2);
            entity.setReviewReason(reviewReason.getReason());
            articleResponsitory.save(entity);
        }
    }

    @Override//将文章设置为精选
    public void stickArticle(Integer id,ArticleTop articleTop) {
        PortalCommonPageDetailEntity entity =  articleResponsitory.findByIdAndType(id,3);
        entity.setTop(1);
        articleResponsitory.save(entity);
        PortalArticleTopEntity portalArticleTopEntity=new PortalArticleTopEntity();
        portalArticleTopEntity.setArticleId(id);
        portalArticleTopEntity.setCoverImg(articleTop.getCoverImg());
        portalArticleTopEntity.setName(articleTop.getName());
        portalArticleTopEntity.setInfo(articleTop.getInfo());
        articleTopResponsitory.save(portalArticleTopEntity);
    }

    @Override//取消设置精选
    @Transactional
    public void unStickArticle(Integer id) {
        PortalCommonPageDetailEntity entity =  articleResponsitory.findByIdAndType(id,3);
        entity.setTop(0);
        articleResponsitory.save(entity);
        //删除精选的
        articleTopResponsitory.deleteByArticleId(id);
    }


    @Override//验证文章是否存在
    public boolean getArticleCheck(Integer id) {
        Integer type = 3;
        if(articleResponsitory.findByIdAndType(id,type)==null){
            return false;
        }else {
            return true;
        }

    }

    @Override//获取创建人ID
    public int getCreateBy(Integer id) {
        Integer type = 3;
        PortalCommonPageDetailEntity entity = articleResponsitory.findByIdAndType(id,type);
        return entity.getCreateBy();
    }

    @Override//获取文章状态
    public Integer getStatus(Integer id) {
        Integer type = 3;
        PortalCommonPageDetailEntity entity = articleResponsitory.findByIdAndType(id,type);
        return entity.getStatus();
    }

    @Override
    public Integer getTop(Integer id) {
        return articleResponsitory.findOne(id).getTop();
    }

    @Override
    public Class getJpaRepositoryClazz() {
        return ArticleResponsitory.class;
    }
}
