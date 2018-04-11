package com.cdyoue.oddJobs.mapper;

import com.cdyoue.oddJobs.dao.lqsq.PortalRealNameInfoResponsitory;
import com.cdyoue.oddJobs.dao.lqsq.UserRoleResponsitory;
import com.cdyoue.oddJobs.dto.EmployerDetail;
import com.cdyoue.oddJobs.dto.EmployerInfo;
import com.cdyoue.oddJobs.dto.account.AccountBase;
import com.cdyoue.oddJobs.dto.account.AccountSumary;
import com.cdyoue.oddJobs.dto.lgfc.TalentBase;
import com.cdyoue.oddJobs.dto.oauth.Menu;
import com.cdyoue.oddJobs.dto.oauth.MenuSumary;
import com.cdyoue.oddJobs.dto.requirement.RecommendTalentBase;
import com.cdyoue.oddJobs.entity.lgsq.MenuEntity;
import com.cdyoue.oddJobs.entity.lgsq.PortalRealNameInfoEntity;
import com.cdyoue.oddJobs.entity.lgsq.UserRoleEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserenterpriseEntity;
import com.cdyoue.oddJobs.entity.lgsq.user.UserpersonalEntity;
import com.cdyoue.oddJobs.exception.DataException;
import com.cdyoue.oddJobs.exception.LogicException;
import com.cdyoue.oddJobs.utils.UserUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by liaoyoule on 2017/4/24.
 */
@Component
public class UserMapper {

    @Autowired
    private PortalRealNameInfoResponsitory portalRealNameInfoResponsitory;
    @Autowired
    private UserRoleResponsitory userRoleResponsitory;


    /**
     * user 转 name
     *
     * @return
     */
    public String userToEmployeerName(UserEntity ue) {
        String userName = null;
        try {
            userName = ue.getUserType() == 0 ? ue.getUserpersonalEntity().getName() : ue.getUserenterpriseEntity().getName();
        } catch (NullPointerException e) {
            throw new LogicException("用户'" + ue.getId() + ":" + ue.getUserName() + "'数据不完整");
        }
        return userName;
    }


    /**
     * userEntity 转 TalentBase
     *
     * @param ue
     * @return
     */
    public TalentBase userEntityToTalentBase(UserEntity ue) {
        if (ue == null) {
        }
        TalentBase tb = new TalentBase();
        tb.setId(ue.getId());
        EmployerDetail ed = this.userToEmployerDetail(ue);
        tb.setName(ed.getName());
        tb.setCoverImgUrl(ed.getCoverImgUrl());
        String expertType = ed.getExpertType();
        if (Optional.ofNullable(expertType).isPresent()) {
            List<Integer> certs = Arrays.stream(expertType.split(",")).map(p -> Integer.parseInt(p)).collect(Collectors.toList());
            tb.setCerts(certs);
        }
        tb.setInfo(ed.getInfo());
        return tb;
    }

    /**
     * userEntity 转 TalentBase
     *
     * @param ue
     * @return
     */
    public RecommendTalentBase userEntityToRecommendTalentBase(UserEntity ue) {
        if (ue == null) {
            return null;
        }
        RecommendTalentBase tb = new RecommendTalentBase();
        tb.setId(ue.getId());
        //tb.setName(ue.getUserName());
        tb.setName(UserUtils.getUserName(ue.getId()));
        EmployerDetail ed = this.userToEmployerDetail(ue);
        tb.setCoverImgUrl(ed.getCoverImgUrl());
        String expertType = ed.getExpertType();
        if (Optional.ofNullable(expertType).isPresent()) {
            List<Integer> certs = Arrays.stream(expertType.split(",")).map(p -> Integer.parseInt(p)).collect(Collectors.toList());
            tb.setCerts(certs);
        }


        return tb;
    }

    public EmployerDetail userToEmployerDetail(UserEntity ue) {
        Integer userType = ue.getUserType();
        EmployerDetail ed = new EmployerDetail();
        ed.setEmployerType(userType);
        ed.setId(ue.getId());
        if (userType == 0) {
            UserpersonalEntity upe = ue.getUserpersonalEntity();
            ed.setCoverImgUrl(upe.getHeadImg());
            ed.setInfo(upe.getInfo());
            ed.setName(upe.getName());
            ed.setInfo(upe.getInfo());


            List<PortalRealNameInfoEntity> prms = portalRealNameInfoResponsitory.findByUserIdAndReviewStatus(ue.getId(), new Byte("2"));
            if (prms != null) {
                final String[] expertType = {null};
                prms.forEach(pri -> {
                    Integer applyType = pri.getApplyType();
                    switch (applyType) {
                        case 1:
                            expertType[0] += "实名" + ";";
                            break;
                        case 2:
                            expertType[0] += "大咖" + ";";
                            break;
                        case 3:
                            expertType[0] += "导师" + ";";
                            break;
                    }

                });
                ed.setExpertType(expertType[0]);
            }
            return ed;
        } else if (userType == 1) {
            UserenterpriseEntity ute = ue.getUserenterpriseEntity();
            ed.setCoverImgUrl(ute.getHeadImg());
            ed.setInfo(ute.getInfo());
            ed.setName(ute.getName());
            ed.setInfo(ute.getInfo());

            return ed;
        }

        throw new DataException("数据结构错误");


    }

