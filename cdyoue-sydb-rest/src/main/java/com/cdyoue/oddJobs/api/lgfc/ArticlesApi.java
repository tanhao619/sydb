package com.cdyoue.oddJobs.api.lgfc;

import com.cdyoue.oddJobs.dto.Reason;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import com.cdyoue.oddJobs.dto.lgfc.Article;
import com.cdyoue.oddJobs.dto.lgfc.ArticleMini;
import com.cdyoue.oddJobs.dto.lgfc.ArticleRequest;
import com.cdyoue.oddJobs.dto.lgfc.ArticleTop;
import com.cdyoue.spring.page.PageInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-28T13:17:18.617Z")

@Api(value = "articles", description = "the articles API")
public interface ArticlesApi {

    @ApiOperation(value = "收藏文章(完成)", notes = "根据id收藏文章,如果该用户已收藏该文章，返回一个错误请求(400),如果文章不存在，返回未查找到(404)。", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/collect",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> collectArticle(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "创建文章(完成)", notes = "创建的文章实体", response = ArticleRequest.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "创建成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/articles",
        produces = { "application/json" }, 
       
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> createArticle(@ApiParam(value = "创建的文章实体" ,required=true ) @RequestBody ArticleRequest article);


    @ApiOperation(value = "删除文章（完成）", notes = "根据id删除文章,如果文章不存在，返回未查找到（404）,如果输入ID不为文章类型的ID，则报错操作。", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}",
        produces = { "application/json" }, 
       
        method = RequestMethod.DELETE)
    ResponseEntity<HttpMessage> deleteArticle(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取文章详情（完成）", notes = "根据文章id获取文章详情，通过ID找寻一个文章详细信息,寻找不到报错（404）", response = Article.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Article.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}",
        produces = { "application/json" }, 
       
        method = RequestMethod.GET)
    ResponseEntity<Article> getArticleById(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "获取文章列表(完成)", notes = "获取所有文章列表，排序方式不可为空否则会报错。关键字为对文章标题的模糊查询", response = Article.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Article.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getArticles(@NotNull @ApiParam(value = "获取所有文章标识 例如：/article?type=excellent", required = true, allowableValues = "all,excellent") @RequestParam(value = "type", required = true) String type,
                                                  @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                                  @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                                  @ApiParam(value = "排序字段和方式 例如：/article?sort=publishTime", allowableValues = "id,createTime,updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
                                                  @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q,
                                                  @ApiParam(value = "用户Id") @RequestParam(value = "userid", required = false) Integer userid
                                        );


    @ApiOperation(value = "获取我的文章列表(完成)", notes = "可以获取我写的文章,关键字对标题进行模糊查询。", response = Article.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "操作成功", response = Article.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/my",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyArticles( @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "排序字段和方式 例如：/articles/my?sort=excellent", allowableValues = "id,createTime,updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
         @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);

    @ApiOperation(value = "获取某个用户的文章列表(完成)", notes = "可以获取某个用户写的文章,关键字对标题进行模糊查询。", response = ArticleMini.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "操作成功", response = ArticleMini.class),
            @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{userId}/someone",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<PageInfo> getSomeOneArticles(
                                            @ApiParam(value = "用户id") @PathVariable(value = "userId", required = true) Integer userId,
                                            @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
                                            @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
                                            @ApiParam(value = "排序字段和方式 例如：/articles/my?sort=excellent", allowableValues = "id,createTime,updateTime,publishTime") @RequestParam(value = "sort", required = false) String sort,
                                            @ApiParam(value = "搜索关键字") @RequestParam(value = "q", required = false) String q);



    @ApiOperation(value = "获取我收藏的文章列表(完成)", notes = "获取我收藏的文章列表", response = Article.class, responseContainer = "List", tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Article.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/my/collections",
        produces = { "application/json" }, 
       
        method = RequestMethod.GET)
    ResponseEntity<PageInfo> getMyCollectionsArticles( @NotNull @ApiParam(value = "获取所有文章标识 例如：/article?type=excellent", required = true, allowableValues = "all,excellent") @RequestParam(value = "type", required = true) String type,
         @ApiParam(value = "分页大小，默认10", defaultValue = "10") @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize,
         @ApiParam(value = "页码。默认第一页", defaultValue = "0") @RequestParam(value = "pageNumber", required = false, defaultValue="0") Integer pageNumber,
         @ApiParam(value = "排序字段和方式 例如：/article?sort=publishTime", allowableValues = "id,publishTime") @RequestParam(value = "sort", required = false) String sort);


    @ApiOperation(value = "取消收藏文章（完成）", notes = "根据id取消收藏文章", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "取消收藏成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误.", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/uncollect",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<HttpMessage> uncollectArticle(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "编辑文章(完成)", notes = "编辑文章", response = ArticleRequest.class, tags={ "articles", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
        @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "文章没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}",
        produces = { "application/json" }, 
       
        method = RequestMethod.PUT)
    ResponseEntity<HttpMessage> updateArticle(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
        @ApiParam(value = "文章对象" ,required=true ) @RequestBody ArticleRequest article);

    @ApiOperation(value = "审核通过文章发布（完成）", notes = "根据id通过文章的审核,只有管理员可操作", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/approve",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> approve(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                        @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reviewReason);

    @ApiOperation(value = "拒绝通过文章发布（完成）", notes = "根据id通过文章的审核,只有管理员可操作", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/reject",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> reject(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id,
                                       @ApiParam(value = "审核原因" ,required=true ) @RequestBody Reason reviewReason);


    @ApiOperation(value = "将文章设置为精选(完成)", notes = "根据id将文章设为精选,只有管理员可操作", response = ArticleTop.class, tags={ "articles", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/stick",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> stickArticle(@ApiParam(value = "文章ID",required=true ) @PathVariable("id") Integer id,
                                             @ApiParam(value = "精选文章实体",required = true)@RequestBody ArticleTop articleTop);


    @ApiOperation(value = "取消设置为精选（新添加的接口）(完成)", notes = "根据id将文章取消精选,只有管理员可操作", response = HttpMessage.class, tags={ "articles", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "操作成功", response = HttpMessage.class),
            @ApiResponse(code = 400, message = "请求错误", response = HttpMessage.class),
            @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
            @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
            @ApiResponse(code = 404, message = "文章没找到", response = HttpMessage.class),
            @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/articles/{id}/unStick",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<HttpMessage> stickArticle(@ApiParam(value = "文章ID",required=true ) @PathVariable("id") Integer id);
}
