package com.cdyoue.oddJobs.service;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.lgfc.ArticleMini;
import com.cdyoue.oddJobs.dto.lgfc.ArticleRequest;
import com.cdyoue.oddJobs.dto.lgfc.ArticleTop;
import com.cdyoue.spring.page.PageInfo;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2017/5/2.
 */
public interface ArticleService {
    //创建新文章
    void createArticle(ArticleRequest article1DTO);
    //通过ID找寻一个文章详细信息
    Article getArticleById(Integer id);
    //收藏文章
    void collectArticle(Integer id);
    //取消收藏
    void uncollectArticle(Integer id);
    //修改文章内容
    void updateArticle(Integer id, ArticleRequest article);
    //查询所有文章分页列表
    PageInfo<Article> findList(PageRequest pr, String type, String q, Integer userid);
    //删除文章
    void deleteArticle(Integer id);
    //查询我写的文章列表
    PageInfo<Article> findMyList(PageRequest pr,String q);
    //查询我收藏的文章列表
    PageInfo<Article> findMyCollectionsList(PageRequest pr, String type);

    boolean getArticleCheck(Integer id);

    void examineArticle(Integer id, Reason reviewReason, boolean b);

    int getCreateBy(Integer id);

    Integer getStatus(Integer id);

    Integer getTop(Integer id);

    void stickArticle(Integer id,ArticleTop articleTop);

    void unStickArticle(Integer id);
//查询某人发布的文章
    PageInfo<ArticleMini> findSomeOneArticle(PageRequest pr, String q,Integer id,Integer status);
}
