package com.cdyoue.oddJobs.service.support;

import com.cdyoue.oddJobs.constant.DataTypeConstant;
import com.cdyoue.oddJobs.entity.syData.EquipmentApplyEntity;
import com.cdyoue.oddJobs.entity.syData.SyDeclarationProjectMessageEntity;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.spec.Operator;
import com.cdyoue.oddJobs.spec.QueryRequest;
import com.cdyoue.oddJobs.spec.SpecificationHelper;
import com.cdyoue.oddJobs.utils.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by liaoyoule on 2017/4/21.
 */
public abstract class ServiceSupport<T> {
    public abstract Class getJpaRepositoryClazz();

    @Autowired
    private SpecificationHelper specificationHelper;

    private JpaSpecificationExecutor getJpaExecutorRepository() {
        return (JpaSpecificationExecutor) SpringContextUtil.getBean(getJpaRepositoryClazz());
    }


    private JpaRepository getJpaRepository() {
        return (JpaRepository) SpringContextUtil.getBean(getJpaRepositoryClazz());
    }


    public Page<T> findByName(String q, Pageable requestPage) {
        Page<T> page = this.findByNameAndCategoryId(q, null, requestPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }


    public Page<T> findByNameAndCategoryId(String q, Integer categoryId, Pageable requestPage) {
        Page<T> page = this.findByNameAndCategoryIdAndReviewStatus(q, categoryId, null, requestPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }


    public Page<T> findByNameAndCategoryIdAndReviewStatus(String q, Integer categoryId, Integer reviewStatus, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF("name");
        qe.setO(Operator.LIKE);
        qe.setV(q);
        queryRequest.add(qe);

        if (Optional.ofNullable(categoryId).isPresent()) {
            QueryRequest qeEc = new QueryRequest();
            qeEc.setF("outsourcingProjectTypeEntity.id");
            qeEc.setO(Operator.EQ);
            qeEc.setV(categoryId + "");
            queryRequest.add(qeEc);
        }

        if (Optional.ofNullable(reviewStatus).isPresent()) {
            QueryRequest qeEr = new QueryRequest();
            qeEr.setF("reviewStatus");
            qeEr.setO(Operator.EQ);
            qeEr.setV(reviewStatus + "");
            qeEr.setT(DataTypeConstant.INTEGER);
            queryRequest.add(qeEr);
        }
        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }


    public Page<T> findByStrLike(String qK, String qV, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.LIKE);
        if(StringUtils.isNotBlank(qV)){
            qe.setV(qV);
        }
        queryRequest.add(qe);
        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByStrAndTopLike(String qK, String qV, String qK2, String qV2, String qK3, String qV3,String qK4,String qV4,String qK5,String qV5, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.EQ);
        qe.setV(qV);
        qe.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qe);

        if (qV2.equals("excellent")) {
            QueryRequest qe2 = new QueryRequest();
            qe2.setF(qK2);
            qe2.setO(Operator.EQ);
            qe2.setV(1 + "");
            queryRequest.add(qe2);
        }

        if (Optional.ofNullable(qK3).isPresent()) {
            QueryRequest qe3 = new QueryRequest();
            qe3.setF(qK3);
            qe3.setO(Operator.LIKE);
            qe3.setV(qV3);
            queryRequest.add(qe3);
        }

        QueryRequest qe4 = new QueryRequest();
        qe4.setF(qK4);
        qe4.setO(Operator.LIKE);
        qe4.setV(qV4);
        queryRequest.add(qe4);


        QueryRequest qe5 = new QueryRequest();

        qe5.setF(qK5);
        qe5.setO(Operator.EQ);
        qe5.setV(qV5);
        qe5.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qe5);

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByStrAndUidLike(String qK, String qV, String qK2, String qV2, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.LIKE);
        qe.setV(qV);
        queryRequest.add(qe);

        QueryRequest qe2 = new QueryRequest();
        qe2.setF(qK2);
        qe2.setO(Operator.LIKE);
        qe2.setV(qV2);
        queryRequest.add(qe2);

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByStrAndUidLike(String qK, String qV, String qK2, String qV2, String qK3, String qV3, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.LIKE);
        qe.setV(qV);
        queryRequest.add(qe);

        QueryRequest qe2 = new QueryRequest();
        qe2.setF(qK2);
        qe2.setO(Operator.LIKE);
        qe2.setV(qV2);
        queryRequest.add(qe2);

        QueryRequest qe3 = new QueryRequest();
        qe2.setF(qK3);
        qe2.setO(Operator.LIKE);
        qe2.setV(qV3);
        queryRequest.add(qe3);

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByStrLikeUserId(Integer userId, String qK, String qV, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.LIKE);
        qe.setV(qV);
        queryRequest.add(qe);

