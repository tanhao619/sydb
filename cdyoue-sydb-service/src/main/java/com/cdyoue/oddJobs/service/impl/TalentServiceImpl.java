package com.cdyoue.oddJobs.service.impl;

import com.cdyoue.oddJobs.dao.lqsq.TalentResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserpersonalResponsitory;
import com.cdyoue.oddJobs.dto.UserMine;
import com.cdyoue.oddJobs.dto.lgfc.*;
import com.cdyoue.oddJobs.entity.lgsq.PortalTechnologyEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.exception.InvalidTokenException;
import com.cdyoue.oddJobs.exception.NotFoundEntityException;
import com.cdyoue.oddJobs.mapper.TalentMapper;
import com.cdyoue.oddJobs.service.TalentService;
import com.cdyoue.oddJobs.utils.SecurityUtils;
import com.cdyoue.spring.page.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tangrui on 2017/5/8.
 */
@Service
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentResponsitory talentResponsitory;

    @Autowired
    private TalentMapper talentMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserResponsitory userResponsitory;

    @Autowired
    private UserpersonalResponsitory userpersonalResponsitory;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${default.recommend.remoteUrl}")
    private String recommendRemoteUrl;



    //private static final String portraitUrl = "http://103.235.234.108:13009/recommendSystem/api/userportrait";

    @Override
    public PageInfo<TalentEducation> findAllEducations(Pageable requestPage) {
        //获取用户ID
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        Page<PortalTechnologyEntity> rpPage = talentResponsitory.findByUserIdAndType(userId, 2, requestPage);
        List<TalentEducation> talentEducations = rpPage.getContent().stream().map(p -> talentMapper.portalTechnologyEntityToTalentEducation(p)).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(talentEducations, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public TalentImage findTalentImageById(Integer userId) {
        TalentImage talentImage = new TalentImage();
        //封装活跃度
        talentImage.setUserActiveness(this.getUserActivenes(userId));
        //封装统计的操作信息
        talentImage.setUserPortrait(this.countBehaviour(userId));
        //封装统计的关注领域
        talentImage.setFocusField(this.countFocusField(userId));
        //封装统计的登录类型
        talentImage.setLoginType(this.countLoginInfo(userId));
        //封装登录信息
        talentImage.setLoginInfoData(this.countLoginInfoData(userId));
        //封装能人性别
        talentImage.setSex(userResponsitory.findOne(userId) == null ? null :userResponsitory.findOne(userId).getSex());
        //封装用户访问统计
        talentImage.setCountMonth(this.countUserVisit(userId));
        //返回能人画像对象
        return talentImage;
    }

    @Override
    public boolean updateAuthority(Integer id) {
        //获取用户ID
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        //获取该id对应的用户id
        Integer userIdById = talentResponsitory.getUserIdById(id);
        //Integer 是包装类  是一个对象  需要用equals 比较
        return userId.equals(userIdById);
    }

    @Override
    public PortalTechnologyEntity findCareerByIdAndUid( Integer id) {
        Integer uid = SecurityUtils.getCurrentUserLogin().getId();
        PortalTechnologyEntity byUserIdAndId = talentResponsitory.findByUserIdAndId(uid, id);
        return byUserIdAndId;
    }

    @Override
    public List<PortalTechnologyEntity> findByUid(Integer uid, Integer type) {
        return talentResponsitory.findByUidAndType(uid, type);
    }


    @Override
    public String findTitleByUidLimitEndTime(Integer uId) {
        String title="";
        Pageable requestPage = new PageRequest(0, 1, Sort.Direction.DESC,"endTime");
        Page<PortalTechnologyEntity> byUserTitle = talentResponsitory.findByUserTitle(uId,requestPage);
        if (byUserTitle!=null && byUserTitle.getContent().size()>0){
            title = byUserTitle.getContent().get(0).getMajor();
        }
        return title;
    }

    //统计该用户关注的领域
    private FocusField countFocusField(Integer userId) {
            FocusField focusField = restTemplate.getForEntity(recommendRemoteUrl.concat("/focusField?id="+userId), FocusField.class).getBody();
            return focusField;
    }

    //统计该用户常用的操作
    private List<UserPortrait> countBehaviour(Integer userId) {
        String portraitUrl = recommendRemoteUrl.replace("data","api");
        List<UserPortrait> lists = null;
        Portrait portrait = restTemplate.getForEntity(portraitUrl.concat("/userportrait/useroperation?userid=") + userId, Portrait.class).getBody();
        UserOperation uo = portrait.getData();
        try {
            lists = talentMapper.UserOperationToLists(uo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    //统计用户登录分布
    private LoginType countLoginInfo(Integer userId){
            String portraitUrl = recommendRemoteUrl.replace("data","api");
            LoginType loginType = restTemplate.getForEntity(portraitUrl.concat("/userportrait/userlogintype?userid=") + userId, LoginType.class).getBody();
            return loginType;
    }

    //用户活跃度
    private UserActiveness getUserActivenes(Integer userId){
        UserActiveness userActiveness = restTemplate.getForEntity(recommendRemoteUrl.concat("/userActiveness?id="+userId), UserActiveness.class).getBody();
        return  userActiveness;
    }

    //统计第一次最后一次及登录总次数
    private LoginInfoData countLoginInfoData(Integer userId){
        LoginInfoData lid = new LoginInfoData();
        String sqlForLoginInfo = "select max(ulh.loginTime) as lastLogin, " +
                "min(ulh.loginTime) as firstLogin " +
                "from lg_portal_user_portrait ulh " +
                "where ulh.userId = ?;";
        SqlRowSet resultSet2 = jdbcTemplate.queryForRowSet(sqlForLoginInfo, userId);
        while(resultSet2.next()) {
            //最后一次登录
            lid.setLastLogin(resultSet2.getTimestamp("lastLogin") == null ? null : resultSet2.getTimestamp("lastLogin").toString());
            //第一次登录
            lid.setFirstLogin(resultSet2.getTimestamp("firstLogin") == null ? null : resultSet2.getTimestamp("firstLogin").toString());
        }
        String portraitUrl = recommendRemoteUrl.replace("data","api");
        LoginType loginType = restTemplate.getForEntity(portraitUrl.concat("/userportrait/userlogintype?userid=") + userId, LoginType.class).getBody();
        Integer totalLogin = 0;
        if (loginType.getData() != null) totalLogin = loginType.getData().getMtlogintimes() + loginType.getData().getPclogintimes();
        lid.setTotalLogin(totalLogin);
        return lid;
    }


    //按月份统计登录次数
    private Integer[] countUserVisit(Integer userId){
        UserVisitCount userVisitCount = new UserVisitCount();
        UserVisitCountData data = new UserVisitCountData();

        //按月份统计登录次数
        String sqlForLoginMonth = "select MONTH(loginTime) as MONTH, COUNT(id) as countMonth from lg_portal_user_portrait where userId = ? GROUP BY MONTH(loginTime);";
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sqlForLoginMonth, userId);
        while (resultSet.next()) {
            Integer month = resultSet.getInt("MONTH");
            switch (month) {
                case 1:
                    data.setJanuaryTimes(resultSet.getInt("countMonth"));
                    break;
                case 2:
                    data.setFebruaryTimes(resultSet.getInt("countMonth"));
                    break;
                case 3:
                    data.setMarchTimes(resultSet.getInt("countMonth"));
                    break;
                case 4:
                    data.setAprilTimes(resultSet.getInt("countMonth"));
                    break;
                case 5:
                    data.setMayTimes(resultSet.getInt("countMonth"));
                    break;
                case 6:
                    data.setJuneTimes(resultSet.getInt("countMonth"));
                    break;
                case 7:
                    data.setJulyTimes(resultSet.getInt("countMonth"));
                    break;
                case 8:
                    data.setAugustTimes(resultSet.getInt("countMonth"));
                    break;
                case 9:
                    data.setSeptemberTimes(resultSet.getInt("countMonth"));
                    break;
                case 10:
                    data.setOctoberTimes(resultSet.getInt("countMonth"));
                    break;
                case 11:
                    data.setNovemberTimes(resultSet.getInt("countMonth"));
                    break;
                case 12:
                    data.setDecemberTimes(resultSet.getInt("countMonth"));
                    break;
            }
        }

        Integer[] monthCount = new Integer[]{data.getJanuaryTimes(), data.getFebruaryTimes(), data.getMarchTimes(), data.getAprilTimes(), data.getMayTimes(), data.getJuneTimes(), data.getJulyTimes(), data.getAugustTimes(), data.getSeptemberTimes(), data.getOctoberTimes(), data.getNovemberTimes(), data.getDecemberTimes()};
        return monthCount;
    }


    @Override
    public PageInfo<TalentCareer> findAllCareers(Pageable requestPage) {
        //获取用户ID
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        //查询
        Page<PortalTechnologyEntity> rpPage = talentResponsitory.findByUserIdAndType(userId, 3, requestPage);
        List<TalentCareer> talentCareers = rpPage.getContent().stream().map(p ->
                talentMapper.portalTechnologyEntityToTalentCareer(p)
        ).collect(Collectors.toList());
        return new PageInfo<>(new PageImpl(talentCareers, requestPage, rpPage.getTotalElements()));
    }

    @Override
    public Integer addMyEducation(TalentEducation talentEducation) {
        PortalTechnologyEntity portalTechnologyEntity = talentMapper.talentEducationToPortalTechnologyEntity(talentEducation);
        //获取用户ID
        UserMine userMine = SecurityUtils.getCurrentUserLogin();
        List<PortalTechnologyEntity> byUidAndType = talentResponsitory.findByUidAndType(userMine.getId(), 2);
        if (byUidAndType.size() < 1) {
            UserEntity userEntity = userResponsitory.findOne(userMine.getId());
//            UserenterpriseEntity userenterpriseEntity= userEntity.getUserenterpriseEntity();
//            UserpersonalEntity userpersonalEntity = userEntity.getUserpersonalEntity();
            BigDecimal integrityDegree = userEntity.getIntegrityDegree();
            int i = integrityDegree.intValue() +5;
            userEntity.setIntegrityDegree(new BigDecimal(i));
            //修改资料完整度
//            userEntity.setIntegrityDegree(new BigDecimal(
//                    DataIntegrityUtils.getDataComp(userMine.getId(), userMine.getUserType()) + 5));
//            userpersonalResponsitory.updateDataComp(DataIntegrityUtils.getDataComp(userMine.getId(),userMine.getUserType())+5,userEntity.getId());
            userResponsitory.save(userEntity);
        }
        portalTechnologyEntity.setUserId(userMine.getId());
        return talentResponsitory.save(portalTechnologyEntity).getId();
    }

    @Override
    public Integer addMyCareer(TalentCareer talentCareer) {
        UserMine currentUserLogin = SecurityUtils.getCurrentUserLogin();
        PortalTechnologyEntity portalTechnologyEntity = talentMapper.talentCareerToPortalTechnologyEntity(talentCareer);
        List<PortalTechnologyEntity> byUidAndType = talentResponsitory.findByUidAndType(currentUserLogin.getId(), 3);
        if (byUidAndType.size() < 1) {
            UserEntity userEntity = userResponsitory.findOne(currentUserLogin.getId());
//            userEntity.setIntegrityDegree(new BigDecimal(DataIntegrityUtils.getDataComp(currentUserLogin.getId(), currentUserLogin.getUserType()) + 5));//修改资料完整度
//            userpersonalResponsitory.updateDataComp(DataIntegrityUtils.getDataComp(currentUserLogin.getId(),currentUserLogin.getUserType())+5,userEntity.getId());
            BigDecimal integrityDegree = userEntity.getIntegrityDegree();
            int i = integrityDegree.intValue() +5;
            userEntity.setIntegrityDegree(new BigDecimal(i));
            userResponsitory.save(userEntity);
        }
        //获取用户ID
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        portalTechnologyEntity.setUserId(userId);

        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long endTime=null;
        try {
            Date date=format.parse(talentCareer.getEndTime());
            endTime=date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(byUidAndType.size()>0){
            Long time=byUidAndType.get(0).getEndTime().getTime();
            if(endTime>=time){
                //更改用户最新的职位
                userpersonalResponsitory.updateJob(talentCareer.getMajor(),userId);
            }
        }else {
            userpersonalResponsitory.updateJob(talentCareer.getMajor(),userId);
        }
        return talentResponsitory.save(portalTechnologyEntity).getId();
    }

    @Override
    public PortalTechnologyEntity findTalentEduById(Integer id) {
            return talentResponsitory.findOne(id);
    }

    @Override
    public void updateTalentEducation(Integer id, TalentEducation talentEducation) {
        PortalTechnologyEntity portalTechnologyEntity = this.findTalentCareerById(id);
        if (portalTechnologyEntity == null) throw new NotFoundEntityException("数据没有找到");
        if (!updateAuthority(id)) throw new InvalidTokenException("只能修改自己的职业");
        portalTechnologyEntity.setName(talentEducation.getName());
        portalTechnologyEntity.setMajor(talentEducation.getMajor());
        portalTechnologyEntity.setEducation(talentEducation.getEducation());
        String startTime = talentEducation.getStartTime();
        if (startTime!=null) portalTechnologyEntity.setStartTime(Timestamp.valueOf(startTime));
        String endTime = talentEducation.getEndTime();
        if (endTime!=null) portalTechnologyEntity.setEndTime(Timestamp.valueOf(endTime));
        talentResponsitory.saveAndFlush(portalTechnologyEntity);
    }

    @Override
    public PortalTechnologyEntity findTalentCareerById(Integer careerId) {
            return talentResponsitory.getOne(careerId);
    }

    @Override
    public void updateTalentCareer(Integer id, TalentCareer talentCareer) {
//        PortalTechnologyEntity portalTechnologyEntity =
        PortalTechnologyEntity one = talentResponsitory.findById(id);
        if (one == null) {
            throw new NotFoundEntityException("数据没有找到");
        }
        if (!updateAuthority(id)) {
            throw new InvalidTokenException("只能修改自己的职业");
        }
        if(StringUtils.isNotBlank(talentCareer.getMajor())){
            one.setMajor(talentCareer.getMajor());
        }
        if(StringUtils.isNotBlank(talentCareer.getIntroduction())){
            one.setIntroduction(talentCareer.getIntroduction());
        }
        String startTime = talentCareer.getStartTime();
        if (StringUtils.isNotBlank(startTime)){
            one.setStartTime(Timestamp.valueOf(startTime));
        }
        String endTime = talentCareer.getEndTime();
        if (StringUtils.isNotBlank(endTime)){
            one.setEndTime(Timestamp.valueOf(endTime));
        }
        Integer userId = SecurityUtils.getCurrentUserLogin().getId();
        List<PortalTechnologyEntity> byUidAndType = talentResponsitory.findByUidAndType(userId, 3);
        Long date=null;
        try {
             date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(talentCareer.getEndTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date>=byUidAndType.get(0).getEndTime().getTime()){
            userpersonalResponsitory.updateJob(talentCareer.getMajor(),userId);
        }
        talentResponsitory.saveAndFlush(one);
    }

}
