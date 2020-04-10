package com.lanmushan.dyauthservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "auth_fk_role_resource")
public class AuthFkRoleResource {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    private Integer fkRoleId;
    private Integer fkResourceId;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFkRoleId() {
        return fkRoleId;
    }

    public void setFkRoleId(Integer fkRoleId) {
        this.fkRoleId = fkRoleId;
    }

    public Integer getFkResourceId() {
        return fkResourceId;
    }

    public void setFkResourceId(Integer fkResourceId) {
        this.fkResourceId = fkResourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
