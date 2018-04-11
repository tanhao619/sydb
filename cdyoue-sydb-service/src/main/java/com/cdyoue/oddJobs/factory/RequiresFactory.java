package com.cdyoue.oddJobs.factory;

import com.cdyoue.oddJobs.entity.lgsq.OutsourcingProjectEntity;
import com.cdyoue.oddJobs.factory.impl.AccRequirement;
import com.cdyoue.oddJobs.factory.impl.PubRequirement;
import com.cdyoue.oddJobs.factory.impl.RecRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Component
public class RequiresFactory {
    @Autowired
    AccRequirement accRequirement;
    @Autowired
    PubRequirement pubRequirement;
    @Autowired
    RecRequirement recRequirement;
    public Page<OutsourcingProjectEntity> getMyReqiures(String type, String query, PageRequest pr){
        switch (type.toLowerCase()){
            case "pub":return pubRequirement.getMyRequires(pr,query);
            case "acc":return accRequirement.getMyRequires(pr, query) ;
            case "rec":return recRequirement.getMyRequires(pr, query);
        }
        return null;
    }

    public Boolean isBehaviorOf(String type, Integer id) {
        switch (type.toLowerCase()){
            case "rec":return recRequirement.isBehaviorOf(id);
            case "acc":return accRequirement.isBehaviorOf(id) ;
            default:
                return null;
        }
    }
}
