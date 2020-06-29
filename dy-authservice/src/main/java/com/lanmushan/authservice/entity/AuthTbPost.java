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
 * 岗位信息表(AuthTbPost)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Table(name = "auth_tb_post")
@Data
public class AuthTbPost implements Serializable {
    private static final long serialVersionUID = -52799363371984032L;
    /**
     * 岗位ID
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 岗位ID")
    private Long id;
    /**
     * 岗位编码
     */
    @ExcelProperty(value = " 岗位编码")
    private String postCode;
    /**
     * 岗位名称
     */
    @ExcelProperty(value = " 岗位名称")
    private String postName;
    /**
     * 显示顺序
     */
    @ExcelProperty(value = " 显示顺序")
    private Integer postSort;
    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = " 状态（0正常 1停用）")
    private String status;
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
    /**
     * 备注
     */
    @ExcelProperty(value = " 备注")
    private String remark;


}