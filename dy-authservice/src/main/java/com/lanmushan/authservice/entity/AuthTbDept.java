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
 * 部门表(AuthTbDept)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Table(name = "auth_tb_dept")
@Data
public class AuthTbDept implements Serializable {
    private static final long serialVersionUID = -53970534869734610L;
    /**
     * 部门id
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 部门id")
    private Long id;
    /**
     * 父部门id
     */
    @ExcelProperty(value = " 父部门id")
    private Long parentId;
    /**
     * 祖级列表
     */
    @ExcelProperty(value = " 祖级列表")
    private String ancestors;
    /**
     * 部门名称
     */
    @ExcelProperty(value = " 部门名称")
    private String deptName;
    /**
     * 显示顺序
     */
    @ExcelProperty(value = " 显示顺序")
    private Integer orderIndex;
    /**
     * 负责人
     */
    @ExcelProperty(value = " 负责人")
    private String leader;
    /**
     * 联系电话
     */
    @ExcelProperty(value = " 联系电话")
    private String phone;
    /**
     * 邮箱
     */
    @ExcelProperty(value = " 邮箱")
    private String email;
    /**
     * 部门状态（0正常 1停用）
     */
    @ExcelProperty(value = " 部门状态（0正常 1停用）")
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @ExcelProperty(value = " 删除标志（0代表存在 2代表删除）")
    private String delFlag;
    /**
     * 创建者
     */
    @ExcelProperty(value = " 创建者")
    private String createUser;
    /**
     * 创建时间
     */
    @ExcelProperty(value = " 创建时间")
    private Date createTime;
    /**
     * 更新者
     */
    @ExcelProperty(value = " 更新者")
    private String updateUser;
    /**
     * 更新时间
     */
    @ExcelProperty(value = " 更新时间")
    private Date updateTime;


}