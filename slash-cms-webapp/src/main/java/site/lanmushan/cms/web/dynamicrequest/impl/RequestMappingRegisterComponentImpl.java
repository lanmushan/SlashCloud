package site.lanmushan.cms.web.dynamicrequest.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.cms.web.controller.CmsController;
import site.lanmushan.cms.web.dynamicrequest.DynamicRequestMappingRegisterComponent;
import site.lanmushan.cms.web.dynamicrequest.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class RequestMappingRegisterComponentImpl implements DynamicRequestMappingRegisterComponent {
    @Autowired
    ApplicationContext applicationContext;
    List<HttpRequest> requestList = new ArrayList<>(0);

    public boolean isRegistered(String url) {
        return getAllUrl().contains(url);
    }

    public List<String> getAllUrl() {
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

    @Override
    public synchronized void registerHttpRequest(HttpRequest request) {
        if (!isRegistered(request.getRequestUrl())) {
            RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
            Method targetMethod = ReflectionUtils.findMethod(CmsController.class, "httpServletRequestHandle", ModelMap.class, HttpServletRequest.class);
            PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(request.getRequestUrl());
            RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(getRequestMethod(request.getRequestMethod()), RequestMethod.OPTIONS);
            RequestMappingInfo mapping_info = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);
            requestMappingHandlerMapping.registerMapping(mapping_info, "cmsController", targetMethod);
            this.requestList.add(request);
        }
    }

    @Override
    public synchronized void registerHttpRequestList(List<HttpRequest> requestList) {
        requestList.forEach(it -> {
            this.registerHttpRequest(it);
        });
    }

    private synchronized void removeHttpRequest(HttpRequest request) {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(request.getRequestUrl());
        RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(getRequestMethod(request.getRequestMethod()), RequestMethod.OPTIONS);
        RequestMappingInfo mapping_info = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);
        requestMappingHandlerMapping.unregisterMapping(mapping_info); // 注销
    }

    @Override
    public synchronized void removeAll() {
        this.requestList.clear();

        this.requestList.forEach(it -> {
            this.registerHttpRequest(it);
        });
    }

    public RequestMethod getRequestMethod(String method) {
        if ("GET".equals(method)) {
            return RequestMethod.GET;
        } else {
            return RequestMethod.POST;
        }
    }
}
