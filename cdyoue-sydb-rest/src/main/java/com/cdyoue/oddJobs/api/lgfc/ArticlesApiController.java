package com.cdyoue.oddJobs.api.lgfc;

import com.cdyoue.oddJobs.constant.TrackCategories;
import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.common.message.ArticlesMessage;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.lgfc.ArticleMini;
import com.cdyoue.oddJobs.dto.lgfc.ArticleRequest;
import com.cdyoue.oddJobs.dto.lgfc.ArticleTop;
import com.cdyoue.oddJobs.en.LoginTypeEnum;
import com.cdyoue.oddJobs.en.MessageEventTypeEnum;
import com.cdyoue.oddJobs.en.MessageMsgTypeEnum;
import com.cdyoue.oddJobs.event.EventPiwikTracking;
import com.cdyoue.oddJobs.service.ArticleService;
import com.cdyoue.oddJobs.service.UserService;
import com.cdyoue.oddJobs.utils.MessageUtils;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.common.SortUtils;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

@Controller
public class ArticlesApiController implements ArticlesApi {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventPiwikTracking eventPiwikTracking;
    //收藏文章
    public ResponseEntity<HttpMessage> collectArticle(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        //如果该用户已收藏该文章，返回一个错误请求(403),如果文章不存在，返回未查找到(404)。
        if (MessageUtils.isMessageExist(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle)) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTREP, HttpStatus.FORBIDDEN);
        } else if (articleService.getArticleCheck(id) != true) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        } else {
            articleService.collectArticle(id);
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECT, HttpStatus.OK);
        }

    }

    //创建新文章
    public ResponseEntity<HttpMessage> createArticle(@ApiParam(value = "创建的文章实体", required = true) @RequestBody ArticleRequest article) {
        ArticleRequest article1DTO = new ArticleRequest();
        BeanUtils.copyProperties(article, article1DTO);
        articleService.createArticle(article1DTO);
        UserMine um = SecurityUtils.getCurrentUserLogin();
        if(um.getLoginTypeEnum().equals(LoginTypeEnum.APP)){
            eventPiwikTracking.doTracking(TrackCategories.PUBLISH_ART,um.getName());
        }
        return new ResponseEntity<HttpMessage>(HttpStatus.CREATED);

    }

    //删除文章（单一删除）
    public ResponseEntity<HttpMessage> deleteArticle(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        //如果文章不存在，返回未查找到（404）,如果输入ID不为文章类型的ID，则报错操作。
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);//用户没登录
        }
        Integer uId = userMine.getId();
        Integer userRole=userMine.getRole();
        Integer status = 1;
        if (articleService.getArticleCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }else if(userRole==2){//超级管理员
            articleService.deleteArticle(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else if (uId != articleService.getCreateBy(id)&&userRole!=2) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUNORHTHRE, HttpStatus.FORBIDDEN);
        }
        /*else if (status == articleService.getStatus(id)) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUNORHTH, HttpStatus.FORBIDDEN);
        }*/
        else {
            articleService.deleteArticle(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);

        }

    }

    //通过ID找寻一个文章详细信息,寻找不到报错（404）
    public ResponseEntity<Article> getArticleById(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        if (articleService.getArticleCheck(id)) {
            Article articleDTO = new Article();
            articleDTO = articleService.getArticleById(id);
            return new ResponseEntity<Article>(articleDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<Article>(HttpStatus.NOT_FOUND);
        }


    }

    //查询所有文章的分页列表
    public ResponseEntity<PageInfo> getArticles(@NotNull @ApiParam(value = "获取所有文章标识 例如：/article?type=excellent", defaultValue = "all", required = true, allowableValues = "all,excellent") @RequestParam(value = "type", required = true) String type,
                                                @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                @ApiParam(value = "排序字段和方式 例如：/article?sort=publishTime", defaultValue = "-createTime", allowableValues = "id,-createTime,updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                @ApiParam(value = "用户Id") @RequestParam(value = "userid", required = false) Integer userid

    ) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<Article> pageInfo = articleService.findList(pr, type, q,userid);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    //获取我的文章分页列表
    public ResponseEntity<PageInfo> getMyArticles(@ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                  @ApiParam(value = "排序字段和方式 例如：/articles/my?sort=excellent", defaultValue = "-updateTime", allowableValues = "id,createTime,-updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                  @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<Article> pageInfo = articleService.findMyList(pr, q);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageInfo> getSomeOneArticles(@ApiParam(value = "用户id") @PathVariable(value = "userId", required = true) Integer userId,
                                                       @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                       @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                       @ApiParam(value = "排序字段和方式 例如：/articles/my?sort=excellent", allowableValues = "id,createTime,updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                       @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q) {
        //查询该用户是否存在
        if(userService.findOne(userId)!=null){
            Sort order = SortUtils.assembleSort(sort);
            PageRequest pr = new PageRequest(pageNumber, pageSize, order);
            Integer status=1;//审核成功的才能查看
            PageInfo<ArticleMini> pageInfo = articleService.findSomeOneArticle(pr,q,userId,status);
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
        }else {
            return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(HttpStatus.BAD_REQUEST);//该用户不存在，错误的请求
        }
    }

    //查询我收藏的文章
    public ResponseEntity<PageInfo> getMyCollectionsArticles(@NotNull @ApiParam(value = "获取所有文章标识 例如：/article?type=excellent", defaultValue = "all", required = true, allowableValues = "all,excellent") @RequestParam(value = "type", required = true) String type,
                                                             @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                             @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                             @ApiParam(value = "排序字段和方式 例如：/article?sort=publishTime", defaultValue = "-createTime", allowableValues = "id, -createTime") @RequestParam(value = "sort", required = false) String sort) {
        Sort order = SortUtils.assembleSort(sort);
        PageRequest pr = new PageRequest(pageNumber, pageSize, order);
        PageInfo<Article> pageInfo = articleService.findMyCollectionsList(pr, type);
        return new ResponseEntity<com.cdyoue.spring.page.PageInfo>(pageInfo, HttpStatus.OK);
    }

    //取消收藏文章
    public ResponseEntity<HttpMessage> uncollectArticle(@ApiParam(value = "", required = true) @PathVariable("id") Integer id) {
        if (MessageUtils.isMessageExist(id, MessageEventTypeEnum.COLLECTION, MessageMsgTypeEnum.CollectionArticle)) {
            articleService.uncollectArticle(id);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }

    }

    //修改文章
    public ResponseEntity<HttpMessage> updateArticle(@ApiParam(value = "", required = true) @PathVariable("id") Integer id,
                                                     @ApiParam(value = "文章对象", required = true) @RequestBody ArticleRequest article) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        Integer userMineRole=userMine.getRole();
        Integer uId = SecurityUtils.getCurrentUserLogin().getId();
        Integer status = 1;
        if (articleService.getArticleCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }else if(userMineRole==2){//管理员
            articleService.updateArticle(id, article);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        } else if (uId != articleService.getCreateBy(id)) {
//            httpMessage.message("不能修改别人的文章");
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUPDFAIL403, HttpStatus.FORBIDDEN);
        }
        /*else if (status == articleService.getStatus(id)) {
//           httpMessage.message("发布文章不能修改");
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUPDFAIL400, HttpStatus.FORBIDDEN);
        }*/
        else {
            articleService.updateArticle(id, article);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }

    }

    @Override//审核文章通过
    public ResponseEntity<HttpMessage> approve(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                               @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reviewReason) {
//        Integer status = 0;
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        if(userMine.getRole()!=2){//管理员才能审核
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        if (articleService.getArticleCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }
//        else if (status == articleService.getStatus(id)) {
//            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUNORHTH2, HttpStatus.FORBIDDEN);
//        }
        else {
            articleService.examineArticle(id,reviewReason, true);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }

    }

    @Override//审核文章未通过
    public ResponseEntity<HttpMessage> reject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                              @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reviewReason) {
//        Integer status = 0;
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);
        }
        if(userMine.getRole()!=2){//管理员才能审核
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        if (!articleService.getArticleCheck(id)) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }
//        else if (status == articleService.getStatus(id)) {
//            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUNORHTH2, HttpStatus.FORBIDDEN);
//        }
        else {
            articleService.examineArticle(id, reviewReason,false);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity<HttpMessage> stickArticle(@ApiParam(value = "文章ID",required=true ) @PathVariable("id") Integer id,
                                                    @ApiParam(value = "精选文章实体",required = true)@RequestBody ArticleTop articleTop) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);//没登录
        }
        if(userMine.getRole()!=2){
          //只有管理员才能操作是否设置为精选
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        Integer status = 1;
        if (articleService.getArticleCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        } else if (status != articleService.getStatus(id)) {
            //审核不通过不能设置为精选
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLETOPNOTPERMITTED,HttpStatus.BAD_REQUEST);
        }else if(articleService.getTop(id)==1){
           //文章已经设置为精选则不能再设置
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLETOP,HttpStatus.BAD_REQUEST);
        } else {
            articleService.stickArticle(id, articleTop);
            return new ResponseEntity<HttpMessage>(HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<HttpMessage> stickArticle(@ApiParam(value = "文章ID",required=true ) @PathVariable("id") Integer id) {
        UserMine userMine=SecurityUtils.getCurrentUserLogin();
        if(userMine==null){
            return new ResponseEntity<HttpMessage>(HttpStatus.UNAUTHORIZED);//没登录
        }
        if(userMine.getRole()!=2){
            //只有管理员才能操作是否设置为精选
            return new ResponseEntity<HttpMessage>(HttpStatus.FORBIDDEN);
        }
        if (articleService.getArticleCheck(id) == false) {
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLESCOLLECTNOTFOUND, HttpStatus.NOT_FOUND);
        }
        if(articleService.getTop(id)==0){
          //文章不是精选的，就不能再取消精选
            return new ResponseEntity<HttpMessage>(ArticlesMessage.ARTICLEUNTOP,HttpStatus.BAD_REQUEST);
        }
        articleService.unStickArticle(id);
        return new ResponseEntity<HttpMessage>(HttpStatus.OK);
    }
}
