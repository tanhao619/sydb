package com.cdyoue.oddJobs.entity.syData;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 */
@Entity
@Table(name = "sy_equipment_collect", schema = "syData", catalog = "")
public class EquipmentCollectEntity {
    private Integer id;
    private Integer uId;
    private Integer eId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uId")
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    @Basic
    @Column(name = "eId")
    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    @Override
    public String toString() {
        return "EquipmentCollectEntity{" +
                "id=" + id +
                ", uId=" + uId +
                ", eId=" + eId +
                '}';
    }
}