        if (Optional.ofNullable(userId).isPresent()) {
            QueryRequest qeEc = new QueryRequest();
            qeEc.setF("userId");
            qeEc.setO(Operator.EQ);
            qeEc.setV(userId + "");
            queryRequest.add(qeEc);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByOneLike(String qK, String qV, Pageable rqPage) {
        Page<T> page = this.findByTwoLike(qK, qV, null, null, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> getMyFinancingPageList(String qK, String qV, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.EQ);
        qe.setV(qV);
        queryRequest.add(qe);

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        return page;
    }


    public Page<T> findByTwoLike(String qK, String qV, String qK2, String qV2, Pageable rqPage) {
        Page<T> page = this.findByThreeLike(qK, qV, qK2, qV2, null, null, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByThreeLike(String qK, String qV, String qK2, String qV2, String qK3, String qV3, Pageable rqPage) {
        Page<T> page = this.findByFourLike(qK, qV, qK2, qV2, qK3, qV3, null, null, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    public Page<T> findByFourLike(String qK, String qV, String qK2, String qV2, String qK3, String qV3, String qK4, String qV4, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.LIKE);
        qe.setV(qV);
        queryRequest.add(qe);

        QueryRequest qe2 = new QueryRequest();
        qe2.setF(qK2);
        qe2.setO(Operator.LIKE);
        qe2.setV(qV2);
        queryRequest.add(qe2);

        QueryRequest qe3 = new QueryRequest();
        qe3.setF(qK3);
        qe3.setO(Operator.LIKE);
        qe3.setV(qV3);
        queryRequest.add(qe3);

        QueryRequest qe4 = new QueryRequest();
        qe4.setF(qK4);
        qe4.setO(Operator.LIKE);
        qe4.setV(qV4);
        queryRequest.add(qe4);

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        return page;
    }

    public Page<T> findByCreateQE(String qK, String qV, String qK2, Integer qV2, String qK3, String qV3,String qK4,Integer qV4, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.EQ);
        qe.setV(qV);
        qe.setT(DataTypeConstant.INTEGER);
        queryRequest.add(qe);


        QueryRequest qe2 = new QueryRequest();
        qe2.setF(qK2);
        qe2.setO(Operator.EQ);
        qe2.setV(qV2+"");
        queryRequest.add(qe2);

        QueryRequest qe3 = new QueryRequest();
        qe3.setF(qK3);
        qe3.setO(Operator.LIKE);
        qe3.setV(qV3);
        queryRequest.add(qe3);

        if(qV4!=null){
            QueryRequest qe4 = new QueryRequest();
            qe4.setF(qK4);
            qe4.setO(Operator.EQ);
            qe4.setV(qV4+"");
            queryRequest.add(qe4);
        }


        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }
//查询空间
    public Page<T> findSpace(String qK, Integer qV, String qK2, String qV2, String qK3, Integer qV3, String qK4, Integer qV4,String qK5,Integer qV5,String qK6,Integer qV6,Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        QueryRequest qe = new QueryRequest();
        qe.setF(qK);
        qe.setO(Operator.EQ);
        qe.setV(qV+"");//空间类型
        queryRequest.add(qe);

        QueryRequest qe2 = new QueryRequest();
        qe2.setF(qK2);
        qe2.setO(Operator.LIKE);
        qe2.setV(qV2);//关键字搜索
        queryRequest.add(qe2);

        QueryRequest qe3 = new QueryRequest();
        qe3.setF(qK3);
        qe3.setO(Operator.EQ);
        qe3.setV(qV3+"");//一级地理位置
        queryRequest.add(qe3);

        QueryRequest qe4 = new QueryRequest();
        qe4.setF(qK4);
        qe4.setO(Operator.EQ);
        qe4.setV(qV4+"");//二级地理位置
        queryRequest.add(qe4);

        if(qV5!=null){
            QueryRequest qe5 = new QueryRequest();
            qe5.setF(qK5);
            qe5.setO(Operator.EQ);
            qe5.setV(qV5+"");//审核状态
            queryRequest.add(qe5);
        }
        if(qV6!=null){
            QueryRequest qe6 = new QueryRequest();
            qe6.setF(qK6);
            qe6.setO(Operator.EQ);
            qe6.setV(qV6+"");//创建人ID
            queryRequest.add(qe6);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }

    //=============================================沈阳分页==========================================//
    public Page<T> getEquipmentPageList(String qK, String qV, String qK2, String qV2, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        if(qV != null){
            QueryRequest qe = new QueryRequest();
            qe.setF(qK);
            qe.setO(Operator.LIKE);
            qe.setV(qV);
            queryRequest.add(qe);
        }

        if(qV2 != null){
            QueryRequest qe2 = new QueryRequest();
            qe2.setF(qK2);
            qe2.setO(Operator.LIKE);
            qe2.setV(qV2);
            queryRequest.add(qe2);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }


    public Page<EquipmentApplyEntity> getEquipmentApplyPageList(String name, String q, Pageable pr) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        if(q != null){
            QueryRequest qe = new QueryRequest();
            qe.setF(name);
            qe.setO(Operator.LIKE);
            qe.setV(q);
            queryRequest.add(qe);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<EquipmentApplyEntity> page = getJpaExecutorRepository().findAll(specifica, pr);
        if (page.getContent().size() == 0) {
            throw new NotFoundEntityException("数据不存在");
        }
        return page;
    }
//查询企业申报项目信息
    public Page<T> getDeclarationProjectPage(String qK, Integer qV, Pageable rqPage) {
        List<QueryRequest> queryRequest = new ArrayList<>();
        if(qV != null){
            QueryRequest qe = new QueryRequest();
            qe.setF(qK);
            qe.setO(Operator.EQ);
            qe.setV(qV+"");
            queryRequest.add(qe);
        }

        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
        return page;
    }

    //查询项目申报列表
//    public Page<T> getProjectPage(String qK, String qV,String qK2,Long qV2, Pageable rqPage) {
//        List<QueryRequest> queryRequest = new ArrayList<>();
//        if(qV != null){
//            QueryRequest qe = new QueryRequest();
//            qe.setF(qK);
//            qe.setO(Operator.LIKE);
//            qe.setV(qV);
//            queryRequest.add(qe);
//        }
//        if(qV2 != null){
//            QueryRequest qe2 = new QueryRequest();
//            qe2.setF(qK2);
//            qe2.setO(Operator.GTE);
//            qe2.setV(qV2+"");
//            queryRequest.add(qe2);
//        }
//
//        Class entityClazz = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//
//        Specification specifica = specificationHelper.getSpecifica(entityClazz, queryRequest);
//        Page<T> page = getJpaExecutorRepository().findAll(specifica, rqPage);
//        return page;
//    }
}
