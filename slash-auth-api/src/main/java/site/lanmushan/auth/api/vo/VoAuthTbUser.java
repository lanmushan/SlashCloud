package site.lanmushan.auth.api.vo;

import lombok.Data;
import site.lanmushan.auth.api.entity.AuthTbRole;
import site.lanmushan.auth.api.entity.AuthTbUser;

import java.util.List;

/**
 * @Author dy
 * @Date 2020/7/4 11:19
 * @Version 1.0
 */
@Data
public class VoAuthTbUser extends AuthTbUser {
    List<AuthTbRole> authTbRoleList;
}
