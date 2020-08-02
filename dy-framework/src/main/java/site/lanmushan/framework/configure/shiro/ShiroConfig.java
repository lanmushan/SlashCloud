package site.lanmushan.framework.configure.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.framework.annotations.EnabledQuickSelect;

import javax.servlet.Filter;

import java.lang.reflect.Method;
import java.util.*;

/**
 * ShiroConfig配置
 */
@Configuration
public class ShiroConfig {
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private  WebApplicationContext applicationContext;
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    RedisSessionDao shiroRedisSessionDao;
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, Filter> filterMap=new LinkedHashMap<>();
        filterMap.put("perms",new UrlFilter());
        filterMap.put("authc",new ShiroLoginFilter());
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        filterChainDefinitionMap.put("/authLogin/selectVerificationCode","anon");
        filterChainDefinitionMap.put("/authLogin/loginManage","anon");
        filterChainDefinitionMap.put("/unLogin","anon");
        filterChainDefinitionMap.put("/unauthorized","anon");
        filterChainDefinitionMap.put("/**","authc");
//        filterChainDefinitionMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/unLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        log.info("Shiro权限认证系统启动成功.....");
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroRealm myShiroRealm(){
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }


    @Bean
    public SecurityManager securityManager(SessionDAO sessionDAO){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(sessionManager(sessionDAO));
        return securityManager;
    }

    /**
     * 配置Session管理器
     * @return
     */
    @Bean
    public SessionManager sessionManager( SessionDAO sessionDAO){
        TokenSessionManager tokenSessionManager = new TokenSessionManager();
        tokenSessionManager.setSessionIdUrlRewritingEnabled(false);
        //修改为自定义的Sesion
        tokenSessionManager.setSessionIdCookie(simpleCookie());
        tokenSessionManager.setSessionDAO(sessionDAO);
        return tokenSessionManager;
    }
    @Bean
    public SessionDAO sessionDAO() {
        return   shiroRedisSessionDao;
    }
    //这里需要设置一个cookie的名称  原因就是会跟原来的session的id值重复的
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("REDISSESSION");
        return simpleCookie;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","/user/403");
        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("exception");     // Default is "exception"
        //r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }

}