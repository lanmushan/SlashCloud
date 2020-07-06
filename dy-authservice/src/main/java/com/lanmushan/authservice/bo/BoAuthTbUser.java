package com.lanmushan.authservice.bo;

import com.lanmushan.authservice.entity.AuthTbUser;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * 用户表(AuthTbUser)表服务接口
 *
 * @author makejava
 * @since 2020-06-15 22:13:48
 */
@Data
public class BoAuthTbUser extends AuthTbUser {
    /**
     * 接收角色列表
     */
    @Transient
    List<String> roleCodeList;
}