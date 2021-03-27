package site.lanmushan.auth.api.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Administrator
 */
@Data
public class RejectUser {
    @NotBlank(message = "登录账号不能为null")
    String account;
}
