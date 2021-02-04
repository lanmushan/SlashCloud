package site.lanmushan.cms.web.service;

import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.cms.web.controller.HomeController;
import site.lanmushan.cms.web.controller.StaticPageController;

import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Component
public class RequestMappingRegisterService  {
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 注册url
     *
     * @param url
     */
    public void registerRequestMapping(List<String> url) {


    }

    public void registerRequestMapping(String url) {
        if(!isRegistered(url))
        {
            RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
            Method targetMethod = ReflectionUtils.findMethod(StaticPageController.class, "handle", ModelMap.class);
            PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(url);
            RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(RequestMethod.GET,RequestMethod.OPTIONS);
            RequestMappingInfo mapping_info = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);
            requestMappingHandlerMapping.registerMapping(mapping_info, "staticPageController", targetMethod);
        }
    }
    public boolean isRegistered(String url) {
        return getAllUrl().contains(url);
    }
    public List<String> getAllUrl(){
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<String> list = new ArrayList<String>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
           // HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String uri : p.getPatterns()) {
                list.add(uri);
            }

        }
        return list;
    }
}
