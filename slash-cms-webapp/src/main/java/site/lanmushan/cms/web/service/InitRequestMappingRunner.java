package site.lanmushan.cms.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.cms.web.controller.HomeController;

import java.lang.reflect.Method;

@Slf4j
@Configuration
public class InitRequestMappingRunner implements CommandLineRunner {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RequestMappingRegisterService requestMappingRegisterService;
    @Override
    public void run(String... args) throws Exception {
        log.info("动态注册");
        requestMappingRegisterService.registerRequestMapping("/{test}/home");
//
//        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        Method targetMethod = ReflectionUtils.findMethod(HomeController.class, "index");
//
//        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition("/home/{classId}");
//        RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(RequestMethod.GET);
//
//        RequestMappingInfo mapping_info = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);
//        requestMappingHandlerMapping.registerMapping(mapping_info, "homeController", targetMethod);


    }
}
