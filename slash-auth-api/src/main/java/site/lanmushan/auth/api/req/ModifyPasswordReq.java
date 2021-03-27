package site.lanmushan.auth.api.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录
 *
 * @author Administrator
 */
@Data
public class ModifyPasswordReq {
    @NotBlank(message = "原始密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
