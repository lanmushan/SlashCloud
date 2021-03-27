package site.lanmushan.framework.swagger.util;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.framework.swagger.vo.ApiInfo;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cayden
 * @date 2020/7/5 17:32
 */
@Slf4j
public class ApiScannerUtils {

    public static List<ApiInfo> getAllURL(ApplicationContext applicationContext) {
        List<ApiInfo> resultList = new ArrayList<>();

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();
            ApiInfo apiInfo = new ApiInfo();
            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    if (api.tags() != null && api.tags().length > 0) {
                        apiInfo.setApiGroupName(api.tags()[0]);
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            ApiOperation apiOperation = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                apiInfo.setApiName(apiOperation.value());
            }
            for (String url : p.getPatterns()) {
                apiInfo.setApiUrl(url);
                break;
            }
            resultList.add(apiInfo);

        }
        return resultList;
    }
}
