package com.lanmushan.authservice.bo;

import com.lanmushan.authservice.entity.AuthTbUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * 用户表(AuthTbUser)表服务接口
 *
 * @author dy
 * @since 2020-06-15 22:13:48
 */
@Data
public class BoAuthTbUser extends AuthTbUser {
    /**
     * 接收角色列表
     */
    @Transient
    @ApiModelProperty(name = "角色编码数据", dataType = "array", required = true)
    List<String> roleCodeList;
}