package com.lanmushan.dysysservice.entity;

import com.lanmushan.framework.util.uuid.SeqGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 字典分组表
 * @author Administrator
 */
@Table(name = "global_dict_group")
public class SysDictGroup {
    @Id
    @KeySql(genId = SeqGenId.class)
    private Long id;
    private String dictGroupName;
    private String dictGroupCode;
    private Integer allowEdit;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictGroupName() {
        return dictGroupName;
    }

    public void setDictGroupName(String dictGroupName) {
        this.dictGroupName = dictGroupName;
    }

    public String getDictGroupCode() {
        return dictGroupCode;
    }

    public void setDictGroupCode(String dictGroupCode) {
        this.dictGroupCode = dictGroupCode;
    }

    public Integer getAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(Integer allowEdit) {
        this.allowEdit = allowEdit;
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
