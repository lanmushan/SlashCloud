package com.lanmushan.authservice.entity;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.Table;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 角色和部门关联表(AuthFkDeptPost)实体类
 *
 * @author makejava
 * @since 2020-06-15 21:54:27
 */
@Table(name = "auth_fk_dept_post")
@Data
public class AuthFkDeptPost implements Serializable {
    private static final long serialVersionUID = 136838469151865685L;
    /**
     * 角色ID
     */
    @ExcelProperty(value = " 角色ID")
    private Long roleId;
    /**
     * 部门ID
     */
    @ExcelProperty(value = " 部门ID")
    private Long deptId;


}