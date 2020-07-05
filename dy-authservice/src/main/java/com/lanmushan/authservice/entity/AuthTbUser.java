package com.lanmushan.authservice.entity;

import java.util.Date;
import java.io.Serializable;

import com.lanmushan.framework.util.uuid.SeqGenId;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.alibaba.excel.annotation.ExcelProperty;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

/**
 * 用户表(AuthTbUser)实体类
 *
 * @author makejava
 * @since 2020-06-28 20:43:47
 */
@Table(name = "auth_tb_user")
@Data
public class AuthTbUser implements Serializable {
    private static final long serialVersionUID = -76669382002540303L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 部门编号
     */
    @ExcelProperty(value = " 部门编号")
    private Long deptId;

    @ExcelProperty(value = " ")
    @NotBlank(message = "用户名称不能为空")
    private String nickName;
    /**
     * 姓名
     */
    @ExcelProperty(value = " 姓名")
    @NotBlank(message = "用户姓名不能为空")
    private String username;
    /**
     * 电话
     */
    @ExcelProperty(value = " 电话")
    @NotBlank(message = "联系电话不能为空")
    @Length(min = 11, max = 11, message = "联系电话只能11位")
    private String phone;
    /**
     * 身份证号
     */
    @ExcelProperty(value = " 身份证号")
    @NotBlank(message = "证件号码不能为空")
    @Length(min = 15, max = 18, message = "身份证号长度为15或18位")
    private String idCard;
    /**
     * 邮箱
     */
    @ExcelProperty(value = " 邮箱")
    @NotBlank(message = "请输入正确的联系邮箱")
    private String email;
    /**
     * 性别
     */
    @ExcelProperty(value = " 性别")
    private Integer sex;
    /**
     * 账号
     */
    @ExcelProperty(value = " 账号")
    @NotBlank(message = "登录账号不能为空")
    @Length(min = 2, message = "登录账号长度最少2位")
    private String account;
    /**
     * 登录密码
     */
    @ExcelProperty(value = " 登录密码")
    private String loginPassword;
    /**
     * 操作密码
     */
    @ExcelProperty(value = " 操作密码")
    private String operatePassword;
    /**
     * 盐
     */
    @ExcelProperty(value = " 盐")
    private String salt;
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
    private Long fkPostId;
    /**
     * 锁定
     */
    @ExcelProperty(value = " 锁定")
    private Integer isLock;
    /**
     * 账号类型
     */
    @ExcelProperty(value = " 账号类型")
    private String accountType;
    /**
     * 头像
     */
    @ExcelProperty(value = " 头像")
    private String headImgAddress;
    /**
     * 是否删除1是
     */
    @ExcelProperty(value = " 是否删除1是")
    private Integer deleted;

    @ExcelProperty(value = " ")
    private String createUser;


}