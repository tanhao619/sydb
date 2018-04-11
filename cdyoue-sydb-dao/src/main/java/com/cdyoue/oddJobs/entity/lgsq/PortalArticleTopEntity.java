package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by sky on 2017/5/26.
 */
//精选文章
@Entity
@Table(name = "lg_portal_article_top", schema = "lgsq", catalog = "")
public class PortalArticleTopEntity {
   private Integer id;
   private Integer articleId;
   private String name;
   private String coverImg;
   private String info;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Basic
    @Column(name = "articleId")
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "coverImg")
    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortalArticleTopEntity that = (PortalArticleTopEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (articleId != null ? articleId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
