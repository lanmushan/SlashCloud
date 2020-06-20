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
 * 菜单表(AuthTbResource)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Table(name = "auth_tb_resource")
@Data
public class AuthTbResource implements Serializable {
    private static final long serialVersionUID = -41039188659252738L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 菜单名称
     */
    @ExcelProperty(value = " 菜单名称")
    private String resourceName;
    /**
     * 菜单描述
     */
    @ExcelProperty(value = " 菜单描述")
    private String resourceDescription;
    /**
     * 菜单链接
     */
    @ExcelProperty(value = " 菜单链接")
    private String resourceUrl;
    /**
     * 图标
     */
    @ExcelProperty(value = " 图标")
    private String iconDefault;
    /**
     * 上级
     */
    @ExcelProperty(value = " 上级")
    private Long fkParentId;
    /**
     * page,api,menu,elm
     */
    @ExcelProperty(value = " page,api,menu,elm")
    private String resourceType;
    /**
     * 禁用
     */
    @ExcelProperty(value = " 禁用")
    private Integer disabled;
    /**
     * 使用于页面元素
     */
    @ExcelProperty(value = " 使用于页面元素")
    private String resourceId;
    /**
     * 排序
     */
    @ExcelProperty(value = " 排序")
    private Integer orderIndex;

    @ExcelProperty(value = " ")
    private Long createUserId;

    @ExcelProperty(value = " ")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;


}