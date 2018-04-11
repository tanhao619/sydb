package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.Contact;
import com.cdyoue.oddJobs.entity.lgsq.PortalWordEntity;
import org.springframework.stereotype.Component;

/**
 * Created by liaoyoule on 2017/4/25.
 */
@Component
public class PortalWordMapper {
    public PortalWordEntity ContactToPortalWordEntity(Contact contact){
        PortalWordEntity pwe = new PortalWordEntity();
        pwe.setInfo(contact.getIntroduction());
        pwe.setTel(contact.getPhone());
        pwe.setWordType(3);
        return pwe;
    }

}
