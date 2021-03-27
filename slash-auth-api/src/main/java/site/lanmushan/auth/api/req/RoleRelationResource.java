package site.lanmushan.auth.api.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Administrator
 */
@Data
public class RoleRelationResource {
    private List<Long> resourceIdList;
    @NotBlank(message = "角色编码不能为空")
    private String roleCode;
    @NotBlank(message = "资源类型不能为空")
    private String resourceType;
}
