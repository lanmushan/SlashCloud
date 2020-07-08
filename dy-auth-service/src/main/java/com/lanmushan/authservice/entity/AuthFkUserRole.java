package com.lanmushan.authservice.entity;

import java.util.Date;
import java.io.Serializable;

import com.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 用户角色关系表(AuthFkUserRole)实体类
 *
 * @author daiyu
 * @since 2020-06-15 22:13:47
 */
@Table(name = "auth_fk_user_role")
@Data
public class AuthFkUserRole implements Serializable {
    private static final long serialVersionUID = -52392275200362752L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 角色名称
     */
    @ExcelProperty(value = " 角色名称")
    private Long fkRoleId;
    /**
     * 用户名称
     */
    @ExcelProperty(value = " 用户名称")
    private Long fkUserId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = " 更新时间")
    private Date updateTime;

    @ExcelProperty(value = " ")
    private String createUser;

    @ExcelProperty(value = " ")
    private String upateUser;


}