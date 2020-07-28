package site.lanmushan.framework.shiro;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


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
        Session session = SecurityUtils.getSubject().getSession();
        System.out.println("权限认证");
        return null;
    }


}
