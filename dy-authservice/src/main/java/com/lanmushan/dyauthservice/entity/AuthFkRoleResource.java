package com.lanmushan.dyauthservice.entity;

import java.util.Date;
import java.io.Serializable;

import com.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * (AuthFkRoleResource)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:47
 */
@Table(name = "auth_fk_role_resource")
@Data
public class AuthFkRoleResource implements Serializable {
    private static final long serialVersionUID = -53753227212321539L;
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " ")
    private Long id;

    @ExcelProperty(value = " ")
    private Long fkRoleId;

    @ExcelProperty(value = " ")
    private Long fkResourceId;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;


}