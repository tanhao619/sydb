package com.cdyoue.oddJobs.api.common;


import com.cdyoue.oddJobs.dto.common.Category;
import com.cdyoue.oddJobs.dto.common.HttpMessage;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-04-20T08:36:42.123Z")

@Api(value = "categories", description = "the categories API")
public interface CategoriesApi {

    @ApiOperation(value = "获取类型列表(完成)", notes = "获取类型列表", response = Category.class, responseContainer = "List", tags={ "category", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "操作成功", response = Category.class),
        @ApiResponse(code = 400, message = "请求错误.", response = HttpMessage.class),
        @ApiResponse(code = 401, message = "用户没有登录", response = HttpMessage.class),
        @ApiResponse(code = 403, message = "用户没有权限", response = HttpMessage.class),
        @ApiResponse(code = 404, message = "xxx 没有找到", response = HttpMessage.class),
        @ApiResponse(code = 500, message = "系统内部错误", response = HttpMessage.class) })
    @RequestMapping(value = "/categories",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Category>> getCategoriesByType(@ApiParam(value = "类别类型 1:领域 2:外包分类 3职能类别", allowableValues = "1,2,3") @RequestParam(value = "type", required = false) Integer type,
                                                       @ApiParam(value = "id") @RequestParam(value = "id", required = false) Integer id
    );

}
