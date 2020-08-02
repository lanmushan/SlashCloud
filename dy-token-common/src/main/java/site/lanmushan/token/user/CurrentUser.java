package site.lanmushan.token.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CurrentUser implements Serializable {
    private Long userId;
    private Long deptId;
    private String nickName;
    private String username;
    private Integer sex;
    private String headImgAddress;
    /**
     * 角色
     */
    private List<String> roleCodes;
}