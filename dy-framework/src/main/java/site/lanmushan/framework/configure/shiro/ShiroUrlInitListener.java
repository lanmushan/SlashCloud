package site.lanmushan.framework.configure.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.framework.annotations.EnabledQuickSelect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class ShiroUrlInitListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //判断是否执行过，执行过则不再执行  ???有点问题加True
        if(true||event.getApplicationContext().getParent() == null) {
            Map<String,String> urlMap= loadAllURL(event.getApplicationContext());
            urlMap.forEach((key,value)->{
                System.out.println(key+"|"+value);
            });
            Map<String,String> map=new LinkedHashMap<>();
            BeanUtils.copyProperties(shiroFilterFactoryBean.getFilterChainDefinitionMap(),map);
            shiroFilterFactoryBean.getFilterChainDefinitionMap().putAll(urlMap);
            shiroFilterFactoryBean.getFilterChainDefinitionMap().putAll(map);
        }
    }
    public Map<String,String> loadAllURL(ApplicationContext applicationContext){

        Map<String,String> resultMap=new HashMap<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            // 获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String uri : patterns) {
                resultMap.put(uri,"perms["+uri+"]");
            }
        }
        Map<String,Object> beans= applicationContext.getBeansWithAnnotation(Service.class);
        beans.forEach((beanName, bean)->{
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method=methods[i];
                EnabledQuickSelect enabledQuickSelect = method.getAnnotation(EnabledQuickSelect.class);
                if(enabledQuickSelect!=null)
                {
                    String url="/"+beanName.replace("Service","")+"/"+method.getName();
                    resultMap.put(url,"perms["+url+"]");
                }
            }
        });
        return resultMap;
    }
}
