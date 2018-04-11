package com.cdyoue.oddJobs.dto.requirement;

import java.math.BigDecimal;

/**
 * Created by tr on 2017/6/9.
 */
public class InviteMeRequireSummary {
    private int id;
    private String name;
    private String enterprise;
    private BigDecimal fin;
    private Byte status;
    private String publishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public BigDecimal getFin() {
        return fin;
    }

    public void setFin(BigDecimal fin) {
        this.fin = fin;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
