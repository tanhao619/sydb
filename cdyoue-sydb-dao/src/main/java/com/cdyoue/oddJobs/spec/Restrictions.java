package com.cdyoue.oddJobs.spec;

import com.cdyoue.oddJobs.en.DataTypeEnum;

import javax.persistence.criteria.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liaoyoule on 2017/4/21.
 */
public class Restrictions {

    private Root root;
    private CriteriaBuilder criteriaBuilder;

    public Restrictions(Root root,CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
    }


    /**
     * 等于
     * @param query
     * @return
     */
    public  Predicate eq(QueryRequest  query) {
        String f = query.getF();

        if (f.contains(".")) {
            String attr  = f.split("[.]")[0];
            String attrField  = f.split("[.]")[1];
            try {
                Class<?> clazz = root.getJavaType();
                Field declaredField = clazz.getDeclaredField(attr);
                declaredField.setAccessible(true);
                Class<?> attrClazz = declaredField.getType();
                Join join = root.join(root.getModel().getSingularAttribute(attr,attrClazz), JoinType.LEFT);
                return criteriaBuilder.equal(join.get(attrField).as(Integer.class), query.getV());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        return this.criteriaBuilder.equal(root.get(query.getF()).as(DataTypeEnum.getTypeClass(query.getT())), query.getV());
    }

    /**
     * 不等于
     * @param query
     * @return
     */
    public  Predicate ne(QueryRequest  query) {
        return this.criteriaBuilder.notEqual(root.get(query.getF()).as(DataTypeEnum.getTypeClass(query.getT())), query.getV());
    }

    /**
     * 模糊匹配
     * @param query
     * @return
     */
    public  Predicate like(QueryRequest  query) {

         return this.criteriaBuilder.like(root.get(query.getF()).as(DataTypeEnum.getTypeClass(query.getT())), "%".concat(query.getV()).concat("%"));
    }


    /**
     * 大于
     * @param query
     * @return
     */
    public  Predicate gt(QueryRequest  query) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        return this.criteriaBuilder.greaterThan(root.get(query.getF()).as(DataTypeEnum.getTypeClass(query.getT())), sdf.parse(query.getV()));

    }

    /**
     * 小于
     * @param query
     * @return
     */

    public  Predicate lt(QueryRequest  query) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return this.criteriaBuilder.lessThan(root.get(query.getF()).as(DataTypeEnum.getTypeClass(query.getT())),sdf.parse(query.getV()));

    }
//
//    /**
//     * 大于等于
//     * @param fieldName
//     * @param value
//     * @param ignoreNull
//     * @return
//     */
//    public static SimpleExpression lte(String fieldName, String value, boolean ignoreNull) {
//        if(StringUtils.isEmpty(value))return null;
//        return new SimpleExpression (fieldName, value, Criterion.Operator.GTE);
//    }
//
//    /**
//     * 小于等于
//     * @param fieldName
//     * @param value
//     * @param ignoreNull
//     * @return
//     */
//    public static SimpleExpression gte(String fieldName, String value, boolean ignoreNull) {
//        if(StringUtils.isEmpty(value))return null;
//        return new SimpleExpression (fieldName, value, Criterion.Operator.LTE);
//    }
//
//    /**
//     * 并且
//     * @param criterions
//     * @return
//     */
//    public static LogicalExpression and(Criterion... criterions){
//        return new LogicalExpression(criterions, Criterion.Operator.AND);
//    }
//    /**
//     * 或者
//     * @param criterions
//     * @return
//     */
//    public static LogicalExpression or(Criterion... criterions){
//        return new LogicalExpression(criterions, Criterion.Operator.OR);
//    }
//    /**
//     * 包含于
//     * @param fieldName
//     * @param value
//     * @return
//     */
//    @SuppressWarnings("rawtypes")
//    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
//        if(ignoreNull&&(value==null||value.isEmpty())){
//            return null;
//        }
//        SimpleExpression[] ses = new SimpleExpression[value.size()];
//        int i=0;
//        for(Object obj : value){
//            ses[i]=new SimpleExpression(fieldName,obj, Criterion.Operator.EQ);
//            i++;
//        }
//        return new LogicalExpression(ses, Criterion.Operator.OR);
//    }
}
