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
 * 角色表(AuthTbRole)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Table(name = "auth_tb_role")
@Data
public class AuthTbRole implements Serializable {
    private static final long serialVersionUID = -17737169299782803L;
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
    private String roleName;
    /**
     * 角色编码
     */
    @ExcelProperty(value = " 角色编码")
    private String roleCode;
    /**
     * 是否默认
     */
    @ExcelProperty(value = " 是否默认")
    private Integer isDefault;
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
    /**
     * 禁用
     */
    @ExcelProperty(value = " 禁用")
    private Integer disabled;


}