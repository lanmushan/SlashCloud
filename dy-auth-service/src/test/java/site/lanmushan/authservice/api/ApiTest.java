package site.lanmushan.authservice.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.authservice.AuthServiceApplication;
import site.lanmushan.framework.annotations.EnabledQuickSelect;

import java.beans.Beans;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ApiTest {
    @Autowired
    private WebApplicationContext applicationContext;


    @Test
    public void test1(){

        Map<String,String> map=loadAllURL();
        map.forEach((key,value)->{
            System.out.println(key);
        });
    }
    public Map<String,String> loadAllURL(){
        String contextPath= applicationContext.getServletContext().getContextPath();;
        Map<String,String> resultMap=new HashMap<>();
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            // 获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String uri : patterns) {
                String wholeUri=contextPath+uri;
                resultMap.put(wholeUri,"perms["+wholeUri+"]");
            }
        }
        Map<String,Object> beans= applicationContext.getBeansWithAnnotation(Service.class);
        beans.forEach((beanName,bean)->{
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                Method method=methods[i];
                EnabledQuickSelect enabledQuickSelect = method.getAnnotation(EnabledQuickSelect.class);
                if(enabledQuickSelect!=null)
                {
                    String url=contextPath+"/"+beanName.replace("Service","")+"/"+method.getName();
                    resultMap.put(url,"perms["+url+"]");
                }
            }
        });
        return resultMap;
    }
}
