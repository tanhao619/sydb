package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by liaoyoule on 2017/5/2.
 */
@Entity
@Table(name = "patent", schema = "lgsq", catalog = "")
public class PatentEntity {
    private int id;
    private Integer classNumberCount;
    private Integer priorityCount;
    private Integer referenceCount;
    private Integer generalization;
    private Date creatTime;
    private String patentNo;
    private Byte patentType;
    private String name;
    private String ipcNo;
    private String summary;
    private String priorityNo;
    private String proposers;
    private String address;
    private String quote;
    private String requestNo;
    private String requestTime;
    private String publishTime;
    private String proxyPerson;
    private String proxyOrganize;
    private String designNo;
    private String inventor;
    private Double patentAge;
    private String patentNoExt;
    private String chain;
    private Double industryIndexAfter;
    private Double industryIndex;
    private Double industryPatentAge;
    private Double patentTechIndex;
    private Double patentMarketVal;
    private Double economicBenefits;
    private Double sentiment;
    private Double law;
    private String introduction;
    private Integer viewCount;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ClassNumberCount")
    public Integer getClassNumberCount() {
        return classNumberCount;
    }

    public void setClassNumberCount(Integer classNumberCount) {
        this.classNumberCount = classNumberCount;
    }

    @Basic
    @Column(name = "PriorityCount")
    public Integer getPriorityCount() {
        return priorityCount;
    }

    public void setPriorityCount(Integer priorityCount) {
        this.priorityCount = priorityCount;
    }

    @Basic
    @Column(name = "ReferenceCount")
    public Integer getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(Integer referenceCount) {
        this.referenceCount = referenceCount;
    }

    @Basic
    @Column(name = "Generalization")
    public Integer getGeneralization() {
        return generalization;
    }

    public void setGeneralization(Integer generalization) {
        this.generalization = generalization;
    }

