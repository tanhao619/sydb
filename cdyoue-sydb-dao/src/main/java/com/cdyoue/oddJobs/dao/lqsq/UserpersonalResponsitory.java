package com.cdyoue.oddJobs.dao.lqsq;

import com.cdyoue.oddJobs.entity.lgsq.PortalMessageEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Transactional
public interface UserpersonalResponsitory extends JpaCustomResponsitory<UserpersonalEntity,Integer>{
    @Modifying
    @Query("delete from PortalMessageEntity m where  m.opera.id = ?1 and m.eventId = ?2 and  m.eventType = 3 and m.msgType = 1")
    void unFollowTalent(Integer operaId, Integer eventId);
    @Query("select u FROM UserpersonalEntity u WHERE u.userId=?1")
    UserpersonalEntity findOneByUid(Integer uId);

    @Query("select u.id FROM UserpersonalEntity u WHERE u.userId=?1")
    Integer findIdByUid(int userId);

    @Query("select u from UserpersonalEntity u where u.userId in (select m.eventId from PortalMessageEntity m where m.eventType=3 and m.msgType =1 and m.opera.id=?1)")
    List<UserpersonalEntity> getMyTalents(int operaId);

    @Query("select u,up from UserEntity u ,UserpersonalEntity up where u.id = up.userId and up.userId = ?1")
    UserEntity getTalentBaseInfo(int userId);

    @Query("select u FROM UserpersonalEntity u WHERE u.userId=?1")
    UserpersonalEntity findByUid(Integer uId);

    @Query("select t from PortalTechnologyEntity t where t.userId = ?1 and t.type = ?2")
    List<PortalTechnologyEntity> getTalentExperience(Integer id, Integer type);

    @Query("select up from UserpersonalEntity up where up.userId in ?1")
    List<UserpersonalEntity> getUsers(List<Integer> userIds);

    @Query("select up from UserpersonalEntity up,UserEntity u,FunctioncategoryEntity f,PortalRealNameInfoEntity rmi where up.userId = u.id and u.expectFunctionCategory = f.id and rmi.userId = up.userId and f.id = ?1 and rmi.applyType = ?2")
    Page<UserpersonalEntity> getTalentsNameNull(Integer industry, Integer reviewStatus, Pageable rqPage);

    @Query("select up from UserpersonalEntity up,UserEntity u,FunctioncategoryEntity f,PortalRealNameInfoEntity rmi where up.userId = u.id and u.expectFunctionCategory = f.id and rmi.userId = up.userId and up.name like ?1% and f.id = ?2 and rmi.applyType = ?3")
    Page<UserpersonalEntity> getTalentsNameNotNull(String q, Integer industry, Integer reviewStatus, Pageable rqPage);

    @Query("select m FROM PortalMessageEntity m WHERE m.opera.id = ?1 and m.eventId = ?2 and m.eventType = 3 and m.msgType = 1")
    PortalMessageEntity getMessage(Integer operaId,Integer eventId);

    @Query("select count (m.id) from PortalMessageEntity m where m.eventType = 2 and m.receiver.id = ?1")
    Integer getInvitedNum(Integer userId);

    @Query("SELECT f.name FROM FunctioncategoryEntity f, UserEntity u, UserpersonalEntity up WHERE u.id = up.userId AND u.expectFunctionCategory = f.id AND up.userId = ?1")
    String getIndustry(Integer userId);

    @Query("select u.invitedNum from UserpersonalEntity u where u.userId=?1")
    Integer getInvitedNumUser(Integer userId);

    @Modifying
    @Query("update UserpersonalEntity u set u.invitedNum=u.invitedNum+1 where u.userId = ?1")
    Integer updateInvitedNum(Integer userId);

    @Modifying
    @Query("update UserpersonalEntity u set u.dataComp=?1 where u.userId = ?2")
    Integer updateDataComp(Integer dataComp, Integer userId);

    @Modifying
    @Query("update UserpersonalEntity u set u.job=?1 where u.userId = ?2")
    Integer updateJob(String job, Integer userId);

    @Query("select m FROM PortalMessageEntity m WHERE m.opera.id = ?1 and m.receiver.id = ?2 and m.eventType = 2")
    List<PortalMessageEntity> isInvited(Integer operaId,Integer receiverId);

//    @Query("SELECT up FROM PortalRealNameInfoEntity pl LEFT JOIN UserEntity u ON u.UserType = 0 " +
//            "AND u.Id = pl.userId LEFT JOIN UserpersonalEntity up ON up.UserId = pl.userId   WHERE pl.reviewStatus =1 ")
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?3 and us.expectFunctionCategory = ?2 and up.name LIKE concat('%',?1,'%') order by us.integrityDegree DESC ")
    Page<UserpersonalEntity> findTalents(String q, Integer industry, Integer
            expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?3 and us.expectFunctionCategory = ?2 and up.name LIKE concat('%',?1,'%') order by us.integrityDegree ASC ")
    Page<UserpersonalEntity> findTalentsA(String q, Integer industry, Integer
            expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?3 and us.expectFunctionCategory = ?2 and up.name LIKE concat('%',?1,'%') order by lr.reviewTime DESC ")
    Page<UserpersonalEntity> findTalentsTimeD(String q, Integer industry, Integer
            expertType, Pageable rqPage);

    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?2  and us.expectFunctionCategory = ?1 order by us.integrityDegree DESC ")
    Page<UserpersonalEntity> findTalents(Integer industry, Integer expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?2  and us.expectFunctionCategory = ?1 order by us.integrityDegree ASC ")
    Page<UserpersonalEntity> findTalentsA(Integer industry, Integer expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?2  and us.expectFunctionCategory = ?1 order by lr.reviewTime DESC ")
    Page<UserpersonalEntity> findTalentsTimeD(Integer industry, Integer expertType, Pageable rqPage);

    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?1 order by us.integrityDegree DESC ")
    Page<UserpersonalEntity> findTalents(Integer expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?1 order by us.integrityDegree ASC ")
    Page<UserpersonalEntity> findTalentsA(Integer expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and lr.applyType =?1 order by lr.reviewTime DESC ")
    Page<UserpersonalEntity> findTalentsTimeD(Integer expertType, Pageable rqPage);

    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and up.name LIKE concat('%',?1,'%') and lr.applyType =?2 order by us.integrityDegree DESC ")
    Page<UserpersonalEntity> findTalents(String q, Integer expertType, Pageable rqPage);
    @Modifying
    @Query("delete from UserpersonalEntity upe where upe.userId = ?1")
    void deleteByUserId(Integer id);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and up.name LIKE concat('%',?1,'%') and lr.applyType =?2 order by us.integrityDegree ASC ")
    Page<UserpersonalEntity> findTalentsA(String q, Integer expertType, Pageable rqPage);
    @Query("SELECT up FROM UserpersonalEntity up,PortalRealNameInfoEntity lr ,UserEntity us WHERE" +
            " lr.reviewStatus = 1 and us.id = lr.userId AND up.userId = lr.userId and up.name LIKE concat('%',?1,'%') and lr.applyType =?2 order by lr.reviewTime DESC ")
    Page<UserpersonalEntity> findTalentsTimeD(String q, Integer expertType, Pageable rqPage);
}
