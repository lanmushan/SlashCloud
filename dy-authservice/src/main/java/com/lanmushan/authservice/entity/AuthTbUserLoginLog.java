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
 * 用户登录记录(AuthTbUserLoginLog)实体类
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Table(name = "auth_tb_user_login_log")
@Data
public class AuthTbUserLoginLog implements Serializable {
    private static final long serialVersionUID = 387461888346908827L;
    /**
     * 编号
     */
    @Id
    @KeySql(genId = SeqGenId.class)
    @ExcelProperty(value = " 编号")
    private Long id;
    /**
     * 登录名
     */
    @ExcelProperty(value = " 登录名")
    private String loginName;
    /**
     * 登录ip
     */
    @ExcelProperty(value = " 登录ip")
    private String loginIp;
    /**
     * 登录消息
     */
    @ExcelProperty(value = " 登录消息")
    private String loginMsg;
    /**
     * 用户编号
     */
    @ExcelProperty(value = " 用户编号")
    private Long fkUserId;

    @ExcelProperty(value = " ")
    private String loginBrowser;

    @ExcelProperty(value = " ")
    private String loginOs;
    /**
     * 登录来源
     */
    @ExcelProperty(value = " 登录来源")
    private String loginSource;
    /**
     * 登陆时间
     */
    @ExcelProperty(value = " 登陆时间")
    private Date createTime;

    @ExcelProperty(value = " ")
    private Date updateTime;


}