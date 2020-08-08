package site.lanmushan.framework.util.api;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import site.lanmushan.framework.configure.ApplicationUtil;
import site.lanmushan.framework.entity.ControllerApiInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author cayden
 * @date 2020/7/5 17:32
 */
@Slf4j
public class ApiScannerUtils {

    public static List<ControllerApiInfo> initApiList() {
        RequestMappingHandlerMapping mapping = ApplicationUtil.getApplication().getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        Set<Object> objects = new HashSet<>();
        for (RequestMappingInfo requestMappingInfo : map.keySet()){
            HandlerMethod handlerMethod = map.get(requestMappingInfo);
            if (handlerMethod.getBeanType().getName().startsWith("org.")){
                continue;
            }
            Object bean = ApplicationUtil.getApplication().getBean(handlerMethod.getBean().toString());
            objects.add(bean);
        }

        List<ControllerApiInfo> allApiInfo = new ArrayList<>();

        for (Object object : objects) {
            resolveApis(object,allApiInfo);
        }
        return allApiInfo;
    }

    private static void resolveApis(Object ob, List<ControllerApiInfo> apiInfos) {
        Class<?> clazz = ob.getClass();
        String apiPrefix = "";

        RequestMapping controllerMapping = clazz.getAnnotation(RequestMapping.class);
        if (controllerMapping == null){
            return;
        }
        String[] controllerMappingValues = controllerMapping.value();
        if (controllerMappingValues != null && controllerMappingValues.length > 1) {
            throw new RuntimeException("只能填写一个path");
        }
        if (controllerMapping != null) {
            apiPrefix = controllerMappingValues[0];
        }

        Boolean hasSlashEnd = apiPrefix.endsWith("/");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String apiStr = apiPrefix;
            String[] paths;
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                paths = requestMapping.value();
                if (paths == null || paths.length == 0){
                    continue;
                }
                String path = paths[0];
                if (path.startsWith("/") && hasSlashEnd) {
                    apiStr += path.substring(1);
                } else if (!path.startsWith("/") && !hasSlashEnd) {
                    apiStr += ("/" + path);
                } else {
                    apiStr += path;
                }
            } else {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping != null) {
                    paths = getMapping.value();
                    String path = paths[0];
                    if (path.startsWith("/") && hasSlashEnd) {
                        apiStr += path.substring(1);
                    } else if (!path.startsWith("/") && !hasSlashEnd) {
                        apiStr += ("/" + path);
                    } else {
                        apiStr += path;
                    }
                } else {
                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    if (postMapping != null) {
                        paths = postMapping.value();
                        String path = paths[0];
                        if (path.startsWith("/") && hasSlashEnd) {
                            apiStr += path.substring(1);
                        } else if (!path.startsWith("/") && !hasSlashEnd) {
                            apiStr += ("/" + path);
                        } else {
                            apiStr += path;
                        }
                    } else {
                        continue;
                    }
                }
            }
            ControllerApiInfo apiInfo = new ControllerApiInfo();
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            String apiRemark = (apiOperation == null || StringUtils.isBlank(apiOperation.value())) ? method.getName() : apiOperation.value();
            apiInfo.setApiName(apiRemark);
            apiInfo.setApiUrl(apiStr);
            apiInfos.add(apiInfo);
        }
    }
}
