package com.cdyoue.oddJobs.mapper.sydb;

import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionDetail;
import com.cdyoue.oddJobs.dto.zlcy.ApplicationDirectionMini;
import com.cdyoue.oddJobs.entity.syData.SyApplicationDirectionMessageEntity;
import com.cdyoue.oddJobs.entity.syData.SyApplicationDirectionView;
import org.springframework.stereotype.Component;

/**
 * Created by dengshaojun on 2017/10/12.
 */
@Component
public class ApplicationDirectionMapper {

    public ApplicationDirectionMini viewToMini(SyApplicationDirectionView view) {
        ApplicationDirectionMini mini = new ApplicationDirectionMini();
        mini.setId(view.getId());
        mini.setCreateEnter(view.getEnterName());
        mini.setCreateTime(view.getCreateTime().getTime());
        mini.setStatus(view.getStatus().intValue());
        return mini;
    }

    public ApplicationDirectionDetail entityToDetail(SyApplicationDirectionMessageEntity entity) {
        ApplicationDirectionDetail detail = new ApplicationDirectionDetail();
        detail.setId(entity.getId());
        detail.setContactPeople(entity.getContactPeople());
        detail.setContactNumber(entity.getContactNumber());
        return detail;
    }
}
