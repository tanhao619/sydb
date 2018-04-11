package com.cdyoue.oddJobs.spec;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;

/**
 * Created by liaoyoule on 2017/4/21.
 */
public class RestrictionsFactory {
    private Restrictions rs;

    public RestrictionsFactory(Restrictions rs) {
        this.rs = rs;
    }

    public   Predicate buildPredicate(QueryRequest query) throws ParseException {
        switch (query.getO()) {
            case EQ:
                return this.rs.eq(query);
            case NE:
                return this.rs.ne(query);
            case LIKE:
                return this.rs.like(query);
            case LT:
                return this.rs.lt(query);
            case GT:
                return this.rs.gt(query);
//                        case LTE:
//                            return builder.lte(p);
//                        case GTE:
//                            return builder.GTE(p);
            default:
                return null;
        }
    }
}
