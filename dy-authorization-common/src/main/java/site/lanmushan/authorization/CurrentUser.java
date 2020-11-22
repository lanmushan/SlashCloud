package site.lanmushan.authorization;

import lombok.Data;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.utils.OperateException;

import java.io.Serializable;
import java.util.List;

/**
 * @Author dy
 * @Date 2020/6/15 22:52
 * @Version 1.0
 */
@Data
public class CurrentUser implements Serializable {
    private Long userId;
    private Long deptId;
    private String nickName;
    private String username;
    private String account;
    private Integer sex;
    private String headImgAddress;
    private List<String> roleCodes;
    private long loginTime = System.currentTimeMillis();

    /**
     * 返回当前用户是不是超级管理员
     * @return
     */
    public boolean isAdmin() {
        List<String> roleList = roleCodes;
        if (null == roleList || roleList.size() == 0) {
            return false;
        }
        return roleList.contains("admin");
    }
}