    public EmployerInfo userToEmployerInfo(UserEntity ue) {
        EmployerDetail employerDetail = this.userToEmployerDetail(ue);
        EmployerInfo employerInfo = new EmployerInfo();
        BeanUtils.copyProperties(employerDetail, employerInfo);
        return employerInfo;
    }

    public AccountSumary userToAccountSumary(UserEntity ue) {
        AccountSumary as = new AccountSumary();
        AccountBase ab = new AccountBase();
        ab.setEmail(ue.getEmail());
        ab.setTel(ue.getTel());
        ab.setCreateTime(ue.getCreateTime());
        ab.setRole(ue.getRole());
        ab.setName(this.userToEmployeerName(ue));
        as.setAccountBase(ab);
        as.setReviewStatus(ue.getReviewStatus());
        as.setReviewReason(ue.getReviewReason());
        as.setId(ue.getId());
        PortalRealNameInfoEntity pri = new PortalRealNameInfoEntity();
        pri.setUserId(ue.getId());
        Example<PortalRealNameInfoEntity> arg = Example.of(pri);
        as.setReferCert( portalRealNameInfoResponsitory.count(arg) == 0 ? false:true);
        return as;
    }


    public List<MenuSumary> getTopMenusByUserId(Integer id) {
        Set<MenuEntity> mes = new HashSet<>();


        userRoleResponsitory.findByUserId(id).stream()
                .filter(userRoleEntity -> userRoleEntity.getRole() != null)
                .forEach(
                        (UserRoleEntity userRoleEntity) ->
                                mes.addAll(userRoleEntity.getRole().getLeftMenus())
                );


        return getTopMenus(mes);
    }






    public List<MenuSumary> getTopMenus(Set<MenuEntity> mes) {

        Multimap<Integer, MenuEntity> meMap = ArrayListMultimap.create();
        LinkedHashSet<MenuEntity> topM = new LinkedHashSet<>();
        this.sortMenus(mes.stream().sorted(Comparator.comparing(MenuEntity::getId)).collect(Collectors.toList()), meMap, topM);

        List<MenuSumary> top = new ArrayList<>();
        topM = new LinkedHashSet<>(topM.stream().sorted(Comparator.comparing(MenuEntity::getId)).collect(Collectors.toList()));
        getTopMenus(top, topM, meMap);

        return top;
    }





    private void getTopMenus(List<MenuSumary> top, Set<MenuEntity> topM, Multimap<Integer, MenuEntity> meMap) {
        topM.stream().forEach(menuEntity -> {
            Collection<MenuEntity> menuEntities = meMap.get(menuEntity.getId());
            MenuSumary menuSumary = new MenuSumary();
            menuSumary.setName(menuEntity.getName());
            menuSumary.setUiSref(menuEntity.getUiSref());
            menuSumary.setIcon(menuEntity.getIcon());
            if (menuEntities.size() != 0) {
                List<Menu> menus = this.menuEntityToMenu(new ArrayList(new HashSet<>(menuEntities)), meMap);
                menuSumary.setLeftMenus(menus.stream().sorted(Comparator.comparing(Menu::getId)).collect(Collectors.toList()));
            }
            top.add(menuSumary);
        });


    }

    private List<Menu> menuEntityToMenu(List<MenuEntity> mes, Multimap<Integer, MenuEntity> meMap) {
        List<Menu> lms = mes.stream().map((MenuEntity lm) -> {
            Menu m = new Menu();
            m.setUiSref(lm.getUiSref());
            m.setId(lm.getId());
            m.setName(lm.getName());
            m.setIcon(lm.getIcon());
            Collection<MenuEntity> menuEntities = meMap.get(lm.getId());

            if (menuEntities.size() != 0) {
                List<Menu> menus = menuEntityToMenu(new ArrayList(new HashSet<>(menuEntities)), meMap);
                m.setMenus(menus.stream().sorted(Comparator.comparing(Menu::getId)).collect(Collectors.toList()));
            }
            return m;
        }).collect(Collectors.toList());
        return lms;
    }

    private void sortMenus(List<MenuEntity> mess, Multimap<Integer, MenuEntity> meMap, Set<MenuEntity> mes) {

        mess.stream().forEach(menuEntity -> {
            sortMenus(menuEntity, meMap, mes);
        });
    }

    private void sortMenus(MenuEntity me, Multimap<Integer, MenuEntity> meMap, Set<MenuEntity> mes) {
        MenuEntity lastMe = me.getTopMenu();
        if (lastMe != null) {
            meMap.put(lastMe.getId(), me);
            sortMenus(lastMe, meMap, mes);
        } else {
            mes.add(me);
        }
    }


}
