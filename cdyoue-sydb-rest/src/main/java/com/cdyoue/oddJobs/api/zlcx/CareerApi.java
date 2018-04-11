package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.zlcx.ExpertCareer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "sy-career", description = "职业经历")
public interface CareerApi {

    @ApiOperation(value = "获取专家的职业经历列表", notes = "获取专家的职业经历列表", response = ExpertCareer.class, responseContainer = "List", tags={ "sy-expert", })
    @RequestMapping(value = "/expert/{expertId}/career",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<ExpertCareer>> getExpertCareer(@ApiParam(value = "专家id", required = true) @PathVariable("expertId") Integer expertId);
}
