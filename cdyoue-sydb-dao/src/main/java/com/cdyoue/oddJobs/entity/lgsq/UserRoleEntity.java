package com.cdyoue.oddJobs.entity.lgsq;

import javax.persistence.*;

/**
 * Created by liaoyoule on 2017/6/12.
 */
@Entity
@Table(name = "user_role", schema = "lgsq", catalog = "")
public class UserRoleEntity {
    private Integer id;
    private Integer userId;
    private RoleEntity role;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        return result;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
