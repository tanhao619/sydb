package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.*;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.lgfc.ArticleMini;
import com.cdyoue.oddJobs.dto.lgfc.ArticleRequest;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.entity.lgsq.PortalArticleTopEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalCommonPageDetailEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/5/2.
 */
@Component
public class ArticleMapper {
    @Autowired
    private UserResponsitory userResponsitory;
    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;
    @Autowired
    private ArticleResponsitory articleResponsitory;
    @Autowired
    private MessageResponsitory messageResponsitory;
    @Autowired
    private ArticleTopResponsitory articleTopResponsitory;

    //PortalCommonPageDetailEntity转换成ArticleDTO
    public Article entityToDto(PortalCommonPageDetailEntity entity){
        Article dto = new Article();
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        int uId=-1;
        if(userMine!=null){
            uId = userMine.getId();
        }
        if(entity.getCreateTime()!=null){
            String time = entity.getCreateTime().toString();
            time = time.substring(0,10);
            time = time.replace("-","/");
            dto.setCreateTime(time);
        }
        if(entity.getUpdateTime()!=null){
            String time = entity.getUpdateTime().toString();
            time = time.substring(0,10);
            time = time.replace("-","/");
            dto.setUpdateTime(time);
        }
        if(entity.getPublishTime()!=null){
            String time = entity.getPublishTime().toString();
            time = time.substring(0,10);
            time = time.replace("-","/");
            dto.setPublishTime(time);
        }
        BeanUtils.copyProperties(entity,dto);
        Boolean isCol = MessageUtils.isMessageExist(entity.getId(), MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle);
        dto.setFavorStatus(isCol);
        dto.setLink(entity.getLink()+entity.getId());
        dto.setContent(entity.getIntroduction());
        dto.setFavorAccount(entity.getFavorCount());
        dto.setViewsAccount(entity.getViewsCount());
        UserEntity one = userResponsitory.findOne(entity.getCreateBy());
        if(one != null){
            int userRole=one.getRole();
            /*if(userRole==2){//管理员
                dto.setAuthor(one.getUserName());
            }else */if(userRole==1){//企业用户
                dto.setAuthor(userenterpriseResponsitory.findNameByUid(entity.getCreateBy()));
            }else if(userRole==0||userRole == 2){//个人用户、管理员
                UserpersonalEntity user = userpersonalResponsitory.findByUid(entity.getCreateBy());
                if(user!=null){
                    dto.setAuthor(user.getName());
                }else{
                    dto.setAuthor("发布人不存在ID="+entity.getCreateBy());
                }
            }

            PortalArticleTopEntity pate = articleTopResponsitory.findByArticleId(entity.getId());
            if (pate != null){
                dto.setTopName(pate.getName());
                dto.setTopImg(pate.getCoverImg());
                dto.setTopInfo(pate.getInfo());
            }
        }
        dto.setTop(entity.getTop());
        return dto;
    }

    //ArticleRequest转换成PortalCommonPageDetailEntity
    public PortalCommonPageDetailEntity requestToEntity(ArticleRequest request){
        PortalCommonPageDetailEntity entity = new PortalCommonPageDetailEntity();
        entity.setTitle(request.getTitle());
        entity.setIntroduction(request.getContent());
        return entity;
    }


    //messageEntity转化成ArticleDTO
    public Article messageToArticle(PortalMessageEntity entity){
        PortalCommonPageDetailEntity entity1   = articleResponsitory.findOne(entity.getEventId());
        Article articleDTO = this.entityToDto(entity1);
        return articleDTO;
    }

    public ArticleMini PortalCommonPageDetailEntityToArticleMini(PortalCommonPageDetailEntity entity){
        ArticleMini articleMini=new ArticleMini();
        articleMini.setId(entity.getId());
        articleMini.setTitle(entity.getTitle());
        articleMini.setPublishTime(new SimpleDateFormat("yyyy/MM/dd").format(entity.getPublishTime()));
        return articleMini;
    }

}
