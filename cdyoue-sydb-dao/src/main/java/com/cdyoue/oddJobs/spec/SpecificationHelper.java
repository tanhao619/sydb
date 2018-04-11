package com.cdyoue.oddJobs.spec;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdyoue on 2017/2/24.
 */
@Component
public class SpecificationHelper<T> {
    public Specification<T> getSpecifica(Class<T> clazz, List<QueryRequest> queryRequest) {
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicateAndList = new ArrayList();
                List<Predicate> predicateOrList = new ArrayList();

                Restrictions builder = new Restrictions(root,cb);
                RestrictionsFactory rf = new RestrictionsFactory(builder);
                queryRequest.forEach(p -> {
                    if(StringUtils.isBlank(p.getV()) || "null".equalsIgnoreCase(p.getV())){
                        return;
                    }
                    Predicate pre = null;
                    try {
                        pre = rf.buildPredicate(p);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    (p.getAndOr() ? predicateAndList : predicateOrList).add(pre);

                });

                Predicate[] eP = new Predicate[predicateAndList.size()];
                Predicate[] op = new Predicate[predicateOrList.size()];


                if(op.length == 0){
                    query.where(cb.and(predicateAndList.toArray(eP)));
                }else {
                    query.where(cb.and(predicateAndList.toArray(eP)),cb.or(predicateOrList.toArray(op)));
                }
                return query.getRestriction();
            }
        };
        return spec;
    }


}
