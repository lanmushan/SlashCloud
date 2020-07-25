package com.lanmushan.authservice.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author dy
 * @Date 2020/6/15 23:08
 * @Version 1.0
 */
@Data
public class BUserLogin {
    @NotBlank(message = "登录账号不能为空")
    private String account;
    @NotBlank(message = "登录密码不能为空")
    private String password;
    @NotBlank(message = "验证码不能为空")
    private String verificationCode;
}