    @Basic
    @Column(name = "CreatTime")
    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "PatentNo")
    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
    }

    @Basic
    @Column(name = "PatentType")
    public Byte getPatentType() {
        return patentType;
    }

    public void setPatentType(Byte patentType) {
        this.patentType = patentType;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "IpcNo")
    public String getIpcNo() {
        return ipcNo;
    }

    public void setIpcNo(String ipcNo) {
        this.ipcNo = ipcNo;
    }

    @Basic
    @Column(name = "Summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "PriorityNo")
    public String getPriorityNo() {
        return priorityNo;
    }

    public void setPriorityNo(String priorityNo) {
        this.priorityNo = priorityNo;
    }

    @Basic
    @Column(name = "Proposers")
    public String getProposers() {
        return proposers;
    }

    public void setProposers(String proposers) {
        this.proposers = proposers;
    }

    @Basic
    @Column(name = "Address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "Quote")
    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Basic
    @Column(name = "RequestNo")
    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    @Basic
    @Column(name = "RequestTime")
    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "PublishTime")
    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    @Basic
    @Column(name = "ProxyPerson")
    public String getProxyPerson() {
        return proxyPerson;
    }

    public void setProxyPerson(String proxyPerson) {
        this.proxyPerson = proxyPerson;
    }

    @Basic
    @Column(name = "ProxyOrganize")
    public String getProxyOrganize() {
        return proxyOrganize;
    }

    public void setProxyOrganize(String proxyOrganize) {
        this.proxyOrganize = proxyOrganize;
    }

    @Basic
    @Column(name = "DesignNo")
    public String getDesignNo() {
        return designNo;
    }

    public void setDesignNo(String designNo) {
        this.designNo = designNo;
    }

    @Basic
    @Column(name = "Inventor")
    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    @Basic
    @Column(name = "PatentAge")
    public Double getPatentAge() {
        return patentAge;
    }

    public void setPatentAge(Double patentAge) {
        this.patentAge = patentAge;
    }

    @Basic
    @Column(name = "PatentNoExt")
    public String getPatentNoExt() {
        return patentNoExt;
    }

    public void setPatentNoExt(String patentNoExt) {
        this.patentNoExt = patentNoExt;
    }

    @Basic
    @Column(name = "Chain")
    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    @Basic
    @Column(name = "IndustryIndexAfter")
    public Double getIndustryIndexAfter() {
        return industryIndexAfter;
    }

    public void setIndustryIndexAfter(Double industryIndexAfter) {
        this.industryIndexAfter = industryIndexAfter;
    }

    @Basic
    @Column(name = "IndustryIndex")
    public Double getIndustryIndex() {
        return industryIndex;
    }

    public void setIndustryIndex(Double industryIndex) {
        this.industryIndex = industryIndex;
    }

    @Basic
    @Column(name = "IndustryPatentAge")
    public Double getIndustryPatentAge() {
        return industryPatentAge;
    }

    public void setIndustryPatentAge(Double industryPatentAge) {
        this.industryPatentAge = industryPatentAge;
    }

    @Basic
    @Column(name = "PatentTechIndex")
    public Double getPatentTechIndex() {
        return patentTechIndex;
    }

    public void setPatentTechIndex(Double patentTechIndex) {
        this.patentTechIndex = patentTechIndex;
    }

    @Basic
    @Column(name = "PatentMarketVal")
    public Double getPatentMarketVal() {
        return patentMarketVal;
    }

    public void setPatentMarketVal(Double patentMarketVal) {
        this.patentMarketVal = patentMarketVal;
    }

    @Basic
    @Column(name = "EconomicBenefits")
    public Double getEconomicBenefits() {
        return economicBenefits;
    }

    public void setEconomicBenefits(Double economicBenefits) {
        this.economicBenefits = economicBenefits;
    }

    @Basic
    @Column(name = "Sentiment")
    public Double getSentiment() {
        return sentiment;
    }

    public void setSentiment(Double sentiment) {
        this.sentiment = sentiment;
    }

    @Basic
    @Column(name = "Law")
    public Double getLaw() {
        return law;
    }

    public void setLaw(Double law) {
        this.law = law;
    }

    @Basic
    @Column(name = "Introduction")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "viewCount")
    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatentEntity that = (PatentEntity) o;

        if (id != that.id) return false;
        if (classNumberCount != null ? !classNumberCount.equals(that.classNumberCount) : that.classNumberCount != null)
            return false;
        if (priorityCount != null ? !priorityCount.equals(that.priorityCount) : that.priorityCount != null)
            return false;
        if (referenceCount != null ? !referenceCount.equals(that.referenceCount) : that.referenceCount != null)
            return false;
        if (generalization != null ? !generalization.equals(that.generalization) : that.generalization != null)
            return false;
        if (creatTime != null ? !creatTime.equals(that.creatTime) : that.creatTime != null) return false;
        if (patentNo != null ? !patentNo.equals(that.patentNo) : that.patentNo != null) return false;
        if (patentType != null ? !patentType.equals(that.patentType) : that.patentType != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (ipcNo != null ? !ipcNo.equals(that.ipcNo) : that.ipcNo != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (priorityNo != null ? !priorityNo.equals(that.priorityNo) : that.priorityNo != null) return false;
        if (proposers != null ? !proposers.equals(that.proposers) : that.proposers != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (quote != null ? !quote.equals(that.quote) : that.quote != null) return false;
        if (requestNo != null ? !requestNo.equals(that.requestNo) : that.requestNo != null) return false;
        if (requestTime != null ? !requestTime.equals(that.requestTime) : that.requestTime != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;
        if (proxyPerson != null ? !proxyPerson.equals(that.proxyPerson) : that.proxyPerson != null) return false;
        if (proxyOrganize != null ? !proxyOrganize.equals(that.proxyOrganize) : that.proxyOrganize != null)
            return false;
        if (designNo != null ? !designNo.equals(that.designNo) : that.designNo != null) return false;
        if (inventor != null ? !inventor.equals(that.inventor) : that.inventor != null) return false;
        if (patentAge != null ? !patentAge.equals(that.patentAge) : that.patentAge != null) return false;
        if (patentNoExt != null ? !patentNoExt.equals(that.patentNoExt) : that.patentNoExt != null) return false;
        if (chain != null ? !chain.equals(that.chain) : that.chain != null) return false;
        if (industryIndexAfter != null ? !industryIndexAfter.equals(that.industryIndexAfter) : that.industryIndexAfter != null)
            return false;
        if (industryIndex != null ? !industryIndex.equals(that.industryIndex) : that.industryIndex != null)
            return false;
        if (industryPatentAge != null ? !industryPatentAge.equals(that.industryPatentAge) : that.industryPatentAge != null)
            return false;
        if (patentTechIndex != null ? !patentTechIndex.equals(that.patentTechIndex) : that.patentTechIndex != null)
            return false;
        if (patentMarketVal != null ? !patentMarketVal.equals(that.patentMarketVal) : that.patentMarketVal != null)
            return false;
        if (economicBenefits != null ? !economicBenefits.equals(that.economicBenefits) : that.economicBenefits != null)
            return false;
        if (sentiment != null ? !sentiment.equals(that.sentiment) : that.sentiment != null) return false;
        if (law != null ? !law.equals(that.law) : that.law != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (classNumberCount != null ? classNumberCount.hashCode() : 0);
        result = 31 * result + (priorityCount != null ? priorityCount.hashCode() : 0);
        result = 31 * result + (referenceCount != null ? referenceCount.hashCode() : 0);
        result = 31 * result + (generalization != null ? generalization.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        result = 31 * result + (patentNo != null ? patentNo.hashCode() : 0);
        result = 31 * result + (patentType != null ? patentType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ipcNo != null ? ipcNo.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (priorityNo != null ? priorityNo.hashCode() : 0);
        result = 31 * result + (proposers != null ? proposers.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (quote != null ? quote.hashCode() : 0);
        result = 31 * result + (requestNo != null ? requestNo.hashCode() : 0);
        result = 31 * result + (requestTime != null ? requestTime.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        result = 31 * result + (proxyPerson != null ? proxyPerson.hashCode() : 0);
        result = 31 * result + (proxyOrganize != null ? proxyOrganize.hashCode() : 0);
        result = 31 * result + (designNo != null ? designNo.hashCode() : 0);
        result = 31 * result + (inventor != null ? inventor.hashCode() : 0);
        result = 31 * result + (patentAge != null ? patentAge.hashCode() : 0);
        result = 31 * result + (patentNoExt != null ? patentNoExt.hashCode() : 0);
        result = 31 * result + (chain != null ? chain.hashCode() : 0);
        result = 31 * result + (industryIndexAfter != null ? industryIndexAfter.hashCode() : 0);
        result = 31 * result + (industryIndex != null ? industryIndex.hashCode() : 0);
        result = 31 * result + (industryPatentAge != null ? industryPatentAge.hashCode() : 0);
        result = 31 * result + (patentTechIndex != null ? patentTechIndex.hashCode() : 0);
        result = 31 * result + (patentMarketVal != null ? patentMarketVal.hashCode() : 0);
        result = 31 * result + (economicBenefits != null ? economicBenefits.hashCode() : 0);
        result = 31 * result + (sentiment != null ? sentiment.hashCode() : 0);
        result = 31 * result + (law != null ? law.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        return result;
    }
}
