package com.cdyoue.oddJobs.api.zlcx;

import com.cdyoue.oddJobs.dto.zlcx.ExpertCareer;
import com.cdyoue.oddJobs.service.sydb.CareerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by dengshaojun on 2017/09/18.
 */
@Controller
public class CareerApiController implements CareerApi {

    @Autowired
    private CareerService careerService;

    @Override
    public ResponseEntity<List<ExpertCareer>> getExpertCareer(@ApiParam(value = "专家id", required = true) @PathVariable("expertId")Integer expertId) {
        List<ExpertCareer> list = careerService.findExpertCareer(expertId);
        return new ResponseEntity<List<ExpertCareer>>(list, HttpStatus.OK);
    }

}
