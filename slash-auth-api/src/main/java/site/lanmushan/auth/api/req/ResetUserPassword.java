package site.lanmushan.auth.api.req;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * @author Administrator
 */
@Data
public class ResetUserPassword {
    @NotBlank(message = "必须制定账号")
    private String account;
}
