package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.UserenterpriseResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.lgfc.OddJobNews;
import com.cdyoue.oddJobs.dto.scfw.InvestAndFinNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by tr on 2017/6/7.
 */
@Component
public class RollingsNewsMapper {
    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private UserenterpriseResponsitory userenterpriseResponsitory;

    public InvestAndFinNews toInvestAndFinNews(Object entity) {
        InvestAndFinNews ifn = new InvestAndFinNews();
        Object[] cells = (Object[]) entity;
        ifn.setName((String)cells[0]);
        ifn.setInfoAndDept((String)cells[1]);
        BigInteger i = (BigInteger) cells[2];
        ifn.setType(i.intValue());
        return ifn;
    }

    public OddJobNews toOddJobNews(Object entity) {
        OddJobNews ojn = new OddJobNews();
        Object[] cells = (Object[]) entity;
        Integer createBy = (Integer)cells[0];
        if (userpersonalResponsitory.findByUid(createBy) != null){
            ojn.setName(userpersonalResponsitory.findByUid(createBy).getName());
        }
        if (userenterpriseResponsitory.findByUid(createBy) != null){
           ojn.setName(userenterpriseResponsitory.findNameByUid(createBy));
       }
        ojn.setContent((String)cells[1]);
        ojn.setType(((BigInteger)cells[2]).intValue());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ojn.setTime(sdf.format((Timestamp)cells[3]));

        return ojn;
    }
}
