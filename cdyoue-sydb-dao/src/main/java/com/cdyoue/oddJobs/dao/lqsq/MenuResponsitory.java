package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dengshaojun on 2017/5/22.
 */
public interface MenuResponsitory extends JpaCustomResponsitory<MenuEntity,Integer>{
    @Query("select me from MenuEntity  me where me.topMenu is not null")
    Page<MenuEntity> findMenus(Pageable pr);
}
