package site.lanmushan.cms.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.ApplicationContext;
import site.lanmushan.cms.api.entity.CmsTbRequestMapping;
import site.lanmushan.cms.mapper.CmsTbRequestMappingMapper;
import site.lanmushan.cms.web.dynamicrequest.DynamicRequestMappingRegisterComponent;
import site.lanmushan.cms.web.dynamicrequest.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
//@Configuration
public class InitRequestMappingRunner implements CommandLineRunner {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DynamicRequestMappingRegisterComponent requestMappingRegisterService;
    @Autowired
    CmsTbRequestMappingMapper cmsTbRequestMappingMapper;

    @Override
    public void run(String... args) throws Exception {
        List<CmsTbRequestMapping> cmsTbRequestMappingList = cmsTbRequestMappingMapper.selectAll();
        List<HttpRequest> httpRequestList = new ArrayList<>(cmsTbRequestMappingList.size());
        cmsTbRequestMappingList.forEach(it -> {
            HttpRequest httpRequest = new HttpRequest();
            httpRequest.setRequestMethod(it.getRequestMethod());
            httpRequest.setRequestUrl(it.getRequestUrl());
            httpRequestList.add(httpRequest);
        });
        requestMappingRegisterService.registerHttpRequestList(httpRequestList);
        log.info("动态注册");
        //  requestMappingRegisterService.registerRequestMapping("/{test}/home");
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
