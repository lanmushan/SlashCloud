package site.lanmushan.framework.configure.shiro;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import site.lanmushan.framework.entity.CurrentUser;
import site.lanmushan.framework.util.CurrentUserUtil;

import java.util.HashSet;
import java.util.Set;


/**
 * 登录认证和权限认证
 * @author Administrator
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 登录认证
     * @param authenticationToken
     * @returnHashedCredentialsMatcher
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        CustomUsernamePasswordToken token=(CustomUsernamePasswordToken) authenticationToken;
        log.info("用户{}登录认证",token.getUsername());
        if(null!=token.getUsername()){
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(token.getUsername(), token.getDpassword(), getName());
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(token.getSalt()));
            return  authenticationInfo;
        }
        return null;
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        CurrentUser currentUser= CurrentUserUtil.getCurrentUser();
        log.info("用户{}权限认证",currentUser.getNickName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleSet=new HashSet<>();
        if(null!=currentUser.getRoleCodes())
        {
            roleSet.addAll(currentUser.getRoleCodes());
        }
        //添加当前用户所拥有的角色
        info.setRoles(roleSet);
        Set<String> perms=new HashSet<>();
        perms.add("/api/authTbResource/select/menu");
        perms.add("/api/authTbResource/select/test");
        info.setStringPermissions(perms);
        System.out.println("权限认证");
        return info;
    }


}
