package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dto.oauth.Menu;
import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/5/8.
 */
@Component
public class MenuMapper {
    public Menu menuEntityToMenu(MenuEntity me){
        Menu m = new Menu();
        m.setName(me.getName());
        m.setId(me.getId());
        m.setUiSref(me.getUiSref());
        return m;
    }

}
